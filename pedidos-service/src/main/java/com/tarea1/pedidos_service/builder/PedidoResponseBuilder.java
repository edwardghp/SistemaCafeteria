package com.tarea1.pedidos_service.builder;

import com.tarea1.pedidos_service.model.Pedido;
import com.tarea1.pedidos_service.model.PedidoResponse;

public class PedidoResponseBuilder {
    private Pedido pedido;
    private String mensaje;

    public PedidoResponseBuilder conPedido(Pedido pedido) {
        this.pedido = pedido;
        return this;
    }

    public PedidoResponseBuilder conMensaje(String mensaje) {
        this.mensaje = mensaje;
        return this;
    }

    public PedidoResponse construir() {
        return new PedidoResponse(pedido, mensaje);
    }
}
