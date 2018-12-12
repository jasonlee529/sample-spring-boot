package jpa.respository.support;

import cn.infisa.data.jpa.core.Identifier;
import cn.infisa.data.jpa.domain.JpaParamable;
import cn.infisa.data.jpa.param.assemble.DynamicSpecifications;
import cn.infisa.data.jpa.param.assemble.SearchFilter;
import cn.infisa.data.jpa.respository.JpaRespositoryContext;
import cn.infisa.data.jpa.respository.JpaService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * @author huang
 * <p>
 * 默认采用该类实现crud以及分页
 * 自定义对外服务Jpa接口规范
 * 对于使用JpaService服务类的pojo必须继承 @see cn.infisa.basic.data.IdEntity
 * 也支持个性化自定义pojo,自定义pojo必须实现 @see cn.infisa.basic.data.Identifier
 */
@Repository
@Transactional(readOnly = true)
public class SimpleJpaService extends JpaRespositoryContext implements JpaService {

    /**
     * Saves all given entities
     * 批量保存
     */
    @Override
    @Transactional
    public <S extends Identifier> List<S> save(Iterable<S> entities) {
        if (entities == null) {
            new IllegalArgumentException("批量保存集合为null");
        }
        Iterator<S> its = entities.iterator();
        Object obj = null;
        while (its.hasNext()) {
            obj = its.next();
            break;
        }
        return (List<S>) super.getJpaRepository(obj).saveAll(entities);
    }

    /**
     * Saves a given entity
     * 单个保存
     */
    @Override
    @Transactional
    public <S extends Identifier> S save(S entity) {
        return super.getJpaRepository(entity).save(entity);
    }

    /**
     * Retrieves an entity by its id
     * 获取单个
     */
    @Override
    @SuppressWarnings("unchecked")
    public <S extends Identifier> Optional findOne(Class<?> clazz, Long id) {
        return (Optional) super.getJpaRepository(clazz).findById(id);
    }

    /**
     * 删除
     */
    @Override
    @Transactional
    public void delete(Class<?> clazz, Long id) {
        super.getJpaRepository(clazz).deleteById(id);
    }

    @Override
    public <S extends Identifier> void delete(Iterable<S> entities) {
        if (entities == null) {
            new IllegalArgumentException("批量保存集合为null");
        }
        Iterator<S> its = entities.iterator();
        Object obj = null;
        while (its.hasNext()) {
            obj = its.next();
            break;
        }
        super.getJpaRepository(obj).deleteAll(entities);
    }

    /**
     * Returns a single entity matching the given {@link Specification}
     *
     * @return
     */
    @Override
    @SuppressWarnings("unchecked")
    public <S extends Identifier> Optional findOne(JpaParamable jpaParam) {
        Specification<Identifier> spec = buildSpecification(jpaParam);
        return (Optional) super.getJpaSpecificationExecutor(jpaParam.getDominClazz()).findOne(spec);
    }

    /**
     * Returns all entities matching the given {@link Specification}
     *
     * @return
     */
    @Override
    @SuppressWarnings("unchecked")
    public <S extends Identifier> List<S> findAll(JpaParamable jpaParam) {
        Specification<Identifier> spec = buildSpecification(jpaParam);
        Sort sort = new Sort(jpaParam.getDirection(), jpaParam.getSortFiled());
        return (List<S>) super.getJpaSpecificationExecutor(jpaParam.getDominClazz()).findAll(spec, sort);
    }

    /**
     * Returns the number of instances that the given {@link Specification} will return
     *
     * @return
     */
    @Override
    public long count(JpaParamable jpaParam) {
        Specification<Identifier> spec = buildSpecification(jpaParam);
        return super.getJpaSpecificationExecutor(jpaParam.getDominClazz()).count(spec);
    }

    /**
     * Retrieves entity Page
     * 获取分页
     */
    @Override
    public Page<Identifier> getPage(JpaParamable jpaParam) {
        PageRequest pageRequest = buildPageRequest(jpaParam);
        Specification<Identifier> spec = buildSpecification(jpaParam);
        return super.getJpaSpecificationExecutor(jpaParam.getDominClazz()).findAll(spec, pageRequest);
    }

    /**
     * 创建分页请求.
     */
    private PageRequest buildPageRequest(JpaParamable jpaParam) {
        Sort sort = new Sort(jpaParam.getDirection(), jpaParam.getSortFiled());
        return new PageRequest(this.getPageNo(jpaParam.getPageNo()) - 1, this.getPageSize(jpaParam.getPageSize()), sort);
    }

    /**
     * 获取页数
     */
    private int getPageNo(int pageNo) {
        return pageNo == 0 ? 1 : pageNo;
    }

    /**
     * 获取页显示数
     */
    private int getPageSize(int pageSize) {
        return pageSize == 0 ? 10 : pageSize;
    }


    @SuppressWarnings("unchecked")
    private <T> Specification<T> buildSpecification(JpaParamable jpaParam) {
        Map<String, SearchFilter> filters = SearchFilter.parse(jpaParam.getFilter());
        Specification<T> spec = (Specification<T>) DynamicSpecifications.bySearchFilterBuilder(filters.values(), jpaParam.getDominClazz(), jpaParam.getDynamic());
        return spec;
    }

}
