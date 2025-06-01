package com.tarea1.pedidos_service.repository;

import com.tarea1.pedidos_service.model.Pedido;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface PedidoRepository extends MongoRepository<Pedido, String> {
}
