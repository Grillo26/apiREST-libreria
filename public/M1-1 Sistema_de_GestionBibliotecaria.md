[Volver al README](../README.md)

---

# M1-1.1 Sistema de Gestión Bibliotecaria

## Programación y Gestión de Biblioteca

**Módulo:** PRESTAMOS Y DEVOLUCIONES  
**Versión:** 1.0  
**Fecha:** 07 de agosto de 2026  
**Estado:** Borrador

---

## 1. Introducción

El Sistema de Gestión Bibliotecaria digitaliza las operaciones cotidianas de una biblioteca, centrándose en la administración del catálogo de libros, la gestión de usuarios (clientes) y el control de préstamos y devoluciones. Este módulo permite organizar el inventario, la búsqueda de materiales y el flujo de préstamos, proporcionando una herramienta eficiente para el personal bibliotecario.

En el contexto de una biblioteca moderna, la gestión manual o semi-digitalizada puede generar problemas como pérdida de libros, inconsistencias en el inventario, largas colas para préstamos y devoluciones, y una experiencia deficiente para el usuario. Este sistema resuelve estas problemáticas centralizando la información, automatizando el proceso de préstamo y devolución, y ofreciendo una interfaz amigable tanto para el bibliotecario como para el lector.

El valor agregado se manifiesta en la optimización de los recursos, la trazabilidad completa de cada préstamo, la reducción de errores administrativos y la mejora en la satisfacción del usuario.

Las integraciones clave incluyen un módulo de gestión de usuarios (clientes), un módulo de catálogo de libros y un módulo de reseñas para fomentar la participación de los lectores.

---

## 2. Alcance

### Incluido en este módulo:

- Gestión completa del catálogo de libros (CRUD).
- Gestión de autores y categorías.
- Búsqueda de libros por título, autor o categoría.
- Registro de préstamos y devoluciones de libros.
- Control de disponibilidad de ejemplares.
- Gestión de multas por retraso (a definir).
- Sistema de reseñas y puntuaciones para los libros.
- Autenticación y autorización de usuarios del sistema (bibliotecarios/administradores).
- Panel de administración para la gestión de datos maestros.

### Excluido de este módulo:

- Sistema de gestión de cobranza y pagos integrado (se implementa como módulo separado si es necesario).
- Análisis de datos y reportes avanzados (BI).
- Módulo de compras y adquisiciones de nuevos libros.

### Actores / Roles involucrados:

- **Bibliotecario / Administrador:** Gestiona el catálogo, autores, categorías, clientes y el ciclo de vida de los préstamos (crear, devolver, gestionar multas).
- **Cliente / Usuario de la Biblioteca:** Puede buscar libros, ver su disponibilidad y consultar su historial de préstamos.

### Establecimientos aplicables:

- Bibliotecas públicas, escolares y universitarias.
- Centros de documentación e información.

---

## 3. Justificación en el Negocio de Gestión Bibliotecaria

### 3.1 Justificación Operativa

La automatización elimina los procesos manuales y en papel, reduciendo drásticamente el tiempo de gestión de préstamos y devoluciones. El sistema de control de disponibilidad evita la reserva doble de ejemplares y facilita la búsqueda de materiales, mejorando la eficiencia del personal y la experiencia del usuario final.

### 3.2 Justificación Normativa

Este sistema se alinea con las mejores prácticas de gestión de información y con potenciales regulaciones de protección de datos (como la Ley de Protección de Datos Personales) al garantizar la trazabilidad de las operaciones y el manejo seguro de la información de los usuarios.

### 3.3 Justificación Tecnológica

El sistema implementa una arquitectura moderna (Spring Boot en el backend y Angular en el frontend) que asegura escalabilidad, mantenibilidad y una interfaz de usuario responsive. La arquitectura basada en API REST permite la futura integración con otros sistemas.

---

## 4. Funciones Principales

