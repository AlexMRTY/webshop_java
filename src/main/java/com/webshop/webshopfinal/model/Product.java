package com.webshop.webshopfinal.model;



import com.webshop.webshopfinal.dao.ProductDAO;

import java.util.Collection;
import java.util.List;

public class Product {
    private int id;
    private String name;
    private String brand;
    private String description;
    private double price;
    private String image;
    private int rating;
    private int stock;

    protected Product(int id, String name, String brand, double price, int stock, String description, int rating, String image) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.description = description;
        this.image = image;
        this.brand = brand;
        this.rating = rating;
    }

    static public ProductDAO getProduct(int id) {
        return ProductDAO.getProduct(id);
    }

    static public Collection<ProductDAO> getProducts() {
        return ProductDAO.getProducts();
    }

    static public Collection<ProductDAO> getProductsByIds(List<Integer> ids) {
        return ProductDAO.getProductsByIds(ids);
    }

    public int getId() {
        return id;
    }

    public String getBrand() {
        return brand;
    }

    public int getRating() {
        return rating;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getStock() {
        return stock;
    }

    public String getDescription() {
        return description;
    }

    public String getImage() {
        return image;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
