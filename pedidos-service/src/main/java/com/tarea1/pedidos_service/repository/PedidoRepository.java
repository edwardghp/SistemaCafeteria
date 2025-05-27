package com.tarea1.pedidos_service.repository;

import com.tarea1.pedidos_service.model.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;


public interface PedidoRepository extends JpaRepository<Pedido, Long> {
}
