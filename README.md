
<<<<<<< HEAD
### Un poco de info

- 
```  .   ____          _            __ _ _
 /\\ / ___'_ __ _ _(_)_ __  __ _ \ \ \ \
( ( )\___ | '_ | '_| | '_ \/ _` | \ \ \ \
 \\/  ___)| |_)| | | | | || (_| |  ) ) ) )
  '  |____| .__|_| |_|_| |_\__, | / / / /
 =========|_|==============|___/=/_/_/_/
```

 :: Spring Boot ::                (v3.4.0)

- **controller**: Contiene las clases responsables de manejar las solicitudes HTTP. Incluye controladores para manejar las operaciones sobre usuarios y teléfonos de usuarios.
  - `ProductosController.java`: Controlador para la gestión de productos.
  - `TelefonosUsuarioController.java`: Controlador para la gestión de teléfonos de usuarios.
  - `UsuariosController.java`: Controlador para la gestión de usuarios.

- **model**: Define las entidades del modelo de datos.
  - `Usuario.java`: Representa la entidad de usuario.
  - `TelefonosUsuario.java` y `TelefonosUsuarioId.java`: Representa la relación entre un usuario y su(s) teléfono(s).
  - `Producto.java`:  La entidadad de productos/platos que está relacioaado a pedidos.

- **repository**: Interfaces que interactúan con la base de datos para realizar las operaciones CRUD.
  - `UsuariosRepository.java`: Repositorio para la entidad de usuario.
  - `TelefonosUsuarioRepository.java`: Repositorio para la entidad de teléfonos de usuarios.

- **Application.java**: Clase principal para ejecutar la aplicación.

## Instalación

1. Clona este repositorio en tu máquina local.

   ```bash
   git clone https://github.com/tu-usuario/tu-repositorio.git

2. Ejecuta los archivos .sql adjuntos en la carpeta DB los que puedes clonar las tablas de la base de datos ya con registros. 
=======
# Kibo - Documentación del Proyecto