### FP-01: Gestión de Catálogo de Libros
- **Descripción:** Permite crear, leer, actualizar y eliminar (CRUD) libros en el catálogo. Cada libro está asociado a un autor y una categoría.
- **Actor principal:** Bibliotecario / Administrador.
- **Resultado esperado:** Catálogo de libros actualizado con la información correcta y validada.

### FP-02: Gestión de Autores y Categorías
- **Descripción:** Administra los autores y las categorías, que se utilizan para catalogar los libros. Permite crear y listar autores y categorías.
- **Actor principal:** Bibliotecario / Administrador.
- **Resultado esperado:** Datos maestros de autores y categorías consistentes y disponibles para la creación de libros.

### FP-03: Búsqueda y Consulta de Libros
- **Descripción:** Permite a cualquier usuario (autenticado o no) buscar libros por título, autor o categoría, y consultar su disponibilidad (ejemplares totales vs. disponibles).
- **Actor principal:** Cliente / Bibliotecario.
- **Resultado esperado:** Listado de libros que coinciden con los criterios de búsqueda, mostrando su estado de disponibilidad.

### FP-04: Gestión de Préstamos y Devoluciones
- **Descripción:** Registra el préstamo de un libro a un cliente, calculando la fecha de devolución prevista. Gestiona la devolución del libro, actualizando la disponibilidad de ejemplares.
- **Actor principal:** Bibliotecario.
- **Resultado esperado:** Préstamo registrado con el estado correspondiente y disponibilidad de ejemplares actualizada.

---

## 5. Requerimientos Funcionales

| ID     | Nombre                                   | Descripción                                                                                                                                                                                                                                                                    | Prioridad | Actor                |
| :----- | :--------------------------------------- | :----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- | :-------- | :------------------- |
| RF-001 | Crear libro                              | El sistema debe permitir crear un nuevo libro, especificando título, autor, categoría, ISBN, año de publicación y número de ejemplares totales. Los ejemplares disponibles se inicializan con el mismo valor.                                                                                | Alta      | Bibliotecario        |
| RF-002 | Listar libros                            | El sistema debe permitir listar todos los libros de forma paginada, mostrando la información básica de cada uno.                                                                                                                                                               | Alta      | Bibliotecario/Cliente |
| RF-003 | Buscar libros por título                 | El sistema debe permitir buscar libros cuyo título contenga una cadena de texto específica.                                                                                                                                                                                  | Alta      | Bibliotecario/Cliente |
| RF-004 | Buscar libros por autor                  | El sistema debe permitir buscar libros por el nombre de su autor.                                                                                                                                                                                                             | Media     | Bibliotecario/Cliente |
| RF-005 | Buscar libros por categoría              | El sistema debe permitir buscar libros por el nombre de su categoría.                                                                                                                                                                                                         | Media     | Bibliotecario/Cliente |
| RF-006 | Crear autor                              | El sistema debe permitir crear un nuevo autor con su nombre y nacionalidad.                                                                                                                                                                                                    | Alta      | Bibliotecario        |
| RF-007 | Listar autores                           | El sistema debe permitir listar todos los autores de forma paginada.                                                                                                                                                                                                           | Alta      | Bibliotecario        |
| RF-008 | Crear categoría                          | El sistema debe permitir crear una nueva categoría con su nombre y descripción.                                                                                                                                                                                                | Alta      | Bibliotecario        |
| RF-009 | Listar categorías                        | El sistema debe permitir listar todas las categorías de forma paginada.                                                                                                                                                                                                        | Alta      | Bibliotecario        |
| RF-010 | Registrar préstamo                       | El sistema debe permitir registrar el préstamo de un libro. Para ello, se selecciona un cliente y un libro. Se crea el registro en la tabla `alquileres` con la fecha actual (`fecha_alquiler`) y una fecha de devolución prevista (ej. 7 o 14 días después). Debe decrementar `ejemplares_disponibles` del libro en 1. | Alta      | Bibliotecario        |
| RF-011 | Registrar devolución                     | El sistema debe permitir registrar la devolución de un libro, actualizando `fecha_devolucion_real` y el estado del alquiler (a "devuelto"). Debe incrementar `ejemplares_disponibles` del libro en 1.                                                                            | Alta      | Bibliotecario        |
| RF-012 | Gestionar estado de alquiler             | El sistema debe gestionar el estado del alquiler ("activo", "devuelto", "atrasado") de forma automática, con la posibilidad de actualización manual.                                                                                                                         | Media     | Sistema/Bibliotecario |
| RF-013 | Autenticación de usuarios (Backend)      | El sistema debe implementar un mecanismo de autenticación (JWT) para proteger los endpoints y asegurar que solo los usuarios con rol de bibliotecario/admin puedan realizar operaciones de escritura.                                                                          | Alta      | Sistema              |
| RF-014 | Protección de endpoints                  | Los endpoints para creación, actualización y eliminación deben estar protegidos y solo ser accesibles para usuarios autenticados con el rol adecuado (Bibliotecario).                                                                                                        | Alta      | Sistema              |
| RF-015 | Crear reseña                             | El sistema debe permitir a un cliente autenticado crear una reseña para un libro, con una puntuación (1-5) y un comentario.                                                                                                                                                    | Media     | Cliente              |
| RF-016 | Listar reseñas de un libro               | El sistema debe permitir listar todas las reseñas asociadas a un libro específico.                                                                                                                                                                                             | Media     | Bibliotecario/Cliente |

