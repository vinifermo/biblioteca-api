CREATE TABLE tb_autor
(
    id              UUID PRIMARY KEY,
    nome            VARCHAR(50) NOT NULL,
    nacionalidade   VARCHAR(50),
    nascimento      DATE        NOT NULL,
    cpf             VARCHAR(50),
    sexo            VARCHAR(50)
);