package com.tarea1.product_service.Strategy;

import com.tarea1.product_service.Entidad.ProductEntity;

public class StockUpdateStrategy implements ProductUpdateStrategy{
    @Override
    public void update(ProductEntity product, Object value) {
        if (value instanceof Integer) {
            product.setStock((Integer) value);
        }
    }
}
