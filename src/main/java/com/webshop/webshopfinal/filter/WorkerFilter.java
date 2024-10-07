package com.webshop.webshopfinal.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebFilter("/employee/worker/*")
public class WorkerFilter implements Filter {

    /**
     * Check if the user has worker privileges
     * @param request
     * @param response
     * @param chain
     * @throws IOException
     * @throws ServletException
     */
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException
    {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        HttpSession session = httpRequest.getSession();

        // Check if the user is logged in and has admin privileges
        if (
                (session != null && session.getAttribute("userRole") != null) &&
                        (
                                session.getAttribute("userRole").equals("WORKER") ||
                                session.getAttribute("userRole").equals("ADMIN")
                        )
        ) {
            // User is authenticated and has admin privileges, continue with the request
            chain.doFilter(request, response);
        } else {
            // User is not authenticated or doesn't have admin privileges, redirect to login or error page
            httpResponse.sendRedirect("/login.jsp");
        }
    }
}
