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
    ticket_id   number primary key,
    ticket_date timestamp,
    title       nvarchar2(20),
    text        nvarchar2(255),
    group_name  nvarchar2(20),
    status      varchar2(6),
    user_name references person,
    person_id references person
);
create sequence ticket_seq start with 1 increment by 1;

create table RESPONSE
(
    response_id   number primary key,
    response_date timestamp,
    answer        nvarchar2(255),
    ticket_id references TICKET,
    person_id references person
);
create sequence response_seq start with 1 increment by 1;

create table Admin
(
    Admin_Id                       number primary key,
    username                     nvarchar2(20) unique,
    pass                               nvarchar2(10),
    person_id                     references PERSON,
    person_family               references PERSON,
    user_name                    references PERSON,
    password                      references PERSON,
    ticket_id                       references TICKET,
    response_id                 references RESPONSE
);
create sequence admin_seq start with 1 increment by 1;

create table SignIn
(
    sign_id                         number primary key,
    person_id                    references PERSON,
    user_name                   references PERSON,
    password                     references PERSON,
    email                            references PERSON,
    phone_number            references PERSON,
    admin_id                      references PERSON
);
create sequence signIn_seq start with 1 increment by 1;

create table Operator
(
    operate_id                      number primary key,
    operate_number            char(6),
    person_id                       references SignIn,
    user_name                     references SignIn,
    password                       references SIGNIN,
    email                              references SIGNIN,
    phone_number             references SignIn,
    sign_id                           references SignIn,
    username                      references Admin,
    pass                               references Admin,
    admin_id                       references SignIn
);
create sequence operator_seq start with 1 increment by 1;