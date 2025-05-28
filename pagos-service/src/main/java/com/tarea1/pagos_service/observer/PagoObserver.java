package com.tarea1.pagos_service.observer;

import com.tarea1.pagos_service.model.Pago;

public interface PagoObserver {
    void onPagoRealizado(Pago pago);
}
