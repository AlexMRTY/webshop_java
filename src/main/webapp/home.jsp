
<%@ page import="java.io.PrintWriter" %>
<%@ page import="java.util.Collection" %>
<%@ page import="com.webshop.webshopfinal.controller.ProductInfo" %>
<%@ page import="java.util.Iterator" %>
<%@ page import="java.util.HashMap" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <script src="https://kit.fontawesome.com/f64dca3a29.js" crossorigin="anonymous"></script>
    <script src="https://cdn.tailwindcss.com"></script>

    <script>
        window.onload = function() {
            if (sessionStorage.getItem("scrollPosition")) {
                window.scrollTo(0, sessionStorage.getItem("scrollPosition"));
            }
        };

        window.onbeforeunload = function() {
            sessionStorage.setItem("scrollPosition", window.scrollY);
        };
    </script>

</head>
<body class="bg-[#e3e6f3]">

    <%
        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
        response.setHeader("Pragma", "no-cache");
        response.setDateHeader("Expires", 0);

        String testVar = "Hello World";
        Boolean isLoggedIn = false;
        if(request.getSession().getAttribute("user") != null){
            isLoggedIn = true;
        }


    %>

    <Section class="h-[70%] bg-no-repeat bg-cover container mx-auto" style="background-image: url('https://res.cloudinary.com/dwcbhc3rp/image/upload/v1727984945/hero4_bcjpsm.png');">
