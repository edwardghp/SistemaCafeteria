# API de Productos

## Tutoriales

- **Desarrollo del API**: [Ver en YouTube](https://www.youtube.com/watch?v=uSwCVj-_TWY&list=PL145AyWAbMDhwUbBL74s1D2ZV9EqBaQ1t&index=2)
- **Instalación de MongoDB**: [Ver en YouTube](https://www.youtube.com/watch?v=eKXIxSZrJfw)

## Endpoints disponibles

### Crear un producto

**POST** `http://localhost:8080/api/products`

**Body (raw - JSON):**
```json
{
  "productName": "cafe",
  "productDescription": "cafe pasado",
  "unitPrice": 1.5,
  "stock": 20
}

Obtener todos los productos
GET http://localhost:8080/api/products

Obtener un producto por ID
GET http://localhost:8080/api/products/{productId}
GET http://localhost:8080/api/products/producto1

Actualizar el stock de un producto
PATCH http://localhost:8080/api/products/{productId}/stock?stock={nuevoStock}
PATCH http://localhost:8080/api/products/producto1/stock?stock=50

