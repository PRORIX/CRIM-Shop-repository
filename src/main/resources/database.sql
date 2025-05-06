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
    nombreCodigo TEXT,
    precio REAL NOT NULL
);

INSERT INTO productos (nombre, descripcion, nombreCodigo, precio) VALUES
('Sudadera Oficial CRIM', 'La oficial, la que representa todo lo que somos: calle, actitud, y cero ganas de encajar en un mundo que huele a plástico. Esta sudadera no está hecha pa’ gustarle a tu jefe, ni pa’ ir mon@ a una entrevista. Está hecha pa’ gritar sin abrir la boca que eres parte de un espacio libre, sin normas absurdas ni censura. CRIM no es solo ropa, es una declaración de intenciones: si no molestas, no estás haciendo nada.','sudadera', 24.99),
('Gorra Negra CRIM OF', 'Negra como tu alma después de otra jornada currando por cuatro duros. Esta gorra no es solo un accesorio, es tu escudo en una jungla de mediocres. CRIM OF en el frente para que el mundo sepa que tú no sigues normas de nadie. No llevas marca, llevas mensaje: libertad, calle y cero postureo.', 'gorra', 9.99),
('Suéter Dragón CRIM', 'Un dragón que no solo escupe fuego, escupe verdades. Como tú, que vas por la vida sin filtro, sin pedir permiso. Este suéter no está hecho para agradar a nadie, está hecho para que lo vistas mientras pateas un sistema que quiere que todos vistamos igual, pensemos igual y callemos la boca. Pues no, compa. Aquí rugimos. Y encima vamos calentitos.', 'sueter', 19.99),
('Camiseta MSQMA', 'MSQMA: Mejor Solo Que Mal Acompañado. No es una frase, es una declaración de guerra. Contra relaciones tóxicas, contra curros explotadores, contra gobiernos que solo dan la cara en campaña. Si vas solo, vas libre. Esta camiseta es para quienes no le deben nada a nadie, y no se callan por quedar bien. Estás sol@, pero nunca baj@.', 'camiseta_msqma', 19.99),
('Camiseta ro-BOT ardiendo', 'Un robot ardiendo... como el algoritmo cuando no entiende por qué tu rollo no encaja. Pues porque no naciste pa’ ser máquina, ni pa’ encajar. Esta camiseta es pa’ los que no se dejan programar por ningún sistema. Arde, lucha, sé error 404 en un mundo de copias. Y si molesta, mejor.', 'camiseta_robot', 19.99),
('Sudadera BASIC', 'Sí, se llama BASIC, pero esto no es para gente básica. Es para lxs que saben que no hace falta ir disfrazado pa’ tener presencia. Sudadera sencilla, directa, sin adornos, como un puñetazo en el pecho al sistema. Aquí no se juega a ser influencer, aquí se es calle, se es real. Porque en un mundo de filtros, la autenticidad es revolución.', 'sudadera_basic', 23.99);



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


