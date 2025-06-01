package com.tarea1.pagos_service.servicio;

import com.tarea1.pagos_service.client.PedidoClient;
import com.tarea1.pagos_service.dto.Pedido;
import com.tarea1.pagos_service.modelo.Pago;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Service
public class PagoService {
    private final PedidoClient pedidoClient;

    public PagoService(PedidoClient pedidoClient) {
        this.pedidoClient = pedidoClient;
    }

    public Pago procesarPago(String pedidoId) {
        // Aquí sólo marcamos el pedido como PAGADO simulando el pago
        Pedido pedidoActualizado = pedidoClient.actualizarEstado(pedidoId, "PAGADO");

        return new Pago(pedidoId, "PAGADO");
    }
}
