insert into client (id, first_name, second_name, middle_name, passport, phone)
values (1, 'Иван', 'Иванович', 'Ивановов', '1416118745', '+79173652735');

insert into offer (id, client_id, date_of_offer, date_of_check, type, amount )
values (1, 1, '2021-11-05 15:00:00', '2021-11-05 15:00:00', 'mortgage', '5000000');

insert into client_status (id, client_id, level, regular)
values (1, 1, 'gold', true);

insert into address (id, name)
values (1, '119856, Россия, г.Москва, ул.Суворова, д.12, к.1, кв.45');

insert into client_address (client_id, address_id)
values (1, 1);


insert into offer (id, client_id, date_of_offer, date_of_check, type, amount )
values (2, 1, '2021-11-04 14:00:00', '2021-11-05 15:00:00', 'consumer', '100000');

insert into address (id, name)
values (2, '117564, Россия, г.Калуга, ул.Кирова, д.17, к.2, кв.5');

insert into client_address (client_id, address_id)
values (1, 2);

insert into client (id, first_name, second_name, middle_name, passport, phone)
values (2, 'Марина', 'Викторовна', 'Ивановова', '1515116785', '+79161352739');

insert into offer (id, client_id, date_of_offer, date_of_check, type, amount )
values (3, 2, '2021-11-03 13:00:00', '2021-11-05 15:00:00', 'auto', '2000000');

insert into client_status (id, client_id, level, regular)
values (2, 2, 'silver', false);

insert into client_address (client_id, address_id)
values (2, 1);
