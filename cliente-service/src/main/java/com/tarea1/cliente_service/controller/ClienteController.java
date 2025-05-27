package com.tarea1.cliente_service.controller;

import com.tarea1.cliente_service.ClienteService;
import com.tarea1.cliente_service.model.Cliente;
import com.tarea1.cliente_service.model.LoginResponse;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/clientes")
public class ClienteController {
    private final ClienteService service;

    public ClienteController(ClienteService service) {
        this.service = service;
    }

    @PostMapping
    public Cliente registrar(@RequestBody Cliente cliente) {
        return service.registrar(cliente.getNombre(), cliente.getEmail(), cliente.getPassword());
    }

    @GetMapping("/{id}")
    public Cliente obtener(@PathVariable Long id) {
        return service.obtenerPorId(id);
    }

    @PostMapping("/login")
    public LoginResponse login(@RequestParam String email, @RequestParam String password) {
        return service.login(email, password);
    }
}
