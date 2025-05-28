package com.tarea1.pagos_service.strategy;

import com.tarea1.pagos_service.dto.PedidoDTO;

public class PagoNormalStrategy implements PagoStrategy{
    public double calcularMonto(PedidoDTO pedido) {
        return pedido.getTotal();
    }
}
