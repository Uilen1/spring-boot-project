CREATE TABLE lancamento(
	codigo BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	descricao VARCHAR(50) NOT NULL,
	data_vencimento DATE NOT NULL,
	data_pagamento DATE,
	valor DECIMAL(10,2) NOT NULL,
	observacao VARCHAR(100),
	tipo VARCHAR(20) NOT NULL,
	codigo_categoria BIGINT(20) NOT NULL,
	codigo_pessoa BIGINT(20) NOT NULL,
	FOREIGN KEY (codigo_categoria) REFERENCES categoria(codigo),
	FOREIGN KEY (codigo_pessoa) REFERENCES pessoa(codigo)
	
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO lancamento(descricao,data_vencimento,data_pagamento,valor,observacao,tipo,codigo_categoria,codigo_pessoa) values ('Salário mensal','2017-06-10',null, 6500.00,'Distribuição de lucros', 'RECEITA', 5,1);

INSERT INTO lancamento(descricao,data_vencimento,data_pagamento,valor,observacao,tipo,codigo_categoria,codigo_pessoa) values ('Bahamas','2017-02-10','2017-02-10', 100.32,null, 'DESPESA', 2,2);

INSERT INTO lancamento(descricao,data_vencimento,data_pagamento,valor,observacao,tipo,codigo_categoria,codigo_pessoa) values ('Top Club','2017-06-10',null, 120.00,null, 'RECEITA', 1,3);

INSERT INTO lancamento(descricao,data_vencimento,data_pagamento,valor,observacao,tipo,codigo_categoria,codigo_pessoa) values ('CEMIG','2017-02-10','2017-02-10', 110.44,'Geração', 'RECEITA', 5,6);

INSERT INTO lancamento(descricao,data_vencimento,data_pagamento,valor,observacao,tipo,codigo_categoria,codigo_pessoa) values ('DMAE','2017-06-10',null, 200.30,null, 'DESPESA', 5,5);

INSERT INTO lancamento(descricao,data_vencimento,data_pagamento,valor,observacao,tipo,codigo_categoria,codigo_pessoa) values ('Extra','2017-03-10',null, 1010.32,null, 'RECEITA', 3,6);

INSERT INTO lancamento(descricao,data_vencimento,data_pagamento,valor,observacao,tipo,codigo_categoria,codigo_pessoa) values ('Bahamas','2023-06-10',null, 500.00,null, 'RECEITA', 2,7);


INSERT INTO lancamento(descricao,data_vencimento,data_pagamento,valor,observacao,tipo,codigo_categoria,codigo_pessoa) values ('Top Club','2023-06-10',null, 120.00,null, 'RECEITA', 1,3);

INSERT INTO lancamento(descricao,data_vencimento,data_pagamento,valor,observacao,tipo,codigo_categoria,codigo_pessoa) values ('Despachante','2023-02-10','2023-02-10', 110.44,'Geração', 'RECEITA', 5,6);

INSERT INTO lancamento(descricao,data_vencimento,data_pagamento,valor,observacao,tipo,codigo_categoria,codigo_pessoa) values ('Pneus','2023-06-10',null, 2234.30,null, 'DESPESA', 5,5);

INSERT INTO lancamento(descricao,data_vencimento,data_pagamento,valor,observacao,tipo,codigo_categoria,codigo_pessoa) values ('Café','2023-03-10',null, 6.80,null, 'RECEITA', 2,6);

INSERT INTO lancamento(descricao,data_vencimento,data_pagamento,valor,observacao,tipo,codigo_categoria,codigo_pessoa) values ('Eletronicos','2023-06-10',null, 4899.00,null, 'DESPESA', 1,3);

INSERT INTO lancamento(descricao,data_vencimento,data_pagamento,valor,observacao,tipo,codigo_categoria,codigo_pessoa) values ('Instrumentos','2023-08-10',null, 780.55,null, 'DESPESA', 1,4);

INSERT INTO lancamento(descricao,data_vencimento,data_pagamento,valor,observacao,tipo,codigo_categoria,codigo_pessoa) values ('Lanche','2023-06-10',null, 10.20,null, 'DESPESA', 2,8);



