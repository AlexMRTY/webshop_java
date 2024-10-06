package com.webshop.webshopfinal.handlers;

import com.webshop.webshopfinal.controller.OrderInfo;
import com.webshop.webshopfinal.dao.OrderDAO;
import com.webshop.webshopfinal.enums.OrderStatus;
import com.webshop.webshopfinal.model.Order;
import com.webshop.webshopfinal.model.OrderItem;
import com.webshop.webshopfinal.controller.ProductInfo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class OrderHandler {
    public static int createOrder(int userId, Collection<ProductInfo> products) {
        Collection<OrderItem> orderItems = OrderItem.createOrderItems(products);
        double totalPrice = 0;
        for (OrderItem orderItem : orderItems) {
            totalPrice += orderItem.getPrice() * orderItem.getQuantity();
        }
        return Order.createOrder(userId, OrderStatus.PLACED, totalPrice, orderItems);
    }

    public static Collection<OrderInfo> getOrders() {
        Collection<OrderDAO> orderDao = OrderDAO.getOrders();
        Collection<OrderInfo> orders = new ArrayList<>();
        Iterator<OrderDAO> iterator = orderDao.iterator();
        int i = iterator.next().getId();
        for (OrderDAO order : orderDao) {
            orders.add(new OrderInfo(
                    order.getId(),
                    order.getUserId(),
                    order.getStatus(),
                    order.getTotalPrice()
            ));
        }
        return orders;
    }

    public static void updateOrderStatus(int orderId, OrderStatus status) {
        Order.updateOrderStatus(orderId, status);
    }
}
