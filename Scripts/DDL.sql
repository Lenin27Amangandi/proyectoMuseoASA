-- database: ../DataBase/prjDataBase.sqlite


DROP TABLE IF EXISTS AdministradorTipo;
DROP TABLE IF EXISTS Administrador;
DROP TABLE IF EXISTS Categoria;
DROP TABLE IF EXISTS Seccion;
DROP TABLE IF EXISTS PiezaDeArte;


CREATE TABLE AdministradorTipo(
    idAdministradorTipo  INTEGER PRIMARY KEY AUTOINCREMENT
    ,Nombre              TEXT NOT NULL

    ,Estado              VARCHAR(1) NOT NULL DEFAULT('A')
    ,FechaCrea           DATETIME DEFAULT(datetime('now','localtime'))
    ,FechaModifica       DATETIME
);

INSERT INTO AdministradorTipo (Nombre)
    VALUES 
    ('SuperAdministrador')
    ,('AdministradorPintura');

SELECT idAdministradorTipo
    , Nombre
    , Estado
    , FechaCrea
    , FechaModifica
FROM AdministradorTipo WHERE Estado = 'A';


    SELECT idAdministradorTipo
        ,Nombre 
        ,Estado
        ,FechaCrea
        ,FechaModifica
    FROM AdministradorTipo
    WHERE Estado = 'A';


CREATE TABLE Administrador(
    idAdministrador     INTEGER PRIMARY KEY AUTOINCREMENT
    --Check sirve para validar que el campo cumpla con una condicion en este caso que sea de 13 caracteres.
    ,Codigo             TEXT CHECK(length(Codigo) = 13) NOT NULL UNIQUE
    ,Tipo               INTEGER NOT NULL

    ,Estado             VARCHAR(1) NOT NULL DEFAULT('A')
    ,FechaCrea          DATETIME DEFAULT(datetime('now','localtime'))
    ,FechaModifica      DATETIME
    ,FOREIGN KEY (Tipo) REFERENCES AdministradorTipo(idAdministradorTipo)
);

SELECT idAdministrador 
                , Codigo              
                , Tipo                
                , Estado              
                , FechaCrea           
                , FechaModifica       
                FROM Administrador 
                WHERE Estado='A' 
                AND Codigo = "1753193828123";

INSERT INTO Administrador (Codigo, Tipo)
    VALUES 
    ('1234567890111' , 1)
    ,('1234567890123', 2)
    ,('1234567890124', 2)
    ,('1234567890125', 2);

UPDATE Administrador 
SET Estado = 'X', FechaModifica = datetime('now','localtime') 
WHERE Codigo = '1234567890123';

CREATE TABLE Categoria (
    idCategoria         INTEGER PRIMARY KEY AUTOINCREMENT
    ,Nombre             TEXT NOT NULL

    ,Estado             VARCHAR(1) NOT NULL DEFAULT('A')
    ,FechaCrea          DATETIME DEFAULT(datetime('now','localtime'))
    ,FechaModifica      DATETIME
);

INSERT INTO Categoria (Nombre)
    VALUES 
    ('Pintura')
    ,('Escultura')
    ,('Fotografia');

CREATE TABLE Seccion(
    idSeccion          INTEGER PRIMARY KEY AUTOINCREMENT
    ,Nombre             TEXT NOT NULL

    ,Estado             VARCHAR(1) NOT NULL DEFAULT('A')
    ,FechaCrea          DATETIME DEFAULT(datetime('now','localtime'))
    ,FechaModifica      DATETIME
);

INSERT INTO Seccion (Nombre)
    VALUES 
    ('Rupestre')
    ,('Clasico')
    ,('Contemporaneo');

SELECT idSeccion 
,Nombre          
,Estado          
,FechaCrea       
,FechaModifica
FROM Seccion   
WHERE Estado='A' 
AND   idSeccion = 3;

CREATE TABLE PiezaDeArte(
    idPieza          INTEGER PRIMARY KEY AUTOINCREMENT
    ,BarCode            TEXT CHECK(length(BarCode) = 13) NOT NULL UNIQUE
    ,Nombre             TEXT NOT NULL
    ,Autor              TEXT    
    ,Descripcion        TEXT NOT NULL
    ,PrecioReplica      REAL NOT NULL
    ,idCategoria        INTEGER NOT NULL
    ,idSeccion          INTEGER NOT NULL

    ,Estado             VARCHAR(1) NOT NULL DEFAULT('A')
    ,FechaCrea          DATETIME DEFAULT(datetime('now','localtime'))
    ,FechaModifica      DATETIME
    ,FOREIGN KEY (idCategoria) REFERENCES Categoria(idCategoria)
    ,FOREIGN KEY (idSeccion) REFERENCES Seccion(idSeccion)
);

    SELECT Nombre FROM PiezaDeArte WHERE Estado = 'A' AND BarCode = 0234567890124;

    SELECT idPieza
                ,BarCode          
                ,Nombre           
                ,Autor            
                ,Descripcion      
                ,PrecioReplica    
                ,idCategoria      
                ,idSeccion        
                ,Estado           
                ,FechaCrea        
                ,FechaModifica    
    From PiezaDeArte
    WHERE Estado='A'
    AND   BarCode = "0234567890123";

INSERT INTO PiezaDeArte (BarCode, Nombre, Autor, Descripcion, PrecioReplica, idCategoria, idSeccion)
    VALUES 
    ('0234567890123'    , 'Mona Lisa'   , 'Leonardo Da Vinci', 'Pintura al oleo'    , 10.90 , 1, 2)
    ,('0234567890124'   , 'La Piedad'   , 'Miguel Angel'     , 'Escultura en marmol', 20.50 , 2, 2)
    ,('0234567890125'   , 'La persistencia de la memoria'    , 'Salvador Dali'  , 'Pintura al oleo', 10.90, 1, 3)
    ,('0234567890126'   , 'ArtCyberpunk', 'Desconocido'      , 'Fotografia Elaborada Por IA', 05.50, 3, 3);

    SELECT Autor FROM PiezaDeArte WHERE Estado = 'A' AND BarCode = "0234567890125";


UPDATE PiezaDeArte 
SET Nombre = 'El Grito', Descripcion = 'Pintura expresionista', PrecioReplica = 15.00, FechaModifica = datetime('now','localtime') 
WHERE BarCode = '0234567890123';

-- UPDATE PiezaDeArte SET Nombre = ?, Autor = ? ,Descripcion = ? , PrecioReplica = ? , idSeccion = ?, idCategoria = ? ,  FechaModifica = ? WHERE BarCode = ?
    UPDATE PiezaDeArte 
    SET Nombre = 'Noche Estrelladaaa', Autor = 'Vincent van Gogh', Descripcion = 'Pintura postimpresionista', PrecioReplica = 25.00, idCategoria = 1, idSeccion = 3, FechaModifica = datetime('now','localtime') 
    WHERE BarCode = '0234567890125';

    SELECT
        p.idPieza,
        p.BarCode,
        p.Nombre AS NombrePieza,
        p.Autor,
        p.Descripcion,
        p.PrecioReplica,
        c.Nombre AS NombreCategoria,
        s.Nombre AS NombreSeccion
    FROM 
        PiezaDeArte p
    JOIN 
        Categoria c ON p.idCategoria = c.idCategoria
    JOIN 
        Seccion s ON p.idSeccion = s.idSeccion;

