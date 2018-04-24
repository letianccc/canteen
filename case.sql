drop database if exists test;
create database test;
use test;
drop table if exists card;
create table card
(
id int primary key auto_increment,
balance double not null
);

insert into card(id, balance) value(10000, 5000);
