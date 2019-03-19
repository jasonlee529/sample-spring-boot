package cn.infisa.data.jpa.param;

import org.springframework.data.domain.Sort.Direction;

/**
 * 
 * @author huang
 *
 */
public class PageParam {

	/**
	 * 请求页面
	 */
	private String pageNo;
	/**
	 * 页面数据大小
	 */
	private String pageSize;
	/**
	 * 排序，升序或者降序
	 */
	private Direction direction;
	/**
	 * 排序字段，可能会有多个排序字段。目前不使用多个，这么多项目确实排序字段用到的少。
	 * 为避免过度设计，用到了多个再改
	 */
	private String sortFiled;
	
	
	public PageParam() {
		super();
		this.sortFiled = "id";
		this.direction = Direction.fromString("DESC");
	}
	public String getPageNo() {
		return pageNo;
	}
	public void setPageNo(String pageNo) {
		this.pageNo = pageNo;
	}
	public String getPageSize() {
		return pageSize;
	}
	public void setPageSize(String pageSize) {
		this.pageSize = pageSize;
	}
	public Direction getDirection() {
		return direction;
	}
	public void setDirection(Direction direction) {
		this.direction = direction;
	}
	public String getSortFiled() {
		return sortFiled;
	}
	public void setSortFiled(String sortFiled) {
		this.sortFiled = sortFiled;
	}
	
}
