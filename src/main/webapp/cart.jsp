<%--<jsp:useBean id="tax" scope="page" type="java.lang.Double"/>--%>
<%--<jsp:useBean id="total" scope="page" type="java.lang.Double"/>--%>
<%--<jsp:useBean id="subtotal" scope="page" type="java.lang.Double"/>--%>

<%--<jsp:useBean id="subtotal" scope="page" type="java.lang.Double"/>--%>
<%--<jsp:useBean id="tax" scope="page" type="java.lang.Double"/>--%>
<%--<jsp:useBean id="total" scope="page" type="java.lang.Double"/>--%>

<%@ page import="com.webshop.webshopfinal.controller.ProductInfo" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="java.util.HashSet" %>
<%@ page import="java.util.ArrayList" %>
<%--<jsp:useBean id="cartItems" scope="page" type="java.util.List"/>--%>

<%--
  Created by IntelliJ IDEA.
  User: asifr
  Date: 10/5/2024
  Time: 7:54 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <script src="https://kit.fontawesome.com/f64dca3a29.js" crossorigin="anonymous"></script>
    <script src="https://cdn.tailwindcss.com"></script>
    <title>Cart</title>
</head>
<body>
<body class="bg-[#e3e6f3]">
  <div class="container mx-auto">

    <%
        List<ProductInfo> cartItems = (List<ProductInfo>) request.getSession().getAttribute("cartItems");

        if (cartItems == null) {
            cartItems = new ArrayList<>();
        }
        HashMap<Integer, Integer> itemQuantity = new HashMap<>();
        for (ProductInfo item : cartItems) {
            if (itemQuantity.containsKey(item.getId())) {
                itemQuantity.put(item.getId(), itemQuantity.get(item.getId()) + 1);
            } else {
                itemQuantity.put(item.getId(), 1);
            }
        }

        double subtotal = 0.0;
        double tax = 0.0;
        double total = 0.0;

        for (ProductInfo item : cartItems) {
            subtotal += item.getPrice() * itemQuantity.get(item.getId());
        }
        tax = subtotal * 0.1;
        total = subtotal + tax;

    %>

    <h1 class="text-3xl font-bold text-center my-4">Your Cart</h1>

    <div class="grid grid-cols-12 grid-rows-12 gap-8">
        <div class="bg-white rounded-[48px] p-12 shadow-xl col-start-2 col-span-6 row-span-9 row-start-2">
          <%
              if (cartItems.isEmpty()) {
          %>
          <h1 class="text-center text-gray-500 text-bold text-3xl">Your cart is empty.</h1>
          <%
          } else {
              HashSet<Integer> itemIds = new HashSet<>();
              for (ProductInfo item : cartItems) {
                  // Add id to set to avoid duplicate items
                    if (itemIds.contains(item.getId())) continue;
                    itemIds.add(item.getId());

          %>
          <div class="flex border rounded border-none flex-row justify-between my-6">
              <div class="flex items-center">
                  <img src="<%= (item.getImage()) %>" alt="<%= item.getName() %>" class="w-32 h-auto rounded-3xl">
                  <div class="ml-4">
                      <h2 class="text-lg font-semibold"><%= item.getName() %></h2>
                      <p class="text-gray-500 text-base font-bold">$<%= item.getPrice() %></p>
                  </div>
              </div>
              <div class="flex items-center justify-center gap-4">
                  <label for="quantity-<%= item.getId() %>" class="font-bold">Quantity: <span class="font-normal"><%= itemQuantity.get(item.getId()) %></span></label>



<%--                  <input--%>
<%--                          type="number"--%>
<%--                          id="quantity-<%= item.getId() %>"--%>
<%--                          name="quantity-<%= item.getId() %>"--%>
<%--                          value="<%= itemQuantity.get(item.getId()) %>"--%>
<%--                          min="1"--%>
<%--                          max="<%= item.getStock() %>"--%>
<%--                          class="border rounded px-2 py-1"--%>
<%--                  >--%>
                  <button class="text-gray-900 bg-white border border-gray-300 focus:outline-none hover:bg-gray-100 focus:ring-4 focus:ring-gray-100 font-medium rounded-full text-sm px-5 py-2.5 ">Remove</button>
              </div>
          </div>
            <hr />
          <%
              } // End of for loop
          } // End of if-else
          %>
        </div>
        <div class="bg-white rounded-[48px] p-12 shadow-xl col-span-4 row-span-5 row-start-2">
          <div class="border rounded p-4">
              <h2 class="text-xl font-semibold">Order Summary</h2>
              <ul class="list-disc">
                  <%
                      for (ProductInfo item : cartItems) {
                  %>
                  <li class="flex justify-between">
                      <span><%= item.getName() %></span>
                      <span><%= item.getPrice() %></span>
                  </li>
                  <%
                      } // End of for loop
                  %>
                  <li class="flex justify-between">
                      <span>Subtotal</span>
                      <span><%= subtotal %></span>
                  </li>
                  <li class="flex justify-between">
                      <span>Tax</span>
                      <span><%= tax %></span>
                  </li>
                  <li class="flex justify-between">
                      <span>Total</span>
                      <span><%= total %></span>
                  </li>

              </ul>
              <form action="/home/me/cart/checkout" method="post">
                  <%
                      if (cartItems.isEmpty()) {
                  %>
                    <button class="bg-blue-200 text-white w-full px-4 py-2 rounded mt-4" disabled>Proceed to Checkout</button>
                  <%
                    } else {
                  %>
                    <button class="bg-blue-500 text-white w-full px-4 py-2 rounded mt-4">Proceed to Checkout</button>
                  <%
                    } // End of if-else
                  %>
              </form>
          </div>
      </div>
  </div>

</body>
</body>
</html>
