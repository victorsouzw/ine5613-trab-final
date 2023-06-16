CREATE DATABASE facilgest;
USE facilgest;


CREATE TABLE Produtos 
( 
 multiplicadorDesconto INT,  
 id_produto INT PRIMARY KEY AUTO_INCREMENT,  
 nome VARCHAR(100) NOT NULL,  
 valor_un INT NOT NULL,  
 estoque INT NOT NULL
); 

CREATE TABLE Clientes 
( 
 endereco VARCHAR(100),  
 CPF_CNPJ VARCHAR(100) PRIMARY KEY,  
 dataDeNascimento VARCHAR(100),  
 email VARCHAR(100),  
 nomeCompleto VARCHAR(100),  
 telefone VARCHAR(100)
); 

CREATE TABLE Nota_Fiscal 
( 
 id_nota INT PRIMARY KEY AUTO_INCREMENT,  
 tipo VARCHAR(100) NOT NULL DEFAULT 'Venda',  
 data_ VARCHAR(100) NOT NULL,  
 total_nota INT NOT NULL,  
 CPF_CNPJ VARCHAR(100),  
 CPF_Vendedor VARCHAR(100),  
 CPF_Gerente VARCHAR(100),  
 CNPJ_Fornecedor VARCHAR(100)
); 

CREATE TABLE Funcionario 
( 
 CPF VARCHAR(100) PRIMARY KEY,  
 nome VARCHAR(100) NOT NULL,  
 dataDeInicio VARCHAR(100) NOT NULL,  
 dataDeNascimento VARCHAR(100) NOT NULL
); 

CREATE TABLE Vendedor 
( 
 CPF_vendedor VARCHAR(100) PRIMARY KEY
); 

CREATE TABLE Gerente 
( 
 CPF_gerente VARCHAR(100) PRIMARY KEY
); 


CREATE TABLE vendas_mes 
( 
 numeroDeVendasMes INT,  
 valorVendasMes INT,  
 data_venda VARCHAR(100),  
 CPF_vendedor VARCHAR(100),
 PRIMARY KEY(data_venda,CPF_vendedor)
); 

CREATE TABLE compras_mes 
( 
 num_compras INT,  
 valor_compras INT,  
 data_compras VARCHAR(100),  
 CPF_gerente VARCHAR(100),
 PRIMARY KEY(data_compras,CPF_gerente)
); 


CREATE TABLE Fornecedor 
( 
 CNPJ VARCHAR(100) PRIMARY KEY,  
 tipoDeProduto VARCHAR(100) NOT NULL,  
 nome VARCHAR(100) NOT NULL
); 

CREATE TABLE lista_desejos 
( 
 preço_desejado INT,  
 CPF_CNPJ VARCHAR(100),  
 id_produto INT,
 PRIMARY KEY(CPF_CNPJ,id_produto)
); 

CREATE TABLE prod_not 
( 
 qntd INT NOT NULL,  
 valor_total INT NOT NULL,  
 id_nota INT,  
 id_produto INT,
 PRIMARY KEY (id_nota,id_produto)
); 

ALTER TABLE Nota_Fiscal ADD FOREIGN KEY(CPF_CNPJ) REFERENCES Clientes (CPF_CNPJ);
ALTER TABLE Nota_Fiscal ADD FOREIGN KEY(CPF_Vendedor) REFERENCES Vendedor (CPF_Vendedor);
ALTER TABLE Nota_Fiscal ADD FOREIGN KEY(CPF_Gerente) REFERENCES Gerente (CPF_Gerente);
ALTER TABLE Nota_Fiscal ADD FOREIGN KEY(CNPJ_Fornecedor) REFERENCES Fornecedor (CNPJ);
ALTER TABLE Vendedor ADD FOREIGN KEY(CPF_vendedor) REFERENCES Funcionario (CPF);
ALTER TABLE Gerente ADD FOREIGN KEY(CPF_gerente) REFERENCES Funcionario (CPF);
ALTER TABLE vendas_mes ADD FOREIGN KEY(CPF_Vendedor) REFERENCES Vendedor (CPF_Vendedor);
ALTER TABLE compras_mes ADD FOREIGN KEY(CPF_Gerente) REFERENCES Gerente (CPF_Gerente);
ALTER TABLE lista_desejos ADD FOREIGN KEY(CPF_CNPJ) REFERENCES Clientes (CPF_CNPJ);
ALTER TABLE lista_desejos ADD FOREIGN KEY(id_produto) REFERENCES Produtos (id_produto);
ALTER TABLE prod_not ADD FOREIGN KEY(id_nota) REFERENCES Nota_Fiscal (id_nota);
ALTER TABLE prod_not ADD FOREIGN KEY(id_produto) REFERENCES Produtos (id_produto);



