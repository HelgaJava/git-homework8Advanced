create table houses(
    id integer primary key autoincrement ,
    price integer check ( price>0 ),
    district text,
    underground text

);

insert into houses (price, district, underground)
VALUES (3000000, 'Новосавиновский', 'Козья слобода');

insert into houses (price, district, underground)
VALUES (5000000, 'Вахитовский', 'Суконная слобода');

insert into houses (price, district, underground)
VALUES (6000000, 'Вахитовский', 'Площадь Тукая');

insert into houses (price, district, underground)
VALUES (3500000, 'Приволжский', 'Проспект Победы');

insert into houses (price, district, underground)
VALUES (4000000, 'Приволжский', 'Проспект Победы');