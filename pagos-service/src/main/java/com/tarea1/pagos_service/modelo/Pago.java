package com.tarea1.pagos_service.modelo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDateTime;

@Entity
public class Pago {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String pedidoId;  // ID del pedido que se pagó
    private LocalDateTime fechaPago;
    private boolean pagado;

    public Pago(String pedidoId, String pagado) {
    }
}