---

## 6. Requerimientos No Funcionales

### 6.1 Rendimiento
- **RNF-001:** El sistema debe responder a las solicitudes de listado y búsqueda en menos de 2 segundos para un catálogo de hasta 10,000 libros.
- **RNF-002:** La operación de préstamo o devolución no debe tardar más de 1 segundo.

### 6.2 Seguridad y Privacidad
- **RNF-003:** Toda la comunicación entre el frontend (Angular) y el backend (Spring Boot) debe realizarse mediante HTTPS.
- **RNF-004:** Las contraseñas de los usuarios (a implementar) deben almacenarse de forma segura utilizando un algoritmo de hashing robusto (ej. BCrypt).
- **RNF-005:** Los tokens JWT deben tener un tiempo de expiración corto (ej. 15-30 minutos) para minimizar el riesgo de robo.

### 6.3 Disponibilidad
- **RNF-006:** El módulo principal debe estar disponible en horario de biblioteca (ej. 8:00-20:00) con un tiempo de inactividad planificado mínimo.

### 6.4 Usabilidad
- **RNF-007:** El frontend (Angular) debe ser responsive y funcionar correctamente en navegadores web modernos, tabletas y teléfonos móviles.
- **RNF-008:** El formulario de préstamo debe ser sencillo, permitiendo buscar al cliente y al libro de manera eficiente (ej. con autocompletado).

### 6.5 Escalabilidad
- **RNF-009:** El sistema debe ser capaz de manejar un número creciente de registros de préstamos (cientos de miles) sin una degradación significativa del rendimiento.

### 6.6 Interoperabilidad
- **RNF-010:** El backend debe exponer una API RESTful que siga los estándares de la industria para facilitar la integración con otros sistemas.

### 6.7 Mantenibilidad
- **RNF-011:** El código debe seguir la arquitectura definida (Capas: Controlador, Servicio, Repositorio).
- **RNF-012:** El proyecto debe tener pruebas unitarias y de integración básicas.

### 6.8 Trazabilidad y Auditoría
- **RNF-013:** Cada préstamo y devolución debe quedar registrado con la fecha y hora exactas. (El campo `fecha_alquiler` y `fecha_devolucion_real`)

---

## 7. Reglas de Negocio

