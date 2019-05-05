-- AktivExpo [ent8]
create table `aktivexpo` (
   `oid`  integer  not null,
  primary key (`oid`)
);


-- Expo_AktivExpo [rel7]
alter table `aktivexpo`  add column  `expo_expoid`  varchar(255);
alter table `aktivexpo`   add index fk_aktivexpo_expo (`expo_expoid`), add constraint fk_aktivexpo_expo foreign key (`expo_expoid`) references `expo` (`expoid`);


