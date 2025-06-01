package com.tarea1.pedidos_service.impl;

import com.tarea1.pedidos_service.PedidoService;
import com.tarea1.pedidos_service.builder.PedidoResponseBuilder;
import com.tarea1.pedidos_service.client.ClienteClient;
import com.tarea1.pedidos_service.client.ProductoClient;
import com.tarea1.pedidos_service.comand.CrearPedidoCommand;
import com.tarea1.pedidos_service.exception.PedidoException;
import com.tarea1.pedidos_service.model.Pedido;
import com.tarea1.pedidos_service.model.PedidoEstado;
import com.tarea1.pedidos_service.model.PedidoResponse;
import com.tarea1.pedidos_service.observer.NotificarClienteListener;
import com.tarea1.pedidos_service.observer.PedidoEventPublisher;
import com.tarea1.pedidos_service.repository.PedidoRepository;
import com.tarea1.pedidos_service.strategy.ValidarCancelacionStrategy;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class PedidoServiceImpl implements PedidoService {
    private final PedidoRepository repo;
    private final ClienteClient clienteClient;
    private final ProductoClient productoClient;
    private final PedidoEventPublisher publisher;

    public PedidoServiceImpl(PedidoRepository repo, ClienteClient clienteClient,
                             ProductoClient productoClient, PedidoEventPublisher publisher) {
        this.repo = repo;
        this.clienteClient = clienteClient;
        this.productoClient = productoClient;
        this.publisher = publisher;
        this.publisher.registrar(new NotificarClienteListener());
    }

    @Override
    @CircuitBreaker(name = "clienteService", fallbackMethod = "fallbackCrearPedido")
    @Retry(name = "clienteService")
    public PedidoResponse crearPedido(Long clienteId, List<String> productosId) {
        clienteClient.obtenerCliente(clienteId); // lanza excepción si no existe
        productosId.forEach(productoClient::obtenerProducto); // Verifica todos

        Pedido nuevo = new Pedido(null, clienteId, productosId, PedidoEstado.PENDIENTE, LocalDateTime.now());
        Pedido creado = new CrearPedidoCommand(nuevo).ejecutar(repo);

        publisher.publicar(creado);

        return new PedidoResponseBuilder()
                .conPedido(creado)
                .conMensaje("Pedido creado exitosamente")
                .construir();
    }

    @Override
    public Optional<Pedido> obtenerPedido(String id) {
        return repo.findById(id);
    }

    @Override
    public Pedido cambiarEstado(String id, PedidoEstado nuevoEstado) {
        Pedido pedido = repo.findById(id).orElseThrow(() -> new PedidoException("No existe el pedido"));
        new ValidarCancelacionStrategy().validarCambio(pedido, nuevoEstado); // ejemplo
        pedido.setEstado(nuevoEstado);
        return repo.save(pedido);
    }

    public PedidoResponse fallbackCrearPedido(Long clienteId, String productoId, Throwable e) {
        return new PedidoResponseBuilder()
                .conMensaje("No se pudo crear el pedido: servicio externo no disponible.")
                .construir();
    }
}
