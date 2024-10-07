package com.webshop.webshopfinal.handlers;

import com.webshop.webshopfinal.dao.ProductDAO;
import com.webshop.webshopfinal.model.Product;
import com.webshop.webshopfinal.controller.ProductInfo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public class ProductHandler {
    /**
     * Get all products
     * @return Collection<ProductInfo>
     */
    public static Collection<ProductInfo> getProducts() {
        Collection<ProductDAO> products = Product.getProducts();
        return convertToProductInfos(products);
    }

    /**
     * Get products by category
     * @param categoryId
     * @return Collection<ProductInfo>
     */
    public static List<ProductInfo> getProductsByIds(List<Integer> ids) {
        Collection<ProductDAO> products = Product.getProductsByIds(ids);
        return (List<ProductInfo>) convertToProductInfos(products);
    }

    /**
     * Get product by id
     * @param id
     * @return ProductInfo
     */
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

    /**
     * Create product
     * @param filteredParameters
     */
    public static void createProduct(Map<String, String> filteredParameters) {
        Product.createProduct(filteredParameters);
    }

    /**
     * Update product
     * @param id
     * @param filteredParameters
     */
    public static void updateProduct(Integer id, Map<String, String> filteredParameters) {
        Product.updateProduct(id, filteredParameters);
    }

    /**
     * Delete product
     * @param id
     */
    public static void deleteProduct(int id) {
        Product.deleteProduct(id);
    }

    /**
     * Convert Collection<ProductDAO> to Collection<ProductInfo>
     * @param products
     * @return Collection<ProductInfo>
     */
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
