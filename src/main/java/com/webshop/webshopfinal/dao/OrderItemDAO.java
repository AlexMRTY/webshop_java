package com.webshop.webshopfinal.dao;

import com.webshop.webshopfinal.model.OrderItem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Collection;
import java.util.List;
import java.util.ArrayList;

public class OrderItemDAO extends OrderItem {
    public static Collection<OrderItemDAO> getOrderItemsByOrderId(List<Integer> orderIds) {
        Collection<OrderItemDAO> orderItems = new ArrayList<OrderItemDAO>();
        Connection con = DB.getConnection();
        PreparedStatement ps = null;
        try {
            StringBuilder query = new StringBuilder("SELECT * FROM order_items WHERE order_id IN (");
            for (int i = 0; i < orderIds.size(); i++) {
                query.append("?");
                if (i < orderIds.size() - 1) {
                    query.append(",");
                }
            }
            query.append(");");
            ps = con.prepareStatement(query.toString());
            for (int i = 0; i < orderIds.size(); i++) {
                ps.setInt(i + 1, orderIds.get(i));
            }
            var rs = ps.executeQuery();
            while (rs.next()) {
                orderItems.add(new OrderItemDAO(
                    rs.getInt("order_item_id"),
                    rs.getInt("order_id"),
                    rs.getInt("product_id"),
                    rs.getInt("quantity"),
                    rs.getDouble("price")
                ));
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
        return orderItems;
    }

    private OrderItemDAO(int id, int orderId, int productId, int quantity, double price) {
        super(id, orderId, productId, quantity, price);
    }
}
