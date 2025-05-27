package com.tarea1.pedidos_service.comand;

import com.tarea1.pedidos_service.model.Pedido;
import com.tarea1.pedidos_service.model.PedidoEstado;
import com.tarea1.pedidos_service.repository.PedidoRepository;

import java.time.LocalDateTime;

public class CrearPedidoCommand {
    private final Pedido pedido;

    public CrearPedidoCommand(Pedido pedido) {
        this.pedido = pedido;
    }

    public Pedido ejecutar(PedidoRepository repo) {
        pedido.setFechaCreacion(LocalDateTime.now());
        pedido.setEstado(PedidoEstado.PENDIENTE);
        return repo.save(pedido);
    }
}
