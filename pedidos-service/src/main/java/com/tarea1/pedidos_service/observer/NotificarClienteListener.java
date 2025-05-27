package com.tarea1.pedidos_service.observer;

import com.tarea1.pedidos_service.model.Pedido;

public class NotificarClienteListener implements PedidoListener {
    @Override
    public void notificar(Pedido pedido) {
        System.out.println("Notificando al cliente del pedido #" + pedido.getId());
    }
}
