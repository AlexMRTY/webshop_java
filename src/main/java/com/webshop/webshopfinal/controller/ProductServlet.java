package com.webshop.webshopfinal.controller;

import com.webshop.webshopfinal.handlers.CategoryHandler;
import com.webshop.webshopfinal.handlers.ProductHandler;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;


public class ProductServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Collection<ProductInfo> products = ProductHandler.getProducts();
        request.setAttribute("products", products);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String brand = request.getParameter("brand");
        String description = request.getParameter("description");
        String price = request.getParameter("price");
        String image = request.getParameter("image");
        String rating = request.getParameter("rating");
        String stock = request.getParameter("stock");

        // Filter the parameters and seperate only the ones that are not null
        Map<String, String> filteredParameters = new HashMap<>();
        if (name != null) {
            filteredParameters.put("name", name);
        }
        if (brand != null) {
            filteredParameters.put("brand", brand);
        }
        if (description != null) {
            filteredParameters.put("description", description);
        }
        if (price != null) {
            filteredParameters.put("price", price);
        }
        if (image != null) {
            filteredParameters.put("image", image);
        }
        if (rating != null) {
            filteredParameters.put("rating", rating);
        }
        if (stock != null) {
            filteredParameters.put("stock", stock);
        }

        ProductHandler.createProduct(filteredParameters);
        response.sendRedirect("/employee/worker/category");
        request.getRequestDispatcher("/home.jsp").forward(request, response);
    }

    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        String name = request.getParameter("name");
        String brand = request.getParameter("brand");
        String description = request.getParameter("description");
        String price = request.getParameter("price");
        String image = request.getParameter("image");
        String rating = request.getParameter("rating");
        String stock = request.getParameter("stock");

        // Filter the parameters and seperate only the ones that are not null
        Map<String, String> filteredParameters = new HashMap<>();
        if (name != null) {
            filteredParameters.put("name", name);
        }
        if (brand != null) {
            filteredParameters.put("brand", brand);
        }
        if (description != null) {
            filteredParameters.put("description", description);
        }
        if (price != null) {
            filteredParameters.put("price", price);
        }
        if (image != null) {
            filteredParameters.put("image", image);
        }
        if (rating != null) {
            filteredParameters.put("rating", rating);
        }
        if (stock != null) {
            filteredParameters.put("stock", stock);
        }

        ProductHandler.updateProduct(Integer.parseInt(id), filteredParameters);
        response.sendRedirect("/employee/worker/product");
    }

    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        ProductHandler.deleteProduct(id);
        response.sendRedirect("/employee/worker/product");
    }
}
