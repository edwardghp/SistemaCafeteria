package com.tarea1.product_service.Controller;

import com.tarea1.product_service.Entidad.ProductEntity;
import com.tarea1.product_service.Repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    //Crear producto
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<String> createProduct(@RequestBody ProductEntity productEntity) {
        // Generar el siguiente ID disponible
        String newId = generateNextProductId();
        productEntity.setId(newId);

        productRepository.save(productEntity);
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
    }
}