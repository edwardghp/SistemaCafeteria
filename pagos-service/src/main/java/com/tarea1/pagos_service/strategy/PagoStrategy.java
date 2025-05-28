package com.tarea1.pagos_service.strategy;

import com.tarea1.pagos_service.dto.PedidoDTO;

public interface PagoStrategy {
    double calcularMonto(PedidoDTO pedido);
}
