package com.tarea1.pedidos_service.observer;

import com.tarea1.pedidos_service.model.Pedido;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class PedidoEventPublisher {
    private final List<PedidoListener> listeners = new ArrayList<>();

    public void registrar(PedidoListener listener) {
        listeners.add(listener);
    }

    public void publicar(Pedido pedido) {
        listeners.forEach(l -> l.notificar(pedido));
    }
}
