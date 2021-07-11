create table role (
    id serial primary key,
    name text
);

create table users (
    id serial primary key,
    name text,
	role_id int references role(id)
);


create table rule (
    id serial primary key,
    name text
);

create table category (
    id serial primary key,
    name text
);

create table state (
    id serial primary key,
    name text
);


create table item (
    id serial primary key,
    name text,
	user_id int references users(id),
	category_id int references category(id),
	state_id int references state(id)
);

create table role_rules (
    id serial primary key,
    name text,
	role_id int references role(id),
	rule_id int references rule(id)
);

create table comments (
    id serial primary key,
    comment text,
	item_id int references item(id)
);

create table attach (
    id serial primary key,
    file text,
	item_id int references item(id)
);