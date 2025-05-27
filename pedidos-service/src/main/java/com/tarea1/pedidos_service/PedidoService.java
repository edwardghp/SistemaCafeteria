package com.tarea1.pedidos_service;

import com.tarea1.pedidos_service.model.Pedido;
import com.tarea1.pedidos_service.model.PedidoEstado;
import com.tarea1.pedidos_service.model.PedidoResponse;

import java.util.List;

public interface PedidoService {
    // Crear un nuevo pedido con cliente y producto
    PedidoResponse crearPedido(Long clienteId, String productoId);

    // Cambiar el estado de un pedido existente (p. ej. a "CANCELADO")
    Pedido cambiarEstado(Long id, PedidoEstado nuevoEstado);
}
