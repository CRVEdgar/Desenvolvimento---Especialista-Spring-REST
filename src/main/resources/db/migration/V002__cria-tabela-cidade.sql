CREATE SEQUENCE cid_id_seq;
create table tab_cidade (
    id bigint not null default nextval('cid_id_seq'),
    nome_cidade varchar(80) not null,
    nome_estado varchar(80) not null,

    primary key (id)
);
ALTER SEQUENCE cid_id_seq OWNED BY tab_cidade.id;