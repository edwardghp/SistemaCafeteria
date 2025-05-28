package com.tarea1.pagos_service.controller;

import com.tarea1.pagos_service.PagoService;
import com.tarea1.pagos_service.model.Pago;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/pagos")
public class PagoController {
    private final PagoService service;
    public PagoController(PagoService service) { this.service = service; }

    @PostMapping("/{pedidoId}")
    public ResponseEntity<Pago> pagar(@PathVariable Long pedidoId) {
        Pago pago = service.procesarPago(pedidoId);
        return ResponseEntity.ok(pago);
    }
}
