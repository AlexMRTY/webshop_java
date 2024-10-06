package com.webshop.webshopfinal.controller;

public class ProductInfo {
    private int id;
    private String name;
    private String brand;
    private String description;
    private double price;
    private String image;
    private double rating;
    private int stock;

    public ProductInfo(int id, String name, String brand, String description, double price, String image, double rating, int stock) {
        this.id = id;
        this.name = name;
        this.brand = brand;
        this.description = description;
        this.price = price;
        this.image = image;
        this.rating = rating;
        this.stock = stock;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getBrand() {
        return brand;
    }

    public String getDescription() {
        return description;
    }

    public double getPrice() {
        return price;
    }

    public String getImage() {
        return image;
    }

    public double getRating() {
        return rating;
    }

    public int getStock() {
        return stock;
    }
}
