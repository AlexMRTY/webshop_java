package com.webshop.webshopfinal.handlers;

import com.webshop.webshopfinal.dao.ProductDAO;
import com.webshop.webshopfinal.model.Product;
import com.webshop.webshopfinal.controller.ProductInfo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ProductHandler {
    public static Collection<ProductInfo> getProducts() {
        Collection<ProductDAO> products = Product.getProducts();
        return getProductInfos(products);
    }

    public static Collection<ProductInfo> getProductsByIds(List<Integer> ids) {
        Collection<ProductDAO> products = Product.getProductsByIds(ids);
        return getProductInfos(products);
    }

    private static Collection<ProductInfo> getProductInfos(Collection<ProductDAO> products) {
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
}
