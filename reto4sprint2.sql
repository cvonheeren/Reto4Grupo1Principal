
/**************************************************************************
* BORRA LAS TABLAS SI YA EXISTEN
***************************************************************************/

DROP TABLE IF EXISTS RESERVA_HABITACION;
DROP TABLE IF EXISTS ALOJAMIENTO_ESTANCIA;
DROP TABLE IF EXISTS ALOJAMIENTO_HABITACION;
DROP TABLE IF EXISTS RESERVAS;
DROP TABLE IF EXISTS ESTANCIAS;
DROP TABLE IF EXISTS HABITACIONES;
DROP TABLE IF EXISTS CLIENTES;
DROP TABLE IF EXISTS ALOJAMIENTOS;
DROP TABLE IF EXISTS UBICACIONES;


/**************************************************************************
* CREA LAS TABLAS
***************************************************************************/

CREATE TABLE UBICACIONES
(
  COD_UBICACION INT(3) AUTO_INCREMENT PRIMARY KEY,
  NOMBRE VARCHAR(50) NOT NULL CHECK (NOMBRE = UPPER(NOMBRE)),
  COD_POSTAL VARCHAR(5) NOT NULL,
  PAIS VARCHAR(50) NOT NULL CHECK (PAIS = UPPER(PAIS))
);

CREATE TABLE ALOJAMIENTOS
(
  COD_ALOJAMIENTO INT(6) AUTO_INCREMENT PRIMARY KEY,
  COD_UBICACION INT(3),
  NOMBRE VARCHAR(50) NOT NULL CHECK (NOMBRE = UPPER(NOMBRE)),
  DESCRIPCION VARCHAR(500),
  LONGITUD FLOAT(6),
  LATITUD FLOAT(6),
  TIPO CHAR NOT NULL CHECK (TIPO IN('H', 'C', 'A')),
  ESTRELLAS INT(1) CHECK (ESTRELLAS BETWEEN 1 AND 5),
  ALTURA INT(2),
  REGARGO FLOAT(6) DEFAULT 0,
  DESAYUNO FLOAT(6) DEFAULT 0,
  MEDIA_PENSION FLOAT(6) DEFAULT 0,
  PENSION_COMPLETA FLOAT(6) DEFAULT 0,
  IMGURL VARCHAR(100) DEFAULT 'https://stampedtribe.com/wp-content/uploads/2018/03/hotel-icon-10.png',
  FOREIGN KEY (COD_UBICACION) REFERENCES UBICACIONES (COD_UBICACION) ON DELETE CASCADE
);

CREATE TABLE CLIENTES
(
  COD_CLIENTE INT(6) AUTO_INCREMENT PRIMARY KEY,
  DNI VARCHAR(9) UNIQUE NOT NULL CHECK (DNI = UPPER(DNI)),
  CONTRASENA VARCHAR(33) NOT NULL,
  NOMBRE VARCHAR(50),
  APELLIDOS VARCHAR(50),
  FECHANAC DATE,
  EMAIL VARCHAR(50)
);

CREATE TABLE HABITACIONES
(
  COD_HABITACION INT(6) AUTO_INCREMENT PRIMARY KEY,
  NOMBRE VARCHAR(50) NOT NULL,
  DESCRIPCION VARCHAR(500),
  CTD_CAMAS_SIMPLES INT(3),
  CTD_CAMAS_MATRIMONIO INT(2),
  CTD_CAMAS_INFANTIL INT(2),
  TARIFA_NORMAL FLOAT(6) NOT NULL,
  TARIFA_VERANO FLOAT(6) NOT NULL,
  TARIFA_FESTIVO FLOAT(6) NOT NULL,
  TAMANO FLOAT(4) NOT NULL
);

CREATE TABLE ESTANCIAS
(
  COD_ESTANCIA INT(6) AUTO_INCREMENT PRIMARY KEY,
  NOMBRE VARCHAR(50) NOT NULL,
  TAMANO FLOAT(4) NOT NULL
);

