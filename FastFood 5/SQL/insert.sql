/* user */

insert into user(username, password, email, function)
value			('lukaszpawelkiewicz', 'lukasz123', 'lukaszpawelkiewicz123@gmail.com', 'admin');

insert into user(username, password, email, function)
value			('kacperkita', 'kacper123', 'kacperkita@gmail.com', 'admin');

insert into user(username, password, email, function)
value			('jakubjadwidzic', 'jakub123', 'jakubjadwidzic@gmail.com', 'admin');

insert into user(username, password, email, function)
value			('kamilgostomski', 'kamil123', 'kamilgostomski@gmail.com', 'admin');


/* food */
insert into food(name, price)
value			('Rosół', 9.99);

insert into food(name, price)
value			('Mięsny specjał', 29.99);

insert into food(name, price)
value			('Sok pomarańczowy', 4.99);


/* component */
insert into component(name, type)
value				 ('Mięso schabowe', 'mięso');

insert into component(name, type)
value				 ('Ziemniaki', 'warzywo');

insert into component(name, type)
value				 ('Papryka', 'warzywo');

insert into component(name, type)
value				 ('Surówka z jabłek', 'surówka');

/* food_component */
/* mięsny specjał składa sie ze schabowego, ziemniaków i surówki z jabłek */
insert into food_component(id_food, id_component)
value					  (2, 1);

insert into food_component(id_food, id_component)
value					  (2, 2);

insert into food_component(id_food, id_component)
value					  (2, 3);