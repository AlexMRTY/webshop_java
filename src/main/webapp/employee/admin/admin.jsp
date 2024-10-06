<%@ page import="com.webshop.webshopfinal.model.Category" %>
<%@ page import="java.util.List" %>
<%@ page import="com.webshop.webshopfinal.model.Product" %>
<%@ page import="com.webshop.webshopfinal.controller.ProductInfo" %>
<%@ page import="com.webshop.webshopfinal.controller.CategoryInfo" %>
<%@ page import="java.util.Collection" %>
<%@ page import="java.util.HashMap" %><%--
  Created by IntelliJ IDEA.
  User: asifr
  Date: 10/5/2024
  Time: 1:11 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <script src="https://kit.fontawesome.com/f64dca3a29.js" crossorigin="anonymous"></script>
    <script src="https://cdn.tailwindcss.com"></script>
    <title>Title</title>
</head>
<body class="bg-gray-100">
<%
    List<CategoryInfo> categories = (List<CategoryInfo>) request.getAttribute("categories");
    List<ProductInfo> products = (List<ProductInfo>) request.getAttribute("products");
%>
<div class="container mx-auto py-8">
    <h1 class="text-3xl font-bold text-center mb-8">Admin Dashboard - Product Management</h1>

    <div class="grid grid-cols-12 gap-8">
        <!-- Product Management Section -->
        <div class="bg-white p-8 shadow-xl rounded-lg col-span-6">
            <h2 class="text-2xl font-bold mb-6">Manage Products</h2>

            <!-- Add Product Form -->
            <h3 class="text-xl font-semibold mb-4">Add New Product</h3>
            <form action="${pageContext.request.contextPath}/employee/admin/product" method="post">
                <input type="hidden" name="op" value="addProduct">
                <div class="mb-4">
                    <label for="productName" class="block text-gray-700">Product Name</label>
                    <input type="text" id="productName" name="name" class="border rounded w-full p-2" required>
                </div>
                <div class="mb-4">
                    <label for="productPrice" class="block text-gray-700">Product Price</label>
                    <input type="number" id="productPrice" name="price" class="border rounded w-full p-2" required>
                </div>
                <div class="mb-4">
                    <label for="productStock" class="block text-gray-700">Stock Quantity</label>
                    <input type="number" id="productStock" name="stock" class="border rounded w-full p-2" required>
                </div>
                <div class="mb-4">
                    <label for="productCategory" class="block text-gray-700">Category</label>
                    <select id="productCategory" name="categoryId" class="border rounded w-full p-2" required>
                        <%-- Assume categories are passed from the servlet as a list --%>
                        <%
                            for (CategoryInfo category : categories) {
                        %>
                        <option value="<%= category.getId() %>"> <%= category.getName() %></option>
                        <%
                            }
                        %>
                    </select>
                </div>
                <div class="mb-4">
                    <label for="productImage" class="block text-gray-700">Product Image</label>
                    <input type="text" id="productImage" name="image" class="border rounded w-full p-2">
                </div>
                <button type="submit" class="bg-blue-500 text-white px-4 py-2 rounded">Add Product</button>
            </form>

            <!-- Edit Product Form -->
            <h3 class="text-xl font-semibold mb-4">Edit Product</h3>
            <form action="${pageContext.request.contextPath}/employee/admin/product" method="post">
                <input type="hidden" name="op" value="PUT">
                <div class="mb-4">
                    <label for="editProductId" class="block text-gray-700">Select Product to Edit</label>
                    <select id="editProductId" name="id" class="border rounded w-full p-2">
                        <%-- Assume products are passed from the servlet as a list --%>
                        <%

                            for (ProductInfo product : products) {
                        %>
                        <option value="<%= product.getId() %>"><%= product.getName() %></option>
                        <%
                            }
                        %>
                    </select>
                </div>
                <div class="mb-4">
                    <label for="editProductName" class="block text-gray-700">Product Name</label>
                    <input type="text" id="editProductName" name="name" class="border rounded w-full p-2" >
                </div>
                <div class="mb-4">
                    <label for="editProductPrice" class="block text-gray-700">Product Price</label>
                    <input type="number" id="editProductPrice" name="price" class="border rounded w-full p-2" >
                </div>
                <div class="mb-4">
                    <label for="editProductStock" class="block text-gray-700">Stock Quantity</label>
                    <input type="number" id="editProductStock" name="stock" class="border rounded w-full p-2" >
                </div>
                <div class="mb-4">
                    <label for="editProductCategory" class="block text-gray-700">Category</label>
                    <select id="editProductCategory" name="categoryId" class="border rounded w-full p-2" >
                        <%-- Assume categories are passed from the servlet as a list --%>
                        <%
                            for (CategoryInfo category : categories) {
                        %>
                        <option value="<%= category.getId() %>"> <%= category.getName() %></option>
                        <%
                            }
                        %>
                    </select>
                </div>
                <div class="mb-4">
                    <label for="editProductImage" class="block text-gray-700">Product Image</label>
                    <input type="text" id="editProductImage" name="image" class="border rounded w-full p-2">
                </div>
                <button type="submit" class="bg-blue-500 text-white px-4 py-2 rounded">Edit Product</button>
            </form>

            <!-- Remove Product Form -->
            <h3 class="text-xl font-semibold mt-8 mb-4">Remove Product</h3>
            <form action="/employee/admin/product" method="post">
                <input type="hidden" name="op" value="DELETE">
                <div class="mb-4">
                    <label for="removeProductId" class="block text-gray-700">Select Product to Remove</label>
                    <select id="removeProductId" name="id" class="border rounded w-full p-2">
                        <%-- Assume products are passed from the servlet as a list --%>
                        <%

                            for (ProductInfo product : products) {
                        %>
                        <option value="<%= product.getId() %>"><%= product.getName() %></option>
                        <%
                            }
                        %>
                    </select>
                </div>
                <button type="submit" class="bg-red-500 text-white px-4 py-2 rounded">Remove Product</button>
            </form>
        </div>

        <!-- Category Management Section -->
        <div class="bg-white p-8 shadow-xl rounded-lg col-span-6">
            <h2 class="text-2xl font-bold mb-6">Manage Categories</h2>

            <!-- Add Category Form -->
            <h3 class="text-xl font-semibold mb-4">Add New Category</h3>
            <form action="/employee/admin/category" method="post">
                <input type="hidden" name="op" value="addCategory">
                <div class="mb-4">
                    <label for="categoryName" class="block text-gray-700">Category Name</label>
                    <input type="text" id="categoryName" name="name" class="border rounded w-full p-2">
                </div>
                <button type="submit" class="bg-blue-500 text-white px-4 py-2 rounded">Add Category</button>
            </form>

            <!-- Add Category Form -->
            <h3 class="text-xl font-semibold mb-4">Edit Category</h3>
            <form action="/employee/admin/category" method="post">
                <input type="hidden" name="op" value="PUT">
                <div class="mb-4">
                    <label for="editRemoveCategoryId" class="block text-gray-700">Select Category to Remove</label>
                    <select id="editRemoveCategoryId" name="id" class="border rounded w-full p-2">
                        <%-- Assume categories are passed from the servlet as a list --%>
                        <%
                            for (CategoryInfo category : categories) {
                        %>
                        <option value="<%= category.getId() %>"><%= category.getName() %></option>
                        <%
                            }
                        %>
                    </select>
                </div>
                <div class="mb-4">
                    <label for="editCategoryName" class="block text-gray-700">Category Name</label>
                    <input type="text" id="editCategoryName" name="name" class="border rounded w-full p-2">
                </div>
                <button type="submit" class="bg-blue-500 text-white px-4 py-2 rounded">Edit Category</button>
            </form>

            <!-- Remove Category Form -->
            <h3 class="text-xl font-semibold mt-8 mb-4">Remove Category</h3>
            <form action="/employee/admin/category" method="post">
                <input type="hidden" name="op" value="DELETE">
                <div class="mb-4">
                    <label for="removeCategoryId" class="block text-gray-700">Select Category to Remove</label>
                    <select id="removeCategoryId" name="id" class="border rounded w-full p-2">
                        <%-- Assume categories are passed from the servlet as a list --%>
                        <%
                            for (CategoryInfo category : categories) {
                        %>
                        <option value="<%= category.getId() %>"><%= category.getName() %></option>
                        <%
                            }
                        %>
                    </select>
                </div>
                <button type="submit" class="bg-red-500 text-white px-4 py-2 rounded">Remove Category</button>
            </form>
        </div>
    </div>

    <!-- Product Section -->
    <section class="bg-white pt-32 mb-32">

        <div class="grid grid-cols-1 sm:grid-cols-2 md:grid-cols-4 gap-6 pb-40 container mx-auto">
            <%
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
            </div>
            <%
                } // End for loop
            %>
        </div>
    </section>
</div>
</body>
</html>
