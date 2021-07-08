CREATE DATABASE cars;

create table transport(

      id serial primary key,

      ttype varchar(255),
      speed int ,
      isTruck boolean


);

insert into tranport (ttype, speed, isTruck) values ("buick legend", 150, false);
update transport set type = "bus", speed = 100;

delete from tranport;



