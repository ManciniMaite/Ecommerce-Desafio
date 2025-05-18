DELETE FROM detalle_compra;
DELETE FROM compra;
DELETE FROM producto;
INSERT INTO producto (id, precio, nombre, descricion) VALUES 
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


