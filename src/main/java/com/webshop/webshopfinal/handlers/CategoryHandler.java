package com.webshop.webshopfinal.handlers;

import com.webshop.webshopfinal.controller.CategoryInfo;
import com.webshop.webshopfinal.dao.CategoryDAO;
import com.webshop.webshopfinal.model.Category;

import java.util.ArrayList;
import java.util.Collection;

public class CategoryHandler {

    /**
     * Get all categories
     * @return Collection<CategoryInfo>
     */
    public static Collection<CategoryInfo> getCategories() {
        Collection<CategoryDAO> categories = Category.getCategories();
        Collection<CategoryInfo> categoryInfos = new ArrayList<>();
        for (CategoryDAO category : categories) {
            categoryInfos.add(new CategoryInfo(category.getId(), category.getName()));
        }
        return categoryInfos;
    }

    /**
     * Get category by id
     * @param id
     * @return CategoryInfo
     */
    public static CategoryInfo getCategory(int id) {
        CategoryDAO category = Category.getCategory(id);
        if (category == null) {
            return null;
        }
        return new CategoryInfo(category.getId(), category.getName());
    }

    /**
     * Create category
     * @param name
     */
    public static void createCategory(String name) {
        Category.createCategory(name);
    }

    /**
     * Update category
     * @param id
     * @param name
     */
    public static void updateCategory(int id, String name) {
        Category.updateCategory(id, name);
    }

    /**
     * Delete category
     * @param id
     */
    public static void deleteCategory(int id) {
        Category.deleteCategory(id);
    }
}
