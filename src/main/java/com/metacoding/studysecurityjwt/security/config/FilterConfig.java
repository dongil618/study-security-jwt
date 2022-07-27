package com.metacoding.studysecurityjwt.security.config;

import com.metacoding.studysecurityjwt.security.filter.MyFilter1;
import com.metacoding.studysecurityjwt.security.filter.MyFilter2;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfig {

    @Bean
    public FilterRegistrationBean<MyFilter1> filter1(){
        FilterRegistrationBean<MyFilter1> bean = new FilterRegistrationBean<>(new MyFilter1());
        bean.addUrlPatterns("/*");  // /** 안됨
        bean.setOrder(0);   // 낮은 번호가 필터중에서 가장 먼저 실행됨. -> 0은 우선순위 가장 높다.
        return bean;
    }

    @Bean
    public FilterRegistrationBean<MyFilter2> filter2(){
        FilterRegistrationBean<MyFilter2> bean = new FilterRegistrationBean<>(new MyFilter2());
        bean.addUrlPatterns("/*");  // /** 안됨
        bean.setOrder(1);     // 필터 1이 실행된 후 실행된다. 참고로 WebSecurityConfig에 chain으로 등록한 필터가 가장 먼저 실행됨!
        return bean;
    }
}
