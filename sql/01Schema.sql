connect infor/sysm@prod
create user idl2 identified by idl2;
grant create session,select any table,create table,create sequence,create view,create procedure to idl2;
alter user idl2 default tablespace dw1 temporary tablespace temp;
alter user idl2 quota 100m on dw1;
