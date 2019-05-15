package com.qs.springbootservlet.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * 
 * @author : qshomewy
 * @description : 自定义过滤器
 */
public class CustomFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        request.setAttribute("filterParam","我是filter传递的参数");
        chain.doFilter(request,response);
        response.getWriter().append(" CustomFilter ");
    }

    @Override
    public void destroy() {

    }
}
