CREATE TABLE conta
(
    id_conta IDENTITY NOT NULL PRIMARY KEY auto_increment,
    nro_conta NUMERIC UNIQUE NOT NULL,
    saldo_total NUMERIC (20,2) NOT NULL,
    cpf VARCHAR(11) UNIQUE NOT NULL,
    nome_responsavel VARCHAR(50) NOT NULL
);


CREATE TABLE transferencia
(
    id IDENTITY NOT NULL PRIMARY KEY auto_increment,
    data_transferencia TIMESTAMP WITH TIME ZONE NOT NULL,
    valor NUMERIC (20,2) NOT NULL,
    tipo TEXT NOT NULL CHECK( tipo IN ('DEPOSITO','SAQUE','TRANSFERENCIA_ENTRADA', 'TRANSFERENCIA_SAIDA') ),
    nome_operador_transacao VARCHAR (50),
    conta_id INT NOT NULL,

        CONSTRAINT FK_CONTA
        FOREIGN KEY (conta_id)
        REFERENCES conta(id_conta)
);

INSERT INTO conta (nome_responsavel, cpf, nro_conta, saldo_total) VALUES ('Fulano', '13232466723', 123452, 500.11);
INSERT INTO conta (nome_responsavel, cpf, nro_conta, saldo_total) VALUES ('Sicrano', '12345678910', 743214, 1500.22);
INSERT INTO conta (nome_responsavel, cpf, nro_conta, saldo_total) VALUES ('Jorge', '79835663554',   278923, 3000.87);
INSERT INTO conta (nome_responsavel, cpf, nro_conta, saldo_total) VALUES ('Pedro', '86543699922', 398712, 1750.25);

INSERT INTO transferencia (data_transferencia, valor, tipo, nome_operador_transacao, conta_id) VALUES ('2019-01-01 12:00:00+03',30895.46,'DEPOSITO', null, 1);
INSERT INTO transferencia (data_transferencia, valor, tipo, nome_operador_transacao, conta_id) VALUES ('2019-02-03 09:53:27+03',12.24,'DEPOSITO', null,2);
INSERT INTO transferencia (data_transferencia, valor, tipo, nome_operador_transacao, conta_id) VALUES ('2019-05-04 08:12:45+03',-500.50,'SAQUE', null,1);
INSERT INTO transferencia (data_transferencia, valor, tipo, nome_operador_transacao, conta_id) VALUES ('2019-08-07 08:12:45+03',-530.50,'SAQUE', null,2);
INSERT INTO transferencia (data_transferencia, valor, tipo, nome_operador_transacao, conta_id) VALUES ('2020-06-08 10:15:01+03',3241.23,'TRANSFERENCIA_SAIDA', 'Beltrano',1);
INSERT INTO transferencia (data_transferencia, valor, tipo, nome_operador_transacao, conta_id) VALUES ('2021-04-01 12:12:04+03',25173.09,'TRANSFERENCIA_ENTRADA', 'Ronnyscley',2);

-- Comandos de inserção para a tabela 'transferencia'

-- Transferências de Depósito
INSERT INTO transferencia (data_transferencia, valor, tipo, nome_operador_transacao, conta_id) VALUES ('2019-01-01 12:00:00+03', 30895.46, 'DEPOSITO', null, 1);
INSERT INTO transferencia (data_transferencia, valor, tipo, nome_operador_transacao, conta_id) VALUES ('2019-02-03 09:53:27+03', 12.24, 'DEPOSITO', null, 2);
INSERT INTO transferencia (data_transferencia, valor, tipo, nome_operador_transacao, conta_id) VALUES ('2022-03-12 15:30:00+03', 1500.75, 'DEPOSITO', null, 1);
INSERT INTO transferencia (data_transferencia, valor, tipo, nome_operador_transacao, conta_id) VALUES ('2022-04-20 14:45:12+03', 300.50, 'DEPOSITO', null, 2);
INSERT INTO transferencia (data_transferencia, valor, tipo, nome_operador_transacao, conta_id) VALUES ('2022-01-05 14:00:00+03', 1000.50, 'DEPOSITO', null, 3);
INSERT INTO transferencia (data_transferencia, valor, tipo, nome_operador_transacao, conta_id) VALUES ('2022-03-08 16:30:27+03', 250.75, 'DEPOSITO', null, 3);
INSERT INTO transferencia (data_transferencia, valor, tipo, nome_operador_transacao, conta_id) VALUES ('2022-02-10 09:45:12+03', 5000.00, 'DEPOSITO', null, 4);
INSERT INTO transferencia (data_transferencia, valor, tipo, nome_operador_transacao, conta_id) VALUES ('2022-04-15 11:20:18+03', 800.25, 'DEPOSITO', null, 4);

