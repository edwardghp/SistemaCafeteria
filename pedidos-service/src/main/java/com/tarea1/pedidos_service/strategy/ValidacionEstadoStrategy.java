package com.tarea1.pedidos_service.strategy;

import com.tarea1.pedidos_service.model.Pedido;
import com.tarea1.pedidos_service.model.PedidoEstado;

public interface ValidacionEstadoStrategy {
    void validarCambio(Pedido pedido, PedidoEstado nuevoEstado);
}
