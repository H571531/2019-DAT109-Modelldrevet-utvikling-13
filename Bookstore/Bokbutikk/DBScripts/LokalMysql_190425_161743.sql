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


-- User [User]
create table `user` (
   `oid`  integer  not null,
   `username`  varchar(255),
   `password`  varchar(255),
   `email`  varchar(255),
  primary key (`oid`)
);


-- Customer [ent1]
create table `customer` (
   `customerid`  varchar(255)  not null,
   `email`  varchar(255),
   `title`  varchar(255),
   `name`  varchar(255),
   `address`  varchar(255),
   `phone`  varchar(255),
  primary key (`customerid`)
);


-- Product [ent4]
create table `product` (
   `productid`  integer  not null,
   `name`  varchar(255),
   `description`  varchar(255),
   `price`  double precision,
  primary key (`productid`)
);


-- CreditCard [ent5]
create table `creditcard` (
   `cardnumber`  integer  not null,
   `expirationdate`  date,
  primary key (`cardnumber`)
);


-- Category [ent6]
create table `category` (
   `categoryid`  integer  not null,
   `categoryname`  varchar(255),
  primary key (`categoryid`)
);


-- Group_DefaultModule [Group2DefaultModule_DefaultModule2Group]
alter table `group`  add column  `module_oid`  integer;
alter table `group`   add index fk_group_module (`module_oid`), add constraint fk_group_module foreign key (`module_oid`) references `module` (`oid`);


-- Group_Module [Group2Module_Module2Group]
create table `group_module` (
   `group_oid`  integer not null,
   `module_oid`  integer not null,
  primary key (`group_oid`, `module_oid`)
);
alter table `group_module`   add index fk_group_module_group (`group_oid`), add constraint fk_group_module_group foreign key (`group_oid`) references `group` (`oid`);
alter table `group_module`   add index fk_group_module_module (`module_oid`), add constraint fk_group_module_module foreign key (`module_oid`) references `module` (`oid`);


-- User_DefaultGroup [User2DefaultGroup_DefaultGroup2User]
alter table `user`  add column  `group_oid`  integer;
alter table `user`   add index fk_user_group (`group_oid`), add constraint fk_user_group foreign key (`group_oid`) references `group` (`oid`);


-- User_Group [User2Group_Group2User]
create table `user_group` (
   `user_oid`  integer not null,
   `group_oid`  integer not null,
  primary key (`user_oid`, `group_oid`)
);
alter table `user_group`   add index fk_user_group_user (`user_oid`), add constraint fk_user_group_user foreign key (`user_oid`) references `user` (`oid`);
alter table `user_group`   add index fk_user_group_group (`group_oid`), add constraint fk_user_group_group foreign key (`group_oid`) references `group` (`oid`);


-- Customer_CreditCard [rel2]
alter table `creditcard`  add column  `customer_customerid`  varchar(255);
alter table `creditcard`   add index fk_creditcard_customer (`customer_customerid`), add constraint fk_creditcard_customer foreign key (`customer_customerid`) references `customer` (`customerid`);


-- Product_Category [rel6]
alter table `product`  add column  `category_categoryid`  integer;
alter table `product`   add index fk_product_category (`category_categoryid`), add constraint fk_product_category foreign key (`category_categoryid`) references `category` (`categoryid`);


