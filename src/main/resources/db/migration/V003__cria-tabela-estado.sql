CREATE SEQUENCE est_id_seq;
create table tab_estado (
    id bigint not null default nextval('est_id_seq'),
    nome varchar(80) not null,

    primary key (id)
);

ALTER SEQUENCE est_id_seq OWNED BY tab_estado.id;

insert into tab_estado (nome) select distinct nome_estado from tab_cidade;

alter table tab_cidade add column estado_id bigint not null;

update tab_cidade set estado_id = (select id from tab_estado where nome = tab_cidade.nome_estado);

alter table tab_cidade add constraint fk_cidade_estado
    foreign key (estado_id) references tab_estado (id);

alter table tab_cidade drop column nome_estado;

alter table tab_cidade RENAME nome_cidade TO nome;