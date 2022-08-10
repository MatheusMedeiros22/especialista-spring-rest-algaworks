insert into cozinha (id, name) values (1, 'Tailandesa');
insert into cozinha (id, name) values (2, 'Indiana');
insert into restaurante (nome, taxa_frete, cozinha_id) values ('Kekio Tai', 10 , 1);
insert into restaurante (nome, taxa_frete, cozinha_id) values ('Kazue Tai', 9.50, 1);
insert into restaurante (nome, taxa_frete, cozinha_id) values ('Tomario Tai', 15, 2);
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

insert into forma_pagamento (id, descricao) values (3, 'Cartão de crédito');
insert into forma_pagamento (id, descricao) values (4, 'Cartão de débito');
insert into forma_pagamento (id, descricao) values (5, 'Dinheiro');

