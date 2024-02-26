drop database if exists springweb;
create database springweb;
use springweb;

drop database if exists todo;
create table todo(
	id int auto_increment,
    content varchar(30),
    deadline date,
    state boolean default false,
    constraint todo_pk_id primary key(id)
);

select * from todo;

drop table if exists article;

create table article(
	id bigint auto_increment,
    title varchar(255),
    content varchar(255),
    constraint article_pk_id primary key(id)
);

select * from article;

