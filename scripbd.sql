--Categoria
CREATE TABLE CATEGORIA(
    idCategoria INT PRIMARY KEY,
    nomCategoria TEXT
);

--cliente
CREATE TABLE CLIENTE(
    idCliente INT PRIMARY KEY,
    nomCliente TEXT,
    ape1Cliente TEXT,
    ape2Cliente TEXT,
    rfcCliente TEXT,
    generoCliente TEXT,
    correoCliente TEXT,
    telCliente TEXT
);

--Compras
CREATE TABLE COMPRAS (
    idCompra INT PRIMARY KEY,
    descripcion TEXT,
    fecha DATE,
    total FLOAT
);

    
--empledo/usuario
CREATE TABLE EMPLEADO(
    idEmpleado INT PRIMARY KEY,
    nombre TEXT,
    ape1Usuario TEXT,
    ape2Usuario TEXT,
    clave TEXT,
    usuario TEXT,
    clave TEXT
);

--producto
CREATE TABLE PRODUCTO(
    idProducto INT PRIMARY KEY,
    nomProducto TEXT,
    idCategoria INT,
    tam TEXT,
    precio FLOAT,
    stock INT,
    FOREIGN KEY (idCategoria) REFERENCES CATEGORIA(idCategoria) -- Definir la llave foránea
);

--ventas
CREATE TABLE VENTA(
    idVenta INT PRIMARY KEY,
    idEmpleado INT, 
    idCliente INT,
    fecha DATE,
    mPago TEXT,
    total FLOAT,
    FOREIGN KEY (idEmpleado) REFERENCES EMPLEADO(idEmpleado), -- Definir la llave foránea
    FOREIGN KEY (idCliente) REFERENCES CLIENTE(idCliente) -- Definir la llave foránea
);

CREATE TABLE DETALLE_VENTA(
    idDetalle INT,
    idProducto INT,
    idVenta INT,
    cantidad INT,
    pUnitario FLOAT,
    total FLOAT,
    FOREIGN KEY (idProducto) REFERENCES PRODUCTO(idProducto), -- Definir la llave foránea
    FOREIGN KEY (idVenta) REFERENCES VENTA(idVenta) -- Definir la llave foránea
);

CREATE TABLE DEVOLUCION(
    idDevolucion INT PRIMARY KEY,
    idVenta INT,
    idProducto INT,
    cantidadDevuelta INT,
    fechaDevuelta DATE,
    motivo TEXT,
    FOREIGN KEY (idVenta) REFERENCES VENTA(idVenta),
    FOREIGN KEY (idProducto) REFERENCES PRODUCTO(idProducto)
);


CREATE TABLE PAGO_VENTA (
    idPago INT PRIMARY KEY,
    idVenta INT,
    fechaPago DATE,
    monto FLOAT,
    nombreClienteNoRegistrado TEXT, -- Nueva columna para el nombre del cliente no registrado
    FOREIGN KEY (idVenta) REFERENCES VENTA(idVenta) -- Definir la llave foránea
);


CREATE VIEW VistaDetalleVenta AS
SELECT dv.idDetalle, v.idVenta, c.nomCliente || ' ' || c.ape1Cliente || ' ' || c.ape2Cliente AS nombreCliente,
       v.fecha, p.nomProducto || ' ' || p.tam, dv.cantidad, dv.pUnitario, dv.total
FROM DETALLE_VENTA dv
INNER JOIN VENTA v ON dv.idVenta = v.idVenta
INNER JOIN CLIENTE c ON v.idCliente = c.idCliente
INNER JOIN PRODUCTO p ON dv.idProducto = p.idProducto;


CREATE VIEW VistaDevoluciones AS
SELECT 
    d.idDevolucion,
    d.idVenta,
    p.nomProducto || ' ' || p.tam AS productoTam,
    d.cantidadDevuelta,
    d.fechaDevuelta,
    d.motivo
FROM 
    DEVOLUCION d
JOIN 
    VENTA v ON d.idVenta = v.idVenta
JOIN 
    PRODUCTO p ON d.idProducto = p.idProducto;


CREATE TRIGGER reducir_stock AFTER INSERT ON DETALLE_VENTA
BEGIN
  UPDATE PRODUCTO
  SET stock = stock - NEW.cantidad
  WHERE idProducto = NEW.idProducto;
END;

CREATE TRIGGER actualizar_venta AFTER INSERT ON DEVOLUCION
BEGIN
    UPDATE VENTA
    SET total = total - (SELECT pUnitario * NEW.cantidadDevuelta
                        FROM DETALLE_VENTA
                        WHERE idVenta = NEW.idVenta AND idProducto = NEW.idProducto)
    WHERE idVenta = NEW.idVenta;
END;


CREATE TRIGGER actualizar_detalle_venta AFTER INSERT ON DEVOLUCION
BEGIN
    UPDATE DETALLE_VENTA
    SET cantidad = cantidad - NEW.cantidadDevuelta,
        total = pUnitario * (cantidad - NEW.cantidadDevuelta)
    WHERE idVenta = NEW.idVenta
      AND idProducto = NEW.idProducto;
END;

CREATE TRIGGER aumentar_stock AFTER INSERT ON DEVOLUCION
WHEN NEW.motivo <> 'Defectuoso'
BEGIN
    UPDATE PRODUCTO
    SET stock = stock + NEW.cantidadDevuelta
    WHERE idProducto = NEW.idProducto;
END;


CREATE VIEW VistaVenta AS
SELECT v.idVenta, e.usuario AS usuarioEmpleado, c.nomCliente || ' ' || c.ape1Cliente || ' ' || c.ape2Cliente AS nombreCompletoCliente,
       v.fecha, v.mPago, v.total
FROM VENTA v
INNER JOIN EMPLEADO e ON v.idEmpleado = e.idEmpleado
INNER JOIN CLIENTE c ON v.idCliente = c.idCliente;

CREATE VIEW VistaVentaPagos AS
SELECT v.idVenta, e.usuario AS usuarioEmpleado,
       COALESCE(c.nomCliente || ' ' || c.ape1Cliente || ' ' || c.ape2Cliente, pv.nombreClienteNoRegistrado) AS nombreCliente,
       pv.nombreClienteNoRegistrado ,v.fecha, v.mPago, v.total,
       COALESCE(SUM(pv.monto), 0) AS totalPagado
FROM VENTA v
INNER JOIN EMPLEADO e ON v.idEmpleado = e.idEmpleado
LEFT JOIN CLIENTE c ON v.idCliente = c.idCliente
LEFT JOIN PAGO_VENTA pv ON v.idVenta = pv.idVenta
WHERE v.mPago = 'Pago a plazos.'
GROUP BY v.idVenta, e.usuario, c.nomCliente, c.ape1Cliente, c.ape2Cliente, v.fecha, v.mPago, v.total, pv.nombreClienteNoRegistrado
HAVING COALESCE(SUM(pv.monto), 0) < v.total;
select * from VistaVentaPagos;


