
Conversación abierta. 1 mensaje leído.

Ir al contenido
Uso de Correo de I.E.S. El Rincón con lectores de pantalla

2 de 288
readme
Recibidos

YEREMAY ARANA
18:11 (hace 10 minutos)
para mí

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
   - UML
   - Casos de Uso
2. **API**
   - Endpoints y funcionalidades
3. **Modelos y Repositorios**
   - Implementación en Spring Boot
4. **Interfaz IONIC**
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
CREATE DATABASE kibo;
USE kibo;

CREATE TABLE usuarios (
    id_usuario BIGINT NOT NULL AUTO_INCREMENT,
    nombre_completo VARCHAR(255),
    correo_electronico VARCHAR(255),
    direccion VARCHAR(255) NOT NULL,
    contrasena VARCHAR(255),
    tarjeta BIGINT,
    tipo_usuario TINYTEXT,
    PRIMARY KEY (id_usuario)
);

CREATE TABLE telefonos_usuarios (
    id_usuario BIGINT NOT NULL,
    telefono VARCHAR(255) NOT NULL,
    PRIMARY KEY (id_usuario, telefono),
    FOREIGN KEY (id_usuario) REFERENCES usuarios(id_usuario) ON DELETE CASCADE ON UPDATE CASCADE
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


