package com.tarea1.product_service.Strategy;

import com.tarea1.product_service.Entidad.ProductEntity;

public interface ProductUpdateStrategy {
    void update(ProductEntity product, Object value);
}
