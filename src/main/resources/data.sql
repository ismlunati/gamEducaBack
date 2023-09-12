INSERT INTO rol(ID, ROLNOMBRE) VALUES (1, 'ROLE_USER');
INSERT INTO rol(ID, ROLNOMBRE) VALUES (2, 'ROLE_ADMIN');

INSERT INTO usuario(NOMBRE, NOMBREUSUARIO, EMAIL, PASSWORD) VALUES ('alumno', 'alumno', 'alumno@gmail.com', '$2a$10$fELDKNlb5SgZxXBsXwEJ8u5hOqjz5YsBmcJInhz4bh5a6Iilf06Ae');
INSERT INTO usuario(NOMBRE, NOMBREUSUARIO, EMAIL, PASSWORD) VALUES ('alumno2', 'alumno2', 'alumno2@gmail.com', '$2a$10$fELDKNlb5SgZxXBsXwEJ8u5hOqjz5YsBmcJInhz4bh5a6Iilf06Ae');
INSERT INTO usuario(NOMBRE, NOMBREUSUARIO, EMAIL, PASSWORD) VALUES ('profesor', 'profesor', 'profesor@gmail.com', '$2a$10$.UwZfShn.bN8fJ8kE/Kq3.frOHTxHglAk5VgIXnA1XCJkIDtRwRl.');

INSERT INTO USUARIO_ROL(USUARIO_ID, ROL_ID) VALUES('alumno', 1);
INSERT INTO USUARIO_ROL(USUARIO_ID, ROL_ID) VALUES('alumno2', 1);
INSERT INTO USUARIO_ROL(USUARIO_ID, ROL_ID) VALUES('profesor', 2);

INSERT INTO alumno(ID, NOMBREUSUARIO) VALUES (1, 'alumno');
INSERT INTO alumno(ID, NOMBREUSUARIO) VALUES (2, 'alumno2');
INSERT INTO profesor(ID, NOMBREUSUARIO) VALUES (1, 'profesor');

INSERT INTO asignatura(ID, NOMBRE, DESCRIPCION, CURSO, CODIGO, PROFESOR_ID) VALUES (1, 'Matematicas', 'Matematicas Asignatura', '1A', 'AAAA111', 1);
INSERT INTO asignatura(ID, NOMBRE, DESCRIPCION, CURSO, CODIGO, PROFESOR_ID) VALUES (2, 'Lengua', 'Lengua Asignatura', '2B', 'BBBB222', 1);
INSERT INTO asignatura(ID, NOMBRE, DESCRIPCION, CURSO, CODIGO, PROFESOR_ID) VALUES (3, 'Ingles', 'Ingles Asignatura', '3B', 'CCCC333', 1);

INSERT INTO ALUMNO_ASIGNATURA(ID, ESTADO, PUNTOS, ALUMNO_ID, ASIGNATURA_ID) VALUES (1, 'PETICION_ENVIADA', 100, 1, 1);
INSERT INTO ALUMNO_ASIGNATURA(ID, ESTADO, PUNTOS, ALUMNO_ID, ASIGNATURA_ID) VALUES (2, 'PETICION_ENVIADA', 200, 2, 1);


INSERT INTO tema(ID, NOMBRE, DESCRIPCION, ASIGNATURA_ID) VALUES (1, 'Tema 1', 'Desc tema 1', 1);
INSERT INTO tema(ID, NOMBRE, DESCRIPCION, ASIGNATURA_ID) VALUES (2, 'Tema 2', 'Desc tema 2', 1);
INSERT INTO tema(ID, NOMBRE, DESCRIPCION, ASIGNATURA_ID) VALUES (3, 'Tema 3', 'Desc tema 3', 1);
INSERT INTO tema(ID, NOMBRE, DESCRIPCION, ASIGNATURA_ID) VALUES (4, 'Tema 1', 'Desc tema 1', 2);
INSERT INTO tema(ID, NOMBRE, DESCRIPCION, ASIGNATURA_ID) VALUES (5, 'Tema 1', 'Desc tema 1', 3);


INSERT INTO artefacto(ID, NOMBRE, DESCRIPCION, COSTEPUNTOS, ESTADO, TEMPORAL, FECHAINICIO, FECHAFIN, ASIGNATURA_ID, REPETIBLE) VALUES (1, '1 punto mas en examen', '1 punto mas en examen', 100, 'PUBLICADO', false, null, null, 1, true);

