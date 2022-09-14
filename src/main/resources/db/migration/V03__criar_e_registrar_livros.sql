create TABLE tb_editora
(
    id         UUID PRIMARY KEY,
    nome       VARCHAR(50) NOT NULL,
    endereco_id            UUID      NOT NULL,
    FOREIGN KEY (endereco_id) references tb_endereco (id)
);



create TABLE tb_livro
(
    id                  UUID PRIMARY KEY,
    nome              VARCHAR(50) NOT NULL,
    genero              VARCHAR(50) NOT NULL,
    autor_id            UUID        NOT NULL,
    editora_id          UUID        NOT NULL,
    FOREIGN KEY (autor_id) references tb_autor (id),
    FOREIGN KEY (editora_id) references tb_editora (id)
);