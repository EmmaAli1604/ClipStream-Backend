-- Borrar base de datos para reinicio.
DROP SCHEMA public CASCADE;
CREATE SCHEMA public;

DROP TABLE IF EXISTS Usuario CASCADE;

-- Tabla para almacenar información de los usuarios.
CREATE TABLE Usuario (
    UsuarioID BIGSERIAL PRIMARY KEY,
    RolID INT DEFAULT 2,
    Nombre VARCHAR(100),
    UserName VARCHAR(50),
    Apellido VARCHAR(100),
    Correo VARCHAR(50),
    Password VARCHAR(50),
    Token VARCHAR(50)
);

-- Tabla para almacenar los roles de los usuarios.
CREATE TABLE Rol(
    RolID INT,
    Nombre varchar(45)
);

-- Tabla para almacenar generos de cortometrajes.
CREATE TABLE Genero (
    GeneroID BIGINT,
    NombreGenero VARCHAR(80)
); 


DROP TABLE IF EXISTS Resena CASCADE;

-- Tabla para almacenar un cortometraje
CREATE TABLE Cortometraje (
	CortometrajeID BIGSERIAL,
	UsuarioID BIGINT,
	GeneroID BIGINT,
	Nombre varchar(80),
	Sinopsis varchar(500),
	Fecha DATE,
	Foto varchar(800),
	Video varchar(800),
	Director varchar(80),
	NumVistas INT DEFAULT 0,
	Calificacion numeric DEFAULT 0
);

