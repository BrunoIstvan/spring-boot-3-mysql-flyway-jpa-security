CREATE TABLE patients (
    id bigint not null auto_increment,
    fullName varchar(100) not null,
    mail varchar(100) not null,
    cpf varchar(14) not null,
    address varchar(100) not null,
    phone varchar(20) not null,
    neighborhood varchar(100) not null,
    postalCode varchar(9) not null,
    city varchar(100) not null,
    state varchar(2) not null,
    complement varchar(100) not null,
    number varchar(20) not null,
    primary key(id)
);