INSERT INTO Produtos ( multiplicadorDesconto,nome,valor_un,estoque )
VALUES (0,'max steel',57,5);
INSERT INTO Produtos ( multiplicadorDesconto,nome,valor_un,estoque )
VALUES (0,'barbie quero ser medica',31,12);
INSERT INTO Produtos ( multiplicadorDesconto,nome,valor_un,estoque )
VALUES (0,'ominitrix',79,2);
INSERT INTO Produtos ( multiplicadorDesconto,nome,valor_un,estoque )
VALUES (0,'hot weels',9,40);
INSERT INTO Produtos ( multiplicadorDesconto,nome,valor_un,estoque )
VALUES (0,'my litle pony',18,32);
INSERT INTO Produtos ( multiplicadorDesconto,nome,valor_un,estoque )
VALUES (0,'chiclete trident',3,100);
INSERT INTO Produtos ( multiplicadorDesconto,nome,valor_un,estoque )
VALUES (0,'boneco do velho da havan',-100,1);




INSERT INTO Clientes(endereco ,CPF_CNPJ,dataDeNascimento,email,nomeCompleto,telefone)
VALUES('av ivo silveira, 1413','06961684111','04/10/2001','anamarryferreira88@gmail.com','ana maria ferreira silva', '48 996690227');
INSERT INTO Clientes(endereco ,CPF_CNPJ,dataDeNascimento,email,nomeCompleto,telefone)
VALUES('R.julio da silva rodrigues,119','11155501905','10/03/1998','arthuralexnascimento@hotmail.com','arthur alexandre nascimento', '48 999963152');
INSERT INTO Clientes(endereco ,CPF_CNPJ,dataDeNascimento,email,nomeCompleto,telefone)
VALUES('av qualquer coisa','0696111111','01/01/0001','botafogocampeao@yahoo.com','jorge matheus', '48 99659999');
INSERT INTO Clientes(endereco ,CPF_CNPJ,dataDeNascimento,email,nomeCompleto,telefone)
VALUES('R.muito longe,999','00001684211','04/12/2022',NULL,'enzo bezerra', NULL);


INSERT INTO Funcionario(CPF,nome,dataDeInicio,dataDeNascimento)
VALUES('11100001905','kleyton cezar','04/12/2022','01,01,0001');
INSERT INTO Funcionario(CPF,nome,dataDeInicio,dataDeNascimento)
VALUES('22200001905','xarles xavier','04/01/2022','01,01,1960');
INSERT INTO Funcionario(CPF,nome,dataDeInicio,dataDeNascimento)
VALUES('11200001905','dante sparda e silva','04/12/2022','02,01,0001');
INSERT INTO Funcionario(CPF,nome,dataDeInicio,dataDeNascimento)
VALUES('11300001905','richarlisson pombo guimaraes','01/11/2022','02,01,2000');


INSERT INTO Vendedor(CPF_vendedor)
VALUES('11100001905');
INSERT INTO Vendedor(CPF_vendedor)
VALUES('22200001905');


INSERT INTO Gerente(CPF_gerente) 
VALUES('11200001905');
INSERT INTO Gerente(CPF_gerente) 
VALUES('11300001905');


