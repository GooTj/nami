package com.onepiece.nami.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.apache.commons.lang.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.PRE_DECORATION_FILTER_ORDER;
import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.PRE_TYPE;

/**
 * 权限filter（区分司机&货主）
 *
 * 某些权限是只能司机访问的、某些权限是只能货主访问的，用什么加以区分呢？
 * 后期要谋划，一个手机号只能有一个角色，为以手机号为主键打好基础
 *
 * @author Goo Tsung-jen
 * @create 2019-06-24  17:17
 * Innovation distinguishes between a leader and a follower.
 */
@Component
public class AuthFilter extends ZuulFilter {

    @Override
    public String filterType() {
        /**前置.*/
        return PRE_TYPE;
    }

    @Override
    public int filterOrder() {
        /**顺序.*/
        return PRE_DECORATION_FILTER_ORDER - 1;
    }

    @Override
    public boolean shouldFilter() {
        /**启用.*/
        return true;
    }

    /**
     * 验证，如通过则继续，如不通过，则返回401
     * @return
     */
    @Override
    public Object run()  {
        /**逻辑.*/
        //获取上下文
        RequestContext requestContext = RequestContext.getCurrentContext();
        HttpServletRequest request = requestContext.getRequest();

        //if (request.getRequestURI())

        return null;
    }
}
