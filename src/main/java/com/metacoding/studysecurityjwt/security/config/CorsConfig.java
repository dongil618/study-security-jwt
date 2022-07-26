package com.metacoding.studysecurityjwt.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
public class CorsConfig {

    @Bean
    public CorsFilter corsFilter(){
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);   // 내 서버가 응답할 때 json을 자바스크립트에서 처리할 수 있게 할지를 설정하는 것 => false이면 javascript로 어떠한 요청을 했을 때 응답이 오지 않는다.
        config.addAllowedOrigin("*");     // 모든 ip에 응답을 허용하겠다.
        config.addAllowedHeader("*");     // 모든 header에 응답을 허용하겠다.
        config.addAllowedMethod("*");     // 모든 post, get, put, delete 등에 응답을 허용하겠다.
        source.registerCorsConfiguration("/api/**", config);

        return new CorsFilter(source);
        // error: constructor CorsFilter in class CorsFilter cannot be applied to given types; return new CorsFilter(source);
        // 에러 해결은 CorsFilter import를 import org.apache.catalina.filters.CorsFilter; -> import org.springframework.web.filter.CorsFilter; 변경해줘야한다.
    }
}