INSERT INTO vendas_mes(numeroDeVendasMes,valorVendasMes,data_venda,CPF_vendedor)
VALUES(1,36,'dezembro','11100001905');
INSERT INTO vendas_mes(numeroDeVendasMes,valorVendasMes,data_venda,CPF_vendedor)
VALUES(1,36,'novembro','11100001905');
INSERT INTO vendas_mes(numeroDeVendasMes,valorVendasMes,data_venda,CPF_vendedor)
VALUES(1,36,'outubro','11100001905');
INSERT INTO vendas_mes(numeroDeVendasMes,valorVendasMes,data_venda,CPF_vendedor)
VALUES(10,30,'outubro','22200001905');


INSERT INTO compras_mes(num_compras,valor_compras,data_compras,CPF_gerente)
VALUES(1,90,'dezembro','11200001905');
INSERT INTO compras_mes(num_compras,valor_compras,data_compras,CPF_gerente)
VALUES(1,110,'novembro','11200001905');




INSERT INTO Fornecedor (CNPJ,tipoDeProduto,nome)
VALUES('33.000.167/1002-00','brinquedos','Rihapy');
INSERT INTO Fornecedor (CNPJ,tipoDeProduto,nome)
VALUES('33.000.167/1002-11','materiais de limpeza','milium');


INSERT INTO Nota_Fiscal (tipo,data_,total_nota,CPF_CNPJ,CPF_Vendedor,CPF_Gerente,CNPJ_Fornecedor)
VALUES('venda','04/12/2022',36,'11155501905','11100001905',NULL,NULL);
INSERT INTO Nota_Fiscal (tipo,data_,total_nota,CPF_CNPJ,CPF_Vendedor,CPF_Gerente,CNPJ_Fornecedor)
VALUES('venda','04/11/2022',36,'11155501905','11100001905',NULL,NULL);
INSERT INTO Nota_Fiscal (tipo,data_,total_nota,CPF_CNPJ,CPF_Vendedor,CPF_Gerente,CNPJ_Fornecedor)
VALUES('venda','04/10/2022',36,'11155501905','11100001905',NULL,NULL);
INSERT INTO Nota_Fiscal (tipo,data_,total_nota,CPF_CNPJ,CPF_Vendedor,CPF_Gerente,CNPJ_Fornecedor)
VALUES('compra','02/12/2022',90,NULL,NULL,'11200001905','33.000.167/1002-00');
INSERT INTO Nota_Fiscal (tipo,data_,total_nota,CPF_CNPJ,CPF_Vendedor,CPF_Gerente,CNPJ_Fornecedor)
VALUES('compra','03/12/2022',110,NULL,NULL,'11200001905','33.000.167/1002-00');
INSERT INTO Nota_Fiscal (tipo,data_,total_nota,CPF_CNPJ,CPF_Vendedor,CPF_Gerente,CNPJ_Fornecedor)
VALUES('venda','04/10/2022',3,'00001684211','22200001905',NULL,NULL);
INSERT INTO Nota_Fiscal (tipo,data_,total_nota,CPF_CNPJ,CPF_Vendedor,CPF_Gerente,CNPJ_Fornecedor)
VALUES('venda','05/10/2022',3,'00001684211','22200001905',NULL,NULL);
INSERT INTO Nota_Fiscal (tipo,data_,total_nota,CPF_CNPJ,CPF_Vendedor,CPF_Gerente,CNPJ_Fornecedor)
VALUES('venda','06/10/2022',3,'00001684211','22200001905',NULL,NULL);
INSERT INTO Nota_Fiscal (tipo,data_,total_nota,CPF_CNPJ,CPF_Vendedor,CPF_Gerente,CNPJ_Fornecedor)
VALUES('venda','07/10/2022',3,'00001684211','22200001905',NULL,NULL);
INSERT INTO Nota_Fiscal (tipo,data_,total_nota,CPF_CNPJ,CPF_Vendedor,CPF_Gerente,CNPJ_Fornecedor)
VALUES('venda','08/10/2022',3,'00001684211','22200001905',NULL,NULL);
INSERT INTO Nota_Fiscal (tipo,data_,total_nota,CPF_CNPJ,CPF_Vendedor,CPF_Gerente,CNPJ_Fornecedor)
VALUES('venda','09/10/2022',3,'00001684211','22200001905',NULL,NULL);
INSERT INTO Nota_Fiscal (tipo,data_,total_nota,CPF_CNPJ,CPF_Vendedor,CPF_Gerente,CNPJ_Fornecedor)
VALUES('venda','10/10/2022',3,'00001684211','22200001905',NULL,NULL);
INSERT INTO Nota_Fiscal (tipo,data_,total_nota,CPF_CNPJ,CPF_Vendedor,CPF_Gerente,CNPJ_Fornecedor)
VALUES('venda','11/10/2022',3,'00001684211','22200001905',NULL,NULL);
INSERT INTO Nota_Fiscal (tipo,data_,total_nota,CPF_CNPJ,CPF_Vendedor,CPF_Gerente,CNPJ_Fornecedor)
VALUES('venda','12/10/2022',3,'00001684211','22200001905',NULL,NULL);
INSERT INTO Nota_Fiscal (tipo,data_,total_nota,CPF_CNPJ,CPF_Vendedor,CPF_Gerente,CNPJ_Fornecedor)
VALUES('venda','13/10/2022',3,'00001684211','22200001905',NULL,NULL);



