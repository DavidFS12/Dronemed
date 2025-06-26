-- Tabla de usuarios
CREATE TABLE usuarios (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    nombre TEXT NOT NULL,
    correo TEXT NOT NULL UNIQUE,
    password TEXT NOT NULL,
    rol TEXT NOT NULL -- Cliente, Técnico, Admin
);

-- Tabla de drones
CREATE TABLE drones (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    modelo TEXT NOT NULL,
    tipo TEXT NOT NULL, -- Ligero, Medio, Pesado
    capacidad INTEGER NOT NULL,
    estado TEXT NOT NULL, -- Activo, Inactivo
    ultima_revision DATE
);

-- Tabla de pedidos
CREATE TABLE pedidos (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    id_cliente INTEGER,
    prioridad TEXT NOT NULL, -- Urgente, Estándar
    estado TEXT NOT NULL, -- Pendiente, Asignado, Entregado
    fecha_creacion DATE,
    id_dron INTEGER,
    FOREIGN KEY (id_cliente) REFERENCES usuarios(id),
    FOREIGN KEY (id_dron) REFERENCES drones(id)
);

-- Tabla de rutas
CREATE TABLE rutas (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    origen TEXT NOT NULL,
    destino TEXT NOT NULL,
    condiciones TEXT
);

-- Tabla de mantenimientos
CREATE TABLE mantenimientos (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    id_dron INTEGER,
    fecha DATE,
    detalles TEXT,
    tecnico TEXT,
    FOREIGN KEY (id_dron) REFERENCES drones(id)
);
