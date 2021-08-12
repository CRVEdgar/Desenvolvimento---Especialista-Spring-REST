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

insert into tab_restaurante (id, nome, taxa_frete, cozinha_id, data_cadastro, data_atualizacao, endereco_cidade_id, endereco_cep, endereco_logradouro, endereco_numero, endereco_bairro) values (1, 'Thai Gourmet', 10, 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 1, '38400-999', 'Rua João Pinheiro', '1000', 'Centro');
insert into tab_restaurante (id, nome, taxa_frete, cozinha_id, data_cadastro, data_atualizacao) values (2, 'Thai Delivery', 9.50, 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
insert into tab_restaurante (id, nome, taxa_frete, cozinha_id, data_cadastro, data_atualizacao) values (3, 'Tuk Tuk Comida Indiana', 15, 2, CURRENT_TIMESTAMP(0), CURRENT_TIMESTAMP(0));

-- insert into tab_restaurante (nome, taxa_frete, cozinha_id, endereco_cidade_id, endereco_cep, endereco_logradouro, endereco_numero, endereco_bairro) values ('Cabana do Sol', 10, 1, 1, '38400-999', 'Rua João Pinheiro', '1000', 'Centro');
-- insert into tab_restaurante (nome, taxa_frete, cozinha_id) values ('Chilli', 9.5, 1);
-- insert into tab_restaurante (nome, taxa_frete, cozinha_id) values ('Querencia dos Pampas', 15.7, 2);



insert into forma_pagamento (descricao) values ('Cartão de crédito');
insert into forma_pagamento (descricao) values ('Cartão de débito');
insert into forma_pagamento (descricao) values ('Dinheiro');

insert into permissao (nome, descricao) values ('CONSULTAR_COZINHAS', 'Permite consultar cozinhas');
insert into permissao (nome, descricao) values ('EDITAR_COZINHAS', 'Permite editar cozinhas');

insert into restaurante_forma_pagamento (restaurante_id, forma_pagamento_id) values (1, 1), (1, 2), (1, 3), (2, 3), (3, 2), (3, 3);