-- Transferências de Saque
INSERT INTO transferencia (data_transferencia, valor, tipo, nome_operador_transacao, conta_id) VALUES ('2019-05-04 08:12:45+03', -500.50, 'SAQUE', null, 1);
INSERT INTO transferencia (data_transferencia, valor, tipo, nome_operador_transacao, conta_id) VALUES ('2019-08-07 08:12:45+03', -530.50, 'SAQUE', null, 2);
INSERT INTO transferencia (data_transferencia, valor, tipo, nome_operador_transacao, conta_id) VALUES ('2022-06-05 09:22:56+03', -200.00, 'SAQUE', null, 1);
INSERT INTO transferencia (data_transferencia, valor, tipo, nome_operador_transacao, conta_id) VALUES ('2022-07-10 11:18:33+03', -50.00, 'SAQUE', null, 2);
INSERT INTO transferencia (data_transferencia, valor, tipo, nome_operador_transacao, conta_id) VALUES ('2022-08-28 16:05:09+03', -1000.00, 'SAQUE', null, 1);
INSERT INTO transferencia (data_transferencia, valor, tipo, nome_operador_transacao, conta_id) VALUES ('2022-05-20 08:12:45+03', -300.25, 'SAQUE', null, 3);
INSERT INTO transferencia (data_transferencia, valor, tipo, nome_operador_transacao, conta_id) VALUES ('2022-07-25 10:05:33+03', -150.50, 'SAQUE', null, 3);
INSERT INTO transferencia (data_transferencia, valor, tipo, nome_operador_transacao, conta_id) VALUES ('2022-06-30 11:30:22+03', -400.00, 'SAQUE', null, 4);
INSERT INTO transferencia (data_transferencia, valor, tipo, nome_operador_transacao, conta_id) VALUES ('2022-08-10 12:40:15+03', -200.75, 'SAQUE', null, 4);

-- Transferências de Saída
INSERT INTO transferencia (data_transferencia, valor, tipo, nome_operador_transacao, conta_id) VALUES ('2020-06-08 10:15:01+03', 3241.23, 'TRANSFERENCIA_SAIDA', 'Beltrano', 1);
INSERT INTO transferencia (data_transferencia, valor, tipo, nome_operador_transacao, conta_id) VALUES ('2022-09-15 13:40:17+03', 800.00, 'TRANSFERENCIA_SAIDA', 'Fulano', 1);
INSERT INTO transferencia (data_transferencia, valor, tipo, nome_operador_transacao, conta_id) VALUES ('2022-09-05 13:15:35+03', 500.50, 'TRANSFERENCIA_SAIDA', 'Ciclano', 3);
INSERT INTO transferencia (data_transferencia, valor, tipo, nome_operador_transacao, conta_id) VALUES ('2022-11-15 15:20:45+03', 300.75, 'TRANSFERENCIA_SAIDA', 'Fulano', 3);
INSERT INTO transferencia (data_transferencia, valor, tipo, nome_operador_transacao, conta_id) VALUES ('2022-10-10 14:40:27+03', 1000.00, 'TRANSFERENCIA_SAIDA', 'Beltrano', 4);
INSERT INTO transferencia (data_transferencia, valor, tipo, nome_operador_transacao, conta_id) VALUES ('2022-12-20 16:55:32+03', 500.25, 'TRANSFERENCIA_SAIDA', 'Ronnyscley', 4);
-- Transferências de Entrada
INSERT INTO transferencia (data_transferencia, valor, tipo, nome_operador_transacao, conta_id) VALUES ('2021-04-01 12:12:04+03', 25173.09, 'TRANSFERENCIA_ENTRADA', 'Ronnyscley', 2);
INSERT INTO transferencia (data_transferencia, valor, tipo, nome_operador_transacao, conta_id) VALUES ('2022-10-19 17:55:21+03', 1200.00, 'TRANSFERENCIA_SAIDA', 'Ciclano', 2);
INSERT INTO transferencia (data_transferencia, valor, tipo, nome_operador_transacao, conta_id) VALUES ('2022-11-25 12:30:10+03', 2000.00, 'TRANSFERENCIA_ENTRADA', 'Beltrano', 1);
INSERT INTO transferencia (data_transferencia, valor, tipo, nome_operador_transacao, conta_id) VALUES ('2022-12-30 09:10:05+03', 500.00, 'TRANSFERENCIA_ENTRADA', 'Ronnyscley', 2);
INSERT INTO transferencia (data_transferencia, valor, tipo, nome_operador_transacao, conta_id) VALUES ('2022-09-25 18:10:50+03', 700.00, 'TRANSFERENCIA_ENTRADA', 'Fulano', 3);
INSERT INTO transferencia (data_transferencia, valor, tipo, nome_operador_transacao, conta_id) VALUES ('2022-11-30 19:45:10+03', 400.25, 'TRANSFERENCIA_ENTRADA', 'Ciclano', 3);
INSERT INTO transferencia (data_transferencia, valor, tipo, nome_operador_transacao, conta_id) VALUES ('2022-10-20 17:25:12+03', 1200.50, 'TRANSFERENCIA_ENTRADA', 'Ronnyscley', 4);
INSERT INTO transferencia (data_transferencia, valor, tipo, nome_operador_transacao, conta_id) VALUES ('2022-12-25 18:55:20+03', 600.75, 'TRANSFERENCIA_ENTRADA', 'Beltrano', 4);