CREATE TABLE RESERVAS
(
  COD_RESERVA INT(6) AUTO_INCREMENT PRIMARY KEY,
  COD_CLIENTE INT(6),
  COD_ALOJAMIENTO INT(6),
  FECHACOMPRA DATETIME NOT NULL,
  FECHAENTRADA DATE NOT NULL,
  FECHASALIDA DATE NOT NULL,
  PRECIOTOTAL FLOAT(6) NOT NULL,
  FOREIGN KEY (COD_CLIENTE) REFERENCES CLIENTES (COD_CLIENTE) ON DELETE CASCADE,
  FOREIGN KEY (COD_ALOJAMIENTO) REFERENCES ALOJAMIENTOS (COD_ALOJAMIENTO) ON DELETE CASCADE
);

CREATE TABLE ALOJAMIENTO_HABITACION
(
  COD_ALOJAMIENTO INT(6),
  COD_HABITACION INT(6),
  CANTIDAD INT(3) NOT NULL,
  FOREIGN KEY (COD_ALOJAMIENTO) REFERENCES ALOJAMIENTOS (COD_ALOJAMIENTO) ON DELETE CASCADE,
  FOREIGN KEY (COD_HABITACION) REFERENCES HABITACIONES (COD_HABITACION) ON DELETE CASCADE
);

CREATE TABLE ALOJAMIENTO_ESTANCIA
(
  COD_ALOJAMIENTO INT(6),
  COD_ESTANCIA INT(6),
  CANTIDAD INT(3) NOT NULL,
  FOREIGN KEY (COD_ALOJAMIENTO) REFERENCES ALOJAMIENTOS (COD_ALOJAMIENTO) ON DELETE CASCADE,
  FOREIGN KEY (COD_ESTANCIA) REFERENCES ESTANCIAS (COD_ESTANCIA) ON DELETE CASCADE
);

CREATE TABLE RESERVA_HABITACION
(
  COD_RESERVA INT(6),
  COD_HABITACION INT(6),
  CANTIDAD INT(3) NOT NULL,
  FOREIGN KEY (COD_RESERVA) REFERENCES RESERVAS (COD_RESERVA) ON DELETE CASCADE,
  FOREIGN KEY (COD_HABITACION) REFERENCES HABITACIONES (COD_HABITACION) ON DELETE CASCADE
);


/**************************************************************************
* INSERTA LOS DATOS
***************************************************************************/

-- DECLARACION DE VARIABLES --

SET @DESC := 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Mauris purus nulla, consectetur id elit id, suscipit dictum mauris. Cras est magna, hendrerit eu fringilla ut, feugiat sed lorem. Sed sit amet placerat sapien, at sodales ligula. Proin semper nisl in nibh molestie mattis at at augue. Aenean ac facilisis ante. Fusce in elementum dolor. Aenean luctus blandit mi, et rutrum tortor placerat vel. Praesent dictum aliquam lectus, in viverra risus. Cras vehicula turpis eu metus faucibus, in mattis neque condimentum. Mauris maximus viverra est, sit amet viverra purus condimentum at.';


-- TABLA UBICACIONES --

INSERT INTO UBICACIONES VALUES (NULL, 'BARCELONA', '08001', 'ESPAÑA');
INSERT INTO UBICACIONES VALUES (NULL, 'BILBAO', '48001', 'ESPAÑA');
INSERT INTO UBICACIONES VALUES (NULL, 'MADRID', '28001', 'ESPAÑA');
INSERT INTO UBICACIONES VALUES (NULL, 'MALAGA', '29001', 'ESPAÑA');
INSERT INTO UBICACIONES VALUES (NULL, 'VALENCIA', '46001', 'ESPAÑA');
INSERT INTO UBICACIONES VALUES (NULL, 'ZARAGOZA', '50001', 'ESPAÑA');
INSERT INTO UBICACIONES VALUES (NULL, 'AMSTERDAM', '50001', 'HOLANDA');
INSERT INTO UBICACIONES VALUES (NULL, 'BERLIN', '50001', 'ALEMANIA');
INSERT INTO UBICACIONES VALUES (NULL, 'LONDRES', '50001', 'REINO UNIDO');
INSERT INTO UBICACIONES VALUES (NULL, 'PARIS', '50001', 'FRANCIA');
INSERT INTO UBICACIONES VALUES (NULL, 'ROMA', '50001', 'ITALIA');