<%--    <div class="absolute inset-0 z-0 ">--%>
<%--        <img src="" alt="Woman in jacket" class="w-full h-full object-cover object-right" />--%>
<%--    </div>--%>
        <!-- Navigation -->
        <nav class="flex justify-between items-center font-bold py-4 px-8">
            <div class="text-3xl font-bold text-gray-800">
                Cara
            </div>
            <ul class="flex space-x-6 text-gray-700">
                <li><a href="#" class="hover:text-teal-500">Home</a></li>
                <li><a href="#" class="hover:text-teal-500">Shop</a></li>
                <li><a href="#" class="hover:text-teal-500">Blog</a></li>
                <li><a href="#" class="hover:text-teal-500">About</a></li>
                <li><a href="#" class="hover:text-teal-500">Contact</a></li>
            </ul>

            <div class="flex flex-row gap-8 items-center">
                <div class="inline-block">
                    <form action="/home/me/cart" method="get">
                        <Button>
                            <i class="fa-solid fa-cart-shopping text-2xl text-gray-800"></i>
                        </Button>
                    </form>
                </div>
                <form action="logout" method="post" class="m-0">
                    <Button class="text-white bg-gray-800 hover:bg-gray-900 focus:outline-none focus:ring-4 focus:ring-gray-300 font-medium rounded-full text-sm px-5 py-2.5 dark:bg-gray-800 dark:hover:bg-gray-700 dark:focus:ring-gray-700 dark:border-gray-700">
    <%--                    <%--%>
    <%--                        if(isLoggedIn){--%>
    <%--                            response.getWriter().print("<span>Logout</span>");--%>
    <%--                        } else {--%>
    <%--                            response.getWriter().print("<span>Login</span>");--%>
    <%--                        }--%>
    <%--                    %>--%>
                        <%= isLoggedIn ? "Logout" : "Login" %>
                    </Button>
                </form>
                <% if (isLoggedIn) { %>
                    <!-- User Avatar Circle with First Letter -->
                    <div class="flex items-center justify-center w-10 h-10 bg-gray-200 text-gray-700 border-solid border-2 border-black rounded-full">
                        <span class="text-xl font-bold">
                            <%=
                                isLoggedIn ? request.getSession().getAttribute("user").toString().substring(0, 1).toUpperCase() : ""
                            %>
                        </span>
                    </div>
                <% } %>
            </div>
        </nav>

        <!-- Hero Section -->
        <section class="flex items-center justify-between px-8 py-12 h-full">
            <div class="space-y-4 max-w-lg">
                <h1 class="text-6xl font-bold text-gray-800 leading-tight">
                    Super value deals <br>
                    <span class="text-teal-500">On all products</span>
                </h1>
                <p class="text-gray-500">
                    Save more with coupons &amp; up to 70% off!
                </p>
                <button class="text-white bg-gray-800 hover:bg-gray-900 focus:outline-none focus:ring-4 focus:ring-gray-300 font-medium rounded-full text-base px-5 py-2.5 dark:bg-gray-800 dark:hover:bg-gray-700 dark:focus:ring-gray-700 dark:border-gray-700">
                    Shop Now
                </button>
            </div>
        </section>
    </Section>
    <!-- Product Section -->
    <section class="bg-white pt-32 mb-32">
        <div class="text-center mb-20">
            <h2 class="text-6xl font-bold my-5">Featured Products</h2>
            <p class="text-2xl text-gray-500 my-5">Summer Collection New Modern Design</p>
        </div>

        <div class="grid grid-cols-1 sm:grid-cols-2 md:grid-cols-4 gap-6 pb-40 container mx-auto">
            <%
                Collection<ProductInfo> products = (Collection<ProductInfo>) request.getAttribute("products");
                for (ProductInfo product : products) {
                    String id = String.valueOf(product.getId());
                    String name = product.getName();
                    String brand = product.getBrand();
                    String price = String.valueOf(product.getPrice());
                    String imageUrl = product.getImage();
                    String rating = String.valueOf(product.getRating());
                    String stock = String.valueOf(product.getStock());
//
            %>

            <!-- Product Card -->
            <div class="bg-white relative border border-gray-200 rounded-3xl p-4 pb-10 shadow-md hover:shadow-lg hover:scale-105 hover:z-10 transition-shadow duration-500">
                <div class="flex justify-center mb-4">
                    <img src=<%= imageUrl %> alt="<%= name %>" class="w-full h-auto rounded-3xl"> <!-- Add image source -->
                </div>
                <div class="text-center">
                    <p class="text-gray-400 text-sm"><%= brand %></p>
                    <h3 class="text-lg font-semibold text-gray-800"><%= name %></h3>
                    <p class="text-gray-400 text-sm mb-4">
                        <i class="
                        fa-solid
                        fa-warehouse
                        <%= Integer.parseInt(stock) > 0 ? "text-green-500" : "text-red-500" %>
                        "></i>
                        <%= stock %>
                    </p>
                    <%
                        HashMap<String, Boolean> inStockAttr = (HashMap<String, Boolean>) request.getAttribute("inStock");
                        if (inStockAttr != null && inStockAttr.containsKey(id) && !inStockAttr.get(id)) {
                    %>
                        <p class="text-red-500">Out of stock</p>
                    <%
                        }
                    %>
                    <!-- Rating stars -->
                    <div class="flex justify-center items-center space-x-1 text-yellow-500 mb-2">
                        <% for (int j = 0; j < Math.floor(Double.parseDouble(rating)); j++) { %>
                        <i class="fa-solid fa-star"></i><!-- Solid star -->
                        <% } %>
                        <% if (Double.parseDouble(rating) % 1 != 0) { %>
                        <i class="fa-solid fa-star-half"></i> <!-- Half star -->
                        <% } %>
                    </div>
                    <p class="text-lg font-bold text-gray-800">$<%= price %></p>
                </div>
                <!-- Cart button -->

                <div class="absolute bottom-0 right-4 mt-4">
                    <form action="/home/me/cart" method="post">
                        <button name="addToCart" type="submit" class="bg-green-100 text-green-600 rounded-full px-3 py-2 hover:bg-green-200" value=<%= id %>>
                            <i class="fa-solid fa-plus text-xl"></i>
                            <i class="fas fa-shopping-cart text-xl"></i> <!-- Add FontAwesome for icon or replace -->
                        </button>
                    </form>
                </div>
            </div>
            <%
                } // End for loop
            %>
        </div>
    </section>

</body>
</html>
