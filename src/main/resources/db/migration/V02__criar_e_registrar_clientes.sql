create table tb_endereco
(
    id          UUID primary key,
    numero      varchar(50),
    cep         varchar(50),
    bairro      varchar(50),
    cidade      varchar(50),
    estado      varchar(2)
);

create table tb_cliente
(
    id              UUID primary key,
    nome            varchar(100),
    nascimento      DATE        NOT NULL,
    cpf             varchar(11),
    sexo            VARCHAR(50),
    endereco_id     UUID,
    foreign key (endereco_id) references tb_endereco (id)
);