DROP TABLE IF EXISTS usuarios;

CREATE TABLE usuarios (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    nombreUsuario TEXT UNIQUE NOT NULL,
    contrasenia TEXTNOT NULL,
    email TEXT UNIQUE NOT NULL
);

-- Tabla de productos
CREATE TABLE IF NOT EXISTS productos (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    nombre TEXT NOT NULL,
    descripcion TEXT,
    precio REAL NOT NULL,
    imagen TEXT,
);


-- Tabla del carrito
CREATE TABLE IF NOT EXISTS carrito (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    id_usuario INTEGER,
    id_producto INTEGER,
    cantidad INTEGER,
    FOREIGN KEY(id_usuario) REFERENCES usuarios(id),
    FOREIGN KEY(id_producto) REFERENCES productos(id)
);

-- Tabla de pedidos
CREATE TABLE IF NOT EXISTS pedidos (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    id_usuario INTEGER,
    fecha TEXT,
    total REAL,
    estado TEXT,
    FOREIGN KEY(id_usuario) REFERENCES usuarios(id)
);

-- Detalles de cada pedido
CREATE TABLE IF NOT EXISTS pedido_detalles (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    id_pedido INTEGER,
    id_producto INTEGER,
    cantidad INTEGER,
    FOREIGN KEY(id_pedido) REFERENCES pedidos(id),
    FOREIGN KEY(id_producto) REFERENCES productos(id)
);

-- Tabla de reembolsos
CREATE TABLE IF NOT EXISTS reembolsos (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    id_pedido INTEGER,
    motivo TEXT,
    estado TEXT,
    FOREIGN KEY(id_pedido) REFERENCES pedidos(id)
);
