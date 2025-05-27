## Tutoriales

- **Desarrollo del API**: [Ver en YouTube](https://www.youtube.com/watch?v=uSwCVj-_TWY&list=PL145AyWAbMDhwUbBL74s1D2ZV9EqBaQ1t&index=2)
- **Instalación de MongoDB**: [Ver en YouTube](https://www.youtube.com/watch?v=eKXIxSZrJfw)

# API de Productos

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
```

---

### Obtener todos los productos

**GET** `http://localhost:8080/api/products`

---

### Obtener un producto por ID

**GET** `http://localhost:8080/api/products/{productId}`  
**Ejemplo**:  
`http://localhost:8080/api/products/producto1`

---

### Actualizar el stock de un producto

**PATCH** `http://localhost:8080/api/products/{productId}/stock?stock={nuevoStock}`  
**Ejemplo**:  
`http://localhost:8080/api/products/producto1/stock?stock=50`

---

# API de Clientes

### Crear un cliente

**POST** `http://localhost:8083/clientes`

**Body (raw - JSON):**
```json
{
  "nombre": "Juan Pérez",
  "email": "juan@example.com",
  "password": "123456"
}
```

---

### Obtener un cliente por ID

**GET** `http://localhost:8083/clientes/{id}`  
**Ejemplo**:  
`http://localhost:8083/clientes/1`

---

# Login de Cliente

### Configuración en Postman (Opción 1 - Parámetros en URL)

- **Método**: POST  
- **URL**:  
  ```
  http://localhost:8080/login?email=juan@example.com&password=123456
  ```
- **Body**: vacío  
- **Headers** (opcional):  
  `Content-Type: application/x-www-form-urlencoded`

---

### Configuración en Postman (Opción 2 - x-www-form-urlencoded)

- **Método**: POST  
- **URL**:  
  ```
  http://localhost:8080/login
  ```
- **Body**: `x-www-form-urlencoded`

| Key      | Value             |
|----------|------------------|
| email    | juan@example.com |
| password | 123456           |