- **RN-001 (Disponibilidad de ejemplares):** Un libro solo puede ser prestado si su `ejemplares_disponibles` es mayor a 0.
- **RN-002 (Decremento automático):** Al registrar un préstamo, se debe decrementar automáticamente el campo `ejemplares_disponibles` del libro.
- **RN-003 (Incremento automático):** Al registrar una devolución, se debe incrementar automáticamente el campo `ejemplares_disponibles` del libro.
- **RN-004 (Estado del préstamo):** El estado de un préstamo debe ser "activo" hasta que se registre la devolución, momento en el cual pasa a "devuelto".
- **RN-005 (Puntuación de reseña):** La puntuación de una reseña debe ser un número entero entre 1 y 5.
- **RN-006 (Unicidad de reseña):** Un cliente solo puede tener una reseña por libro (se utiliza la restricción `UNIQUE`).
- **RN-007 (Eliminación lógica de clientes):** Los clientes no se eliminan físicamente de la base de datos; se marcan con `is_eliminado = TRUE` para preservar el historial de préstamos.
- **RN-008 (Eliminación de libros):** Si un autor o categoría es eliminada (con `ON DELETE SET NULL`), los libros asociados permanecen, pero sin esa referencia.

---

## 8. Casos de Uso Principales

### CU-01: Registrar un Préstamo de Libro

| Campo                | Detalle                                                                                                                                                                                                                                                                                                                           |
| :------------------- | :-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| **ID**               | CU-01                                                                                                                                                                                                                                                                                                                             |
| **Nombre**           | Registrar un préstamo de libro                                                                                                                                                                                                                                                                                                    |
| **Actor Principal**  | Bibliotecario                                                                                                                                                                                                                                                                                                                     |
| **Actores Secundarios** | Cliente, Sistema                                                                                                                                                                                                                                                                                                                  |
| **Precondiciones**   | El cliente debe estar registrado en el sistema (tabla `clientes`). El libro debe estar en el catálogo (tabla `libros`) y tener al menos un ejemplar disponible (`ejemplares_disponibles > 0`).                                                                                                                                      |
| **Postcondiciones**  | Se ha creado un nuevo registro en la tabla `alquileres` con estado 'activo'. El campo `ejemplares_disponibles` del libro se ha decrementado en 1.                                                                                                                                                                                |
| **Trigger**          | Un cliente solicita el préstamo de un libro en la biblioteca.                                                                                                                                                                                                                                                                    |
| **Flujo Principal:** | 1. El bibliotecario busca al cliente por email o nombre. <br> 2. El sistema muestra los datos del cliente y su historial de préstamos. <br> 3. El bibliotecario busca el libro por título o ISBN. <br> 4. El sistema muestra la información del libro y su disponibilidad. <br> 5. El bibliotecario confirma el préstamo. <br> 6. El sistema crea el registro en `alquileres`, establece `fecha_alquiler` (hoy) y `fecha_devolucion_prevista` (hoy + 14 días). <br> 7. El sistema decrementa `ejemplares_disponibles` en 1. <br> 8. El sistema muestra un mensaje de confirmación con la fecha de devolución prevista. |
| **Flujos Alternativos:** | FA-01: Si el libro no está disponible, se informa al cliente y se puede ofrecer ponerlo en lista de espera (funcionalidad futura). <br> FA-02: Si el cliente no está registrado, se redirige a la creación de un nuevo cliente.                                                                                             |
| **Flujos de Excepción:** | FE-01: Si ocurre un error en la base de datos (ej. concurrencia), el sistema muestra un mensaje de error y la transacción se revierte.                                                                                                                                                                                            |

---

### CU-02: Registrar una Devolución de Libro

