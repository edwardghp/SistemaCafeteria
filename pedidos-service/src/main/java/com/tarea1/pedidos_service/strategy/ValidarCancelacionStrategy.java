package com.tarea1.pedidos_service.strategy;

import com.tarea1.pedidos_service.exception.PedidoException;
import com.tarea1.pedidos_service.model.Pedido;
import com.tarea1.pedidos_service.model.PedidoEstado;

public class ValidarCancelacionStrategy implements ValidacionEstadoStrategy {
    @Override
    public void validarCambio(Pedido pedido, PedidoEstado nuevoEstado) {
        if (pedido.getEstado() != PedidoEstado.PENDIENTE) {
            throw new PedidoException("Solo se puede cancelar pedidos pendientes.");
        }
    }
}
