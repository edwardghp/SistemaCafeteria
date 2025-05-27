package com.tarea1.product_service.Factory;

import com.tarea1.product_service.Entidad.ProductEntity;

public class ProductFactory {
    public static ProductEntity createProduct(String name, String description, Double price, int stock, String id) {
        ProductEntity product = new ProductEntity();
        product.setProductName(name);
        product.setProductDescription(description);
        product.setUnitPrice(price);
        product.setStock(stock);
        product.setId(id);
        return product;
    }
}
