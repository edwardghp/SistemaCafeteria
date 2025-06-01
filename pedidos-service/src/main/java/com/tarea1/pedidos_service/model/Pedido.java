package com.tarea1.pedidos_service.model;


import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.LocalDateTime;
import java.util.List;

@Document(collection = "pedidos")
@Data
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Pedido {

    @Id
    private String id;
    private Long clienteId;
    private List<String> productosId;
    private PedidoEstado estado;
    private LocalDateTime fechaCreacion;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Long getClienteId() {
        return clienteId;
    }

    public void setClienteId(Long clienteId) {
        this.clienteId = clienteId;
    }

    public List<String> getProductosId() {
        return productosId;
    }

    public void setProductosId(List<String> productosId) {
        this.productosId = productosId;
    }

    public PedidoEstado getEstado() {
        return estado;
    }

    public void setEstado(PedidoEstado estado) {
        this.estado = estado;
    }

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDateTime fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }
}
