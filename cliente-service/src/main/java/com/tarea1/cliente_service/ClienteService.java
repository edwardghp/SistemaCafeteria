package com.tarea1.cliente_service;

import com.tarea1.cliente_service.model.Cliente;
import com.tarea1.cliente_service.model.LoginResponse;
import org.springframework.stereotype.Service;

import java.util.Optional;

public interface ClienteService {
    Cliente registrar(String nombre,String apellido, String email, String password);
    Cliente obtenerPorId(Long id);
    LoginResponse login(String email, String password);
    Optional<Cliente> findByEmail(String email);
}
