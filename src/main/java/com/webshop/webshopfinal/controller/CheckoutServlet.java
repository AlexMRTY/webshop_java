package com.webshop.webshopfinal.controller;


import com.webshop.webshopfinal.handlers.OrderHandler;
import com.webshop.webshopfinal.handlers.ProductHandler;
import com.webshop.webshopfinal.handlers.UserHandler;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;

@WebServlet("/home/me/cart/checkout")
public class CheckoutServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Find the cart cookie
        Cookie[] cookies = request.getCookies();
        Cookie cartCookie = null;
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("cart")) {
                cartCookie = cookie;
            }
        }
        // Extract product from the cart cookie
        Collection<ProductInfo> products = new ArrayList<>();
        if (cartCookie != null) {
            String cart = cartCookie.getValue();
            String[] productIds = cart.split("\\|");
            for (String productId : productIds) {
                ProductInfo product = ProductHandler.getProduct(Integer.parseInt(productId));
                products.add(product);
            }
        }

        // Get the user id
        String username = (String) request.getSession().getAttribute("user");
        int userId = Objects.requireNonNull(UserHandler.getUser(username)).getId();

        // create an order
        int status = OrderHandler.createOrder(1, products);

        // Redirect to order confirmation page
        if (status == -1) {
            response.sendRedirect("/order-failed.jsp");
        } else {
            Cookie newCookie = new Cookie("cart", "");
            newCookie.setMaxAge(0);
            newCookie.setPath("/");
            response.addCookie(newCookie);
            response.sendRedirect("/order-confirmation.jsp");
        }
    }
}
