
<%@ page import="com.webshop.webshopfinal.controller.OrderInfo" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>

<%@ page import="com.webshop.webshopfinal.controller.OrderItemInfo" %>
<%@ page import="com.webshop.webshopfinal.controller.ProductInfo" %><%--
  Created by IntelliJ IDEA.
  User: asifr
  Date: 10/5/2024
  Time: 1:18 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <script src="https://kit.fontawesome.com/f64dca3a29.js" crossorigin="anonymous"></script>
    <script src="https://cdn.tailwindcss.com"></script>
    <title>Worker Terminal</title>
    <title>Orders Overview</title>
</head>
<body class="bg-[#e3e6f3]">
<div class="container mx-auto">
    <h1 class="text-3xl font-bold text-center my-4">Orders Overview</h1>

    <%
        // Assuming you get a list of orders from the servlet and set it in the request
        List<OrderInfo> orders = (List<OrderInfo>) request.getAttribute("orders");
        List<OrderItemInfo> orderItems = (List<OrderItemInfo>) request.getAttribute("orderItems");
        List<ProductInfo> products = (List<ProductInfo>) request.getAttribute("products");

        if (orders == null) {
            orders = new ArrayList<>();
        }
        if (orderItems == null) {
            orderItems = new ArrayList<>();
        }
        if (products == null) {
            products = new ArrayList<>();
        }
    %>

    <div class="grid grid-cols-12 grid-rows-12 gap-8">
        <div class="bg-white rounded-[48px] p-12 shadow-xl col-start-3 col-span-8 row-span-9 my-20">
            <%
                if (orders.isEmpty() || orderItems.isEmpty() || products.isEmpty()) {
            %>
            <h1 class="text-center text-gray-500 text-bold text-3xl">No orders found.</h1>
            <%
            } else {
                for (OrderInfo order : orders) {
            %>
            <div class="border rounded p-6 my-6">
                <div class="flex flex-row justify-between items-center">
                    <div>
                        <h2 class="text-2xl font-semibold mb-4">Order ID: <%= order.getId() %></h2>
                        <p class="text-lg">Order Status: <%= order.getStatus() %></p>
                        <p class="text-lg">Order Total: $<%= order.getTotalPrice() %></p>
                    </div>
                    <form action="/employee/worker" method="post">
                        <button name="orderId" value="<%= order.getId() %>" class="text-gray-900 bg-white border border-gray-300 focus:outline-none hover:bg-gray-100 focus:ring-4 focus:ring-gray-100 font-medium rounded-full text-sm px-5 py-2.5 ">Complete</button>
                    </form>
                </div>
                <hr class="my-4">
                <h3 class="text-xl font-semibold mb-4">Products in this Order:</h3>
                <%
                    for (OrderItemInfo orderItem : orderItems) {
                        if (orderItem.getOrderId() != order.getId()) continue;
                        ProductInfo product = null;
                        for (ProductInfo prod : products) {
                            if (prod.getId() == orderItem.getProductId()) {
                                product = prod;
                                break;
                            }
                        }

                %>
                <div class="flex items-center mb-4">
                    <img src="<%= product.getImage() %>" alt="<%= product.getName() %>" class="w-32 h-auto rounded-3xl">
                    <div class="ml-4">
                        <h4 class="text-lg font-semibold"><%= product.getName() %></h4>
                        <p class="text-gray-500">Price: $<%= product.getPrice() %></p>
                        <p class="text-gray-500">Quantity: <%= orderItem.getQuantity() %></p>
                    </div>
                </div>
                <%
                    } // End of for loop (order items)
                %>
            </div>
            <hr />
            <%
                    } // End of for loop (orders)
                } // End of if-else
            %>
        </div>
    </div>
</div>
</body>
</html>

