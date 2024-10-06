package com.webshop.webshopfinal.model;


import com.webshop.webshopfinal.dao.CategoryDAO;

import java.util.Collection;

public class Category {
    private int id;
    private String name;

    public Category(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public static Collection<CategoryDAO> getCategories() {
        return CategoryDAO.getCategories();
    }

    public static CategoryDAO getCategory(int id) {
        return CategoryDAO.getCategory(id);
    }

    public static void createCategory(String name) {
        CategoryDAO.createCategory(name);
    }

    public static void updateCategory(int id, String name) {
        CategoryDAO.updateCategory(id, name);
    }

    public static void deleteCategory(int id) {
        CategoryDAO.deleteCategory(id);
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
