package jpa.respository;

import cn.infisa.data.jpa.core.Identifier;
import cn.infisa.data.jpa.domain.JpaParamable;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;
import java.util.Optional;

/**
 * 
 * @author huang
 * 对外服务接口,外部程序调用此接口
 */
@NoRepositoryBean
public interface JpaService {

	/**
	 * Saves all given entities
	 * 批量保存
	 */
	public <S extends Identifier> List<S> save(Iterable<S> entities);
	
	/**
	 * Saves a given entity
	 * 单个保存
	 */
	public <S extends Identifier> S save(S entity);
		
	/**
	 * Retrieves an entity by its id
	 * 获取单个
	 */
	public <S extends Identifier> Optional findOne(Class<?> clazz, Long id);
	

	/**
	 * delete an entity by its id
	 * 删除单个
	 */
	public void delete(Class<?> clazz, Long id);
	
	/**
	 * 条件删除
	 * @param entities
	 */
	public <S extends Identifier> void delete(Iterable<S> entities);
	/**
	 * Retrieves entity Page
	 * 获取分页
	 */
	public <S extends Identifier > Page<S> getPage(JpaParamable jpaParam);
	
	/**
	 * Returns a single entity matching the given {@link Specification}
	 * @return
	 */
	public <S extends Identifier> Optional findOne(JpaParamable jpaParam);
	
	/**
	 * Returns all entities matching the given {@link Specification}
	 * @return
	 */
	public <S extends Identifier> List<S> findAll(JpaParamable jpaParam);
	
	/**
	 * Returns the number of instances that the given {@link Specification} will return
	 * @return
	 */
	public long count(JpaParamable jpaParam);
}
