package com.tarea1.cliente_service.factory;

import com.tarea1.cliente_service.exception.ClienteException;
import com.tarea1.cliente_service.model.Cliente;

public class ClienteFactory {
    public static Cliente crearCliente(String nombre,String apellido, String email, String password) {
        if (nombre == null || apellido == null || email == null || password == null) {
            throw new ClienteException("Los campos no pueden ser nulos");
        }
        return new Cliente(null, nombre.trim(),apellido.trim(), email.trim(), password.trim());
    }
}