| Campo                | Detalle                                                                                                                                                                                                                                                                                                                                                                                                                                                             |
| :------------------- | :------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------ |
| **ID**               | CU-02                                                                                                                                                                                                                                                                                                                                                                                                                                                               |
| **Nombre**           | Registrar una devolución de libro                                                                                                                                                                                                                                                                                                                                                                                                                                   |
| **Actor Principal**  | Bibliotecario                                                                                                                                                                                                                                                                                                                                                                                                                                                       |
| **Actores Secundarios** | Cliente, Sistema                                                                                                                                                                                                                                                                                                                                                                                                                                                    |
| **Precondiciones**   | El cliente debe tener un préstamo activo para el libro que desea devolver.                                                                                                                                                                                                                                                                                                                                                                                          |
| **Postcondiciones**  | El registro en `alquileres` se actualiza con `fecha_devolucion_real` (hoy) y el estado cambia a 'devuelto'. El campo `ejemplares_disponibles` del libro se incrementa en 1.                                                                                                                                                                                                                                                                                     |
| **Trigger**          | Un cliente devuelve un libro que previamente había tomado prestado.                                                                                                                                                                                                                                                                                                                                                                                                |
| **Flujo Principal:** | 1. El bibliotecario busca al cliente por email o nombre. <br> 2. El sistema muestra los datos del cliente y sus préstamos activos. <br> 3. El bibliotecario selecciona el préstamo del libro que se está devolviendo. <br> 4. El sistema confirma la devolución. <br> 5. El sistema actualiza la tabla `alquileres`, estableciendo `fecha_devolucion_real` (hoy) y el estado a 'devuelto'. <br> 6. El sistema incrementa `ejemplares_disponibles` en 1. <br> 7. El sistema muestra un mensaje de confirmación.                                                                                      |
| **Flujos Alternativos:** | FA-01: Si el préstamo tiene fecha de devolución prevista anterior a hoy, el sistema podría calcular una multa (funcionalidad futura).                                                                                                                                                                                                                                                                                                                             |
| **Flujos de Excepción:** | FE-01: Si no se encuentra el préstamo activo, se muestra un mensaje de error. FE-02: Si ocurre un error en la base de datos, la transacción se revierte.                                                                                                                                                                                                                                                                                                           |

---

### CU-03: Crear un Nuevo Libro

| Campo                | Detalle                                                                                                                                                                                                                                                                                                 |
| :------------------- | :------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------ |
| **ID**               | CU-03                                                                                                                                                                                                                                                                                                   |
| **Nombre**           | Crear un nuevo libro en el catálogo                                                                                                                                                                                                                                                                     |
| **Actor Principal**  | Bibliotecario                                                                                                                                                                                                                                                                                           |
| **Actores Secundarios** | Sistema                                                                                                                                                                                                                                                                                                 |
| **Precondiciones**   | El autor y la categoría deben existir en el sistema.                                                                                                                                                                                                                                                   |
| **Postcondiciones**  | Se ha creado un nuevo registro en la tabla `libros`.                                                                                                                                                                                                                                                    |
| **Trigger**          | El bibliotecario necesita añadir un nuevo libro al catálogo.                                                                                                                                                                                                                                            |
| **Flujo Principal:** | 1. El bibliotecario accede a la opción "Crear Libro". <br> 2. Completa el formulario con título, selecciona un autor, selecciona una categoría, ISBN, año y número de ejemplares totales. <br> 3. El sistema valida que los campos obligatorios no estén vacíos. <br> 4. El sistema valida que el ISBN no esté duplicado. <br> 5. El sistema guarda el libro. <br> 6. El sistema muestra un mensaje de confirmación con el detalle del libro creado. |
| **Flujos Alternativos:** | FA-01: Si el ISBN ya existe, el sistema muestra un mensaje de error. <br> FA-02: Si el autor o categoría no existen, el bibliotecario debe crearlos primero.                                                                                                                                         |
| **Flujos de Excepción:** | FE-01: Si ocurre un error en la base de datos, la transacción se revierte y se muestra un mensaje de error.                                                                                                                                                                                              |

---

## 9. Criterios de Aceptación

