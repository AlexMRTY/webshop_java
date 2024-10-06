package com.webshop.webshopfinal.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("employee/admin")
public class AdminServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.getRequestDispatcher("/employee/admin/category").include(request, response);
        request.getRequestDispatcher("/employee/admin/product").include(request, response);

        request.getRequestDispatcher("/employee/admin/admin.jsp").forward(request, response);
    }
}
