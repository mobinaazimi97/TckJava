CREATE TABLE PERSON(
person_id                   number primary key ,
person_name            nvarchar2(30),
person_family           nvarchar2(30),
phone_number        char(13),
Gmail                       nvarchar2(50),
user_name               nvarchar2(20),
password                nvarchar2(10));
create sequence person_seq start with 1 increment by 1;

CREATE TABLE TICKET(
ticket_id                       number,
ticket_date_time        timestamp,
person_id                    number primary key ,
title                              nvarchar2(60),
group_name               varchar2(10),
status                          varchar2(6));
create sequence ticket_seq start with 2 increment by 2;

create table respones(
respones_id                number,
ticket_id                     number primary key,
preson_id                   number,
respones_date_time   timestamp,
status                          varchar2(6),
answer                        nvarchar2(60));
create sequence respones_seq start with 3 increment by 3;
