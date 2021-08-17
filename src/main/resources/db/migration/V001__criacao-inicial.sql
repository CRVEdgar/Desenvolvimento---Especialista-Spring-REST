CREATE SEQUENCE coz_id_seq;
create table tab_cozinhas (
     id bigint not null default nextval('coz_id_seq'),
     nome varchar(60) not null,

     primary key (id)
);
ALTER SEQUENCE coz_id_seq OWNED BY tab_cozinhas.id;