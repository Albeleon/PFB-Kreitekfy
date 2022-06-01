INSERT INTO usuarios (id, nombre, imagen, rol) VALUES (99991, '_-_usuario_-_', null, 0);

INSERT INTO albumes (id, nombre, imagen) VALUES (99991, '_-_album_-_', null);
INSERT INTO estilos (id, nombre) VALUES (99991, '_-_estilo_-_');
INSERT INTO estilos (id, nombre) VALUES (99992, '_-_estilo2_-_');
INSERT INTO estilos (id, nombre) VALUES (99993, '_-_estilo3_-_');
INSERT INTO artistas (id, nombre) VALUES (99991, '_-_artista_-_');

INSERT INTO canciones (id, nombre, duracion, fecha, album_id, estilo_id, artista_id, reproduccion, valoracion_media) VALUES (99991, '_-_cancion1_-_', 23, '2022-05-01', 99991, 99991, 99991, 5,3);
INSERT INTO canciones (id, nombre, duracion, fecha, album_id, estilo_id, artista_id, reproduccion, valoracion_media) VALUES (99992, '_-_cancion2_-_', 54, '2022-05-03', 99991, 99991, 99991, 8,null);
INSERT INTO canciones (id, nombre, duracion, fecha, album_id, estilo_id, artista_id, reproduccion, valoracion_media) VALUES (99993, '_-_cancion3_-_', 75, '2022-05-11', 99991, 99992, 99991, 12,4);
INSERT INTO canciones (id, nombre, duracion, fecha, album_id, estilo_id, artista_id, reproduccion, valoracion_media) VALUES (99994, '_-_cancion4_-_', 12, '2022-05-14', 99991, 99993, 99991, 6,1);
INSERT INTO canciones (id, nombre, duracion, fecha, album_id, estilo_id, artista_id, reproduccion, valoracion_media) VALUES (99995, '_-_cancion5_-_', 342, '2022-05-23', 99991, 99991, 99991, 4,2);
INSERT INTO canciones (id, nombre, duracion, fecha, album_id, estilo_id, artista_id, reproduccion, valoracion_media) VALUES (99996, '_-_cancion6_-_', 89, '2022-05-02', 99991, 99991, 99991, 9,null);

INSERT INTO canciones_usuarios (cancion_id, usuario_id, reproduccion, valoracion) VALUES (99991, 99991, 4, 3);
INSERT INTO canciones_usuarios (cancion_id, usuario_id, reproduccion, valoracion) VALUES (99993, 99991, 6, 4);
INSERT INTO canciones_usuarios (cancion_id, usuario_id, reproduccion, valoracion) VALUES (99995, 99991, 4, 2);
INSERT INTO canciones_usuarios (cancion_id, usuario_id, reproduccion, valoracion) VALUES (99994, 99991, 40, 1);