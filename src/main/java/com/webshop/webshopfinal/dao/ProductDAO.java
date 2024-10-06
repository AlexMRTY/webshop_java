package com.webshop.webshopfinal.dao;

import com.webshop.webshopfinal.model.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;
import java.util.Vector;

public class ProductDAO extends Product {
    public static Collection<ProductDAO> getProducts () {
        Vector<ProductDAO> products = new Vector<ProductDAO>();
        try {
            Connection con = DB.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT * FROM products;");
            var rs = ps.executeQuery();
            while (rs.next()) {
                products.add(new ProductDAO(
                    rs.getInt("product_id"),
                    rs.getString("name"),
                    rs.getString("brand"),
                    rs.getString("description"),
                    rs.getDouble("price"),
                    rs.getString("image"),
                    rs.getInt("rating"),
                    rs.getInt("stock")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return products;
    }

    public static Collection<ProductDAO> getProductsByIds (List<Integer> ids) {
        Vector<ProductDAO> products = new Vector<ProductDAO>();
        try {
            Connection con = DB.getConnection();
            StringBuilder query = new StringBuilder("SELECT * FROM products WHERE product_id IN (");
            for (int i = 0; i < ids.size(); i++) {
                query.append("?");
                if (i < ids.size() - 1) {
                    query.append(",");
                }
            }
            query.append(");");
            PreparedStatement ps = con.prepareStatement(query.toString());
            for (int i = 0; i < ids.size(); i++) {
                ps.setInt(i + 1, ids.get(i));
            }
            var rs = ps.executeQuery();
            while (rs.next()) {
                products.add(new ProductDAO(
                        rs.getInt("product_id"),
                        rs.getString("name"),
                        rs.getString("brand"),
                        rs.getString("description"),
                        rs.getDouble("price"),
                        rs.getString("image"),
                        rs.getInt("rating"),
                        rs.getInt("stock")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return products;
    }

    public static ProductDAO getProduct (int id) {
        ProductDAO product = null;
        try {
            Connection con = DB.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT * FROM products WHERE product_id = ?;");
            ps.setInt(1, id);
            var rs = ps.executeQuery();
            if (rs.next()) {
                product = new ProductDAO(
                    rs.getInt("product_id"),
                    rs.getString("name"),
                    rs.getString("brand"),
                    rs.getString("description"),
                    rs.getDouble("price"),
                    rs.getString("image"),
                    rs.getInt("rating"),
                    rs.getInt("stock")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return product;
    }



    private ProductDAO(int id, String name, String brand, String description, double price, String image, int rating, int stock) {
        super(id, name, brand, price, stock, description, rating, image);
    }
}
