package com.webshop.webshopfinal.model;

import com.webshop.webshopfinal.dao.OrderDAO;
import com.webshop.webshopfinal.enums.OrderStatus;

import java.util.Collection;

public class Order {
    private int id;
    private int userId;
    private OrderStatus status;
    private double totalAmount;

    protected Order(int id, int userId, OrderStatus status, double totalPrice) {
        this.id = id;
        this.userId = userId;
        this.status = status;
        this.totalAmount = totalPrice;
    }

    /**
     * Constructor for Order
     * @param userId
     * @param status
     * @param totalPrice
     */
    static public int createOrder(int userId, OrderStatus status, double totalPrice, Collection<OrderItem> orderItems) {
        return OrderDAO.createOrder(userId, status, totalPrice, orderItems);
    }

    /**
     * Get all orders
     * @return Collection<OrderDAO>
     */
    static public Collection<OrderDAO> getOrders() {
        return OrderDAO.getOrders();
    }

    /**
     * Update order status
     * @param orderId
     * @param status
     * @return void
     */
    public static void updateOrderStatus(int orderId, OrderStatus status) {
        OrderDAO.updateOrderStatus(orderId, status);
    }

    public int getId() {
        return id;
    }

    public int getUserId() {
        return userId;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public double getTotalPrice() {
        return totalAmount;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUser_id(int userId) {
        this.userId = userId;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public void setTotal_price(double totalPrice) {
        this.totalAmount = totalPrice;
    }

}
