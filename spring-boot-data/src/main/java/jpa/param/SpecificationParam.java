package jpa.param;

import com.google.common.collect.Maps;

import java.util.Map;

/**
 * @author huang
 */
public class SpecificationParam {

    /**
     * 组合查询所需参数
     * 查询filter
     */
    private Map<String, Object> filter;
    /**
     * 组合查询所需参数是用and或者or组合
     */
    private String dynamic;
    /**
     * DynamicSpecificationsx需要的参数,指pojo实体类
     */
    private Class<?> clazz;

    /**
     * DynamicSpecificationsx需要目标respository
     */
    private Class<?> targetClazz;

    public SpecificationParam() {
        super();
        this.filter = Maps.newHashMap();
    }

    public Map<String, Object> getFilter() {
        return filter;
    }

    public void setFilter(Map<String, Object> filter) {
        this.filter = filter;
    }

    public String getDynamic() {
        return dynamic;
    }

    public void setDynamic(String dynamic) {
        this.dynamic = dynamic;
    }

    public Class<?> getClazz() {
        return clazz;
    }

    public void setClazz(Class<?> clazz) {
        this.clazz = clazz;
    }

    public Class<?> getTargetClazz() {
        return targetClazz;
    }

    public void setTargetClazz(Class<?> targetClazz) {
        this.targetClazz = targetClazz;
    }

}
