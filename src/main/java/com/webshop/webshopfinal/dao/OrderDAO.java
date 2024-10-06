package com.webshop.webshopfinal.dao;

import com.webshop.webshopfinal.enums.OrderStatus;
import com.webshop.webshopfinal.model.Order;
import com.webshop.webshopfinal.model.OrderItem;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;

public class OrderDAO extends Order {

    public static Collection<OrderDAO> getOrders() {
        Collection<OrderDAO> orders = new ArrayList<>();
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = DB.getConnection();
            ps = con.prepareStatement("SELECT * FROM orders WHERE status = 'PLACED' LIMIT 50");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                OrderDAO order = new OrderDAO(
                        rs.getInt("id"),
                        rs.getInt("user_id"),
                        OrderStatus.valueOf(rs.getString("status")),
                        rs.getDouble("total_amount")
                );
                orders.add(order);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (ps != null) ps.close();

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return orders;
    }

    public static int createOrder(int userId, OrderStatus status, double totalPrice, Collection<OrderItem> orderItems) {
        int orderId = -1;
        Connection con = null;
        PreparedStatement ps = null;
        PreparedStatement ps2 = null;
        PreparedStatement ps3 = null;
        try {
            con = DB.getConnection();

            con.setAutoCommit(false);

            ps = con.prepareStatement("INSERT INTO orders (user_id, status, total_amount) VALUES (?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, userId);
            ps.setString(2, status.toString());
            ps.setDouble(3, totalPrice);
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                orderId = rs.getInt(1);
            }

            ps2 = con.prepareStatement("INSERT INTO order_items (order_id, product_id, quantity, price) VALUES (?, ?, ?, ?)");
            for (OrderItem orderItem : orderItems) {
                ps2.setInt(1, orderId);
                ps2.setInt(2, orderItem.getProductId());
                ps2.setInt(3, orderItem.getQuantity());
                ps2.setDouble(4, orderItem.getPrice());
                ps2.executeUpdate();
            }

            ps3 = con.prepareStatement("UPDATE products SET stock = stock - ? WHERE product_id = ? AND stock >= ?");
            for (OrderItem orderItem : orderItems) {
                ps3.setInt(1, orderItem.getQuantity());
                ps3.setInt(2, orderItem.getProductId());
                ps3.setInt(3, orderItem.getQuantity());
                int updatedRecords =  ps3.executeUpdate();
                if (updatedRecords == 0) {
                    throw new SQLException("Product with id " + orderItem.getProductId() + " not found.");
                }
            }

            con.commit();
        } catch (Exception e) {
            // 5. Rollback in case of an error
            if (con != null) {
                try {
                    con.rollback();
                    System.out.println("Transaction rolled back due to error.");
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
            e.printStackTrace();
        } finally {
            try {
                if (ps != null) ps.close();
                if (ps2 != null) ps2.close();
                if (ps3 != null) ps3.close();
                if (con != null) {
                    con.setAutoCommit(true);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return orderId;
    }

    public static void updateOrderStatus(int orderId, OrderStatus status) {
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = DB.getConnection();
            ps = con.prepareStatement("UPDATE orders SET status = ? WHERE id = ?");
            ps.setString(1, status.toString());
            ps.setInt(2, orderId);
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

    private OrderDAO(int id, int userId, OrderStatus status, double totalPrice) {
        super(id, userId, status, totalPrice);
    }
}
