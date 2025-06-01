package com.tarea1.cliente_service.impl;

import com.tarea1.cliente_service.ClienteService;
import com.tarea1.cliente_service.builder.LoginResponseBuilder;
import com.tarea1.cliente_service.factory.ClienteFactory;
import com.tarea1.cliente_service.model.Cliente;
import com.tarea1.cliente_service.model.LoginResponse;
import com.tarea1.cliente_service.repository.ClienteRepository;
import com.tarea1.cliente_service.strategy.AutenticacionStrategy;
import com.tarea1.cliente_service.strategy.EmailPasswordStrategy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClienteServiceImpl implements ClienteService {
    private final ClienteRepository repository;
    private final PasswordEncoder passwordEncoder;

    public ClienteServiceImpl(ClienteRepository repository, PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Cliente registrar(String nombre, String apellido, String email, String password) {
        String encoded = passwordEncoder.encode(password);
        Cliente cliente = ClienteFactory.crearCliente(nombre,apellido, email, encoded);
        return repository.save(cliente);
    }

    @Override
    public Cliente obtenerPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));
    }

    @Override
    public LoginResponse login(String email, String password) {
        AutenticacionStrategy strategy = new EmailPasswordStrategy(repository, passwordEncoder);
        Cliente clienteAutenticado = strategy.autenticar(email, password);
        return new LoginResponseBuilder()
                .conMensaje("Login exitoso")
                .conCliente(clienteAutenticado)
                .construir();
    }

    @Override
    public Optional<Cliente> findByEmail(String email) {
        return repository.findByEmail(email);
    }
}
