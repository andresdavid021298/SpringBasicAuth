# Ejemplo Basic Auth con SpringBoot

Proyecto que integra una api con tres endpoints (GET, POST, DELETE) y una cache interna de usuarios administrada con una estructura Map.

## Dependencias

 - **slf4j-api**: Manejo de Logs
 - **jakarta.validation-api**: Validaciones en el objeto request del controlador
 - **spring-boot-starter-security**: Seguridad
 - **spring-boot-starter-web**: API Rest

## API Rest

Se creo una api relacionado a usuario con tres endpoints:
- Listado de todos los usuarios (**GET**- > */user/getAll*)
- Crea un nuevo usuario (**POST**- > */user/new*)
- Elimina un usuario (**DELETE**- > */user/:nombreUsuario*)

## Seguridad

En el archivo *SecurityConfiguration* se crean tres beans, uno para agregar un nuevo objeto dentro de la cadena de filtros Http (*SecurityFilterChain*), otro para los usuarios que se tendrán en memoria encargados de tener roles y acciones dentro de la aplicación(*UserDetailsService*) y otro, el encriptado de contraseñas(*PasswordEncoder*)

El endpoint de listar todos los usuarios se configuró para estar permitido sin necesidad de autorización

Se tienen tres roles con sus correspondientes acciones:
- ADMIN_USER: Permite tanto borrar como crear usuarios
- CREATOR_USER: Permite solamente crear usuarios
- DELETE_USER: Permite solamente borrar usuarios
