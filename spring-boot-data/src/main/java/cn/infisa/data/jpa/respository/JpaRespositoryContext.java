package cn.infisa.data.jpa.respository;

import cn.infisa.data.jpa.core.Identifier;
import com.google.common.collect.Maps;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactoryUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Map;

/**
 * 
 * @author huang
 * 默认采用该类实现crud以及分页
 * 新提出的api，可替换原有的每一个pojo对应一个service的api
 */
public class JpaRespositoryContext implements ApplicationContextAware {
	
	private ApplicationContext applicationContext = null;
	/**
	 * 存贮所有继承了JpaRepository的接口的泛型Type和对应的接口class
	 */
	private final Map<Type,Class<?>> jpabeansMap = Maps.newHashMap();
	
	/**
	 * 获取JpaRepository
	 */
	@SuppressWarnings("unchecked")
	protected JpaRepository<Identifier, Long> getJpaRepository(Object obj){
		Class<?> targetClass = this.getTargetClass(obj.getClass());
		return (JpaRepository<Identifier, Long>) this.getApplicationContext().getBean(targetClass);
	}
	/**
	 * 获取crudRepository以操作find
	 * 查找到具体的实体类映射需要具体的类，
	 * 因此在做查询操作则需要具体的pojo类映射数据库的结果集
	 */
	@SuppressWarnings("unchecked")
	protected JpaRepository<Identifier, Long> getJpaRepository(final Class<?> domainClazz){
		Class<?> targetClass = this.getTargetClass(domainClazz);
		return (JpaRepository<Identifier, Long>) this.getApplicationContext().getBean(targetClass);
	}
	
	/**
	 * 获取JpaSpecificationExecutor
	 * @param domainClazz
	 * @return
	 */
	@SuppressWarnings("unchecked")
	protected JpaSpecificationExecutor<Identifier> getJpaSpecificationExecutor(final Class<?> domainClazz){
		Class<?> targetClass = this.getTargetClass(domainClazz);
		return (JpaSpecificationExecutor<Identifier>) this.getApplicationContext().getBean(targetClass);
	}
	
	@Override
	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		this.applicationContext = applicationContext;
		this.beanMappingRepository();
	}

	protected ApplicationContext getApplicationContext() {
		return applicationContext;
	}
	
	private Class<?> getTargetClass(final Class<?> domainClazz){
		return jpabeansMap.get(domainClazz);
	}
	
	/**
	 * 获取pojo与Repository的映射关系
	 * @see org.springframework.data.jpa.repository的泛型下标为0的为外层注入的类型
	 */
	private void beanMappingRepository(){
		String[] jpabeans = BeanFactoryUtils.beanNamesForTypeIncludingAncestors(this.applicationContext, JpaRepository.class);
		for(String bean : jpabeans){
			Class<?> targetClass = this.applicationContext.getType(bean);
			Type[] types = targetClass.getGenericInterfaces();
			for(Type type :types){
				Type domainClazz = ((ParameterizedType) type).getActualTypeArguments()[0];
				jpabeansMap.put(domainClazz, targetClass);
			}
		}
	}
}
