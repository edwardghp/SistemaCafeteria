package com.tarea1.pedidos_service;

import com.tarea1.pedidos_service.model.Pedido;
import com.tarea1.pedidos_service.model.PedidoEstado;
import com.tarea1.pedidos_service.model.PedidoResponse;

import java.util.List;
import java.util.Optional;

public interface PedidoService {
    PedidoResponse crearPedido(Long clienteId, List<String> productosId);
    Optional<Pedido> obtenerPedido(String id);
    Pedido cambiarEstado(String id, PedidoEstado nuevoEstado);
}
