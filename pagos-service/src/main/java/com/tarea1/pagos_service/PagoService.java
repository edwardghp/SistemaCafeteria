package com.tarea1.pagos_service;

import com.tarea1.pagos_service.builder.PagoBuilder;
import com.tarea1.pagos_service.dto.PedidoDTO;
import com.tarea1.pagos_service.feign.PedidoClient;
import com.tarea1.pagos_service.model.Pago;
import com.tarea1.pagos_service.observer.Notificator;
import com.tarea1.pagos_service.observer.PagoObserver;
import com.tarea1.pagos_service.repository.PagoRepository;
import com.tarea1.pagos_service.strategy.PagoNormalStrategy;
import com.tarea1.pagos_service.strategy.PagoStrategy;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PagoService {
    private final PagoRepository repo;
    private final PedidoClient pedidoClient;
    private final PagoStrategy strategy;
    private final List<PagoObserver> observadores = List.of(new Notificator());

    public PagoService(PagoRepository repo, PedidoClient pedidoClient) {
        this.repo = repo;
        this.pedidoClient = pedidoClient;
        this.strategy = new PagoNormalStrategy();
    }

    @CircuitBreaker(name = "pedidoService", fallbackMethod = "fallback")
    @Retry(name = "pedidoService")
    public Pago procesarPago(Long pedidoId) {
        PedidoDTO pedido = pedidoClient.obtenerPedidoPorId(pedidoId);
        double monto = strategy.calcularMonto(pedido);
        Pago pago = PagoBuilder.builder()
                .conPedidoId(pedidoId)
                .conMonto(monto)
                .conEstado("PAGADO")
                .build();
        Pago guardado = repo.save(pago);
        observadores.forEach(obs -> obs.onPagoRealizado(guardado));
        return guardado;
    }

    public Pago fallback(Long pedidoId, Throwable t) {
        throw new RuntimeException("Fallo al obtener pedido: " + t.getMessage());
    }
}