-- TABLA ALOJAMIENTOS --

-- 				COD_ALOJAMIENTO, COD_UBICACION, NOMBRE, DESCRIPCION, LONGITUD, LATITUD, TIPO, ESTRELLAS, ALTURA, REGARGO, DESAYUNO, MEDIA_PENSION, PENSION_COMPLETA, IMGURL
-- HOTELES --
INSERT INTO ALOJAMIENTOS VALUES (NULL, 1, 'CATALONIA SAGRADA FAMILIA', @DESC, 41.4077823, 2.1853613, 'H', 3, NULL, 49.90, 60, 5, 0, 'https://media-cdn.tripadvisor.com/media/photo-s/0f/ee/90/f2/hotel-catalonia-sagrada.jpg');
INSERT INTO ALOJAMIENTOS VALUES (NULL, 1, 'ROYAL RAMBLAS', @DESC, 41.3843398, 2.1704865, 'H', 4, NULL, 59.99, 75.99, 10, 5, 'https://s-ec.bstatic.com/images/hotel/max1024x768/146/146955267.jpg');
INSERT INTO ALOJAMIENTOS VALUES (NULL, 1, 'BARCELONA PRINCESS', @DESC, 41.4109621, 2.218664, 'H', 4, NULL, 45.5, 85.99, 10, 5, 'https://s-ec.bstatic.com/images/hotel/max1024x768/120/120950439.jpg');
INSERT INTO ALOJAMIENTOS VALUES (NULL, 2, 'ERCILLA HOTEL', @DESC, 43.2610987, -2.9386205, 'H', 4, NULL, 70, 95.99, 10, 5, 'https://t-ec.bstatic.com/images/hotel/max1024x768/185/185416683.jpg');
INSERT INTO ALOJAMIENTOS VALUES (NULL, 2, 'GRAN HOTEL DOMINE BILBAO', @DESC, 43.267156, -2.933837, 'H', 4, NULL, 59.99, 65.99, 10, 5, 'https://t-ec.bstatic.com/images/hotel/max1024x768/185/185023182.jpg');
INSERT INTO ALOJAMIENTOS VALUES (NULL, 3, 'EXE PLAZA', @DESC, 40.4675402, -3.690392, 'H', 4, NULL, 85, 105.99, 10, 5, 'https://t-ec.bstatic.com/images/hotel/max1024x768/960/96033499.jpg');
INSERT INTO ALOJAMIENTOS VALUES (NULL, 3, 'AYRE GRAN HOTEL COLON', @DESC, 40.413003, -3.669204, 'H', 4, NULL, 59.99, 75.99, 10, 5, 'https://t-ec.bstatic.com/images/hotel/max1024x768/141/14129143.jpg');
INSERT INTO ALOJAMIENTOS VALUES (NULL, 3, 'TOTEM MADRID', @DESC, 40.4264885, -3.6855333, 'H', 4, NULL, 60, 75.99, 10, 5, 'https://t-ec.bstatic.com/images/hotel/max1024x768/786/78692848.jpg');
INSERT INTO ALOJAMIENTOS VALUES (NULL, 4, 'SOHO BAHIA MALAGA', @DESC, 36.7155267, -4.4235837, 'H', 4, NULL, 32.99, 45.99, 10, 5, 'https://t-ec.bstatic.com/images/hotel/max1024x768/147/147630171.jpg');
INSERT INTO ALOJAMIENTOS VALUES (NULL, 4, 'HOTEL GUADALMEDINA', @DESC, 36.715032, -4.426636, 'H', 4, NULL, 41.75, 75.99, 10, 5, 'https://t-ec.bstatic.com/images/hotel/max1024x768/774/77460002.jpg');

