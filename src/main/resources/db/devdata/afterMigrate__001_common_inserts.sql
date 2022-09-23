INSERT INTO crud.tb_autor(id, nome, nacionalidade, nascimento, cpf, sexo) values
('3404b937-e7f9-49bc-8230-da5f2b8c41f0', 'Machado de Assis', 'Brasileiro', '1839-06-21', '131.876.786-05', 'Masculino'),
('411a5759-08b2-4b32-a9cf-a368a4e68e5c', 'Fiódor Dostoiévski', 'Rússia', '1821-11-11','121.126.786-15', 'Masculino'),
('bf5f2972-8d89-447d-8805-b8fea4051abd', 'Martin Fowler', 'Reino Unido', '1963-12-18','161.112.796-11', 'Masculino') ON CONFLICT DO NOTHING;