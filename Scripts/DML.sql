-- database: ../DataBase/prjDataBase.sqlite
INSERT INTO AdministradorTipo (Nombre)
    VALUES 
    ('SuperAdministrador')
    ,('AdministradorPinturas');

INSERT INTO Supervisor (Codigo, Tipo)
    VALUES 
    ('1234567890123' , 2)
    ,('1234567890124', 2)
    ,('1234567890125', 2);

INSERT INTO Categoria (Nombre)
    VALUES 
    ('Pintura')
    ,('Escultura')
    ,('Fotografia');

INSERT INTO Seccion (Nombre)
    VALUES 
    ('Rupestre')
    ,('Clasico')
    ,('Contemporaneo');

INSERT INTO PiezaDeArte (BarCode, Nombre, Autor, Descripcion, PrecioReplica, idCategoria, idSeccion)
    VALUES 
    ('0234567890123'    , 'Mona Lisa'   , 'Leonardo Da Vinci', 'Pintura al oleo'    , 10.90 , 1, 2)
    ,('0234567890124'   , 'La Piedad'   , 'Miguel Angel'     , 'Escultura en marmol', 20.50 , 2, 2)
    ,('0234567890125'   , 'La persistencia de la memoria'    , 'Salvador Dali'  , 'Pintura al oleo', 10.90, 1, 3)
    ,('0234567890126'   , 'ArtCyberpunk', 'Desconocido'      , 'Fotografia Elaborada Por IA', 05.50, 3, 3);

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
