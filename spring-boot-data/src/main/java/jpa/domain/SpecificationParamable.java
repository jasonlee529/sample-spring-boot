package jpa.domain;

import java.util.Map;

/**
 * 
 * @author huang
 * jpa动态查询参数接口
 */
public interface SpecificationParamable {

	public String getDynamic();
	
	public Map<String, Object> getFilter();
}