-- CASAS --
INSERT INTO ALOJAMIENTOS VALUES (NULL, 1, 'LA CASITA DE PE BEACH', @DESC, 41.4888555, 2.3524318, 'C', NULL, NULL, 69.95, 75.99, 10, 5, 'https://t-ec.bstatic.com/images/hotel/max1024x768/133/133706949.jpg');
INSERT INTO ALOJAMIENTOS VALUES (NULL, 2, 'CASA URONDO BARRI', @DESC, 43.3056003, -2.9471019, 'C', NULL, NULL, 50, 55.99, 10, 5, 'https://s-ec.bstatic.com/images/hotel/max1024x768/153/153447632.jpg');
INSERT INTO ALOJAMIENTOS VALUES (NULL, 3, 'WINDROSE 6', @DESC, 40.3967797,-3.7410645, 'C', NULL, NULL, 43.99, 75.99, 10, 5, 'https://t-ec.bstatic.com/images/hotel/max1024x768/135/135327807.jpg');
INSERT INTO ALOJAMIENTOS VALUES (NULL, 4, 'CASA MIRADOR DE GIBRALFARO', @DESC, 36.7255864, -4.4133006, 'C', NULL, NULL, 59.95, 65.99, 10, 5, 'https://t-ec.bstatic.com/images/hotel/max1024x768/100/100963182.jpg');


-- APARTAMENTOS --
INSERT INTO ALOJAMIENTOS VALUES (NULL, 1, 'BCN RAMBLA CATALUNYA APARTMENTS', @DESC, 41.393308, 2.1601169, 'A', NULL, 3, 59.99, 75.99, 10, 5, 'https://s-ec.bstatic.com/images/hotel/max1024x768/138/138892244.jpg');
INSERT INTO ALOJAMIENTOS VALUES (NULL, 1, 'TWO SISTERS APARTMENTS', @DESC, 41.3779474, 2.1570735, 'A', NULL, 4, 59.99, 75.99, 10, 5, 'https://s-ec.bstatic.com/images/hotel/max1024x768/447/44756593.jpg');
INSERT INTO ALOJAMIENTOS VALUES (NULL, 2, 'BILBAO APARTAMENTOS ATXURI', @DESC, 43.2500361, -2.9203758, 'A', NULL, 5, 59.99, 75.99, 10, 5, 'https://s-ec.bstatic.com/images/hotel/max1024x768/868/86897811.jpg');
INSERT INTO ALOJAMIENTOS VALUES (NULL, 2, 'ROOM AND KITCHEN BILBAO', @DESC, 43.2687309, -2.9509771, 'A', NULL, 4, 59.99, 75.99, 10, 5, 'https://t-ec.bstatic.com/images/hotel/max1024x768/101/101386430.jpg');
INSERT INTO ALOJAMIENTOS VALUES (NULL, 3, 'SLOW SUITES CHUECA', @DESC, 40.4220131, -3.6963391, 'A', NULL, 3, 59.99, 75.99, 10, 5, 'https://t-ec.bstatic.com/images/hotel/max1024x768/157/157292836.jpg');
INSERT INTO ALOJAMIENTOS VALUES (NULL, 3, 'APARTHOTEL TRIBUNAL', @DESC, 40.4254533,-3.701217, 'A', NULL, 4, 59.99, 75.99, 10, 5, 'https://t-ec.bstatic.com/images/hotel/max1024x768/180/180484242.jpg');
INSERT INTO ALOJAMIENTOS VALUES (NULL, 4, 'APARTAMENTOS PINAR MALAGA CENTRO', @DESC, 36.722889, -4.42419, 'A', NULL, 5, 59.99, 75.99, 10, 5, 'https://s-ec.bstatic.com/images/hotel/max1024x768/425/42580122.jpg');
INSERT INTO ALOJAMIENTOS VALUES (NULL, 4, 'APARTAMENTOS MALAGA FLAT', @DESC, 36.7258599, -4.4234111, 'A', NULL, 4, 59.99, 75.99, 10, 5, 'https://s-ec.bstatic.com/images/hotel/max1024x768/378/37894295.jpg');

