package cn.infisa.data.jpa.domain;

import org.springframework.data.domain.Sort.Direction;

/**
 * 
 * @author huang
 * jpa分页所需参数接口
 */
public interface PageParamable {

	public int getPageNo();
	
	public int getPageSize();
	
	public Direction getDirection();
	
	public String getSortFiled();
}
