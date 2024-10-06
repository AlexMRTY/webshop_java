package com.webshop.webshopfinal.controller;

import com.webshop.webshopfinal.handlers.CategoryHandler;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Collection;

@WebServlet("/employee/admin/category")
public class CategoryServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Collection<CategoryInfo> categories = CategoryHandler.getCategories();
        request.setAttribute("categories", categories);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        CategoryHandler.createCategory(name);
        response.sendRedirect("/employee/worker/category");
    }

    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        CategoryHandler.updateCategory(id, name);
        response.sendRedirect("/employee/worker/category");
    }

    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        CategoryHandler.deleteCategory(id);
        response.sendRedirect("/employee/worker/category");
    }
}
