insert into client (id, first_name, second_name, middle_name, passport, phone)
values (1001, 'Иван', 'Иванович', 'Ивановов', '1416118745', '+79173652735');

insert into offer (id, client_id, date_of_offer, date_of_check, type, amount )
values (1001, 1001, '2021-11-05 15:00:00', '2021-11-05 15:00:00', 'mortgage', '5000000');

insert into client_status (id, client_id, level, regular)
values (1001, 1001, 'gold', true);

insert into address (id, name)
values (1001, '119856, Россия, г.Москва, ул.Суворова, д.12, к.1, кв.45');

insert into client_address (client_id, address_id)
values (1001, 1001);


insert into offer (id, client_id, date_of_offer, date_of_check, type, amount )
values (1002, 1001, '2021-11-04 14:00:00', '2021-11-05 15:00:00', 'consumer', '100000');

insert into address (id, name)
values (1002, '117564, Россия, г.Калуга, ул.Кирова, д.17, к.2, кв.5');

insert into client_address (client_id, address_id)
values (1001, 1002);

insert into client (id, first_name, second_name, middle_name, passport, phone)
values (1002, 'Марина', 'Викторовна', 'Ивановова', '1515116785', '+79161352739');

insert into offer (id, client_id, date_of_offer, date_of_check, type, amount )
values (1003, 1002, '2021-11-03 13:00:00', '2021-11-05 15:00:00', 'auto', '2000000');

insert into client_status (id, client_id, level, regular)
values (1002, 1002, 'silver', false);

insert into client_address (client_id, address_id)
values (1002, 1001);

insert into passport_black_list (id, number)
values (1004, '1498914545');
values (1005, '1216185749');
values (1006, '1866858709');
values (1007, '1616838746');
values (1008, '1425178756');

insert into phone_black_list (id, number)
values (1009, '+79161352739');
values (1010, '+79167975633');
values (1011, '+79186758638');
values (1012, '+79129866734');
values (1013, '+79141396730');

insert into address_black_list (id, name)
values (1014, '117544, Россия, г.Тула, ул.Мира, д.16, к.1, кв.50');
values (1015, '118534, Россия, г.Москва, ул.Тверская, д.19, к.3, кв.9');
values (1016, '147661, Россия, г.Москва, ул.Белая, д.56, к.1, кв.52');
values (1017, '118534, Россия, г.Москва, ул.Правды, д.89, к.4, кв.76');
values (1018, '116566, Россия, г.Москва, ул.Озерная, д.48, к.1, кв.69');

