package jpa.param.assemble;

import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;
import javax.persistence.criteria.Predicate.BooleanOperator;
import java.util.Collection;
import java.util.List;

/**
 * 
 * @author huang
 * 自定义组合查询方法
 * 增加查询条件or方法
 */
public class DynamicSpecifications {
	
	public static <T> Specification<T> bySearchFilterBuilder(final Collection<SearchFilter> filters, final Class<T> clazz, final String criteriaBuilderType) {
		return new Specification<T>() {
			@SuppressWarnings("rawtypes")
			@Override
			public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
				if ((filters != null) && !(filters.isEmpty())) {
					List<Predicate> predicates = Lists.newArrayList();
					for (SearchFilter filter : filters) {
						// nested path translate, 如Task的名为"user.name"的filedName, 转换为Task.user.name属性
						String[] names = StringUtils.split(filter.fieldName, ".");
						Path expression = root.get(names[0]);
						for (int i = 1; i < names.length; i++) {
							expression = expression.get(names[i]);
						}
						addPredicate(filter, expression, builder, predicates);
					}
					return (Predicate) getCriteriaBuilder(builder, predicates, criteriaBuilderType);
				}
				return builder.conjunction();
			}
		};
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private static void addPredicate(SearchFilter filter,Path expression,CriteriaBuilder builder, List<Predicate> predicates){
		switch (filter.operator) {
		case EQ:
			predicates.add(builder.equal(expression, filter.value));
			break;
		case NEQ:
			predicates.add(builder.notEqual(expression, filter.value));
			break;
		case LIKE:
			predicates.add(builder.like(expression, "%" + filter.value + "%"));
			break;
		case GT:
			predicates.add(builder.greaterThan(expression, (Comparable) filter.value));
			break;
		case LT:
			predicates.add(builder.lessThan(expression, (Comparable) filter.value));
			break;
		case GTE:
			predicates.add(builder.greaterThanOrEqualTo(expression, (Comparable) filter.value));
			break;
		case LTE:
			predicates.add(builder.lessThanOrEqualTo(expression, (Comparable) filter.value));
			break;
		case ISNULL:
			predicates.add(builder.isNull(expression));
			break;
		case ISNOTNULL:
			predicates.add(builder.isNotNull(expression));
			break;
		}
	}
	/**
	 * 默认将所有条件用 and 联合起来
	 * 根据criteriaBuilderType来区分查询条件联合方式
	 * @return 
	 * 
	 */
	private static Predicate getCriteriaBuilder(CriteriaBuilder builder, List<Predicate> predicates, String criteriaBuilderType){
		if (predicates.size() > 0) {
			if(StringUtils.isBlank(criteriaBuilderType)){
				return builder.and(predicates.toArray(new Predicate[predicates.size()]));
			}else if(StringUtils.equals(BooleanOperator.AND.name(), criteriaBuilderType)){
				return builder.and(predicates.toArray(new Predicate[predicates.size()]));
			}else if(StringUtils.equals(BooleanOperator.OR.name(), criteriaBuilderType)){
				return builder.or(predicates.toArray(new Predicate[predicates.size()]));
			}
			
		}
		return null;
	}
}
