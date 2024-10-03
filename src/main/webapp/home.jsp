
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <script src="https://kit.fontawesome.com/f64dca3a29.js" crossorigin="anonymous"></script>
    <script src="https://cdn.tailwindcss.com"></script>
</head>
<body class="bg-[#e3e6f3]">

<Section class="h-screen bg-no-repeat bg-contain container mx-auto" style="background-image: url('https://res.cloudinary.com/dwcbhc3rp/image/upload/v1727984945/hero4_bcjpsm.png');">
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

        <div>
            <div class="inline-block">
                <a href="#" class="text-gray-600">
                    <!-- Icon for cart (replace with an icon library if needed) -->
                    <i class="fa-solid fa-cart-shopping text-xl"></i>
                </a>
            </div>
            <button class="px-6 py-2 bg-[#5c64f4] text-white font-semibold rounded hover:bg-yellow-400 transition">
                Login
            </button>
        </div>

    </nav>

    <!-- Hero Section -->
    <section class="flex items-center justify-between px-8 py-12 h-full">
        <div class="space-y-4 max-w-lg">
            <h3 class="text-gray-500">Trade-in-offer</h3>
            <h1 class="text-5xl font-bold text-gray-800 leading-tight">
                Super value deals <br>
                <span class="text-teal-500">On all products</span>
            </h1>
            <p class="text-gray-500">
                Save more with coupons &amp; up to 70% off!
            </p>
            <button class="px-6 py-3 bg-yellow-300 text-gray-700 font-semibold rounded hover:bg-yellow-400 transition">
                Shop Now
            </button>
        </div>
    </section>
</Section>

</body>
</html>
