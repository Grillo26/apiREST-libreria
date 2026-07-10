-- Tabla: clientes
CREATE TABLE IF NOT EXISTS clientes (
                                        id BIGSERIAL PRIMARY KEY,
                                        nombre VARCHAR(100) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    telefono VARCHAR(20),
    fecha_registro TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    is_eliminado BOOLEAN DEFAULT FALSE
    );

-- Tabla: autores
CREATE TABLE IF NOT EXISTS autores (
                                       id BIGSERIAL PRIMARY KEY,
                                       nombre VARCHAR(100) NOT NULL,
    nacionalidad VARCHAR(50)
    );

-- Tabla: categorías
CREATE TABLE IF NOT EXISTS categorias (
                                          id BIGSERIAL PRIMARY KEY,
                                          nombre VARCHAR(50) NOT NULL,
    descripcion TEXT
    );

-- Tabla: libros
CREATE TABLE IF NOT EXISTS libros (
                                      id BIGSERIAL PRIMARY KEY,
                                      titulo VARCHAR(200) NOT NULL,
    autor_id BIGINT REFERENCES autores(id) ON DELETE SET NULL,
    categoria_id BIGINT REFERENCES categorias(id) ON DELETE SET NULL,
    isbn VARCHAR(20) UNIQUE,
    anio_publicacion INTEGER,
    ejemplares_total INTEGER NOT NULL DEFAULT 1,
    ejemplares_disponibles INTEGER NOT NULL DEFAULT 1
    );

-- Tabla: alquileres
CREATE TABLE IF NOT EXISTS alquileres (
                                          id BIGSERIAL PRIMARY KEY,
                                          cliente_id BIGINT NOT NULL REFERENCES clientes(id) ON DELETE CASCADE,
    libro_id BIGINT NOT NULL REFERENCES libros(id) ON DELETE CASCADE,
    fecha_alquiler DATE NOT NULL,
    fecha_devolucion_prevista DATE NOT NULL,
    fecha_devolucion_real DATE,
    estado VARCHAR(20) DEFAULT 'activo' CHECK (estado IN ('activo', 'devuelto', 'atrasado'))
    );

-- Tabla: reseñas
CREATE TABLE IF NOT EXISTS resenias (
                                        id BIGSERIAL PRIMARY KEY,
                                        cliente_id BIGINT NOT NULL REFERENCES clientes(id) ON DELETE CASCADE,
    libro_id BIGINT NOT NULL REFERENCES libros(id) ON DELETE CASCADE,
    puntuacion INTEGER CHECK (puntuacion BETWEEN 1 AND 5),
    comentario TEXT,
    fecha_resenia TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    UNIQUE (cliente_id, libro_id)
    );

-- Índices
CREATE INDEX IF NOT EXISTS idx_libros_titulo ON libros(titulo);
CREATE INDEX IF NOT EXISTS idx_alquileres_cliente ON alquileres(cliente_id);
CREATE INDEX IF NOT EXISTS idx_alquileres_estado ON alquileres(estado);
CREATE INDEX IF NOT EXISTS idx_resenias_libro ON resenias(libro_id);