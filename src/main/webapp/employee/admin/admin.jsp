<%@ page import="com.webshop.webshopfinal.model.Category" %>
<%@ page import="java.util.List" %>
<%@ page import="com.webshop.webshopfinal.model.Product" %><%--
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
    List<Category> categories = (List<Category>) request.getAttribute("categories");
    List<Product> products = (List<Product>) request.getAttribute("products");
%>
<div class="container mx-auto py-8">
    <h1 class="text-3xl font-bold text-center mb-8">Admin Dashboard - Product Management</h1>

    <div class="grid grid-cols-12 gap-8">
        <!-- Product Management Section -->
        <div class="bg-white p-8 shadow-xl rounded-lg col-span-6">
            <h2 class="text-2xl font-bold mb-6">Manage Products</h2>

            <!-- Add Product Form -->
            <h3 class="text-xl font-semibold mb-4">Add New Product</h3>
            <form action="adminProductServlet" method="post" enctype="multipart/form-data">
                <input type="hidden" name="action" value="addProduct">
                <div class="mb-4">
                    <label for="productName" class="block text-gray-700">Product Name</label>
                    <input type="text" id="productName" name="productName" class="border rounded w-full p-2">
                </div>
                <div class="mb-4">
                    <label for="productPrice" class="block text-gray-700">Product Price</label>
                    <input type="number" id="productPrice" name="productPrice" class="border rounded w-full p-2">
                </div>
                <div class="mb-4">
                    <label for="productStock" class="block text-gray-700">Stock Quantity</label>
                    <input type="number" id="productStock" name="productStock" class="border rounded w-full p-2">
                </div>
                <div class="mb-4">
                    <label for="productCategory" class="block text-gray-700">Category</label>
                    <select id="productCategory" name="productCategory" class="border rounded w-full p-2">
                        <%-- Assume categories are passed from the servlet as a list --%>
                        <%
                            for (Category category : categories) {
                        %>
                        <option value="<%= category.getId() %>"><%= category.getName() %></option>
                        <%
                            }
                        %>
                    </select>
                </div>
                <div class="mb-4">
                    <label for="productImage" class="block text-gray-700">Product Image</label>
                    <input type="file" id="productImage" name="productImage" class="border rounded w-full p-2">
                </div>
                <button type="submit" class="bg-blue-500 text-white px-4 py-2 rounded">Add Product</button>
            </form>

            <!-- Remove Product Form -->
            <h3 class="text-xl font-semibold mt-8 mb-4">Remove Product</h3>
            <form action="adminProductServlet" method="post">
                <input type="hidden" name="action" value="removeProduct">
                <div class="mb-4">
                    <label for="removeProductId" class="block text-gray-700">Select Product to Remove</label>
                    <select id="removeProductId" name="productId" class="border rounded w-full p-2">
                        <%-- Assume products are passed from the servlet as a list --%>
                        <%

                            for (Product product : products) {
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
            <form action="adminCategoryServlet" method="post">
                <input type="hidden" name="action" value="addCategory">
                <div class="mb-4">
                    <label for="categoryName" class="block text-gray-700">Category Name</label>
                    <input type="text" id="categoryName" name="categoryName" class="border rounded w-full p-2">
                </div>
                <button type="submit" class="bg-blue-500 text-white px-4 py-2 rounded">Add Category</button>
            </form>

            <!-- Remove Category Form -->
            <h3 class="text-xl font-semibold mt-8 mb-4">Remove Category</h3>
            <form action="adminCategoryServlet" method="post">
                <input type="hidden" name="action" value="removeCategory">
                <div class="mb-4">
                    <label for="removeCategoryId" class="block text-gray-700">Select Category to Remove</label>
                    <select id="removeCategoryId" name="categoryId" class="border rounded w-full p-2">
                        <%-- Assume categories are passed from the servlet as a list --%>
                        <%
                            for (Category category : categories) {
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
</div>
</body>
</html>
