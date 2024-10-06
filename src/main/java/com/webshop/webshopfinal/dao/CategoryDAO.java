package com.webshop.webshopfinal.dao;

import com.webshop.webshopfinal.model.Category;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.Collection;

public class CategoryDAO extends Category {

    public static Collection<CategoryDAO> getCategories() {
        Collection<CategoryDAO> categories = new ArrayList<>();
        Connection con = DB.getConnection();
        PreparedStatement ps = null;
        try {
            ps = con.prepareStatement("SELECT * FROM categories");
            var rs = ps.executeQuery();
            while (rs.next()) {
                categories.add(new CategoryDAO(rs.getInt("category_id"), rs.getString("category_name")));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (ps != null) ps.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return categories;
    }

    public static CategoryDAO getCategory(int id) {
        CategoryDAO category = null;
        Connection con = DB.getConnection();
        PreparedStatement ps = null;
        try {
            ps = con.prepareStatement("SELECT * FROM categories WHERE category_id = ?");
            ps.setInt(1, id);
            var rs = ps.executeQuery();
            if (rs.next()) {
                category = new CategoryDAO(rs.getInt("id"), rs.getString("category_name"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (ps != null) ps.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return category;
        }

    public static void createCategory(String name) {
        Connection con = DB.getConnection();
        PreparedStatement ps = null;
        try {
            ps = con.prepareStatement("INSERT INTO categories (category_name) VALUES (?)");
            ps.setString(1, name);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (ps != null) ps.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    public static void updateCategory(int id, String name) {
        Connection con = DB.getConnection();
        PreparedStatement ps = null;
        try {
            ps = con.prepareStatement("UPDATE categories SET category_name = ? WHERE category_id = ?");
            ps.setString(1, name);
            ps.setInt(2, id);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (ps != null) ps.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void deleteCategory(int id) {
        Connection con = DB.getConnection();
        PreparedStatement ps = null;
        try {
            ps = con.prepareStatement("DELETE FROM categories WHERE category_id = ?");
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (ps != null) ps.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private CategoryDAO(int id, String name) {
        super(id, name);
    }
}
