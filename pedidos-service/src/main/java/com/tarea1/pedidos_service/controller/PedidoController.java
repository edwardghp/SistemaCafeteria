package com.tarea1.pedidos_service.controller;

import com.tarea1.pedidos_service.PedidoService;
import com.tarea1.pedidos_service.model.Pedido;
import com.tarea1.pedidos_service.model.PedidoEstado;
import com.tarea1.pedidos_service.model.PedidoResponse;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/pedidos")
public class PedidoController {
    private final PedidoService service;

    public PedidoController(PedidoService service) {
        this.service = service;
    }

    @PostMapping
    public PedidoResponse crearPedido(@RequestParam Long clienteId, @RequestParam String productoId) {
        return service.crearPedido(clienteId, productoId);
    }

    @PutMapping("/{id}/estado")
    public Pedido cambiarEstado(@PathVariable Long id, @RequestParam PedidoEstado nuevoEstado) {
        return service.cambiarEstado(id, nuevoEstado);
    }
}