INSERT INTO LOGRO(ID, DESCRIPCION, NOMBRE) VALUES (1, 'LOGRO', 'LOGRO');
INSERT INTO LOGRO(ID, DESCRIPCION, NOMBRE) VALUES (2, 'LOGRO_2', 'LOGRO_2');

INSERT INTO ARTEFACTO_LOGRO(LOGRO_ID, ARTEFACTO_ID, DESBLOQUEAR, OBTENER) VALUES(1, 1, false, false);
INSERT INTO ARTEFACTO_LOGRO(LOGRO_ID, ARTEFACTO_ID, DESBLOQUEAR, OBTENER) VALUES(2, 1, false, false);


INSERT INTO RETO(ID, DESCRIPCION, NOMBRE, PUNTOS_OTORGADOS, ASIGNATURA_ID, LOGRO_ID, TEMPORAL, FECHAINICIO, FECHAFIN, AUTOMATICO) VALUES (1, 'Reto', 'Reto', 100, 1, 1, false, null, null, false);
INSERT INTO RETO(ID, DESCRIPCION, NOMBRE, PUNTOS_OTORGADOS, ASIGNATURA_ID, LOGRO_ID, TEMPORAL, FECHAINICIO, FECHAFIN, AUTOMATICO) VALUES (2, 'Reto2', 'Reto2', 100, 1, 1, false, null, null, false);


INSERT INTO ALUMNOS_RETOS(ID, ESTADO, ALUMNO_ID, RETO_ID) VALUES (1, 'COMPLETADO', 1, 1);
INSERT INTO ALUMNOS_RETOS(ID, ESTADO, ALUMNO_ID, RETO_ID) VALUES (2, 'COMPLETADO', 2, 1);
INSERT INTO ALUMNOS_RETOS(ID, ESTADO, ALUMNO_ID, RETO_ID) VALUES (3, 'COMPLETADO', 1, 2);


INSERT INTO PREGUNTA(ID, ENUNCIADO, ALUMNO_ID, ASIGNATURA_ID, TEMA_ID) VALUES (1, '¿Cual es el nombre del rey?', 1, 1, 1);
INSERT INTO PREGUNTA(ID, ENUNCIADO, ALUMNO_ID, ASIGNATURA_ID, TEMA_ID) VALUES (2, '¿En que deporte juega el betis?', 1, 1, 1);
INSERT INTO PREGUNTA(ID, ENUNCIADO, ALUMNO_ID, ASIGNATURA_ID, TEMA_ID) VALUES (3, '¿Cuando acabo la guerra civil?', 1, 1, 1);

INSERT INTO RESPUESTA(ID, ES_CORRECTA, TEXTO, PREGUNTA_ID) VALUES (1, false, 'Pepe', 1);
INSERT INTO RESPUESTA(ID, ES_CORRECTA, TEXTO, PREGUNTA_ID) VALUES (2, false, 'Manolo', 1);
INSERT INTO RESPUESTA(ID, ES_CORRECTA, TEXTO, PREGUNTA_ID) VALUES (3, true, 'Felipe', 1);

INSERT INTO RESPUESTA(ID, ES_CORRECTA, TEXTO, PREGUNTA_ID) VALUES (4, false, 'Beisbol', 2);
INSERT INTO RESPUESTA(ID, ES_CORRECTA, TEXTO, PREGUNTA_ID) VALUES (5, true, 'Futbol', 2);
INSERT INTO RESPUESTA(ID, ES_CORRECTA, TEXTO, PREGUNTA_ID) VALUES (6, false, 'MMA', 2);

INSERT INTO RESPUESTA(ID, ES_CORRECTA, TEXTO, PREGUNTA_ID) VALUES (7, false, '1690', 3);
INSERT INTO RESPUESTA(ID, ES_CORRECTA, TEXTO, PREGUNTA_ID) VALUES (8, true, '1939', 3);
INSERT INTO RESPUESTA(ID, ES_CORRECTA, TEXTO, PREGUNTA_ID) VALUES (9, false, 'No acabo', 3);

INSERT INTO TEST(ID, AUTOMATICO, DESCRIPCION, NOMBRE, NUMERO_PREGUNTAS, FECHA_INICIO, FECHA_FIN) VALUES (1, true, 'Test de pruebas', 'Test de pruebas', 2, null, null);
