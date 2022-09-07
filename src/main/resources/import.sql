insert into cozinha (id, name) values (1, 'Tailandesa');
insert into cozinha (id, name) values (2, 'Indiana');
insert into restaurante (nome, taxa_frete, cozinha_id) values ('Kekio Tai', 10 , 1);
insert into restaurante (id, nome, taxa_frete, cozinha_id, data_cadastro, data_atualizacao) values (2, 'Thai Delivery', 9.50, 1, utc_timestamp, utc_timestamp);
insert into restaurante (id, nome, taxa_frete, cozinha_id, data_cadastro, data_atualizacao) values (3, 'Tuk Tuk Comida Indiana', 15, 2, utc_timestamp, utc_timestamp);
insert into forma_pagamento (descricao) values ('Foi pago com cartao de credito');
insert into forma_pagamento (descricao) values ('Foi pago com pix');
insert into estado (id, nome) values (1, 'Pernambuco');
insert into estado (id, nome) values (2, 'Amazonas');
insert into cidade (id, nome, estado_id) values (1, 'Recife', 1);
insert into cidade (id, nome, estado_id) values (2, 'Olinda', 1);
insert into cidade (id, nome, estado_id) values (3, 'Manaus', 2);
insert into estado (id, nome) values (4, 'Minas Gerais');
insert into estado (id, nome) values (5, 'São Paulo');
insert into estado (id, nome) values (6, 'Ceará');

insert into cidade (id, nome, estado_id) values (4, 'Uberlândia', 4);
insert into cidade (id, nome, estado_id) values (5, 'Belo Horizonte', 4);
insert into cidade (id, nome, estado_id) values (6, 'São Paulo', 5);
insert into cidade (id, nome, estado_id) values (7, 'Campinas', 5);
insert into cidade (id, nome, estado_id) values (8, 'Fortaleza', 6);

insert into restaurante (id, nome, taxa_frete, cozinha_id, data_cadastro, data_atualizacao, endereco_cidade_id, endereco_cep, endereco_logradouro, endereco_numero, endereco_bairro) values (4, 'Thai Gourmet', 10, 1, utc_timestamp, utc_timestamp, 1, '38400-999', 'Rua João Pinheiro', '1000', 'Centro');
insert into forma_pagamento (id, descricao) values (1, 'Cartão de crédito');
insert into forma_pagamento (id, descricao) values (2, 'Cartão de débito');
insert into forma_pagamento (id, descricao) values (3, 'Dinheiro');

insert into permissao (id, nome, descricao) values (1, 'CONSULTAR_COZINHAS', 'Permite consultar cozinhas');
insert into permissao (id, nome, descricao) values (2, 'EDITAR_COZINHAS', 'Permite editar cozinhas');


insert into restaurante_forma_pagamento (restaurante_id, forma_pagamento_id) values (1, 1), (1, 2), (1, 3), (2, 3), (3, 2), (3, 3);