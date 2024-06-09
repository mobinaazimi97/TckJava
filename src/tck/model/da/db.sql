CREATE TABLE PERSON
(
    person_id     number primary key,
    person_name   nvarchar2(30),
    person_family nvarchar2(30),
    phone_number  char(13),
    email         nvarchar2(50),
    user_name     nvarchar2(20) unique,
    password      nvarchar2(10),
    role          varchar2(5),
    enabled       number(1)
);
create sequence person_seq start with 1 increment by 1;

CREATE TABLE TICKET
(
    ticket_id        number primary key,
    ticket_date_time timestamp,
    title            nvarchar2(20),
    text             nvarchar2(255),
    group_name       nvarchar2(20),
    status             varchar2(6),
    person_id references person
);
create sequence ticket_seq start with 1 increment by 1;

create table response
(
    response_id        number primary key,
    response_date_time timestamp,
    answer             nvarchar2(255),
    status             varchar2(6),
    ticket_id references ticket,
    person_id references person
);
create sequence response_seq start with 1 increment by 1;
