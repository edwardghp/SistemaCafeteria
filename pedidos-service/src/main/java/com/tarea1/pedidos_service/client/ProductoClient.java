package com.tarea1.pedidos_service.client;

import com.tarea1.pedidos_service.dto.Producto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "product-service")
public interface ProductoClient {
    @GetMapping("/api/products/{id}")
    Producto obtenerProducto(@PathVariable("id") String id);
}
