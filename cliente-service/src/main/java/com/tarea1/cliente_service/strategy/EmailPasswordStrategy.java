package com.tarea1.cliente_service.strategy;

import com.tarea1.cliente_service.exception.ClienteException;
import com.tarea1.cliente_service.model.Cliente;
import com.tarea1.cliente_service.repository.ClienteRepository;

public class EmailPasswordStrategy implements AutenticacionStrategy{
    private final ClienteRepository repository;

    public EmailPasswordStrategy(ClienteRepository repository) {
        this.repository = repository;
    }

    @Override
    public Cliente autenticar(String email, String password) {
        return repository.findByEmail(email)
                .filter(c -> c.getPassword().equals(password))
                .orElseThrow(() -> new ClienteException("Credenciales inválidas"));
    }
}