-- TABLA CLIENTES --

INSERT INTO CLIENTES VALUES (NULL, '83178234E', MD5('1234'), 'NURIA', 'RUIZ RAMOS', '1984-02-24', 'nuria.ruiz@gmail.com');
INSERT INTO CLIENTES VALUES (NULL, '01820557S', MD5('1234'), 'ALBERTO', 'ASENSIO GARCIA', '1976-05-01', 'alberto.asensio@gmail.com');
INSERT INTO CLIENTES VALUES (NULL, '12345678A', MD5('1234'), 'PRUEBAS', 'AAAAA', '1976-05-01', 'testeo@gmail.com');

-- TABLA HABITACIONES --

INSERT INTO HABITACIONES VALUES (NULL, 'HABITACION DOBLE ESTANDAR', @DESC, 2, 0, 0, 15, 25, 30, 25.8);
INSERT INTO HABITACIONES VALUES (NULL, 'HABITACION DOBLE MATRIMONIO', @DESC, 0, 1, 0, 15, 25, 30, 30);
INSERT INTO HABITACIONES VALUES (NULL, 'HABITACION TRIPLE ESTANDAR', @DESC, 3, 0, 0, 15, 25, 30, 30.5);
INSERT INTO HABITACIONES VALUES (NULL, 'HABITACION FAMILIAR 1 NINO', @DESC, 0, 1, 1, 15, 25, 30, 31.6);
INSERT INTO HABITACIONES VALUES (NULL, 'HABITACION FAMILIAR 2 NINOS', @DESC, 0, 1, 2, 15, 25, 30, 38.5);
INSERT INTO HABITACIONES VALUES (NULL, 'HABITACION FAMILIAR 3 NINOS', @DESC, 0, 1, 3, 15, 25, 30, 45.5);
INSERT INTO HABITACIONES VALUES (NULL, 'HABITACION SUITE ESTANDAR', @DESC, 2, 0, 0, 15, 25, 30, 50);
INSERT INTO HABITACIONES VALUES (NULL, 'HABITACION SUITE MATRIMONIO', @DESC, 0, 1, 0, 15, 25, 30, 50);
INSERT INTO HABITACIONES VALUES (NULL, 'HABITACION SUITE FAMILIAR', @DESC, 0, 1, 2, 15, 25, 30, 70);

-- TABLA ALOJAMIENTO_HABITACION --

