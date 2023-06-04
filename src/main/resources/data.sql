INSERT INTO rol(ID, ROLNOMBRE) VALUES (1, 'ROLE_USER');
INSERT INTO rol(ID, ROLNOMBRE) VALUES (2, 'ROLE_ADMIN');

INSERT INTO usuario(NOMBRE, NOMBREUSUARIO, EMAIL, PASSWORD) VALUES ('alumno', 'alumno', 'alumno@gmail.com', '$2a$10$fELDKNlb5SgZxXBsXwEJ8u5hOqjz5YsBmcJInhz4bh5a6Iilf06Ae');
INSERT INTO usuario(NOMBRE, NOMBREUSUARIO, EMAIL, PASSWORD) VALUES ('profesor', 'profesor', 'profesor@gmail.com', '$2a$10$.UwZfShn.bN8fJ8kE/Kq3.frOHTxHglAk5VgIXnA1XCJkIDtRwRl.');

INSERT INTO USUARIO_ROL(USUARIO_ID, ROL_ID) VALUES('alumno', 1);
INSERT INTO USUARIO_ROL(USUARIO_ID, ROL_ID) VALUES('profesor', 2);

INSERT INTO alumno(ID, NOMBREUSUARIO) VALUES (1, 'alumno');
INSERT INTO profesor(ID, NOMBREUSUARIO) VALUES (1, 'profesor');

INSERT INTO asignatura(ID, NOMBRE, DESCRIPCION, CURSO, CODIGO, PROFESOR_ID) VALUES (1, 'Matematicas', 'Matematicas Asignatura', '1A', 'AAAA111', 1);
INSERT INTO asignatura(ID, NOMBRE, DESCRIPCION, CURSO, CODIGO, PROFESOR_ID) VALUES (2, 'Lengua', 'Lengua Asignatura', '2B', 'BBBB222', 1);
INSERT INTO asignatura(ID, NOMBRE, DESCRIPCION, CURSO, CODIGO, PROFESOR_ID) VALUES (3, 'Ingles', 'Ingles Asignatura', '3B', 'CCCC333', 1);

INSERT INTO ALUMNO_ASIGNATURA(ID, ESTADO, PUNTOS, ALUMNO_ID, ASIGNATURA_ID) VALUES (1, 'Pendiente', 100, 1, 1);

INSERT INTO tema(ID, NOMBRE, DESCRIPCION, ASIGNATURA_ID) VALUES (1, 'Tema 1', 'Desc tema 1', 1);
INSERT INTO tema(ID, NOMBRE, DESCRIPCION, ASIGNATURA_ID) VALUES (2, 'Tema 2', 'Desc tema 2', 1);
INSERT INTO tema(ID, NOMBRE, DESCRIPCION, ASIGNATURA_ID) VALUES (3, 'Tema 3', 'Desc tema 3', 1);
INSERT INTO tema(ID, NOMBRE, DESCRIPCION, ASIGNATURA_ID) VALUES (4, 'Tema 1', 'Desc tema 1', 2);
INSERT INTO tema(ID, NOMBRE, DESCRIPCION, ASIGNATURA_ID) VALUES (5, 'Tema 1', 'Desc tema 1', 3);


INSERT INTO artefacto(ID, NOMBRE, DESCRIPCION, COSTEPUNTOS, ESTADO, TEMPORAL, FECHAINICIO, FECHAFIN, ASIGNATURA_ID) VALUES (1, '1 punto mas en examen', '1 punto mas en examen', 100, 'Activo', false, null, null, 1);

