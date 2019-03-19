package cn.infisa.data.jpa.service;

import cn.infisa.data.jpa.core.IdEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;

@Transactional(readOnly = false)
public abstract class CrudService<D extends CrudRepository, E extends IdEntity, ID extends Serializable> {
    /**
     * 持久层对象
     */
    @Autowired
    protected D dao;

    /**
     * 获取单条数据
     *
     * @param id
     * @return
     */
    public E findOne(ID id) {
        return (E) dao.findById(id).get();
    }

    /**
     * 查询多条数据
     *
     * @return
     */
    public List<E> findList() {
        return (List<E>) dao.findAll();
    }

    /**
     * 保存一个实例
     *
     * @return
     */
    public E save(E entity) {
        return (E) dao.save(entity);
    }

    /**
     * 保存多个实例
     *
     * @return
     */
    public List<E> save(List<E> entities) {
        return (List<E>) dao.save(entities);
    }

    /**
     * 删除数据通过id
     *
     * @param id
     */
    public void delete(ID id) {
        dao.delete(id);
    }

    /**
     * 删除数据通过实例
     *
     * @param entity
     */
    public void delete(E entity) {
        dao.delete(entity);
    }
}
