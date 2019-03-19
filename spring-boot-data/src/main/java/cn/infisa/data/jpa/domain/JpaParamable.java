package cn.infisa.data.jpa.domain;


import cn.infisa.data.jpa.param.PageParam;
import cn.infisa.data.jpa.param.SpecificationParam;

/**
 * 
 * @author huang
 * jpa获取查询参数接口
 * 所有参数统一进入jpa入口
 */
public interface JpaParamable extends PageParamable, SpecificationParamable {

	public Class<?> getDominClazz();
	
	public PageParam getPageParam();
	
	public SpecificationParam getSpecificationParam();
}
