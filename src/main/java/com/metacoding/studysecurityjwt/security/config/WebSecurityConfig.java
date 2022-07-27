package com.metacoding.studysecurityjwt.security.config;

import com.metacoding.studysecurityjwt.security.filter.MyFilter1;
import com.metacoding.studysecurityjwt.security.filter.MyFilter3;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.filter.CorsFilter;


@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final CorsFilter corsFilter;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.addFilterBefore(new MyFilter3(), BasicAuthenticationFilter.class);   // 그냥 addFilter(new MyFilter1());으로 해주면 org.springframework.beans.factory.BeanCreationException: 에러 발생 -> addFilterBefore나 addFilterAfter사용
        http.csrf().disable();
        http
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)  // jwt 사용 시 필요! 왜냐하면 jwt에서는 세션을 사용하지 않기 때문.
                .and()
                .addFilter(corsFilter)   // 모든 요청에 대해 이 필터를 거치게 된다. Controller단의 @CrossOrigin은 인증이 필요 x때만 사용, 시큐리티 필터에 등록 인증(o)
                .formLogin().disable()     // jwt 사용하기 때문에 form login을 안한다.
                .httpBasic().disable()    // 이건 왜? httpBaisc방식은 Authorization에 id와 pw를 담아서 요청하는 방식인데 Authorization에 토큰을 담아 요청해야하기 때문에 disable해준다.
                .authorizeRequests()
                .antMatchers("/api/v1/user/**")
                .access("hasRole('ROLE_USER') or hasRole('ROLE_MANAGER') or hasRole('ROLE_ADMIN')")
                .antMatchers("/api/v1/manager/**")
                .access("hasRole('ROLE_MANAGER') or hasRole('ROLE_ADMIN')")
                .antMatchers("/api/v1/admin/**")
                .access("hasRole('ROLE_ADMIN')")
                .anyRequest().permitAll();
    }
}
