package com.tarea1.pedidos_service.client;

import com.tarea1.pedidos_service.dto.Cliente;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "cliente-service")
public interface ClienteClient {
    @GetMapping("/clientes/{id}")
    Cliente obtenerCliente(@PathVariable("id") Long id);
}