### CA-001: Préstamo exitoso
- **Given:** El libro "Cien años de soledad" tiene 3 ejemplares disponibles. El cliente "Ana García" está registrado.
- **When:** El bibliotecario crea un préstamo para Ana García y este libro.
- **Then:** Se crea un nuevo registro en `alquileres` con estado 'activo'. Los ejemplares disponibles del libro pasan a ser 2.

### CA-002: Devolución exitosa
- **Given:** El cliente "Ana García" tiene un préstamo activo del libro "Cien años de soledad".
- **When:** El bibliotecario registra la devolución.
- **Then:** El préstamo se marca como 'devuelto' y se registra la fecha de devolución real. Los ejemplares disponibles del libro pasan a ser 3.

### CA-003: Préstamo no permitido por falta de disponibilidad
- **Given:** El libro "El Principito" tiene 0 ejemplares disponibles.
- **When:** El bibliotecario intenta crear un préstamo para este libro.
- **Then:** El sistema muestra un mensaje de error y no permite completar la operación.

### CA-004: Búsqueda de libros por título
- **Given:** Existen libros con "cien" en el título.
- **When:** Un usuario busca "cien" en el campo de búsqueda de título.
- **Then:** El sistema muestra una lista paginada con todos los libros que coinciden.

### CA-005: Creación de autor exitosa
- **Given:** Un autor llamado "Gabriel García Márquez" no existe en la base de datos.
- **When:** El bibliotecario envía un POST a `/api/authores` con nombre "Gabriel García Márquez" y nacionalidad "Colombiana".
- **Then:** El sistema guarda el autor, retorna un estado 201 (Created) y el autor se puede listar.

### CA-006: Autenticación correcta (JWT)
- **Given:** Un usuario con credenciales válidas (ej. "admin" / "password").
- **When:** El usuario envía sus credenciales al endpoint `/api/auth/login`.
- **Then:** El sistema retorna un estado 200 (OK) y un token JWT en la respuesta.

### CA-007: Protección de endpoint
- **Given:** Un usuario no autenticado intenta acceder al endpoint `POST /api/libros`.
- **When:** El usuario envía una solicitud sin token JWT.
- **Then:** El sistema retorna un estado 401 (Unauthorized).

---

## 10. Riesgos Técnicos

| ID     | Riesgo                                                                           | Probabilidad | Impacto | Nivel    | Plan de Mitigación                                                                                                |
| :----- | :------------------------------------------------------------------------------- | :----------- | :------ | :------- | :---------------------------------------------------------------------------------------------------------------- |
| RT-001 | Conflictos de concurrencia al prestar/devolver un libro simultáneamente.         | Media        | Alto    | Alto     | Implementar bloqueo pesimista u optimista a nivel de base de datos para actualizar `ejemplares_disponibles`.       |
| RT-002 | Degradación del rendimiento al crecer el catálogo de libros sin índices.         | Media        | Medio   | Medio    | Crear índices en las columnas más consultadas (ej. `titulo`, `autor_id`).                                          |
| RT-003 | Vulnerabilidades de seguridad en la autenticación JWT.                           | Baja         | Alto    | Alto     | Utilizar bibliotecas seguras, firmar tokens con una clave secreta robusta y establecer tiempos de expiración cortos. |
| RT-004 | Inconsistencia de datos entre la tabla `libros` y `alquileres` por fallos lógicos. | Baja         | Alto    | Alto     | Utilizar transacciones de base de datos (`@Transactional`) para asegurar la atomicidad de las operaciones de préstamo/devolución. |
| RT-005 | Pérdida de conexión con la base de datos.                                        | Media        | Alto    | Alto     | Configurar un pool de conexiones y mecanismos de reconexión automática.                                           |
| RT-006 | Fallo en el manejo de fechas/husos horarios.                                     | Baja         | Medio   | Bajo     | Definir un estándar de fecha (UTC) y documentarlo para el equipo de desarrollo.                                    |

---

**Fin del documento**