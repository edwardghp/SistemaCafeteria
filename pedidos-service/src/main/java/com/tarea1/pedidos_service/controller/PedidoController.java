package com.tarea1.pedidos_service.controller;

import com.tarea1.pedidos_service.PedidoService;
import com.tarea1.pedidos_service.model.Pedido;
import com.tarea1.pedidos_service.model.PedidoEstado;
import com.tarea1.pedidos_service.model.PedidoResponse;
import com.tarea1.pedidos_service.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/{id}")
    public ResponseEntity<Pedido> getPedidoById(@PathVariable Long id) {
        return pedidoRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
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
