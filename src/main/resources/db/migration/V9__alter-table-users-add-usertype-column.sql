alter table users add type varchar(100) null;
update users set type = 'PATIENT';
alter table users alter column type varchar(100) not null;