INSERT INTO prod_not(qntd,valor_total,id_nota,id_produto)
VALUES(2,18,1,4);
INSERT INTO prod_not(qntd,valor_total,id_nota,id_produto)
VALUES(1,18,1,5);
INSERT INTO prod_not(qntd,valor_total,id_nota,id_produto)
VALUES(2,18,2,4);
INSERT INTO prod_not(qntd,valor_total,id_nota,id_produto)
VALUES(1,18,2,5);
INSERT INTO prod_not(qntd,valor_total,id_nota,id_produto)
VALUES(2,18,3,4);
INSERT INTO prod_not(qntd,valor_total,id_nota,id_produto)
VALUES(1,18,3,5);
INSERT INTO prod_not(qntd,valor_total,id_nota,id_produto)
VALUES(1,3,4,6);
INSERT INTO prod_not(qntd,valor_total,id_nota,id_produto)
VALUES(1,3,5,6);
INSERT INTO prod_not(qntd,valor_total,id_nota,id_produto)
VALUES(1,3,6,6);
INSERT INTO prod_not(qntd,valor_total,id_nota,id_produto)
VALUES(1,3,7,6);
INSERT INTO prod_not(qntd,valor_total,id_nota,id_produto)
VALUES(1,3,8,6);
INSERT INTO prod_not(qntd,valor_total,id_nota,id_produto)
VALUES(1,3,9,6);
INSERT INTO prod_not(qntd,valor_total,id_nota,id_produto)
VALUES(1,3,10,6);
INSERT INTO prod_not(qntd,valor_total,id_nota,id_produto)
VALUES(1,3,11,6);
INSERT INTO prod_not(qntd,valor_total,id_nota,id_produto)
VALUES(1,3,12,6);
INSERT INTO prod_not(qntd,valor_total,id_nota,id_produto)
VALUES(1,3,13,6);




INSERT INTO lista_desejos(preço_desejado,CPF_CNPJ,id_produto)
VALUES(5,'11155501905',4);
INSERT INTO lista_desejos(preço_desejado,CPF_CNPJ,id_produto)
VALUES(NULL,'11155501905',1);
INSERT INTO lista_desejos(preço_desejado,CPF_CNPJ,id_produto)
VALUES(15,'06961684111',5);
INSERT INTO lista_desejos(preço_desejado,CPF_CNPJ,id_produto)
VALUES(6,'06961684111',4);
INSERT INTO lista_desejos(preço_desejado,CPF_CNPJ,id_produto)
VALUES(NULL,'00001684211',4);
INSERT INTO lista_desejos(preço_desejado,CPF_CNPJ,id_produto)
VALUES(NULL,'00001684211',1);
INSERT INTO lista_desejos(preço_desejado,CPF_CNPJ,id_produto)
VALUES(NULL,'00001684211',5);
INSERT INTO lista_desejos(preço_desejado,CPF_CNPJ,id_produto)
VALUES(NULL,'00001684211',7);


