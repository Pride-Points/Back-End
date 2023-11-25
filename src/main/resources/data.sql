-- Inserir dados na tabela TB_EMPRESA
INSERT INTO tb_empresa
(nome_fantasia, cnpj, cep, numero, cidade, estado)
VALUES ('SPTECH', '101024944910000', '07243270', 49, 'Guarulhos', 'São Paulo'),('STEFANINI', '101024943910000', '07243270', 49, 'Eusabio matoso', 'São Paulo');

INSERT INTO tb_funcionario (ultima_troca_senha, email, nome, senha, cargo, cpf, is_ativo, is_gerente, tipo_funcionario, empresa_id)
VALUES (CURRENT_DATE(), 'caique@gmail.com', 'Caique Gomes', 'caique@123', 'Gerente', '51127145819', 1, 1, 'Admin', 1),(CURRENT_DATE(), 'douglas@gmail.com', 'Douglas Santos', 'Douglas@123', 'Gerente', '10000000000', 1, 1, 'Admin', 2);

-- Inserir dados na tabela tb_fisica
INSERT INTO tb_fisica (id, ultima_troca_senha, email, nome, senha, dt_nascimento, forcar_troca_de_senha, genero, orientacao_sexual)
VALUES (2, CURRENT_DATE(), 'medejlovesbja3aaork@gmail.com', 'Carloto', 'Medej@123', '2001-08-15', false,'homem cis', 'homossexual');

-- Inserir avaliação 1
INSERT INTO tb_avaliacao (id, nota, dt_avaliacao, tag, comentario, fisica_id, empresa_id)
VALUES (1, 9.5, '2023-09-28', 'Ótimo', 'Adorei conhecer o local, muito acolhedor e seguro, além de ter boas pessoas para conhecer', 2, 1);

-- Inserir avaliação 2
INSERT INTO tb_avaliacao (id, nota, dt_avaliacao, tag, comentario, fisica_id, empresa_id)
VALUES (2, 7.0, '2023-09-28', 'Médio', 'Mediano esse local', 2, 1);

-- Inserir avaliação 3
INSERT INTO tb_avaliacao (id, nota, dt_avaliacao, tag, comentario, fisica_id, empresa_id)
VALUES (3, 3.0, '2023-09-28', 'Horrível', 'Horrível este local', 2, 1);
