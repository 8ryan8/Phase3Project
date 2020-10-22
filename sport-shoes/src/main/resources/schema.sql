use sport_shoes;

insert into admin (id, admin_id, admin_pwd) 
values(1, 'admin1', 'password1');
insert into admin (id, admin_id, admin_pwd) 
values(2, 'admin2', 'password2');
insert into admin (id, admin_id, admin_pwd) 
values(3, 'admin3', 'password3');
	
insert into category (id, name) 
values(1, 'Nike');
insert into category (id, name) 
values(2, 'Adidas');
insert into category (id, name) 
values(3, 'New_Balance');

insert into product (id, name, price, date_added, category_id) 
values(1, 'Flex', 49.97, sysdate(), 1);
insert into product (id, name, price, date_added, category_id) 
values(2, 'Todos', 48.75, sysdate(), 1);
insert into product (id, name, price, date_added, category_id) 
values(3, 'Runfalcon', 50.00, sysdate(), 2);
insert into product (id, name, price, date_added, category_id) 
values(4, 'Superstar', 55.00, sysdate(), 2);
insert into product (id, name, price, date_added, category_id) 
values(5, 'v5', 174.95, sysdate(), 3);
insert into product (id, name, price, date_added, category_id) 
values(6, 'arishi', 52.50, sysdate(), 3);

insert into users (id, fname, lname, address, age, date_added, emailid, pwd)
values(1, 'Pablo', 'Fernandez', 'Mexico', 12, sysdate(), 'pablo@gmail.com','password1');
insert into users (id, fname, lname, address, age, date_added, emailid, pwd)
values(2, 'Fernando', 'Sanchez', 'Venezuela', 50, sysdate(), 'fernando@yahoo.com','password2');
insert into users (id, fname, lname, address, age, date_added, emailid, pwd)
values(3, 'Camilo', 'Villagomez', 'Colombia', 23, sysdate(), 'camilo@aol.com','password3');

insert into purchases (id, user_id, date, gross_total)
values(1, 1, sysdate(), 312.47);
insert into purchases (id, user_id, date, gross_total)
values(2, 3, sysdate(), 97.50);
insert into purchases (id, user_id, date, gross_total)
values(3, 1, sysdate(), 50.00);

insert into purchase_items (id, purchase_id, product_id ,user_purchase_item_id, rate, qty, price)
values(1, 1, 1, 1, 49.97, 1, 49.97);
insert into purchase_items (id, purchase_id, product_id ,user_purchase_item_id, rate, qty, price)
values(2, 1, 2, 1, 48.75, 2, 97.50);
insert into purchase_items (id, purchase_id, product_id ,user_purchase_item_id, rate, qty, price)
values(3, 1, 4, 1, 55.00, 3, 165.00);
insert into purchase_items (id, purchase_id, product_id ,user_purchase_item_id, rate, qty, price)
values(4, 2, 2, 3, 48.75, 2, 97.50);
insert into purchase_items (id, purchase_id, product_id ,user_purchase_item_id, rate, qty, price)
values(5, 3, 3, 1, 50.00, 1, 50.00);
