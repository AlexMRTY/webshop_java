package com.webshop.webshopfinal.handlers;

import com.webshop.webshopfinal.dao.ProductDAO;
import com.webshop.webshopfinal.model.Product;
import com.webshop.webshopfinal.controller.ProductInfo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public class ProductHandler {
    public static Collection<ProductInfo> getProducts() {
        Collection<ProductDAO> products = Product.getProducts();
        return convertToProductInfos(products);
    }

    public static List<ProductInfo> getProductsByIds(List<Integer> ids) {
        Collection<ProductDAO> products = Product.getProductsByIds(ids);
        return (List<ProductInfo>) convertToProductInfos(products);
    }

    public static ProductInfo getProduct(int id) {
        ProductDAO product = Product.getProduct(id);
        return new ProductInfo(
                product.getId(),
                product.getName(),
                product.getBrand(),
                product.getDescription(),
                product.getPrice(),
                product.getImage(),
                product.getRating(),
                product.getStock()
        );
    }

    public static void createProduct(Map<String, String> filteredParameters) {
        Product.createProduct(filteredParameters);
    }

    public static void updateProduct(Integer id, Map<String, String> filteredParameters) {
        Product.updateProduct(id, filteredParameters);
    }

    public static void deleteProduct(int id) {
        Product.deleteProduct(id);
    }

    private static Collection<ProductInfo> convertToProductInfos(Collection<ProductDAO> products) {
        ArrayList<ProductInfo> productInfos = new ArrayList<ProductInfo>();
        for (ProductDAO product : products) {
            productInfos.add(new ProductInfo(
                product.getId(),
                product.getName(),
                product.getBrand(),
                product.getDescription(),
                product.getPrice(),
                product.getImage(),
                product.getRating(),
                product.getStock()
            ));
        }
        return productInfos;
    }




}
