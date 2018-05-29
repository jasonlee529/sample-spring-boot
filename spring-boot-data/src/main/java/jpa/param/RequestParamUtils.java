package jpa.param;

import org.springframework.data.domain.Sort.Direction;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Enumeration;
import java.util.Map;
import java.util.TreeMap;

/**
 * @author huang
 * jpa查询所需参数封装的一个类
 * 直接与http请求做对接返回一个map
 */
public class RequestParamUtils {

    /**
     * http请求参数的前缀是filter_格式
     */
    private static final String HTTP_PARAMETERS_STARTING_WITH = "filter_";
    /**
     * 组合查询所需参数是用and或者or组合
     */
    private static final String DYNAMIC_BUILDER = "dynamic";
    /**
     * 页码
     */
    private static final String PAGE_NO = "pageNo";
    /**
     * 页面呈现数据大小
     */
    private static final String PAGE_SIZE = "pageSize";
    /**
     * 分页排序方向
     */
    private static final String SORT_DIRECTION = "sortDirection";
    /**
     * 排序字段
     */
    private static final String SORT_FILED = "sortFiled";

    /**
     * 前提条件是http请求参数的前缀是filter_格式
     */
    public static Parameters getParams(HttpServletRequest request) {
        Map<String, Object> filter = WebUtils.getParametersStartingWith(request, HTTP_PARAMETERS_STARTING_WITH);
        URLDecoder(filter);
        filter.putAll(getAttributesStartingWith(request, HTTP_PARAMETERS_STARTING_WITH));
        return assemParam(filter);
    }


    private static void URLDecoder(Map<String, Object> filter) {
        for (Map.Entry<String, Object> entry : filter.entrySet()) {
            String decoder = "";
            try {
                decoder = URLDecoder.decode(entry.getValue().toString(), "utf-8");
                entry.setValue(decoder);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * @param filter
     * @return
     */
    private static Parameters assemParam(Map<String, Object> filter) {
        checkFilter(filter);
        Parameters jpaParam = new JpaParam(filter);
        removePageParam(jpaParam);
        return jpaParam;
    }

    private static void checkFilter(Map<String, Object> filter) {
        if (filter.get(PAGE_NO) == null) {
            new IllegalArgumentException("没有获取到PAGE_NO.....");
        } else if (filter.get(PAGE_SIZE) == null) {
            new IllegalArgumentException("没有获取到PAGE_SIZE.....");
        }
    }

    /**
     * 区分分页参数和其他业务查询参数不耦合在一起
     */
    private static void removePageParam(Parameters jpaParam) {
        Map<String, Object> filter = jpaParam.getFilter();
        if (filter != null) {
            if (filter.get(PAGE_NO) != null) {
                jpaParam.getPageParam().setPageNo(filter.get(PAGE_NO).toString());
                filter.remove(PAGE_NO);
            }
            if (filter.get(PAGE_SIZE) != null) {
                jpaParam.getPageParam().setPageSize(filter.get(PAGE_SIZE).toString());
                filter.remove(PAGE_SIZE);
            }
            if (filter.get(DYNAMIC_BUILDER) != null) {
                jpaParam.getSpecificationParam().setDynamic(filter.get(DYNAMIC_BUILDER).toString());
                filter.remove(DYNAMIC_BUILDER);
            }
            if (filter.get(SORT_DIRECTION) != null) {
                jpaParam.getPageParam().setDirection(Direction.fromString(filter.get(SORT_DIRECTION).toString()));
                filter.remove(SORT_DIRECTION);
            }
            if (filter.get(SORT_FILED) != null) {
                jpaParam.getPageParam().setSortFiled(filter.get(SORT_FILED).toString());
                filter.remove(SORT_FILED);
            }
        }
    }

    /**
     * 取得带相同前缀的Request Attributes,来自业务层自定义的set()方法注入
     *
     * @param request
     * @param prefix
     * @return
     */
    private static Map<String, Object> getAttributesStartingWith(HttpServletRequest request, String prefix) {
        Enumeration<?> attributeNames = request.getAttributeNames();
        Map<String, Object> params = new TreeMap<String, Object>();

        while ((attributeNames != null) && attributeNames.hasMoreElements()) {
            String paramName = (String) attributeNames.nextElement();
            if ("".equals(prefix) || paramName.startsWith(prefix)) {
                String unprefixed = paramName.substring(prefix.length());
                Object obj = request.getAttribute(paramName);
                if (obj == null) {
                } else {
                    params.put(unprefixed, obj);
                }
            }
        }
        return params;
    }
}
