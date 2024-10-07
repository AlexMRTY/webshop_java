package com.webshop.webshopfinal.handlers;

import com.webshop.webshopfinal.controller.OrderItemInfo;
import com.webshop.webshopfinal.dao.OrderItemDAO;
import com.webshop.webshopfinal.model.OrderItem;

import java.util.Collection;
import java.util.List;
import java.util.ArrayList;

public class OrderItemHandler {
    /**
     * Get order items by order id
     * @param orderIds
     * @return Collection<OrderItemInfo>
     */
    public static Collection<OrderItemInfo> getOrderItemsByOrderId(List<Integer> orderIds) {
        Collection<OrderItemDAO> orderItemDAOs = OrderItem.getOrderItemsByOrderId(orderIds);
        Collection<OrderItemInfo> orderItems = new ArrayList<>();
        for (OrderItemDAO orderItemDAO : orderItemDAOs) {
            orderItems.add(new OrderItemInfo(
                orderItemDAO.getId(),
                orderItemDAO.getOrderId(),
                orderItemDAO.getProductId(),
                orderItemDAO.getQuantity(),
                orderItemDAO.getPrice()
            ));
        }
        return orderItems;
    }
}
