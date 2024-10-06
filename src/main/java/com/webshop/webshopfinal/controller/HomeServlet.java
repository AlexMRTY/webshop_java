package com.webshop.webshopfinal.controller;

import com.webshop.webshopfinal.handlers.ProductHandler;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Collection;

@WebServlet("/home")
public class HomeServlet extends HttpServlet {

//    GET Products
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Collection<ProductInfo> products = ProductHandler.getProducts();
        request.setAttribute("products", products);
        request.getRequestDispatcher("/home.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Collection<ProductInfo> products = ProductHandler.getProducts();
        request.setAttribute("products", products);
        request.getRequestDispatcher("/home.jsp").forward(request, response);
    }
}
