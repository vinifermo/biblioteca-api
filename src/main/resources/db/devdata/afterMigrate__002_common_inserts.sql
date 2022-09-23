INSERT INTO tb_endereco(id, numero, cep, cidade, estado, bairro) values
('fa8c0a64-625c-4917-85e1-f32291a0d739','59', '86600167', 'Londrina', 'PR','Dutra'),
('356fc596-5587-4ecf-95a3-a178fc10f85f','584', '86707000', 'Arapongas', 'PR','Joa'),
('3e6ecfc7-e7da-4eb2-9398-4b36337536a6','1100', '13125-100', 'São Paulo', 'SP','Moema') ON CONFLICT DO NOTHING;

INSERT INTO tb_cliente(id, nome, endereco_id, nascimento, cpf, sexo) values
('0542a825-57f0-4fc9-ab97-fc652263cd97', 'Leonardo Arruda', 'fa8c0a64-625c-4917-85e1-f32291a0d739','05-08-1998','64367341046','Feminino'),
('3100be3a-9d80-423c-b16c-749ec749b5af', 'Victor Pietro','356fc596-5587-4ecf-95a3-a178fc10f85f', '05-04-1995', '02175467066','Feminino'),
('29006e7b-4d54-404a-902b-87364a4a59e8', 'Olavo Wilke','3e6ecfc7-e7da-4eb2-9398-4b36337536a6','05-08-1996','26912955088','Feminino') ON CONFLICT DO NOTHING;

--Livros inserts
INSERT INTO tb_editora(id, nome, endereco_id) values
('f5e9621e-a57f-48c6-83c4-96426e9d2004', 'Tipografia Nacional','fa8c0a64-625c-4917-85e1-f32291a0d739'),
('508660f7-9a44-4060-a455-99e6db911089', 'José Olympio','356fc596-5587-4ecf-95a3-a178fc10f85f'),
('3a7f98bc-19c9-493a-9447-48ae49963d0b', 'NovaTec Editora','3e6ecfc7-e7da-4eb2-9398-4b36337536a6') ON CONFLICT DO NOTHING;

INSERT INTO tb_livro(id, nome, genero, autor_id, editora_id) values
('3b792c7a-c9bc-4e0d-8834-f3414a4ed3ad', 'Memórias Póstumas de Braz Cubas', 'Drama', '3404b937-e7f9-49bc-8230-da5f2b8c41f0', 'f5e9621e-a57f-48c6-83c4-96426e9d2004'),
('5882eecd-d5f0-4c78-8544-e8c778c1f229', 'Notas do Subterrâneo', 'Suspense', '411a5759-08b2-4b32-a9cf-a368a4e68e5c', '508660f7-9a44-4060-a455-99e6db911089'),
('79ecf4c4-bc61-4367-8ad4-10b60ab315ee', 'Refatoração', 'Terror', 'bf5f2972-8d89-447d-8805-b8fea4051abd','3a7f98bc-19c9-493a-9447-48ae49963d0b') ON CONFLICT DO NOTHING;