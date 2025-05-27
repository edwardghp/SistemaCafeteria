package com.tarea1.product_service.Controller;

import com.tarea1.product_service.Entidad.ProductEntity;
import com.tarea1.product_service.Factory.ProductFactory;
import com.tarea1.product_service.Repository.ProductRepository;
import com.tarea1.product_service.Strategy.ProductUpdateStrategy;
import com.tarea1.product_service.Strategy.StockUpdateStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    @Autowired
    private ProductRepository productRepository;

    //Listar los productos
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ProductEntity> getAllProducts() {
        return productRepository.findAll();
    }

    //Crear un producto
    /*@PostMapping
    @ResponseStatus(HttpStatus.OK)
    public void createProduct(@RequestBody ProductEntity productEntity){
        productRepository.save(productEntity);
    }*/
/*
    //Crear producto
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<String> createProduct(@RequestBody ProductEntity productEntity) {
        // Generar el siguiente ID disponible
        String newId = generateNextProductId();
        productEntity.setId(newId);

        productRepository.save(productEntity);
        return ResponseEntity.ok("Producto creado con ID: " + newId);
    }*/

    // Crear producto
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<String> createProduct(@RequestBody ProductEntity productEntity) {
        String newId = generateNextProductId();
        ProductEntity newProduct = ProductFactory.createProduct(
                productEntity.getProductName(),
                productEntity.getProductDescription(),
                productEntity.getUnitPrice(),
                productEntity.getStock(),
                newId
        );
        productRepository.save(newProduct);
        return ResponseEntity.ok("Producto creado con ID: " + newId);
    }

    //Generar los ids de los productos
    private String generateNextProductId() {
        List<ProductEntity> allProducts = productRepository.findAll();

        int maxNumber = allProducts.stream()
                .map(ProductEntity::getId)
                .filter(id -> id != null && id.startsWith("producto"))
                .map(id -> id.replace("producto", ""))
                .filter(suffix -> suffix.matches("\\d+"))
                .mapToInt(Integer::parseInt)
                .max()
                .orElse(0);

        return "producto" + (maxNumber + 1);
    }

    //Detalle de un producto por id
    @GetMapping("/{id}")
    public ResponseEntity<ProductEntity> getProductById(@PathVariable String id) {
        Optional<ProductEntity> product = productRepository.findById(id);
        return product.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    // Actualizar stock (solo STAFF)
    @PatchMapping("/{id}/stock")
    @PreAuthorize("hasRole('PERSONAL')")
    public ResponseEntity<String> updateProductStock(@PathVariable String id, @RequestParam int stock) {
        Optional<ProductEntity> optionalProduct = productRepository.findById(id);
        if (optionalProduct.isPresent()) {
            ProductEntity product = optionalProduct.get();
            ProductUpdateStrategy stockStrategy = new StockUpdateStrategy();
            stockStrategy.update(product, stock);
            productRepository.save(product);
            return ResponseEntity.ok("Stock actualizado correctamente.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Producto no encontrado.");
        }
    }

    /*
    @PatchMapping("/{id}/stock")
    public ResponseEntity<String> updateProductStock(@PathVariable String id, @RequestParam int stock) {
        Optional<ProductEntity> optionalProduct = productRepository.findById(id);

        if (optionalProduct.isPresent()) {
            ProductEntity product = optionalProduct.get();
            product.setStock(stock);
            productRepository.save(product);
            return ResponseEntity.ok("Stock actualizado correctamente.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Producto no encontrado.");
        }
    }*/
}