INSERT INTO `users` (first_name, last_name, `username`, `password`, `email`, `role`)
VALUES
    ('james', 'heller', 'testUser', 'pass', 'jhoe@gmail.com', 'admin');




# String[][] products = {
#                         {"Cartoon Astronaut T-Shirts", "adidas", "78", "https://res.cloudinary.com/dwcbhc3rp/image/upload/v1728043326/f5_wzdy3e.jpg", "5", "20"},
#                         {"Floral Summer Shirt","adidas", "78","https://res.cloudinary.com/dwcbhc3rp/image/upload/v1728043325/f2_op17xy.jpg", "4", "17"},
#                         {"Hawaiian Shirt", "adidas", "78", "https://res.cloudinary.com/dwcbhc3rp/image/upload/v1728043325/f1_xdivo2.jpg", "4", "5"},
#                         {"Classic White Shirt", "adidas", "78","https://res.cloudinary.com/dwcbhc3rp/image/upload/v1728043325/f3_pjwtfb.jpg", "4", "16"},
#                         {"Classic White Shirt", "adidas", "78","https://res.cloudinary.com/dwcbhc3rp/image/upload/v1728043325/f3_pjwtfb.jpg", "4", "16"},
#                         {"Classic White Shirt", "adidas", "78","https://res.cloudinary.com/dwcbhc3rp/image/upload/v1728043325/f3_pjwtfb.jpg", "4", "16"},
#                 };

INSERT INTO `products` (name, brand, price, image, rating, stock)
VALUES
    ('Cartoon Astronaut T-Shirts', 'adidas', '78', 'https://res.cloudinary.com/dwcbhc3rp/image/upload/v1728043326/f5_wzdy3e.jpg', '5', '20'),
    ('Floral Summer Shirt', 'adidas', '78', 'https://res.cloudinary.com/dwcbhc3rp/image/upload/v1728043325/f2_op17xy.jpg', '4', '17'),
    ('Hawaiian Shirt', 'adidas', '78', 'https://res.cloudinary.com/dwcbhc3rp/image/upload/v1728043325/f1_xdivo2.jpg', '4', '5'),
    ('Classic White Shirt', 'adidas', '78', 'https://res.cloudinary.com/dwcbhc3rp/image/upload/v1728043325/f3_pjwtfb.jpg', '4', '16'),
    ('Classic White Shirt', 'adidas', '78', 'https://res.cloudinary.com/dwcbhc3rp/image/upload/v1728043325/f3_pjwtfb.jpg', '4', '16'),
    ('Classic White Shirt', 'adidas', '78', 'https://res.cloudinary.com/dwcbhc3rp/image/upload/v1728043325/f3_pjwtfb.jpg', '4', '16');

-- Create categories table
CREATE TABLE `categories` (
                              `category_id` int PRIMARY KEY AUTO_INCREMENT,
                              `category_name` varchar(100) UNIQUE NOT NULL,
                              `created_at` TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                              `updated_at` TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

ALTER TABLE `products` ADD COLUMN `category_id` int;
ALTER TABLE `products` ADD FOREIGN KEY (`category_id`) REFERENCES `categories`(`category_id`) ON DELETE CASCADE;

-- Create products table
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


UPDATE products SET stock = stock - 13 WHERE product_id = 1 AND stock >= 13;

INSERT INTO categories (category_name) VALUES ('T-shirt-type3')

UPDATE products SET products.category_id = 3 WHERE product_id = 3


