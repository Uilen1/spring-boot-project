CREATE TABLE pessoa(
	codigo BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	nome VARCHAR(50) NOT NULL,
	ativo BOOLEAN NOT NULL DEFAULT true,
	logradouro VARCHAR(50),
	numero VARCHAR(50),
	complemento VARCHAR(50),
	bairro VARCHAR(50),
	cep VARCHAR(50),
	cidade VARCHAR(50),
	estado VARCHAR(50) 
	
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO pessoa(nome,ativo,logradouro,numero,complemento,bairro,cep,cidade,estado) values ('Uilen',true,'Rua jouvert buck','345','casa','jardim europa','14640000','morro agudo','São Paulo');

INSERT INTO pessoa(nome,ativo,logradouro,numero,complemento,bairro,cep,cidade,estado) values ('Amanda Carolina',true,'Rua jouvert buck','345','casa','jardim europa','14640000','morro agudo','São Paulo');

INSERT INTO pessoa(nome,ativo,logradouro,numero,complemento,bairro,cep,cidade,estado) values ('Carlos',true,'Rua tiradentes','312','casa','centro','14640000','morro agudo','São Paulo');

INSERT INTO pessoa(nome,ativo,logradouro,numero,complemento,bairro,cep,cidade,estado) values ('Angela',true,'Rua tiradentes','312','casa','centro','14640000','morro agudo','São Paulo');

INSERT INTO pessoa(nome,ativo,logradouro,numero,complemento,bairro,cep,cidade,estado) values ('Adir',true,'Rua tiradentes','312','casa','centro','14640000','morro agudo','São Paulo');

INSERT INTO pessoa(nome,ativo,logradouro,numero,complemento,bairro,cep,cidade,estado) values ('Jordana',true,'Rua tiradentes','312','casa','centro','14640000','morro agudo','São Paulo');

INSERT INTO pessoa(nome,ativo,logradouro,numero,complemento,bairro,cep,cidade,estado) values ('Antonio',true,'Rua tiradentes','312','casa','centro','14640000','morro agudo','São Paulo');

