CREATE TABLE PERSON(
person_id                   number primary key ,
person_name            nvarchar2(30),
person_family           nvarchar2(30),
phone_number        char(13),
Email                       nvarchar2(50),
user_name               nvarchar2(20) unique ,
role                          varchar2(5),
password                nvarchar2(10),
enabled                  number(1)
);
create sequence person_seq start with 1 increment by 1;

CREATE TABLE TICKET(
ticket_id                       number primary key ,
ticket_date_time        timestamp,
person_id                   references  p,
title                             nvarchar2(20),
text                             nvarchar2(70),
group_name              nvarchar2(10));
create sequence ticket_seq start with 1 increment by 1;

create table response(
response_id                number primary key ,
ticket_id                     references TICKET ,
person_id                   references p  ,
response_date_time   timestamp,
status                          varchar2(6),
answer                        nvarchar2(60));
create sequence response_seq start with 1 increment by 1;
