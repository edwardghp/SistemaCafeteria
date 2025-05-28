package com.tarea1.pagos_service.observer;

import com.tarea1.pagos_service.model.Pago;

public class Notificator implements PagoObserver{
    public void onPagoRealizado(Pago pago) {
        System.out.println("Notificación: pago realizado para el pedido " + pago.getPedidoId());
    }
}
