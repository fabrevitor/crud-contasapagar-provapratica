-- create database contasdb;
-- drop database contasdb;
use contasdb;

CREATE TABLE filial (
  codigo int NOT NULL AUTO_INCREMENT,
  nome varchar(60) DEFAULT NULL,
  PRIMARY KEY (codigo)
);

CREATE TABLE contas (
  codigo int NOT NULL AUTO_INCREMENT,
  descricao varchar(60) NOT NULL,
  dataRegistro datetime NOT NULL,
  foiPago boolean NOT NULL,
  valor double NOT NULL,
  dataPagamento datetime NOT NULL,
  saldoAntes double NULL,
  saldoDepois double NULL,
  filialCodigo int NOT NULL,
  PRIMARY KEY (codigo),
  FOREIGN KEY (filialCodigo) REFERENCES filial (codigo)
);

INSERT INTO filial (nome) VALUES 
  ('Filial 1 - Palhoça'),
  ('Filial 2 - Floripa'),
  ('Filial 3 - São José');

INSERT INTO contas (descricao, dataRegistro, foiPago, valor, filialCodigo) VALUES 
  ('Conta de Luz','2021-04-15 00:00:00',0, 500,1),
  ('Conta de Água','2021-04-15 00:00:00', 0, 100,1),
  ('Conta de Água',CURRENT_TIMESTAMP(), 0, 150,2),
  ('Conta de Luz',CURRENT_TIMESTAMP(), 0, 150,2),
  ('IPTU',CURRENT_TIMESTAMP(), 0,1200,2),
  ('IPTU',CURRENT_TIMESTAMP(), 0, 1300,3);
  
  
  select * from filial;
  select * from contas;