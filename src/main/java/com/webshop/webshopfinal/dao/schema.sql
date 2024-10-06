use webshop;

DROP TABLE IF EXISTS `order_items`;
DROP TABLE IF EXISTS `orders`;
DROP TABLE IF EXISTS `categories`;
DROP TABLE IF EXISTS `products`;
DROP TABLE IF EXISTS `users`;

CREATE TABLE `users` (
  `id` int PRIMARY KEY AUTO_INCREMENT,
  `first_name` varchar(100),
  `last_name` varchar(100),
  `username` varchar(100) UNIQUE,
  `email` varchar(100) UNIQUE,
  `password` varchar(255),
  `role` ENUM ('ADMIN', 'WORKER', 'CUSTOMER'),
  `created_at` TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  `updated_at` TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

CREATE TABLE `categories` (
    `category_id` int PRIMARY KEY AUTO_INCREMENT,
    `category_name` varchar(100) UNIQUE NOT NULL,
    `created_at` TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    `updated_at` TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

CREATE TABLE `products` (
    `product_id` int PRIMARY KEY AUTO_INCREMENT,
    `name` varchar(100),
    `brand` varchar(100),
    `description` text,
    `price` decimal(10,2),
    `image` varchar(200),
    `rating` int,
    `stock` int,
    `category_id` int, -- Foreign key reference to categories
    `created_at` TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    `updated_at` TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (`category_id`) REFERENCES `categories`(`category_id`) ON DELETE CASCADE -- Establish relationship with categories table
);

CREATE TABLE `orders` (
  `id` int PRIMARY KEY AUTO_INCREMENT,
  `user_id` int,
  `total_amount` decimal(10,2),
  `status` ENUM ('PLACED', 'SHIPPED'),
  `created_at` TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  `updated_at` TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

CREATE TABLE `order_items` (
  `order_item_id` int PRIMARY KEY AUTO_INCREMENT,
  `order_id` int,
  `product_id` int,
  `quantity` int,
  `price` decimal(10,2),
  `created_at` TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  `updated_at` TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

ALTER TABLE `orders` ADD FOREIGN KEY (`user_id`) REFERENCES `users` (`id`);

ALTER TABLE `order_items` ADD FOREIGN KEY (`order_id`) REFERENCES `orders` (`id`);

ALTER TABLE `order_items` ADD FOREIGN KEY (`product_id`) REFERENCES `products` (`product_id`);


# Inserting data into the tables

# Categories
INSERT INTO webshop.categories (category_id, category_name, created_at, updated_at) VALUES (1, 'Cool T-Shirt', '2024-10-06 16:54:43', '2024-10-06 21:51:40');
INSERT INTO webshop.categories (category_id, category_name, created_at, updated_at) VALUES (3, 'Summer T-shirt', '2024-10-06 16:55:12', '2024-10-06 21:16:49');
INSERT INTO webshop.categories (category_id, category_name, created_at, updated_at) VALUES (4, 'Dress', '2024-10-06 20:57:33', '2024-10-06 20:57:33');
INSERT INTO webshop.categories (category_id, category_name, created_at, updated_at) VALUES (5, 'Pants', '2024-10-06 21:51:57', '2024-10-06 21:51:57');
INSERT INTO webshop.categories (category_id, category_name, created_at, updated_at) VALUES (6, 'Woman Shirt', '2024-10-06 21:52:52', '2024-10-06 21:52:52');
INSERT INTO webshop.categories (category_id, category_name, created_at, updated_at) VALUES (7, 'Boring Shirt', '2024-10-06 21:54:13', '2024-10-06 21:54:13');


# Products
INSERT INTO webshop.products (product_id, name, brand, description, price, image, rating, stock, created_at, updated_at, category_id) VALUES (1, 'Cartoon Astronaut T-Shirts', '2 peace', null, 78.00, 'https://res.cloudinary.com/dwcbhc3rp/image/upload/v1728043326/f5_wzdy3e.jpg', 4, 10, '2024-10-04 20:06:14', '2024-10-06 22:01:47', 1);
INSERT INTO webshop.products (product_id, name, brand, description, price, image, rating, stock, created_at, updated_at, category_id) VALUES (3, 'Hawaiian Shirt', 'adidas', null, 78.00, 'https://res.cloudinary.com/dwcbhc3rp/image/upload/v1728043325/f1_xdivo2.jpg', 4, 8, '2024-10-04 20:06:14', '2024-10-06 21:47:47', 3);
INSERT INTO webshop.products (product_id, name, brand, description, price, image, rating, stock, created_at, updated_at, category_id) VALUES (7, 'Split Shirt', 'adidas', null, 100.00, 'https://res.cloudinary.com/dwcbhc3rp/image/upload/v1728043326/f6_msmhje.jpg', 3, 14, '2024-10-06 20:45:58', '2024-10-06 22:02:38', null);
INSERT INTO webshop.products (product_id, name, brand, description, price, image, rating, stock, created_at, updated_at, category_id) VALUES (8, 'Modern Dress', 'L\'EVIS', null, 120.00, 'https://res.cloudinary.com/dwcbhc3rp/image/upload/v1728043326/f8_rk7rrg.jpg', 5, 12, '2024-10-06 20:49:11', '2024-10-06 22:03:14', null);
INSERT INTO webshop.products (product_id, name, brand, description, price, image, rating, stock, created_at, updated_at, category_id) VALUES (9, 'Fancy Pants', 'Nike', null, 200.00, 'https://res.cloudinary.com/dwcbhc3rp/image/upload/v1728043326/n6_iatlxi.jpg', 1, 5, '2024-10-06 21:52:18', '2024-10-06 22:04:08', null);
INSERT INTO webshop.products (product_id, name, brand, description, price, image, rating, stock, created_at, updated_at, category_id) VALUES (10, 'Boring Woman Shirt', 'abibas', null, 20.00, 'https://res.cloudinary.com/dwcbhc3rp/image/upload/v1728043325/n2_jjvfrf.jpg', 5, 13, '2024-10-06 21:53:20', '2024-10-06 22:04:48', null);
INSERT INTO webshop.products (product_id, name, brand, description, price, image, rating, stock, created_at, updated_at, category_id) VALUES (11, 'Boring Man Shirt', 'abidas', null, 20.00, 'https://res.cloudinary.com/dwcbhc3rp/image/upload/v1728043326/n5_dzbuwg.jpg', 2, 13, '2024-10-06 21:54:30', '2024-10-06 22:05:25', null);
INSERT INTO webshop.products (product_id, name, brand, description, price, image, rating, stock, created_at, updated_at, category_id) VALUES (12, '?', 'Sexy', null, 1000.00, 'https://res.cloudinary.com/dwcbhc3rp/image/upload/v1727984537/samples/outdoor-woman.jpg', 5, 1, '2024-10-06 21:55:40', '2024-10-06 22:06:22', null);


# Users
INSERT INTO webshop.users (id, first_name, last_name, username, email, password, role, created_at, updated_at) VALUES (1, 'John', 'Doe', 'johndoe', 'john@gmail.com', 'password', null, '2024-10-03 21:38:24', '2024-10-03 21:38:24');
INSERT INTO webshop.users (id, first_name, last_name, username, email, password, role, created_at, updated_at) VALUES (2, 'james', 'heller', 'testUser', 'jhoe@gmail.com', 'pass', 'ADMIN', '2024-10-04 09:54:51', '2024-10-04 09:54:51');
INSERT INTO webshop.users (id, first_name, last_name, username, email, password, role, created_at, updated_at) VALUES (3, 'Shiro', 'Shiro', 'Zubi', 'Zubi@ass.com', 'pass', 'CUSTOMER', '2024-10-06 11:51:54', '2024-10-06 11:51:54');
INSERT INTO webshop.users (id, first_name, last_name, username, email, password, role, created_at, updated_at) VALUES (4, 'Arafat', 'Bossom', 'arafat', 'theythem@woke.com', 'thatsoffensive', 'WORKER', '2024-10-06 11:54:43', '2024-10-06 11:54:43');


