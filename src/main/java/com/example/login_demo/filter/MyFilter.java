package com.example.login_demo.filter;

import org.springframework.stereotype.Component;

import javax.servlet.*;
import java.io.IOException;
//@Component
public class MyFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("MyFilter...");
        filterChain.doFilter(servletRequest, servletResponse);
    }
}
