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

INSERT INTO conta (nome_responsavel, cpf, nro_conta, saldo_total) VALUES ('Fulano', '13232466723', 1234, 500.11);
INSERT INTO conta (nome_responsavel, cpf, nro_conta, saldo_total) VALUES ('Sicrano', '12345678910', 4321, 1500.22);
INSERT INTO conta (nome_responsavel, cpf, nro_conta, saldo_total) VALUES ('Jorge', '79835663554', 789, 3000.87);
INSERT INTO conta (nome_responsavel, cpf, nro_conta, saldo_total) VALUES ('Pedro', '86543699922', 987, 1750.25);

INSERT INTO transferencia (data_transferencia, valor, tipo, nome_operador_transacao, conta_id) VALUES ('2019-01-01 12:00:00+03',30895.46,'DEPOSITO', null, 1);
INSERT INTO transferencia (data_transferencia, valor, tipo, nome_operador_transacao, conta_id) VALUES ('2019-02-03 09:53:27+03',12.24,'DEPOSITO', null,2);
INSERT INTO transferencia (data_transferencia, valor, tipo, nome_operador_transacao, conta_id) VALUES ('2019-05-04 08:12:45+03',-500.50,'SAQUE', null,1);
INSERT INTO transferencia (data_transferencia, valor, tipo, nome_operador_transacao, conta_id) VALUES ('2019-08-07 08:12:45+03',-530.50,'SAQUE', null,2);
INSERT INTO transferencia (data_transferencia, valor, tipo, nome_operador_transacao, conta_id) VALUES ('2020-06-08 10:15:01+03',3241.23,'TRANSFERENCIA_SAIDA', 'Beltrano',1);
INSERT INTO transferencia (data_transferencia, valor, tipo, nome_operador_transacao, conta_id) VALUES ('2021-04-01 12:12:04+03',25173.09,'TRANSFERENCIA_ENTRADA', 'Ronnyscley',2);