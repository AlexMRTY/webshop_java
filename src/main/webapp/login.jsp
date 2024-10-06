<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
    <script src="https://cdn.tailwindcss.com"></script>
</head>
<body class="bg-[#deebfe] bg-no-repeat bg-right bg-[length:60%] flex items-center min-h-screen px-[20%]" style="background-image: url('https://res.cloudinary.com/dwcbhc3rp/image/upload/v1728049777/a6_bzuavy.jpg');">

<div class="bg-white p-16 rounded-3xl shadow-lg w-full max-w-md">
    <h2 class="text-4xl font-bold mb-6 text-center">Log in</h2>
    <form action="login" method="post">
        <div class="mb-4">
            <label for="username" class="block text-sm font-medium text-gray-700">Username</label>
            <input type="text" id="username" name="username" class="w-full px-3 py-2 border border-gray-300 rounded-md focus:outline-none focus:ring focus:border-blue-300" required>
        </div>
        <div class="mb-6">
            <label for="password" class="block text-sm font-medium text-gray-700">Password</label>
            <input type="password" id="password" name="password" class="w-full px-3 py-2 border border-gray-300 rounded-md focus:outline-none focus:ring focus:border-blue-300" required>
        </div>
        <button type="submit" class="w-full text-white bg-gray-800 hover:bg-gray-900 focus:outline-none focus:ring-4 focus:ring-gray-300 font-medium rounded-full text-sm px-5 py-2.5">Log in</button>
    </form>
    <div class="mt-4 text-center">
        <a href="signup.jsp" class="text-sm text-gray-500 hover:text-teal-500">or, sign up</a>
    </div>
</div>
</body>
</html>