package com.tarea1.pedidos_service.controller;

import com.tarea1.pedidos_service.PedidoService;
import com.tarea1.pedidos_service.builder.PedidoResponseBuilder;
import com.tarea1.pedidos_service.client.ClienteClient;
import com.tarea1.pedidos_service.client.ProductoClient;
import com.tarea1.pedidos_service.model.Pedido;
import com.tarea1.pedidos_service.model.PedidoEstado;
import com.tarea1.pedidos_service.model.PedidoResponse;
import com.tarea1.pedidos_service.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/pedidos")
public class PedidoController {
    private final PedidoService service;
    @Autowired
    private PedidoRepository pedidoRepository;

    public PedidoController(PedidoService service) {
        this.service = service;
    }


    @PostMapping
    public PedidoResponse crearPedido(@RequestParam Long clienteId, @RequestParam List<String> productosId) {
        return service.crearPedido(clienteId, productosId);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pedido> getPedidoById(@PathVariable String id) {
        return ResponseEntity.of(service.obtenerPedido(id));
    }

    @PutMapping("/{id}/cancelar")
    public Pedido cancelarPedido(@PathVariable String id) {
        return service.cambiarEstado(id, PedidoEstado.CANCELADO);
    }
}
