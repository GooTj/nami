package com.onepiece.nami.filter;

import com.google.common.util.concurrent.RateLimiter;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.exception.ZuulException;
import com.onepiece.nami.exception.RateLimiterException;
import org.springframework.stereotype.Component;

import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.PRE_TYPE;
import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.SERVLET_DETECTION_FILTER_ORDER;

/**
 * 限流拦截器
 *
 * @author Goo Tsung-jen
 * @create 2019-06-24  18:35
 * Innovation distinguishes between a leader and a follower.
 */
@Component
public class RateLimiterFilter extends ZuulFilter {
    /**
     * 1.限流的时机应在转发之前，理论上在最前，参考FilterConstants，最小值为-3（SERVLET_DETECTION_FILTER_ORDER）
     * 2.限流的措施参考令牌桶限流，Google实现了令牌桶限流，Guava中有实现
     */

    //定义令牌桶，每秒放置1000个令牌
    private static final RateLimiter RATE_LIMITER = RateLimiter.create(1000);
    @Override
    public String filterType() {
        return PRE_TYPE;
    }

    @Override
    public int filterOrder() {
        return SERVLET_DETECTION_FILTER_ORDER - 1;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        //如果不可以取到令牌 todo 统一异常处理
        if (!RATE_LIMITER.tryAcquire()) {
            throw new RateLimiterException();

        }
        return null;
    }
}
