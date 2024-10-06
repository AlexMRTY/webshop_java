package com.webshop.webshopfinal.controller;

import com.webshop.webshopfinal.handlers.UserHandler;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/login")
public class LogInServlet extends HttpServlet {



    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        UserInfo user = UserHandler.loginUser(username, password);
        if (user == null) {
            response.sendRedirect("login.jsp");
        } else {
            request.getSession().setAttribute("user", user.getUsername());
            request.getSession().setAttribute("userRole", user.getRole());
            response.sendRedirect("home");
        }
    }

}
