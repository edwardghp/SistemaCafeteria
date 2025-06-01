package com.tarea1.pagos_service.client;

import com.tarea1.pagos_service.dto.Pedido;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "pedidos-service")
public interface PedidoClient {

    @PatchMapping("/pedidos/{id}/estado")
    Pedido actualizarEstado(@PathVariable("id") String id, @RequestParam("estado") String estado);
}
