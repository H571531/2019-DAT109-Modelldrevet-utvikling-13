-- Expo_AktivExpo [rel7]
alter table   add column  `expo_expoid`  varchar(255);
alter table    add index fk_ent8_expo (`expo_expoid`), add constraint fk_ent8_expo foreign key (`expo_expoid`) references `expo` (`expoid`);


