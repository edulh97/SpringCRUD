
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
