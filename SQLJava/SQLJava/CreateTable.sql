USE BIBLIOTECA;

-- Tabla GENEROS
CREATE TABLE GENEROS (
  id INT IDENTITY(1,1) PRIMARY KEY,
  nombre VARCHAR(255)
);

-- Tabla EDITORIALES
CREATE TABLE EDITORIALES (
  id INT IDENTITY(1,1) PRIMARY KEY,
  nombre VARCHAR(255)
);

-- Tabla AUTORES
CREATE TABLE AUTORES (
  id INT IDENTITY(1,1) PRIMARY KEY,
  nombre VARCHAR(255),
  apellido VARCHAR(255)
);

-- Tabla LIBROS
CREATE TABLE LIBROS (
  id INT IDENTITY(1,1) PRIMARY KEY,
  nombre VARCHAR(255),
  paginas INT,
  autor INT,
  a�o_publicacion INT,
  editorial INT,
  genero INT,
  FOREIGN KEY (autor) REFERENCES AUTORES(id),
  FOREIGN KEY (genero) REFERENCES GENEROS(id),
  FOREIGN KEY (editorial) REFERENCES EDITORIALES(id)
);

-- Tabla TIPO_IDENTIDAD
CREATE TABLE TIPO_IDENTIDAD (
  id INT IDENTITY(1,1) PRIMARY KEY,
  tipo VARCHAR(255)
);

-- Tabla USUARIOS
CREATE TABLE USUARIOS (
  id INT IDENTITY(1,1) PRIMARY KEY,
  nombre VARCHAR(255),
  apellido VARCHAR(255),
  tipo_identidad INT,
  numero_identidad VARCHAR(20),
  numero_celular VARCHAR(20),
  FOREIGN KEY (tipo_identidad) REFERENCES TIPO_IDENTIDAD(id)
);

-- Tabla PRESTAMOS
CREATE TABLE PRESTAMOS (
  id INT IDENTITY(1,1) PRIMARY KEY,
  usuarioID INT,
  libroID INT,
  fecha DATE,
  activo BIT,
  FOREIGN KEY (usuarioID) REFERENCES USUARIOS(id),
  FOREIGN KEY (libroID) REFERENCES LIBROS(id)
);