-- Tabla para las reseñas/comentarios de los cortometrajes.
create table Resena (
	ResenaID BIGSERIAL,
	UsuarioID BIGINT,
	CortometrajeID BIGINT,
	Contenido VARCHAR(200),
	Likes BIGINT DEFAULT 0,
	FechaCreacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Tabla para relacionar la calificacion que le da un usuario a un cortometraje
CREATE TABLE Calificar (
    CortometrajeID BIGINT,
    UsuarioID BIGINT,
    Puntuacion NUMERIC
);

---- RESTRICCIONES ----

-- RESTRICCIONES TABLA USUARIO
ALTER TABLE Usuario ALTER COLUMN UsuarioID SET NOT NULL;
ALTER TABLE Usuario ALTER COLUMN RolID SET NOT NULL;
ALTER TABLE Usuario ADD CONSTRAINT usuario_d1 CHECK (Nombre <> '');
ALTER TABLE Usuario ADD CONSTRAINT usuario_d2 CHECK (Apellido <> '');
ALTER TABLE Usuario ADD CONSTRAINT usuario_d4 CHECK (Correo like '%_@_%._%');
ALTER TABLE Usuario ADD CONSTRAINT usuario_d5 CHECK (UserName <> '');

-- RESTRICCIONES TABLA ROL
ALTER TABLE Rol ALTER COLUMN RolID SET NOT NULL;
ALTER TABLE Rol ALTER COLUMN Nombre SET NOT NULL;

-- RESTRICCIONES TABLA GENERO
ALTER TABLE Genero ALTER COLUMN GeneroID SET NOT NULL;
ALTER TABLE Genero ADD CONSTRAINT genero_d1 CHECK (NombreGenero <> '');

-- RESTRICCIONES TABLA CORTOMETRAJE
ALTER TABLE Cortometraje ALTER COLUMN CortometrajeID SET NOT NULL;
ALTER TABLE Cortometraje ALTER COLUMN UsuarioID SET NOT NULL;
ALTER TABLE Cortometraje ALTER COLUMN GeneroID SET NOT NULL;

-- RESTRICCIONES TABLA CALIFICAR
ALTER TABLE Calificar ALTER COLUMN CortometrajeID SET NOT NULL;
ALTER TABLE Calificar ALTER COLUMN UsuarioID SET NOT NULL;


---- LLAVES PRIMARIAS ----

-- LLAVES PRIMARIAS USUARIO
ALTER TABLE Usuario ADD CONSTRAINT pk_usuario PRIMARY KEY (UsuarioID);

-- LLAVES PRIMARIAS ROL
ALTER TABLE Rol ADD CONSTRAINT pk_rol PRIMARY KEY (RolID);

-- LLAVES PRIMARIAS GENERO
ALTER TABLE Genero ADD CONSTRAINT pk_genero PRIMARY KEY (GeneroID);

-- LLAVES PRIMARIAS CORTOMETRAJE
ALTER TABLE Cortometraje ADD CONSTRAINT pk_cortometraje PRIMARY KEY (CortometrajeID);

-- LLAVES PRIMARIAS TABLA Comentarios
AlTER table Resena add constraint pk_resena primary key (ResenaID);


---- LLAVES FORÁNEAS ----

-- LLAVES FORÁNEAS CORTOMETRAJE
ALTER TABLE Cortometraje ADD CONSTRAINT fk_cortometraje_usuario FOREIGN KEY (UsuarioID) REFERENCES Usuario(UsuarioID)
    ON DELETE CASCADE ON UPDATE CASCADE;
ALTER TABLE Cortometraje ADD CONSTRAINT fk_generoid FOREIGN KEY (GeneroID) REFERENCES Genero(GeneroID)
    ON DELETE CASCADE ON UPDATE CASCADE; 

-- LLAVES FORÁENAS USUARIO
ALTER TABLE Usuario ADD CONSTRAINT fk_usuario_rol FOREIGN KEY (RolID) REFERENCES Rol(RolID)
    ON DELETE SET NULL ON UPDATE CASCADE;

-- LLAVES FORÁNEAS comentarios
alter table Resena add constraint fk_usuario_id foreign key (UsuarioID) references Usuario(UsuarioID)
    ON DELETE CASCADE ON UPDATE CASCADE;

alter table Resena add constraint fk_cortometraje_id foreign key (CortometrajeID) references Cortometraje(CortometrajeID)
    ON DELETE CASCADE ON UPDATE CASCADE;


-- LLAVES FORÁNEAS CALIFICAR
ALTER TABLE Calificar ADD CONSTRAINT fk_usuario_calificar FOREIGN KEY (UsuarioID) REFERENCES Usuario(UsuarioID)
    ON DELETE SET NULL ON UPDATE CASCADE;
ALTER TABLE Calificar ADD CONSTRAINT fk_cortometraje_calificar FOREIGN KEY (CortometrajeID) REFERENCES Cortometraje(CortometrajeID)
    ON DELETE CASCADE ON UPDATE CASCADE;

   
CREATE OR REPLACE FUNCTION actualizar_promedio_calificacion()
RETURNS trigger
LANGUAGE plpgsql
AS $$
DECLARE
    ids bigint[];      -- arreglo que siempre será array
    uniq_id bigint;
    v_avg numeric;
BEGIN
    IF TG_OP = 'INSERT' THEN
        ids := ARRAY[NEW.CortometrajeID]::bigint[];

    ELSIF TG_OP = 'DELETE' THEN
        ids := ARRAY[OLD.CortometrajeID]::bigint[];

    ELSIF TG_OP = 'UPDATE' THEN
        IF OLD.CortometrajeID IS DISTINCT FROM NEW.CortometrajeID THEN
            ids := ARRAY[OLD.CortometrajeID, NEW.CortometrajeID]::bigint[];
        ELSE
            ids := ARRAY[NEW.CortometrajeID]::bigint[];
        END IF;

    ELSE
        RETURN NULL;
    END IF;

    -- Recorrer IDs únicos y recalcular
    FOREACH uniq_id IN ARRAY ids
    LOOP
        -- NULL significa que no se debe actualizar nada
        IF uniq_id IS NULL THEN
            CONTINUE;
        END IF;

        SELECT AVG(Puntuacion)
        INTO v_avg
        FROM Calificar
        WHERE CortometrajeID = uniq_id;

        UPDATE Cortometraje
        SET Calificacion = v_avg
        WHERE CortometrajeID = uniq_id;
    END LOOP;

    RETURN NULL;
END;
$$;

DROP TRIGGER IF EXISTS trg_actualizar_promedio_calificacion ON Calificar;

CREATE TRIGGER trg_actualizar_promedio_calificacion
AFTER INSERT OR UPDATE OR DELETE ON Calificar
FOR EACH ROW EXECUTE FUNCTION actualizar_promedio_calificacion();

-- Roles (No mover)
INSERT INTO Rol (RolID, Nombre) VALUES (1, 'User');
INSERT INTO Rol (RolID, Nombre) VALUES (2, 'Admin');


-- Géneros de películas (acción, aventura, comedia, drama, terror, ciencia ficción, fantasía, romance)
INSERT INTO Genero (GeneroID, NombreGenero) VALUES (1, 'Drama');
INSERT INTO Genero (GeneroID, NombreGenero) VALUES (2, 'Acción');
INSERT INTO Genero (GeneroID, NombreGenero) VALUES (3, 'Aventura');
INSERT INTO Genero (GeneroID, NombreGenero) VALUES (4, 'Comedia');
INSERT INTO Genero (GeneroID, NombreGenero) VALUES (5, 'Terror');
INSERT INTO Genero (GeneroID, NombreGenero) VALUES (6, 'Ciencia ficción');
INSERT INTO Genero (GeneroID, NombreGenero) VALUES (7, 'Fantasía');
INSERT INTO Genero (GeneroID, NombreGenero) VALUES (8, 'Romance');