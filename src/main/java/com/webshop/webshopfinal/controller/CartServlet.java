package com.webshop.webshopfinal.controller;

import com.webshop.webshopfinal.handlers.ProductHandler;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@WebServlet("/home/me/cart")
public class CartServlet extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        // Check if cart cookie exists
        Cookie[] cookies = request.getCookies();
        Cookie cartCookie = null;
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("cart")) {
                cartCookie = cookie;
                break;
            }
        }
        // If not, create a new cart cookie
        if (cartCookie == null) {
            // Create a new cart cookie
            cartCookie = new Cookie("cart", "");
            cartCookie.setPath("/");
        }
        // Get product id from request, and check if product is available in stock.
        String productId = request.getParameter("addToCart");
        // Count number of product with this productId in cart
        int nrOfProductInCart = 0;
        if (cartCookie.getValue().contains(productId)) {
            String[] productIds = cartCookie.getValue().split("\\|");
            for (String id : productIds) {
                if (id.equals(productId)) {
                    nrOfProductInCart++;
                }
            }
        }
        ProductInfo product = ProductHandler.getProduct(Integer.parseInt(productId));
        if (product.getStock() == 0 || nrOfProductInCart >= product.getStock()) {
            // Redirect to appropriate page
            String origin = request.getHeader("referer");
            HashMap<String, Boolean> inStock = new HashMap<>();
            inStock.put(String.valueOf(product.getId()), false);
            request.setAttribute("inStock", inStock);
            if (origin.equals("http://localhost:8080/home")) {
                // Redirect to home.jsp
                request.getRequestDispatcher(request.getContextPath() + "/home").forward(request, response);
                return;
            }
            if (origin.equals("http://localhost:8080/home/me/cart")) {
                // Redirect to cart.jsp
                doGet(request, response);
            }
            return;
        }

        // Add product to cart
        String cart = cartCookie.getValue();
        cart += productId + "|";
        cartCookie.setValue(cart);
        cartCookie.setPath("/");

        // Add cart cookie to response
        response.addCookie(cartCookie);

        // Redirect to appropriate page
        String origin = request.getHeader("referer");
        if (origin.equals("http://localhost:8080/home")) {
            // Redirect to home.jsp
            response.sendRedirect("/home");
        }
        if (origin.equals("http://localhost:8080/home/me/cart")) {
            // Redirect to cart.jsp
            doGet(request, response);
        }
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        // Product Array to return
        List<ProductInfo> products = new ArrayList<ProductInfo>();

        // Find Cart Cookie
        Cookie[] cookies = request.getCookies();
        Cookie cartCookie = null;
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("cart")) {
                cartCookie = cookie;
                break;
            }
        }
        // If cart is empty, return nothing.
        if (cartCookie == null) {
            request.getSession().setAttribute("cartItems", products);
            request.getRequestDispatcher("/cart.jsp").forward(request, response);
        }
        // If cart is not empty, return products in cart.
        else {
            String cart = cartCookie.getValue();
            if (cart.isEmpty()) {
                request.getSession().setAttribute("cartItems", products);
                request.getRequestDispatcher("/cart.jsp").forward(request, response);
            } else {
                String[] productIds = cart.split("\\|");
                List<Integer> productIdsList = new ArrayList<>();
                for (String productId : productIds) {
                    productIdsList.add(Integer.parseInt(productId));
                }
                products = ProductHandler.getProductsByIds(productIdsList);
                request.getSession().setAttribute("cartItems", products);
                request.getRequestDispatcher("/cart.jsp").forward(request, response);
            }
        }
    }
}
