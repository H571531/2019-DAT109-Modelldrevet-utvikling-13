-- Group [Group]
create table `group` (
   `oid`  integer  not null,
   `groupname`  varchar(255),
  primary key (`oid`)
);


-- Module [Module]
create table `module` (
   `oid`  integer  not null,
   `moduleid`  varchar(255),
   `modulename`  varchar(255),
  primary key (`oid`)
);


-- Admin [User]
create table `admin` (
   `oid`  integer  not null,
   `email`  varchar(255),
   `password`  varchar(255),
   `username`  varchar(255),
  primary key (`oid`)
);


-- Expo [ent1]
create table `expo` (
   `expoid`  varchar(255)  not null,
   `voteregistrationopen`  bit,
   `statisticsopentopublic`  bit,
  primary key (`expoid`)
);


-- Stand [ent2]
create table `stand` (
   `standid`  varchar(255)  not null,
   `poster`  varchar(255),
   `standname`  varchar(255),
   `authors`  longtext,
   `token`  varchar(255),
  primary key (`standid`)
);


-- Study [ent3]
create table `study` (
   `studyid`  varchar(255)  not null,
   `studyname`  varchar(255),
  primary key (`studyid`)
);


-- Institute [ent4]
create table `institute` (
   `instituteid`  varchar(255)  not null,
   `institutename`  varchar(255),
  primary key (`instituteid`)
);


-- Vote [ent5]
create table `vote` (
   `voteid`  integer  not null,
   `votevalue`  integer,
  primary key (`voteid`)
);


-- Visitor [ent6]
create table `visitor` (
   `visitorid`  varchar(255)  not null,
   `token`  varchar(255),
  primary key (`visitorid`)
);


-- AktivExpo [ent8]
create table `aktivexpo` (
   `oid`  integer  not null,
  primary key (`oid`)
);


-- Group_DefaultModule [Group2DefaultModule_DefaultModule2Group]
alter table `group`  add column  `module_oid`  integer;
alter table `group`   add index fk_group_module (`module_oid`), add constraint fk_group_module foreign key (`module_oid`) references `module` (`oid`);
create index `idx_group_module` on `group`(`module_oid`);


-- Group_Module [Group2Module_Module2Group]
create table `group_module` (
   `group_oid`  integer not null,
   `module_oid`  integer not null,
  primary key (`group_oid`, `module_oid`)
);
alter table `group_module`   add index fk_group_module_group (`group_oid`), add constraint fk_group_module_group foreign key (`group_oid`) references `group` (`oid`);
alter table `group_module`   add index fk_group_module_module (`module_oid`), add constraint fk_group_module_module foreign key (`module_oid`) references `module` (`oid`);
create index `idx_group_module_group` on `group_module`(`group_oid`);
create index `idx_group_module_module` on `group_module`(`module_oid`);


-- User_DefaultGroup [User2DefaultGroup_DefaultGroup2User]
alter table `admin`  add column  `group_oid`  integer;
alter table `admin`   add index fk_admin_group (`group_oid`), add constraint fk_admin_group foreign key (`group_oid`) references `group` (`oid`);
create index `idx_admin_group` on `admin`(`group_oid`);


-- User_Group [User2Group_Group2User]
create table `user_group` (
   `admin_oid`  integer not null,
   `group_oid`  integer not null,
  primary key (`admin_oid`, `group_oid`)
);
alter table `user_group`   add index fk_user_group_admin (`admin_oid`), add constraint fk_user_group_admin foreign key (`admin_oid`) references `admin` (`oid`);
alter table `user_group`   add index fk_user_group_group (`group_oid`), add constraint fk_user_group_group foreign key (`group_oid`) references `group` (`oid`);
create index `idx_user_group_admin` on `user_group`(`admin_oid`);
create index `idx_user_group_group` on `user_group`(`group_oid`);


-- Stand_Expo [rel1]
alter table `stand`  add column  `expo_expoid`  varchar(255);
alter table `stand`   add index fk_stand_expo (`expo_expoid`), add constraint fk_stand_expo foreign key (`expo_expoid`) references `expo` (`expoid`);
create index `idx_stand_expo` on `stand`(`expo_expoid`);


-- Stand_Study [rel2]
alter table `stand`  add column  `study_studyid`  varchar(255);
alter table `stand`   add index fk_stand_study (`study_studyid`), add constraint fk_stand_study foreign key (`study_studyid`) references `study` (`studyid`);
create index `idx_stand_study` on `stand`(`study_studyid`);


-- Study_Institute [rel3]
alter table `study`  add column  `institute_instituteid`  varchar(255);
alter table `study`   add index fk_study_institute (`institute_instituteid`), add constraint fk_study_institute foreign key (`institute_instituteid`) references `institute` (`instituteid`);
create index `idx_study_institute` on `study`(`institute_instituteid`);


-- Stand_Vote [rel4]
alter table `vote`  add column  `stand_standid`  varchar(255);
alter table `vote`   add index fk_vote_stand (`stand_standid`), add constraint fk_vote_stand foreign key (`stand_standid`) references `stand` (`standid`);
create index `idx_vote_stand` on `vote`(`stand_standid`);


-- Visitor_Vote [rel5]
alter table `vote`  add column  `visitor_visitorid`  varchar(255);
alter table `vote`   add index fk_vote_visitor (`visitor_visitorid`), add constraint fk_vote_visitor foreign key (`visitor_visitorid`) references `visitor` (`visitorid`);
create index `idx_vote_visitor` on `vote`(`visitor_visitorid`);


-- Expo_AktivExpo [rel7]
alter table `aktivexpo`  add column  `expo_expoid`  varchar(255);
alter table `aktivexpo`   add index fk_aktivexpo_expo (`expo_expoid`), add constraint fk_aktivexpo_expo foreign key (`expo_expoid`) references `expo` (`expoid`);
create index `idx_aktivexpo_expo` on `aktivexpo`(`expo_expoid`);


-- Stand.antallStemmer [ent2#att18]
create view `stand_antallstemmer_view` as
select AL1.`standid` as `standid`, count(distinct AL2.`voteid`) as `der_attr`
from  `stand` AL1 
               left outer join `vote` AL2 on AL1.`standid`=AL2.`stand_standid`
group by AL1.`standid`;


