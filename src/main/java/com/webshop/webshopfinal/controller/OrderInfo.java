package com.webshop.webshopfinal.controller;

import com.webshop.webshopfinal.enums.OrderStatus;

public class OrderInfo {
    private int id;
    private int userId;
    private OrderStatus status;
    private double totalAmount;

    public OrderInfo(int id, int userId, OrderStatus status, double totalPrice) {
        this.id = id;
        this.userId = userId;
        this.status = status;
        this.totalAmount = totalPrice;
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
