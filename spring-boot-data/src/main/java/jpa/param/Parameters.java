package jpa.param;

import cn.infisa.data.jpa.domain.JpaParamable;
import cn.infisa.data.jpa.param.assemble.SearchFilter;
import org.springframework.data.domain.Sort.Direction;

import javax.persistence.criteria.Predicate.BooleanOperator;
import java.util.Map;

/**
 * @author huang
 */
public interface Parameters extends JpaParamable {

    /**
     * 添加查询参数
     *
     * @param operator
     * @param param
     * @return
     */
    Parameters addParam(SearchFilter.Operator operator, String field, Object param);

    /**
     * 服务于添加字段 ISNULL ISNOTNULL
     * ＠see cn.infisa.basic.data.jpa.param.assemble.SearchFilter.Operator.ISNULL
     * ＠see cn.infisa.basic.data.jpa.param.assemble.SearchFilter.Operator.ISNOTNULL
     *
     * @param operator
     * @param field
     * @return
     */
    Parameters addParam(SearchFilter.Operator operator, String field);

    /**
     * 添加pojo参数class
     *
     * @param calzz
     * @return
     */
    Parameters addClass(Class<?> clazz);

    /**
     * 参数的连接方式 and or
     *
     * @param booleanOperator
     * @return
     */
    Parameters addDynamic(BooleanOperator booleanOperator);

    /**
     * 添加排序字段
     *
     * @param direction
     * @param sortFiled
     * @return
     */
    Parameters addDirectionField(Direction direction, String sortFiled);

    /**
     * 提供一次性放入参数,但filter必须满足查询格式
     * 一般只给http协议返回map使用
     *
     * @param filter
     * @see org.springside.modules.web.Servlets.getParametersStartingWith(ServletRequest request, String prefix)
     */
    Parameters addFilter(Map<String, Object> filter);

}
