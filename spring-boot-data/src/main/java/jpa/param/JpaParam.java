package jpa.param;

import java.util.Map;

/**
 * 
 * @author huang
 * 提供最外层使用类,提供多元化的构造方法
 *
 */
public class JpaParam extends AbstractJpaParam{

	public JpaParam() {
		super();
		this.init();
	}
	
	public JpaParam(Class<?> clazz) {
		super();
		this.init();
		this.addClass(clazz);
	}
	
	public JpaParam(Map<String, Object> filter) {
		super();
		this.init();
		this.addFilter(filter);
	}
}
