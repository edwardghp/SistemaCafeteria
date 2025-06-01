package com.tarea1.cliente_service.strategy;

import com.tarea1.cliente_service.exception.ClienteException;
import com.tarea1.cliente_service.model.Cliente;
import com.tarea1.cliente_service.repository.ClienteRepository;
import org.springframework.security.crypto.password.PasswordEncoder;

public class EmailPasswordStrategy implements AutenticacionStrategy{
    private final ClienteRepository repository;
    private final PasswordEncoder passwordEncoder;

    public EmailPasswordStrategy(ClienteRepository repository, PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Cliente autenticar(String email, String password) {
        Cliente cliente = repository.findByEmail(email)
                .orElseThrow(() -> new ClienteException("Credenciales inválidas"));

        if (!passwordEncoder.matches(password, cliente.getPassword())) {
            throw new ClienteException("Credenciales inválidas");
        }

        return cliente;
    }
}
