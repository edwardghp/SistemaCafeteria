package com.tarea1.cliente_service.strategy;

import com.tarea1.cliente_service.model.Cliente;

public interface AutenticacionStrategy {
    Cliente autenticar(String identificador, String secreto);
}
