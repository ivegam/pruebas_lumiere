CREATE TABLE IF NOT EXISTS operators(
  id serial NOT NULL,
  code character varying(255),
  name character varying(255),
  line_type character varying(255),
  custom_order bigint not null default 0,
  UNIQUE(code, line_type),
 CONSTRAINT pk_operators PRIMARY KEY (id)
);

-- Mobile operators
INSERT INTO operators (code, name, line_type, custom_order) values ('001','MOVISTAR','mobile',1);
INSERT INTO operators (code, name, line_type, custom_order) values ('003','VODAFONE','mobile',2);
INSERT INTO operators (code, name, line_type, custom_order) values ('004','ORANGE','mobile',3);
INSERT INTO operators (code, name, line_type, custom_order) values ('005','YOIGO','mobile',4);
INSERT INTO operators (code, name, line_type, custom_order) values ('006','EUSKALTEL','mobile',5);
INSERT INTO operators (code, name, line_type, custom_order) values ('007','TUENTI_MOVIL','mobile',6);
INSERT INTO operators (code, name, line_type, custom_order) values ('024','CARREFOUR','mobile',7);
INSERT INTO operators (code, name, line_type, custom_order) values ('034','DIA','mobile',8);
INSERT INTO operators (code, name, line_type, custom_order) values ('044','MASMOVIL','mobile',9);
INSERT INTO operators (code, name, line_type, custom_order) values ('064','SIMYO','mobile',10);
INSERT INTO operators (code, name, line_type, custom_order) values ('073','R CABLE Y TELECOMUNICACIONES','mobile',11);
INSERT INTO operators (code, name, line_type, custom_order) values ('074','JAZZTEL','mobile',12);

-- Land operators

INSERT INTO operators (code, name, line_type, custom_order) values ('001','MOVISTAR','land',1);
INSERT INTO operators (code, name, line_type, custom_order) values ('003','VODAFONE','land',2);
INSERT INTO operators (code, name, line_type, custom_order) values ('004','ORANGE','land',3);
INSERT INTO operators (code, name, line_type, custom_order) values ('005','YOIGO','land',4);
INSERT INTO operators (code, name, line_type, custom_order) values ('006','EUSKALTEL','land',5);
INSERT INTO operators (code, name, line_type, custom_order) values ('044','MASMOVIL','land',6);
INSERT INTO operators (code, name, line_type, custom_order) values ('073','R CABLE Y TELECOMUNICACIONES','land',7);
INSERT INTO operators (code, name, line_type, custom_order) values ('074','JAZZTEL','land',8);