INSERT INTO ALOJAMIENTO_HABITACION VALUES (1, 1, 1);
INSERT INTO ALOJAMIENTO_HABITACION VALUES (1, 2, 1);
INSERT INTO ALOJAMIENTO_HABITACION VALUES (1, 3, 10);
INSERT INTO ALOJAMIENTO_HABITACION VALUES (2, 2, 10);
INSERT INTO ALOJAMIENTO_HABITACION VALUES (2, 4, 10);
INSERT INTO ALOJAMIENTO_HABITACION VALUES (3, 1, 10);
INSERT INTO ALOJAMIENTO_HABITACION VALUES (3, 5, 10);
INSERT INTO ALOJAMIENTO_HABITACION VALUES (3, 6, 10);
INSERT INTO ALOJAMIENTO_HABITACION VALUES (4, 2, 10);
INSERT INTO ALOJAMIENTO_HABITACION VALUES (5, 2, 10);
INSERT INTO ALOJAMIENTO_HABITACION VALUES (6, 2, 10);
INSERT INTO ALOJAMIENTO_HABITACION VALUES (7, 2, 10);
INSERT INTO ALOJAMIENTO_HABITACION VALUES (8, 2, 10);
INSERT INTO ALOJAMIENTO_HABITACION VALUES (9, 2, 10);
INSERT INTO ALOJAMIENTO_HABITACION VALUES (10, 2, 10);
INSERT INTO ALOJAMIENTO_HABITACION VALUES (11, 2, 10);
INSERT INTO ALOJAMIENTO_HABITACION VALUES (12, 2, 10);
INSERT INTO ALOJAMIENTO_HABITACION VALUES (13, 2, 10);
INSERT INTO ALOJAMIENTO_HABITACION VALUES (14, 2, 10);
INSERT INTO ALOJAMIENTO_HABITACION VALUES (15, 2, 10);
INSERT INTO ALOJAMIENTO_HABITACION VALUES (16, 2, 10);
INSERT INTO ALOJAMIENTO_HABITACION VALUES (17, 2, 10);
INSERT INTO ALOJAMIENTO_HABITACION VALUES (18, 2, 10);
INSERT INTO ALOJAMIENTO_HABITACION VALUES (19, 2, 10);
INSERT INTO ALOJAMIENTO_HABITACION VALUES (20, 2, 10);
INSERT INTO ALOJAMIENTO_HABITACION VALUES (21, 2, 10);
INSERT INTO ALOJAMIENTO_HABITACION VALUES (22, 2, 10);


-- TABLA ESTANCIAS --

INSERT INTO ESTANCIAS VALUES (NULL, 'SALA', 7.8);
INSERT INTO ESTANCIAS VALUES (NULL, 'SALA', 9.3);
INSERT INTO ESTANCIAS VALUES (NULL, 'COMEDOR', 10.8);
INSERT INTO ESTANCIAS VALUES (NULL, 'COMEDOR', 8.2);
INSERT INTO ESTANCIAS VALUES (NULL, 'BALCON', 4.8);
INSERT INTO ESTANCIAS VALUES (NULL, 'BALCON', 5.3);
INSERT INTO ESTANCIAS VALUES (NULL, 'GARAJE', 12);
INSERT INTO ESTANCIAS VALUES (NULL, 'GARAJE', 11.8);
INSERT INTO ESTANCIAS VALUES (NULL, 'BANIO', 6.3);
INSERT INTO ESTANCIAS VALUES (NULL, 'BANIO', 7.5);
INSERT INTO ESTANCIAS VALUES (NULL, 'COCINA', 10);
INSERT INTO ESTANCIAS VALUES (NULL, 'COCINA', 12);

-- TABLA ALOJAMIENTO_ESTANCIA --

