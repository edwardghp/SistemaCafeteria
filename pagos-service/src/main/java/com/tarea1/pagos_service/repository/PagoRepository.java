package com.tarea1.pagos_service.repository;

import com.tarea1.pagos_service.model.Pago;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PagoRepository extends JpaRepository<Pago, Long> {
}
