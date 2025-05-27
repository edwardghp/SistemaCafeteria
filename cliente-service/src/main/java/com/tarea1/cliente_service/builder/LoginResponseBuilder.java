package com.tarea1.cliente_service.builder;

import com.tarea1.cliente_service.model.Cliente;
import com.tarea1.cliente_service.model.LoginResponse;

public class LoginResponseBuilder {
    private String mensaje;
    private Cliente cliente;

    public LoginResponseBuilder conMensaje(String mensaje) {
        this.mensaje = mensaje;
        return this;
    }

    public LoginResponseBuilder conCliente(Cliente cliente) {
        this.cliente = cliente;
        return this;
    }

    public LoginResponse construir() {
        return new LoginResponse(mensaje, cliente);
    }
}