## Índice
1. [Introducción](#introducción)
2. [Tecnologías Utilizadas](#tecnologías-utilizadas)
3. [Estructura del Proyecto](#estructura-del-proyecto)
4. [Base de Datos](#base-de-datos)
   - [Tablas y Relaciones](#tablas-y-relaciones)
   - [Relaciones Clave](#relaciones-clave)
   - [Creación de la Base de Datos](#creación-de-la-base-de-datos)
5. [Modelos en Spring Boot](#modelos-en-spring-boot)
6. [API - Endpoints](#api---endpoints)
   - [UsuariosController](#usuarioscontroller)
   - [ProductosController](#productoscontroller)
7. [Configuración del Proyecto](#configuración-del-proyecto)
8. [Ejecución del Proyecto](#ejecución-del-proyecto)
9. [Interfaz IONIC](#interfaz-ionic)
   - [Funcionalidades de la Interfaz](#funcionalidades-de-la-interfaz)

## Introducción
Kibo es una aplicación de gestión de usuarios, productos y pedidos. Se ha desarrollado con una arquitectura MVC utilizando **Spring Boot** para la API REST y **IONIC** para la interfaz de usuario. Los usuarios pueden realizar operaciones CRUD (Crear, Leer, Actualizar y Eliminar) sobre los usuarios, sus teléfonos y los productos.

## Tecnologías Utilizadas
- **Backend**: Spring Boot, JPA, Hibernate, MySQL
- **Frontend**: Ionic
- **Base de Datos**: MySQL
- **Herramientas Adicionales**: Postman para pruebas API

## Estructura del Proyecto
1. **Diagramas**
   - Entidad-Relación
  ![image](https://github.com/user-attachments/assets/15fe08d6-9a04-4494-8dbf-86ec65a69da7)

   - UML
![image](https://github.com/user-attachments/assets/bb4ea6f4-fc36-44da-883e-1d2c0942a760)

   - Casos de Uso
![image](https://github.com/user-attachments/assets/432a8387-9127-4146-a05d-2b4d9076fb3d)

1. **API**
   - Endpoints y funcionalidades
2. **Modelos y Repositorios**
   - Implementación en Spring Boot
3. **Interfaz IONIC**
   - Gestión y visualización de datos

## Base de Datos
### Tablas y Relaciones
- **USUARIOS** (id_usuario, NombreCompleto, Correo_electronico, Teléfonos, Dirección, Contraseña, Tarjeta, Tipo_usuario)
- **PEDIDOS** (id_pedido, id_usuario, Fecha_pedido, Estado_pedido, Tipo_entrega, Total)
- **PRODUCTOS** (id_producto, Nombre_producto, Precio, Descripción, Alérgenos)
- **DETALLEPEDIDOS** (id_detalle, id_pedido, id_producto, Cantidad, Subtotal)
- **TELEFONOS_USUARIOS** (id_usuario, telefono)

### Relaciones Clave
- Un usuario puede realizar muchos pedidos (1:N)
- Un pedido puede contener muchos productos a través de la tabla DETALLEPEDIDOS (1:N)
- Un usuario puede tener múltiples teléfonos (1:N)

## Creación de la Base de Datos
```sql
DROP DATABASE IF EXISTS kibo;

CREATE DATABASE kibo;

USE kibo;

CREATE TABLE `usuarios` (
    `id_usuario` bigint NOT NULL AUTO_INCREMENT,
    `nombre_completo` varchar(255) DEFAULT NULL,
    `correo_electronico` varchar(255) DEFAULT NULL UNIQUE,
    `direccion` varchar(255) NOT NULL,
    `contrasena` varchar(255) DEFAULT NULL,
    `tarjeta` bigint DEFAULT NULL,
    `tipo_usuario` tinytext,
    `token` VARCHAR(255) DEFAULT NULL UNIQUE,
    CONSTRAINT `usuarios_pk` PRIMARY KEY (`id_usuario`)
);

CREATE TABLE `telefonos_usuarios` (
    `id_telefono` BIGINT NOT NULL AUTO_INCREMENT,
    `id_usuario` BIGINT NOT NULL,
    `telefono` VARCHAR(255) NOT NULL,
    CONSTRAINT `telefonos_usuarios_pk` PRIMARY KEY (`id_telefono`),
    CONSTRAINT `telefonos_usuarios_uq` UNIQUE (`id_usuario`, `telefono`),
    CONSTRAINT `telefonos_usuarios_fk1` FOREIGN KEY (`id_usuario`) REFERENCES `usuarios` (`id_usuario`) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE `productos` (
    `id_producto` bigint NOT NULL AUTO_INCREMENT,
    `nombre_producto` varchar(100) NOT NULL,
    `precio` decimal(10, 2) NOT NULL,
    `descripcion` varchar(100) NOT NULL,
    `alergenos` varchar(100) NOT NULL,
    CONSTRAINT `productos_pk` PRIMARY KEY (`id_producto`)
);

CREATE TABLE `pedidos` (
    `id_pedido` BIGINT NOT NULL AUTO_INCREMENT,
    `id_usuario` BIGINT NOT NULL,
    `fecha_pedido` datetime DEFAULT CURRENT_TIMESTAMP,
    `estado_pedido` varchar(255) DEFAULT NULL,
    `tipo_entrega` varchar(255) NOT NULL,
    `total` decimal(10, 2) DEFAULT NULL,
    CONSTRAINT `pedidos_pk` PRIMARY KEY (`id_pedido`),
    CONSTRAINT `pedidos_fk` FOREIGN KEY (`id_usuario`) REFERENCES `usuarios` (`id_usuario`) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE `productos_categorias` (
  `id_producto`  BIGINT NOT NULL,
  `id_categoria` BIGINT NOT NULL,
  PRIMARY KEY (`id_producto`, `id_categoria`),
  CONSTRAINT `fk_prodcat_prod`
    FOREIGN KEY (`id_producto`)
    REFERENCES `productos` (`id_producto`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_prodcat_cat`
    FOREIGN KEY (`id_categoria`)
    REFERENCES `categorias` (`id_categoria`)
    ON DELETE CASCADE
    ON UPDATE CASCADE
);

CREATE TABLE `resenas` (
  `id_resena` BIGINT NOT NULL AUTO_INCREMENT,
  `id_usuario` BIGINT NOT NULL,
  `id_producto` BIGINT NOT NULL,
  `calificacion` TINYINT NOT NULL,
  `comentario` TEXT DEFAULT NULL,
  `fecha_creacion` DATETIME DEFAULT CURRENT_TIMESTAMP,
  CONSTRAINT `resenas_pk` PRIMARY KEY (`id_resena`),
  CONSTRAINT `resenas_fk1` FOREIGN KEY (`id_usuario`) REFERENCES `usuarios` (`id_usuario`) ON DELETE CASCADE,
  CONSTRAINT `resenas_fk2` FOREIGN KEY (`id_producto`) REFERENCES `productos` (`id_producto`) ON DELETE CASCADE,
  CONSTRAINT `resenas_uq` UNIQUE (`id_usuario`, `id_producto`) -- Para evitar múltiples reseñas del mismo usuario
);

CREATE TABLE `categorias` (
  `id_categoria` BIGINT       NOT NULL AUTO_INCREMENT,
  `nombre_categoria` VARCHAR(100) NOT NULL UNIQUE,
  `descripcion`       VARCHAR(255) DEFAULT NULL,
  PRIMARY KEY (`id_categoria`)
);

CREATE TABLE `detalles_pedido` (
    `id_detalle` BIGINT NOT NULL AUTO_INCREMENT,
    `id_pedido` BIGINT NOT NULL,
    `id_producto` BIGINT NOT NULL,
    `cantidad` INT NOT NULL,
    `precio_unitario` DECIMAL(10, 2) NOT NULL,
    `subtotal` DECIMAL(10, 2) GENERATED ALWAYS AS (
        `cantidad` * `precio_unitario`
    ) STORED,
    CONSTRAINT `detalles_pedido_pk` PRIMARY KEY (`id_detalle`),
    CONSTRAINT `detalles_pedido_fk1` FOREIGN KEY (`id_pedido`) REFERENCES `pedidos` (`id_pedido`) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT `detalles_pedido_fk2` FOREIGN KEY (`id_producto`) REFERENCES `productos` (`id_producto`) ON DELETE RESTRICT ON UPDATE CASCADE
);
```

## Modelos en Spring Boot
### Usuario.java
```java
@Entity
@Table(name = "usuarios")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombreCompleto;
    private String correoElectronico;
    private String direccion;
    private String contrasena;
    private String tarjeta;
    private String tipoUsuario;
   
    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
    private List<TelefonoUsuario> telefonos;
}

@Entity
@Table(name = "pedidos")
public class Pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pedido", nullable = false)
    private Long id;

    @Column(name = "id_usuario", nullable = false)
    private Long idUsuario;

    @CreationTimestamp
    @Column(name = "fecha_pedido", nullable = false)
    private LocalDateTime fechaPedido;

    @Column(name = "estado_pedido", length = 255)
    private String estadoPedido;

    @Column(name = "tipo_entrega", nullable = false, length = 255)
    private String tipoEntrega;

    @Column(name = "total", precision = 10, scale = 2)
    private BigDecimal total;

    @JsonManagedReference 
    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<DetallePedido> detalles;

@Entity
@Table(name = "productos")
public class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_producto", nullable = false)
    private Long id;

    @Column(name = "nombre_producto", nullable = false, length = 100)
    private String nombreProducto;

    @Column(name = "precio", nullable = false, precision = 10, scale = 2)
    private BigDecimal precio;

    @Column(name = "descripcion", nullable = false, length = 100)
    private String descripcion;

    @Column(name = "alergenos", nullable = false, length = 100)
    private String alergenos;

@IdClass(TelefonosUsuarioId.class)
public class TelefonosUsuario {

    @Id
    @Column(name = "id_usuario")
    private Long idUsuario;

    @Id
    private String telefono;

    @ManyToOne
    @JoinColumn(name = "id_usuario", insertable = false, updatable = false)
    @JsonIgnore
    private Usuario usuario;

```

## API - Endpoints
### UsuariosController
- `GET /kibo/usuarios` - Obtiene todos los usuarios
- `POST /kibo/usuarios` - Crea un nuevo usuario
- `PUT /kibo/usuarios/{id}` - Actualiza un usuario
- `DELETE /kibo/usuarios/{id}` - Elimina un usuario

### ProductosController
- `GET /kibo/productos` - Obtiene todos los productos
- `POST /kibo/productos` - Crea un nuevo producto
- `PUT /kibo/productos/{id}` - Actualiza un producto
- `DELETE /kibo/productos/{id}` - Elimina un producto

## Configuración del Proyecto
### Dependencias en `pom.xml`
- **Spring Boot Starter**: Base para iniciar la aplicación
- **Spring Boot Starter Data JPA**: Manejo de datos con JPA y Hibernate
- **MySQL Connector/J**: Conexión con MySQL
- **Spring Boot Starter Web**: Creación de APIs REST

## Ejecución del Proyecto
1. Configurar MySQL y crear la base de datos `kibo`
2. Ejecutar la aplicación con `mvn spring-boot:run`
3. Acceder a los endpoints a través de Postman o navegador (`http://localhost:8080/kibo/usuarios`)

## Interfaz IONIC
- **Gestión de usuarios**: Creación, edición y eliminación
- **Visualización de productos**
- **Gestión de pedidos**

### Funcionalidades de la Interfaz
- **Mostrar teléfonos asociados a un usuario** con botón verde
- **Eliminar un usuario** con botón rojo
- **Crear usuario** mediante un formulario emergente
- **Editar usuario** con botón azul

---
_Proyecto desarrollado por Eduardo López Hernández y Yeremay Araña Betancor_





Si alguien recibe este correo y no es miembro del dominio ieselrincon.es es porque has  facilitado y/o cedido voluntariamente tu dirección electrónica. No obstante, en aras del cumplimiento del Reglamento (UE) 2016/679 del Parlamento Europeo y del Consejo, de 27 de abril de 2016, puede ejercer los derechos de acceso, rectificación, cancelación, limitación, oposición y portabilidad,  de manera gratuita,  mediante correo electrónico a: admin@ieselrincon.es o bien en la dirección del centro: Avenida Industrial José Sánchez Peñate s/n 35010 Las Palmas de Gran Canaria LAS PALMAS (ESPAÑA)

 

El I.E.S. EL RINCÓN NO RECOGE, NI GESTIONA DATOS PERSONALES A TRAVÉS DE LOS CORREOS ELECTRÓNICOS; EXCEPTO LOS GESTIONADOS POR EL PERSONAL DE SECRETARÍA, DIRECCIÓN, JEFATURA Y LOS TUTORES CORRESPONDIENTES  SEÑALADOS DESDE LA DIRECCIÓN DEL CENTRO.


>>>>>>> 390eb3efa0dfe9b46e968d3b38e609e8270c5087
