package com.webshop.webshopfinal.dao;

import com.webshop.webshopfinal.controller.ProductInfo;
import com.webshop.webshopfinal.model.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Vector;

public class ProductDAO extends Product {

    /**
     * Get all products
     * @return Collection<ProductDAO>
     */
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

    /**
     * Get products by ids
     * @param ids
     * @return Collection<ProductDAO>
     */
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

    /**
     * Get product by id
     * @param id
     * @return ProductDAO
     */
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

    /**
     * Create product
     * @param product
     */
    public static void createProduct (Map<String, String> product) {
        List<String> columns = new Vector<String>();
        List<String> values = new Vector<String>();
        for (String key : product.keySet()) {
            columns.add(key);
            values.add(product.get(key));
        }

        Connection con = DB.getConnection();
        PreparedStatement ps = null;
        try {
            StringBuilder query = new StringBuilder("INSERT INTO products (");
            for (int i = 0; i < columns.size(); i++) {
                query.append(columns.get(i));
                if (i < columns.size() - 1) {
                    query.append(",");
                }
            }
            query.append(") VALUES (");
            for (int i = 0; i < values.size(); i++) {
                query.append("?");
                if (i < values.size() - 1) {
                    query.append(",");
                }
            }
            query.append(");");
            ps = con.prepareStatement(query.toString());
            for (int i = 0; i < values.size(); i++) {
                ps.setString(i + 1, values.get(i));
            }
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (ps != null) ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Update product
     * @param id
     * @param product
     */
    public static void updateProduct (Integer id, Map<String, String> product) {
        List<String> columns = new Vector<String>();
        List<String> values = new Vector<String>();
        for (String key : product.keySet()) {
            columns.add(key);
            values.add(product.get(key));
        }

        Connection con = DB.getConnection();
        PreparedStatement ps = null;
        try {
            StringBuilder query = new StringBuilder("UPDATE products SET ");
            for (int i = 0; i < columns.size(); i++) {
                query.append(columns.get(i) + " = ?");
                if (i < columns.size() - 1) {
                    query.append(",");
                }
            }
            query.append(" WHERE product_id = ?;");
            ps = con.prepareStatement(query.toString());
            for (int i = 0; i < values.size(); i++) {
                ps.setString(i + 1, values.get(i));
            }
            ps.setInt(values.size() + 1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (ps != null) ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Delete product
     * @param id
     */
    public static void deleteProduct (int id) {
        Connection con = DB.getConnection();
        PreparedStatement ps = null;
        try {
            ps = con.prepareStatement("DELETE FROM products WHERE product_id = ?;");
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (ps != null) ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }


    private ProductDAO(int id, String name, String brand, String description, double price, String image, int rating, int stock) {
        super(id, name, brand, price, stock, description, rating, image);
    }
}
