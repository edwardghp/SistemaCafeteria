package com.tarea1.pedidos_service.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

public class GlobalExceptionHandler {
    @ExceptionHandler(PedidoException.class)
    public ResponseEntity<String> handlePedidoException(PedidoException ex) {
        return ResponseEntity.badRequest().body("Error en pedido: " + ex.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception ex) {
        return ResponseEntity.internalServerError().body("Error interno: " + ex.getMessage());
    }
}
