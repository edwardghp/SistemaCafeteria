package com.tarea1.pagos_service.builder;

import com.tarea1.pagos_service.model.Pago;

public class PagoBuilder {
    private final Pago pago;
    private PagoBuilder() {
        this.pago = new Pago();
    }
    public static PagoBuilder builder() { return new PagoBuilder(); }
    public PagoBuilder conPedidoId(Long id) {
        this.pago.setPedidoId(id); return this;
    }
    public PagoBuilder conMonto(Double monto) {
        this.pago.setMonto(monto); return this;
    }
    public PagoBuilder conEstado(String estado) {
        this.pago.setEstado(estado); return this;
    }
    public Pago build() { return this.pago; }
}
