package com.tarea1.pedidos_service.observer;

import com.tarea1.pedidos_service.model.Pedido;

public interface PedidoListener {
    void notificar(Pedido pedido);
}
