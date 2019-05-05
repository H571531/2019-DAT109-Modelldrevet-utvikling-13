-- Category [ent6]
create table `category` (
   `categoryid`  integer  not null,
   `categoryname`  varchar(255),
  primary key (`categoryid`)
);


-- Customer_Shopping Cart [rel1]
alter table `shopping_cart`  add column  `id`  varchar(255);
alter table `shopping_cart`   add index fk_shopping_cart_customer_2 (`id`), add constraint fk_shopping_cart_customer_2 foreign key (`id`) references `customer` (`id`);


-- Customer_CreditCard [rel2]
alter table `creditcard`  add column  `id`  varchar(255);
alter table `creditcard`   add index fk_creditcard_customer_2 (`id`), add constraint fk_creditcard_customer_2 foreign key (`id`) references `customer` (`id`);


-- Product_Category [rel6]
alter table `product`  add column  `category_categoryid`  integer;
alter table `product`   add index fk_product_category (`category_categoryid`), add constraint fk_product_category foreign key (`category_categoryid`) references `category` (`categoryid`);


