package com.webshop.webshopfinal.model;

import com.webshop.webshopfinal.controller.ProductInfo;

import java.util.*;

import com.webshop.webshopfinal.dao.OrderItemDAO;

public class OrderItem {
    private int id;
    private int orderId;
    private int productId;
    private int quantity;
    private double price;

    protected OrderItem(int id, int orderId, int productId, int quantity, double price) {
        this.id = id;
        this.orderId = orderId;
        this.productId = productId;
        this.quantity = quantity;
        this.price = price;
    }

    static public Collection<OrderItem> createOrderItems(Collection<ProductInfo> products) {
        Collection<OrderItem> orderItems = new ArrayList<OrderItem>();
        HashMap<Integer, Integer> productQuantity = new HashMap<Integer, Integer>();
        for (ProductInfo product : products) {
            if (productQuantity.containsKey(product.getId())) {
                productQuantity.put(product.getId(), productQuantity.get(product.getId()) + 1);
            } else {
                productQuantity.put(product.getId(), 1);
            }
        }
        HashSet<Integer> order = new HashSet<Integer>();
        for (ProductInfo product : products) {
            if (order.contains(product.getId())) {
                continue;
            }
            orderItems.add(new OrderItem(
                    0,  // id
                0,  // orderId
                product.getId(),
                productQuantity.get(product.getId()),
                product.getPrice()
            ));
            order.add(product.getId());
        }
        return orderItems;
    }

    static public Collection<OrderItemDAO> getOrderItemsByOrderId(List<Integer> orderIds) {
        return OrderItemDAO.getOrderItemsByOrderId(orderIds);
    }

    public int getId() {
        return id;
    }

    public int getOrderId() {
        return orderId;
    }

    public int getProductId() {
        return productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setPrice(double price) {
        this.price = price;
    }

}
