
CREATE SEQUENCE ped_id_seq;
create table tab_pedido (
    id bigint not null default nextval('ped_id_seq'),
    subtotal decimal(10,2) not null,
    taxa_frete decimal(10,2) not null,
    valor_total decimal(10,2) not null,

    restaurante_id bigint not null,
    usuario_cliente_id bigint not null,
    forma_pagamento_id bigint not null,

    endereco_cidade_id bigint not null,
    endereco_cep varchar(9) not null,
    endereco_logradouro varchar(100) not null,
    endereco_numero varchar(20) not null,
    endereco_complemento varchar(60) null,
    endereco_bairro varchar(60) not null,

    status varchar(10) not null,
    data_criacao timestamp not null,
    data_confirmacao timestamp null,
    data_cancelamento timestamp null,
    data_entrega timestamp null,

    primary key (id),

    constraint fk_pedido_restaurante foreign key (restaurante_id) references tab_restaurante (id),
    constraint fk_pedido_usuario_cliente foreign key (usuario_cliente_id) references tab_usuarios (id),
    constraint fk_pedido_forma_pagamento foreign key (forma_pagamento_id) references tab_forma_pagamento (id)
);
ALTER SEQUENCE ped_id_seq OWNED BY tab_pedido.id;

CREATE SEQUENCE item_ped_id_seq;
create table tab_itemPedido (
     id bigint not null default nextval('item_ped_id_seq'),
     quantidade smallint not null,
     preco_unitario decimal(10,2) not null,
     preco_total decimal(10,2) not null,
     observacao varchar(255) null,
     pedido_id bigint not null,
     produto_id bigint not null,

     primary key (id),
     constraint uk_item_pedido_produto UNIQUE (pedido_id, produto_id),
--      UNIQUE key uk_item_pedido_produto (pedido_id, produto_id),


     constraint fk_item_pedido_pedido foreign key (pedido_id) references tab_pedido (id),
     constraint fk_item_pedido_produto foreign key (produto_id) references tab_produto (id)
) ;
ALTER SEQUENCE item_ped_id_seq OWNED BY tab_itemPedido.id;