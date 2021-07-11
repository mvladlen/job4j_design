insert into role (name) values ('admin');
insert into users (name) values ('Petya');
insert into rule (name) values ('add new users');
insert into rule (name) values ('delete users');
insert into category (name) values ('food');
insert into state (name) values ('good');
insert into item (name, user_id ,category_id, state_id)  values ('Milk', 1, 1, 1);
insert into role_rules (name, role_id , rule_id)  values ('Add new users', 1, 1);
insert into role_rules (name, role_id , rule_id)  values ('Add new users', 1, 2);
insert into comments (comment, item_id ) values ('very tasty', 1);
insert into comments (comment, item_id ) values ('tasty', 1);
insert into attach (file, item_id ) values ('milk_front.png', 1);
insert into attach (file, item_id ) values ('milk_back.png', 1);

