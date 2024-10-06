package com.webshop.webshopfinal.filter;


import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebFilter("/home/me/*")
public class AuthFilter implements Filter {

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse res = (HttpServletResponse) servletResponse;
        String username = (String) req.getSession().getAttribute("user");
        if (username == null) {
            res.sendRedirect("/login.jsp");
            return;
        }
        filterChain.doFilter(req, res);
    }
}
