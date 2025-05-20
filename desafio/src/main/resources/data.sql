DELETE FROM detalle_compra;
DELETE FROM compra;
DELETE FROM producto;
INSERT INTO producto (id, precio, nombre, descripcion) VALUES 
	(1,637.9, 'ZAPATILLAS NIKE AIR FORCE', 'Una confección clásica de los años 80 se combina con detalles audaces para tener un estilo que deja huella en la cancha y en la calle. Y como no podemos resistirnos, una combinación de colores especial para el Día de San Valentín endulza el look.'),
	(2,667.9,'ZAPATILLAS NIKE AIR MAX 1','Conoce al líder de la manada. El Air Max 1 camina en las nubes sobre el ruido, mezcla un diseño atemporal y comodidad con amortiguación. Este ícono clásico llegó a la escena en el 87 y continúa siendo el alma de la franquicia hasta hoy.'),
	(3,673.9,'ZAPATILLAS NIKE DUNK HIGH','El icono de los 80, que combina el estilo vintage con el ADN del básquetbol, vuelve con cuero premium suavizado impecablemente para ofrecer un look atemporal.'),
	(4,644.4,'ZAPATILLAS NIKE DAYBREAK','Las Nike Daybreak, lanzadas en 1979, rinden homenaje al pasado. Incorporan la misma suela exterior de goma tipo gofre para ofrecer un estilo vintage auténtico.'),
	(5,618.1,'ZAPATILLAS NIKE BLAZER LOW PLATFORM','Elogiados por su sencillez y comodidad, los Nike Blazer Low Platform elevan el ícono del básquetbol. La entresuela y suela con elevación te permiten pisar con confianza, y la parte superior mantiene las proporciones que te gustaban del original.'),
	(6,625.9,'ZAPATILLAS ADIDAS FORUM BOLD STRIPES','Estas zapatillas son fieles a todos los detalles, patrones y materiales originales. Los detalles extragrandes del Trifolio complementan su estilo divertido, mientras que la suela con plataforma de caucho te permite destacarte entre la multitud.'),
	(7,651.9,'ZAPATILLAS ADIDAS GAZELLE BOLD','Originalmente diseñadas en los 60 como unas zapatillas de training, las Gazelle ahora son un ícono de la moda urbana que se adapta perfectamente del día a la noche. La gamuza premium y el cuero envuelven tus pies con comodidad retro'),
	(8,627.9,'ZAPATILLAS ADIDAS COURT SUPER','Sal a la calle con total confianza con estas zapatillas adidas. Inspiradas en los archivos de Originals,se mantienen fieles a su legado pero moldean un look moderno. Un logo del Trifolio bordado descansa sobre una parte superior de cuero texturizado'),
	(9,590.9,'ZAPATILLAS ADIDAS VL COURT 3.0','Elegantes o informales. Vos decidís. Estas zapatillas adidas combinan a la perfección con prácticamente todo lo que tenés en tu armario. La suela de caucho vulcanizada está inspirada en el parque de skate y es perfecta para explorar la ciudad.'),
	(10,643.9,'ZAPATILLAS ADIDAS SL 72 OG','Lanzadas por primera vez en 1972, las zapatillas adidas SL 72 se han reimaginado para el mundo de hoy. El exterior de malla y gamuza de corte bajo y la silueta estilizada ofrecen un estilo retro inspirado en el running. La mediasuela liviana de EVA'),
	(11,593.1,'ZAPATILLAS PUMA PALERMO MODA ELEVATED','Este clásico zapato de terraza debutó en los años 80 y ahora lo hemos recuperado para los fanáticos, revitalizado como el Palermo Lamoda. Con su característica construcción de punta en T y la clásica suela de goma'),
	(12,599.9,'ZAPATILLAS PUMA CA FLYZ','Son perfectas para vos. Confeccionada con la mezcla de materiales sintéticos de alta calidad que te hará ver impecable a donde vayas. Este calzado es perfecto para combinarlas con tus mejores outfits urbanos'),
	(13,608.5,'ZAPATILLAS PUMA CALI DREAM LTHAWNS','Las zapatillas Puma Cali Dream Lthawns para mujer combinan estilo retro con comodidad moderna. Perfectas para el día a día, ofrecen un diseño elegante y funcional.'),
	(14,650.1,'ZAPATILLAS PUMA PALERMO','Este modelo debutó a principios de los años 80 y pronto se convirtió en un básico en las gradas de los estadios de fútbol británicos. Ahora, las traemos de vuelta para los fans. Al igual que el modelo origina'),
	(15,612.1,'ZAPATILLAS PUMA CA PRO CLASSIC','Completá tu look con las Zapatillas Urbanas Puma Ca Pro Classic. Un par que envuelve tus pies en moda, elegancia y comodidad para cada uno de tus planes. Dejá huella de tu estilo por donde sea que pases con este par!');


DELETE FROM usuario;
INSERT INTO usuario (id, nombre, apellido, nro_documento) VALUES
	(1,'Maite','Mancini','42245626'),
	(2,'Juan','Perez','36459862'),
	(3,'Esteban','Quito','20685963'),
	(4,'Maria','Garcia','45496358');


DELETE FROM fecha_especial;
INSERT INTO fecha_especial (id, fecha) VALUES 
    (1, '2025-05-18'),
    (2, '2025-07-01');

DELETE FROM compra;
INSERT INTO compra (fecha_compra, monto_descuento, sub_total, total, cliente_id, id) VALUES 
('2025-05-18', 300, 637.9, 337.9, 1, 17),
('2025-05-18', 300, 2003.7, 1703.7, 1, 18),
('2025-05-18', 300, 12958, 12658, 1, 19),
('2025-04-01', 2772.0251, 10688.101, 7916.075, 2, 20),
('2025-04-01', 2105.175, 8020.7, 5915.5254, 2, 21),
('2025-04-08', 500, 673.9, 173.90002, 2, 22),
('2025-05-18', 300, 667.9, 367.90002, 2, 23),
('2025-05-20', 0, 673.9, 673.9, 4, 24),
('2025-05-20', 2294.25, 8777, 6482.75, 4, 25);


DELETE FROM detalle_compra;
INSERT INTO detalle_compra (cantidad, precio_unitario, subtotal, compra_id, id, producto_id) VALUES 
(1, 637.9, 637.9, 17, 23, 1),
(3, 667.9, 2003.7, 18, 24, 2),
(10, 667.9, 6679, 19, 25, 2),
(10, 627.9, 6279, 19, 26, 8),
(6, 618.1, 3708.6, 20, 27, 5),
(3, 627.9, 1883.7, 20, 28, 8),
(3, 612.1, 1836.3, 20, 29, 15),
(5, 651.9, 3259.5, 20, 30, 7),
(3, 673.9, 2021.7, 21, 31, 3),
(10, 599.9, 5999, 21, 32, 12),
(1, 673.9, 673.9, 22, 33, 3),
(1, 667.9, 667.9, 23, 34, 2),
(1, 673.9, 673.9, 24, 35, 3),
(7, 637.9, 4465.3003, 25, 36, 1),
(3, 625.9, 1877.7001, 25, 37, 6),
(4, 608.5, 2434, 25, 38, 13);

