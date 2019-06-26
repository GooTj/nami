package com.onepiece.nami.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * 跨域
 * C - Cross  O - Origin  R - Resource   S - Sharing
 * @author Goo Tsung-jen
 * @create 2019-06-25  16:16
 * Innovation distinguishes between a leader and a follower.
 */
@Configuration
public class CorsConfig {

    @Bean
    public CorsFilter corsFilter() {

        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();

        final CorsConfiguration config = new CorsConfiguration();

        //是否跨域
        config.setAllowCredentials(true);
        //原始域 eg：www.zzzh56.com
        config.setAllowedOrigins(Arrays.asList("*"));
        //允许的头
        config.setAllowedHeaders(Arrays.asList("*"));
        //get&post……
        config.setAllowedMethods(Arrays.asList("*"));
        //缓存时间,在一段时间内对于相同的跨域请求，不再检查
        config.setMaxAge(300L);

        source.registerCorsConfiguration("/**",config);


        return new CorsFilter(source);
    }
}
