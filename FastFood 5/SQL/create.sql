use fastfood;

create table user(
	id			int not null auto_increment,
	username 	varchar(20) not null unique,
	password 	char(32) not null,
    email		varchar(50) not null,
    function	varchar(20),
    
    primary key(id)
);

create table food(
	id			int not null auto_increment,
    name 		varchar(30) not null,
    price		decimal(4,2) not null,
    
	primary key(id)
);

create table component(
	id 			int not null auto_increment,
    name		varchar(20) not null,
    type		varchar(20) not null,	

    primary key(id)
);

create table orderr(
	id			int not null auto_increment,
    id_user		int not null,
    id_food 	int not null,
    date		date not null,
    
    primary key(id),
    constraint fk_user_food foreign key (id_user) references user(id),
    constraint fk_food_user foreign key (id_food) references food(id)
);

CREATE TABLE food_component (
    id_food INT NOT NULL,
    id_component INT NOT NULL,
    CONSTRAINT fk_food_component FOREIGN KEY (id_food)
        REFERENCES food (id),
    CONSTRAINT fk_component_food FOREIGN KEY (id_component)
        REFERENCES component (id)
);

drop table food_component;
drop table orderr;
drop table component;
drop table food;
drop table user;
