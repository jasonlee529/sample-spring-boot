package jpa.param.assemble;

import com.google.common.collect.Maps;
import org.apache.commons.lang3.StringUtils;

import java.util.Map;
import java.util.Map.Entry;

/**
 * 
 * @author huang
 *
 */
public class SearchFilter {

	public enum Operator {
		/**
		 * EQ equal , NEQ notEqual, LIKE, GT greaterThan ,
		 * LT lessThan, GTE greaterThanOrEqualTo, LTE lessThanOrEqualTo, ISNULL, ISNOTNULL
		 */
		EQ, NEQ, LIKE, GT, LT, GTE, LTE, ISNULL, ISNOTNULL
	}

	public String fieldName;
	public Object value;
	public Operator operator;

	public static final String SEPARATOR = "_";
	public SearchFilter(String fieldName, Operator operator, Object value) {
		this.fieldName = fieldName;
		this.value = value;
		this.operator = operator;
	}

	/**
	 * searchParams中key的格式为OPERATOR_FIELDNAME
	 */
	public static Map<String, SearchFilter> parse(Map<String, Object> searchParams) {
		Map<String, SearchFilter> filters = Maps.newHashMap();
		for (Entry<String, Object> entry : searchParams.entrySet()) {
			// 过滤掉空值
			String key = entry.getKey();
			Object value = entry.getValue();
			// 拆分operator与filedAttribute
			String[] names = StringUtils.split(key, SEPARATOR);
			if (names.length != 2) {
				throw new IllegalArgumentException(key + " is not a valid search filter name");
			}
			Operator operator = Operator.valueOf(names[0]);
			if(isNeedValue(operator)){
				if(value instanceof String){
					if (StringUtils.isBlank((String) value)) {
						continue;
					}
				}
			}
			// 创建searchFilter
			SearchFilter filter = new SearchFilter(names[1], operator, value);
			filters.put(key, filter);
		}
		return filters;
	}
	
	private static boolean isNeedValue(Operator operator){
		return operator == Operator.ISNULL || operator == Operator.ISNOTNULL ? false : true;
	}
}
