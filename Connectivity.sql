use COMP333_Con_Base;

drop database COMP333_Con_Base;

create database COMP333_Con_Base;

DROP TABLE useraccount;

CREATE TABLE `stat` (
  `connectivity` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`connectivity`));

delete from stat 
where connectivity=NULL;

insert into stat (connectivity)
values ('Connected Succssfully');

select * from stat;

select connectivity from stat;