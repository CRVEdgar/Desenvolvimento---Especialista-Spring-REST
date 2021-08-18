-- SET FOREIGN_KEY_CHECKS=0; -- Desabilita checagem de FK
SET session_replication_role = 'replica';

DELETE FROM tab_cidade;
DELETE FROM tab_cozinhas;
DELETE FROM tab_estado;
DELETE FROM tab_forma_pagamento;
DELETE FROM tab_grupos;
DELETE FROM tab_permissao;
DELETE FROM tab_produto;
DELETE FROM tab_restaurante;
DELETE FROM tab_usuarios;
DELETE FROM tbl_grupo_permissoes;
DELETE FROM tbl_restaurante_forma_pagamento;
DELETE FROM tbl_usuario_grupo;

-- SET FOREIGN_KEY_CHECKS=1; -- Ativa checagem de FK
SET session_replication_role = 'origin';

ALTER SEQUENCE cid_id_seq RESTART WITH 1;
ALTER SEQUENCE coz_id_seq RESTART WITH 1;
ALTER SEQUENCE est_id_seq RESTART WITH 1;
ALTER SEQUENCE tab_forma_pagamento_id_seq RESTART WITH 1;
ALTER SEQUENCE tab_grupos_id_seq RESTART WITH 1;
ALTER SEQUENCE tab_permissao_id_seq RESTART WITH 1;
ALTER SEQUENCE tab_produto_id_seq RESTART WITH 1;
ALTER SEQUENCE tab_restaurante_id_seq RESTART WITH 1;
ALTER SEQUENCE tab_usuarios_id_seq RESTART WITH 1;


-- INSERINDO OS DADOS
insert into tab_estado(nome) values ('Minhas Gerais');
insert into tab_estado(nome) values ('São Paulo');
insert into tab_estado(nome) values ('Ceará');

insert into tab_cidade(nome, estado_id) values ('Uberlandia', 1);
insert into tab_cidade(nome, estado_id) values ('Belo Horizonte', 1);
insert into tab_cidade(nome, estado_id) values ('São Paulo', 2);
insert into tab_cidade(nome, estado_id) values ('Campinas', 2);
insert into tab_cidade(nome, estado_id) values ('Fortaleza', 3);



insert into tab_cozinhas (nome) values ('Brasileira');
insert into tab_cozinhas (nome) values ('Australiana');
insert into tab_cozinhas (nome) values ('Tailandesa');
insert into tab_cozinhas (nome) values ('Indiana');


insert into tab_restaurante (nome, taxa_frete, cozinha_id, data_cadastro, data_atualizacao, endereco_cidade_id, endereco_cep, endereco_logradouro, endereco_numero, endereco_bairro) values ('Thai Gourmet', 10, 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 1, '38400-999', 'Rua João Pinheiro', '1000', 'Centro');
insert into tab_restaurante (nome, taxa_frete, cozinha_id, data_cadastro, data_atualizacao) values ('Thai Delivery', 9.50, 1, CURRENT_TIMESTAMP(0), CURRENT_TIMESTAMP(0));
insert into tab_restaurante (nome, taxa_frete, cozinha_id, data_cadastro, data_atualizacao) values ('Tuk Tuk Comida Indiana', 15, 2, CURRENT_TIMESTAMP(6), CURRENT_TIMESTAMP(6));
insert into tab_restaurante (nome, taxa_frete, cozinha_id, data_cadastro, data_atualizacao) values ('Java Steakhouse', 12, 3, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
insert into tab_restaurante (nome, taxa_frete, cozinha_id, data_cadastro, data_atualizacao) values ('Lanchonete do Tio Sam', 11, 4, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
insert into tab_restaurante (nome, taxa_frete, cozinha_id, data_cadastro, data_atualizacao) values ('Bar da Maria', 6, 4, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- insert into tab_restaurante (nome, taxa_frete, cozinha_id, endereco_cidade_id, endereco_cep, endereco_logradouro, endereco_numero, endereco_bairro) values ('Cabana do Sol', 10, 1, 1, '38400-999', 'Rua João Pinheiro', '1000', 'Centro');
-- insert into tab_restaurante (nome, taxa_frete, cozinha_id) values ('Chilli', 9.5, 1);
-- insert into tab_restaurante (nome, taxa_frete, cozinha_id) values ('Querencia dos Pampas', 15.7, 2);



insert into tab_forma_pagamento (descricao) values ('Cartão de crédito');
insert into tab_forma_pagamento (descricao) values ('Cartão de débito');
insert into tab_forma_pagamento (descricao) values ('Dinheiro');

insert into tab_permissao (nome, descricao) values ('CONSULTAR_COZINHAS', 'Permite consultar cozinhas');
insert into tab_permissao (nome, descricao) values ('EDITAR_COZINHAS', 'Permite editar cozinhas');

insert into tbl_restaurante_forma_pagamento (restaurante_id, forma_pagamento_id) values (1, 1), (1, 2), (1, 3), (2, 3), (3, 2), (3, 3);



insert into tab_produto (nome, descricao, preco, ativo, restaurante_id) values ('Porco com molho agridoce', 'Deliciosa carne suína ao molho especial', 78.90, '1', 1);
insert into tab_produto (nome, descricao, preco, ativo, restaurante_id) values ('Camarão tailandês', '16 camarões grandes ao molho picante', 110, '1', 1);

insert into tab_produto (nome, descricao, preco, ativo, restaurante_id) values ('Salada picante com carne grelhada', 'Salada de folhas com cortes finos de carne bovina grelhada e nosso molho especial de pimenta vermelha', 87.20, '1', 2);

insert into tab_produto (nome, descricao, preco, ativo, restaurante_id) values ('Garlic Naan', 'Pão tradicional indiano com cobertura de alho', 21, '1', 3);
insert into tab_produto (nome, descricao, preco, ativo, restaurante_id) values ('Murg Curry', 'Cubos de frango preparados com molho curry e especiarias', 43, '1', 3);

insert into tab_produto (nome, descricao, preco, ativo, restaurante_id) values ('Bife Ancho', 'Corte macio e suculento, com dois dedos de espessura, retirado da parte dianteira do contrafilé', 79, '1', 4);
insert into tab_produto (nome, descricao, preco, ativo, restaurante_id) values ('T-Bone', 'Corte muito saboroso, com um osso em formato de T, sendo de um lado o contrafilé e do outro o filé mignon', 89, '1', 4);

insert into tab_produto (nome, descricao, preco, ativo, restaurante_id) values ('Sanduíche X-Tudo', 'Sandubão com muito queijo, hamburger bovino, bacon, ovo, salada e maionese', 19, '1', 5);

insert into tab_produto (nome, descricao, preco, ativo, restaurante_id) values ('Espetinho de Cupim', 'Acompanha farinha, mandioca e vinagrete', 8, '1', 6);


-- insert into tab_cozinhas (id, nome) values (1, 'Brasileira') ON CONFLICT (id) DO NOTHING;
-- insert into tab_cozinhas (id, nome) values (2, 'Australiana') ON CONFLICT (id) DO NOTHING;
-- insert into tab_cozinhas (id, nome) values (3, 'Tailandesa') ON CONFLICT (id) DO NOTHING;
-- insert into tab_cozinhas (id, nome) values (4, 'Indiana') ON CONFLICT (id) DO NOTHING;