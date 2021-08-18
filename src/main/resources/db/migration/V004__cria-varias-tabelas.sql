create table tab_forma_pagamento (
     id  bigserial not null,
     descricao varchar(255) not null,
     primary key (id)
);

create table tab_grupos (
    id  bigserial not null,
    nome varchar(255),
    primary key (id)
);

create table tab_permissao (
   id  bigserial not null,
   descricao varchar(255) not null,
   nome varchar(255) not null,
   primary key (id)
);

create table tab_produto (
     id  bigserial not null,
     ativo boolean,
     descricao varchar(255),
     nome varchar(255),
     preco numeric(19, 2),
     restaurante_id int8,
     primary key (id)
);

create table tab_restaurante (
     id  bigserial not null,
     data_atualizacao timestamp not null,
     data_cadastro timestamp not null,
     endereco_bairro varchar(255),
     endereco_cep varchar(255),
     endereco_complemento varchar(255),
     endereco_logradouro varchar(255),
     endereco_numero varchar(255),
     nome varchar(255) not null,
     taxa_frete numeric(19, 2) not null,
     cozinha_id int8 not null,
     endereco_cidade_id int8,
     primary key (id)
);

create table tab_usuarios (
      id  bigserial not null,
      data_cadastro timestamp,
      email varchar(255),
      nome varchar(255),
      senha varchar(255),
      primary key (id)
);

create table tbl_grupo_permissoes (
      grupo_id int8 not null,
      permisoes_id int8 not null
);

create table tbl_restaurante_forma_pagamento (
     restaurante_id int8 not null,
     forma_pagamento_id int8 not null
);

create table tbl_usuario_grupo (
    usuario_id int8 not null,
    grupos_id int8 not null
);




alter table if exists tab_produto
    add constraint fk_produto_restaurante
        foreign key (restaurante_id)
            references tab_restaurante(id);

alter table if exists tab_restaurante
    add constraint fk_restaurante_cozinha
        foreign key (cozinha_id)
            references tab_cozinhas(id);

alter table if exists tab_restaurante
    add constraint fk_restaurante_cidade
        foreign key (endereco_cidade_id)
            references tab_cidade(id);

alter table if exists tbl_grupo_permissoes
    add constraint fk_grupo_permissao_permissao
        foreign key (permisoes_id)
            references tab_permissao(id);

alter table if exists tbl_grupo_permissoes
    add constraint fk_grupo_permissao_grupo
        foreign key (grupo_id)
            references tab_grupos(id);

alter table if exists tbl_restaurante_forma_pagamento
    add constraint fk_rest_forma_pagto_forma_pagto
        foreign key (forma_pagamento_id)
            references tab_forma_pagamento(id);

alter table if exists tbl_restaurante_forma_pagamento
    add constraint fk_rest_forma_pagto_restaurante
        foreign key (restaurante_id)
            references tab_restaurante(id);

alter table if exists tbl_usuario_grupo
    add constraint fk_usuario_grupo_grupo
        foreign key (grupos_id)
            references tab_grupos(id);

alter table if exists tbl_usuario_grupo
    add constraint fk_usuario_grupo_usuario
        foreign key (usuario_id)
            references tab_usuarios(id);
