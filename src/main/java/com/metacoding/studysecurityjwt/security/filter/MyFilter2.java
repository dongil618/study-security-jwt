package com.metacoding.studysecurityjwt.security.filter;

import javax.servlet.*;
import java.io.IOException;

public class MyFilter2 implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.println("필터2");
        chain.doFilter(request, response);  // chain을 걸어주지 않으면 1번만 실행되고 끝남.
    }
}
