package com.webshop.webshopfinal.controller;

import com.webshop.webshopfinal.enums.OrderStatus;
import com.webshop.webshopfinal.handlers.ProductHandler;
import com.webshop.webshopfinal.handlers.OrderHandler;
import com.webshop.webshopfinal.handlers.OrderItemHandler;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

@WebServlet("/employee/worker")
public class WorkerServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // Get all orders
        Collection<OrderInfo> orders = OrderHandler.getOrders();

        // Get all order items in the orders
        List<Integer> orderIdList = new ArrayList<>();
        for (OrderInfo order : orders) {
            orderIdList.add(order.getId());
        }
        Collection<OrderItemInfo> orderItems = OrderItemHandler.getOrderItemsByOrderId(orderIdList);

        // Get all products in the order items
        List<Integer> productIdList = new ArrayList<>();
        HashSet<Integer> productIdSet = new HashSet<>();
        for (OrderItemInfo orderItem : orderItems) {
            if (productIdSet.contains(orderItem.getProductId())) {
                continue;
            }
            productIdSet.add(orderItem.getProductId());
            productIdList.add(orderItem.getProductId());
        }
        Collection<ProductInfo> products = ProductHandler.getProductsByIds(productIdList);

        request.setAttribute("orders", orders);
        request.setAttribute("orderItems", orderItems);
        request.setAttribute("products", products);
        request.getRequestDispatcher("/employee/worker/worker.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int orderId = Integer.parseInt(request.getParameter("orderId"));
        OrderHandler.updateOrderStatus(orderId, OrderStatus.SHIPPED);
        response.sendRedirect("/employee/worker");
    }
}
