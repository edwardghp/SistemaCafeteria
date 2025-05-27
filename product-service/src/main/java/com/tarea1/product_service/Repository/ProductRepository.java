package com.tarea1.product_service.Repository;

import com.tarea1.product_service.Entidad.ProductEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductRepository extends MongoRepository<ProductEntity,String>{

}
