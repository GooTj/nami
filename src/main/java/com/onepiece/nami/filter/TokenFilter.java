package com.onepiece.nami.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.apache.commons.lang.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.PRE_DECORATION_FILTER_ORDER;
import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.PRE_TYPE;

/**
 * 验证token
 * @author Goo Tsung-jen
 * @create 2019-06-24  17:17
 * Innovation distinguishes between a leader and a follower.
 */
@Component
public class TokenFilter extends ZuulFilter {

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
        return false;
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
        String token=request.getParameter("token");
        if (StringUtils.isEmpty(token)) {
            //不通过
            requestContext.setSendZuulResponse(false);
            //返回401
            requestContext.setResponseStatusCode(HttpStatus.UNAUTHORIZED.value());
        }
        return null;
    }
}
