package jpa.param;

import cn.infisa.data.jpa.param.assemble.SearchFilter;
import org.springframework.data.domain.Sort;

import javax.persistence.criteria.Predicate;
import java.util.Map;

/**
 * @author huang
 */
public class AbstractJpaParam implements Parameters {

    private PageParam pageParam;

    private SpecificationParam specificationParam;


    protected void init() {
        this.pageParam = new PageParam();
        this.specificationParam = new SpecificationParam();
    }

    @Override
    public Parameters addParam(SearchFilter.Operator operator, String field, Object param) {
        this.specificationParam.getFilter().put(operator.name() + SearchFilter.SEPARATOR + field, param);
        return this;
    }

    @Override
    public Parameters addParam(SearchFilter.Operator operator, String field) {
        this.specificationParam.getFilter().put(operator.name() + SearchFilter.SEPARATOR + field, null);
        return this;
    }

    @Override
    public Parameters addClass(Class<?> clazz) {
        this.specificationParam.setClazz(clazz);
        return this;
    }

    @Override
    public Parameters addDynamic(Predicate.BooleanOperator booleanOperator) {
        this.specificationParam.setDynamic(booleanOperator.name());
        return this;
    }

    @Override
    public Parameters addDirectionField(Sort.Direction direction, String sortFiled) {
        this.pageParam.setDirection(direction);
        this.pageParam.setSortFiled(sortFiled);
        return this;
    }

    @Override
    public Parameters addFilter(Map<String, Object> filter) {
        this.getSpecificationParam().setFilter(filter);
        return this;
    }

    @Override
    public int getPageNo() {
        return Integer.valueOf(this.pageParam.getPageNo());
    }

    @Override
    public int getPageSize() {
        return Integer.valueOf(this.pageParam.getPageSize());
    }

    @Override
    public Sort.Direction getDirection() {
        return this.pageParam.getDirection();
    }

    @Override
    public String getSortFiled() {
        return this.pageParam.getSortFiled();
    }

    @Override
    public String getDynamic() {
        return this.specificationParam.getDynamic();
    }

    @Override
    public Map<String, Object> getFilter() {
        return this.specificationParam.getFilter();
    }

    @Override
    public Class<?> getDominClazz() {
        return this.specificationParam.getClazz();
    }

    @Override
    public PageParam getPageParam() {
        return pageParam;
    }

    public void setPageParam(PageParam pageParam) {
        this.pageParam = pageParam;
    }

    @Override
    public SpecificationParam getSpecificationParam() {
        return specificationParam;
    }

    public void setSpecificationParam(SpecificationParam specificationParam) {
        this.specificationParam = specificationParam;
    }

}
