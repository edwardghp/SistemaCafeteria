package com.tarea1.pagos_service.controller;
import com.tarea1.pagos_service.modelo.Pago;
import com.tarea1.pagos_service.servicio.PagoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pagos")
public class PagoController {

    private final PagoService pagoService;

    public PagoController(PagoService pagoService) {
        this.pagoService = pagoService;
    }

    @PostMapping("/{pedidoId}")
    public ResponseEntity<Pago> pagarPedido(@PathVariable String pedidoId) {
        Pago pago = pagoService.procesarPago(pedidoId);
        return ResponseEntity.ok(pago);
    }
}
