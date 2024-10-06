package com.webshop.webshopfinal.controller;

import com.webshop.webshopfinal.handlers.CategoryHandler;
import com.webshop.webshopfinal.handlers.ProductHandler;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/employee/admin/product")
public class ProductServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Collection<ProductInfo> products = ProductHandler.getProducts();
        request.setAttribute("products", products);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("op");
        if (action.equals("DELETE")) {
            doDelete(request, response);
            return;
        } else if (action.equals("PUT")) {
            doPut(request, response);
            return;
        }

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

        // Otherwise create a new product
        ProductHandler.createProduct(filteredParameters);
        response.sendRedirect("/employee/admin");
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
        if (name != null && !name.isEmpty()) {
            filteredParameters.put("name", name);
        }
        if (brand != null && !brand.isEmpty()) {
            filteredParameters.put("brand", brand);
        }
        if (description != null && !description.isEmpty()) {
            filteredParameters.put("description", description);
        }
        if (price != null && !price.isEmpty()) {
            filteredParameters.put("price", price);
        }
        if (image != null && !image.isEmpty()) {
            filteredParameters.put("image", image);
        }
        if (rating != null && !rating.isEmpty()) {
            filteredParameters.put("rating", rating);
        }
        if (stock != null && !stock.isEmpty()) {
            filteredParameters.put("stock", stock);
        }

        ProductHandler.updateProduct(Integer.parseInt(id), filteredParameters);
        response.sendRedirect("/employee/admin");
    }

    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        ProductHandler.deleteProduct(id);
        response.sendRedirect("/employee/admin");
    }
}