INSERT INTO ALOJAMIENTO_ESTANCIA VALUES (11, 1, 1);
INSERT INTO ALOJAMIENTO_ESTANCIA VALUES (11, 3, 1);
INSERT INTO ALOJAMIENTO_ESTANCIA VALUES (11, 5, 1);
INSERT INTO ALOJAMIENTO_ESTANCIA VALUES (11, 7, 1);
INSERT INTO ALOJAMIENTO_ESTANCIA VALUES (11, 9, 2);
INSERT INTO ALOJAMIENTO_ESTANCIA VALUES (11, 11, 1);
INSERT INTO ALOJAMIENTO_ESTANCIA VALUES (12, 4, 1);
INSERT INTO ALOJAMIENTO_ESTANCIA VALUES (12, 6, 2);
INSERT INTO ALOJAMIENTO_ESTANCIA VALUES (12, 8, 1);
INSERT INTO ALOJAMIENTO_ESTANCIA VALUES (12, 10, 2);
INSERT INTO ALOJAMIENTO_ESTANCIA VALUES (12, 12, 1);
INSERT INTO ALOJAMIENTO_ESTANCIA VALUES (13, 1, 1);
INSERT INTO ALOJAMIENTO_ESTANCIA VALUES (13, 3, 1);
INSERT INTO ALOJAMIENTO_ESTANCIA VALUES (13, 5, 1);
INSERT INTO ALOJAMIENTO_ESTANCIA VALUES (13, 7, 1);
INSERT INTO ALOJAMIENTO_ESTANCIA VALUES (13, 9, 2);
INSERT INTO ALOJAMIENTO_ESTANCIA VALUES (13, 11, 1);
INSERT INTO ALOJAMIENTO_ESTANCIA VALUES (14, 4, 1);
INSERT INTO ALOJAMIENTO_ESTANCIA VALUES (14, 6, 2);
INSERT INTO ALOJAMIENTO_ESTANCIA VALUES (14, 8, 1);
INSERT INTO ALOJAMIENTO_ESTANCIA VALUES (14, 10, 2);
INSERT INTO ALOJAMIENTO_ESTANCIA VALUES (14, 12, 1);
INSERT INTO ALOJAMIENTO_ESTANCIA VALUES (15, 1, 1);
INSERT INTO ALOJAMIENTO_ESTANCIA VALUES (15, 5, 1);
INSERT INTO ALOJAMIENTO_ESTANCIA VALUES (15, 10, 2);
INSERT INTO ALOJAMIENTO_ESTANCIA VALUES (15, 11, 1);
INSERT INTO ALOJAMIENTO_ESTANCIA VALUES (16, 2, 1);
INSERT INTO ALOJAMIENTO_ESTANCIA VALUES (16, 6, 1);
INSERT INTO ALOJAMIENTO_ESTANCIA VALUES (16, 10, 2);
INSERT INTO ALOJAMIENTO_ESTANCIA VALUES (16, 12, 1);
INSERT INTO ALOJAMIENTO_ESTANCIA VALUES (17, 1, 1);
INSERT INTO ALOJAMIENTO_ESTANCIA VALUES (17, 5, 2);
INSERT INTO ALOJAMIENTO_ESTANCIA VALUES (18, 1, 1);
INSERT INTO ALOJAMIENTO_ESTANCIA VALUES (18, 5, 1);
INSERT INTO ALOJAMIENTO_ESTANCIA VALUES (18, 10, 2);
INSERT INTO ALOJAMIENTO_ESTANCIA VALUES (18, 11, 1);
INSERT INTO ALOJAMIENTO_ESTANCIA VALUES (19, 1, 1);
INSERT INTO ALOJAMIENTO_ESTANCIA VALUES (19, 5, 1);
INSERT INTO ALOJAMIENTO_ESTANCIA VALUES (20, 10, 2);
INSERT INTO ALOJAMIENTO_ESTANCIA VALUES (20, 11, 1);
INSERT INTO ALOJAMIENTO_ESTANCIA VALUES (21, 11, 1);
INSERT INTO ALOJAMIENTO_ESTANCIA VALUES (21, 11, 1);
INSERT INTO ALOJAMIENTO_ESTANCIA VALUES (22, 11, 1);
INSERT INTO ALOJAMIENTO_ESTANCIA VALUES (22, 11, 1);


-- TABLA RESERVAS --

INSERT INTO RESERVAS VALUES (NULL, 1, 1, '2019-04-14', '2019-04-17', '2019-04-20', 300);
INSERT INTO RESERVAS VALUES (NULL, 2, 1, '2019-04-14', '2019-04-17', '2019-04-20', 200);


-- TABLA RESERVA_HABITACION --

INSERT INTO RESERVA_HABITACION VALUES (1, 1, 1);
INSERT INTO RESERVA_HABITACION VALUES (2, 2, 1);
