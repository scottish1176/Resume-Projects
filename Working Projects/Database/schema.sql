BEGIN TRANSACTION;


DROP TABLE IF EXISTS recipe;
DROP SEQUENCE IF EXISTS seq_recipe_id;
DROP TABLE IF EXISTS ingredient;
DROP SEQUENCE IF EXISTS seq_ingredient_id;
DROP TABLE IF EXISTS measurement_units;
DROP SEQUENCE IF EXISTS seq_measurement_units_id;
DROP TABLE IF EXISTS measurement_qty;
DROP SEQUENCE IF EXISTS seq_measurement_qty_id;
DROP TABLE IF EXISTS recipe_ingredients;
--DROP TABLE IF EXISTS users;


CREATE SEQUENCE seq_recipe_id
INCREMENT BY 1
NO MAXVALUE
START WITH 1000
CACHE 1;

CREATE TABLE recipe (
  recipe_id int DEFAULT nextval('seq_recipe_id') NOT NULL,
  title varchar NOT NULL,
  description varchar,
  directions varchar,
  notes_from_author varchar,
  original_source varchar,
  CONSTRAINT PK_recipe_id PRIMARY KEY (recipe_id)
);

INSERT INTO recipe 
(title, description, directions, notes_from_author, original_source)
VALUES ('Taco Pie', 'Annie favorite', 
'1. Combine all dry crust ingredents in a bowl. 
2.Whisk the egg then combine with dry ingredients.
3. knead the mixture with your hands and form the dough into a ball. Press the dough into pie plate as evenly as possible, Bake at 350 for 10-15 minutes (until slightly browned). Set aside.
4. Brown the ground beef and then add the spice mixture, water, and salsa (if desired). cook for about 5 more minutes
5. Pour into the prepared crust and bake at 350° far about 15 minutes.
Serve with guacamole and pico!',
'Cayenne Pepper & Salsa are optional, and this is the first recipe ever put into this database :)',
'Maxliving.com'); 


CREATE SEQUENCE seq_ingredient_id
INCREMENT BY 1
NO MAXVALUE
START WITH 2000
CACHE 1;

CREATE TABLE ingredient (
  ingredient_id int DEFAULT nextval('seq_ingredient_id') NOT NULL,
  ingredient_name varchar,
  CONSTRAINT PK_ingredient PRIMARY KEY (ingredient_id)
);

INSERT INTO ingredient 
(ingredient_id, ingredient_name)
VALUES (2000, 'Almund Flour'),
(2001, 'Sea Salt'),
(2002, 'Chipotle Chili Powder'), 
(2003, 'Baking Powder'),
(2004, 'Egg'),
(2005, 'Grass-fed Ground Beef'),
(2006, 'Chili Powder'),
(2007, 'Cumin'),
(2008, 'Onion Powder'),
(2009, 'Garlic Powder'),
(2010, 'Oregano'),
(2011, 'Cayenne Pepper'),
(2012, 'Water'),
(2013, 'Salsa');


CREATE SEQUENCE seq_measurement_units_id
INCREMENT BY 1
NO MAXVALUE
START WITH 3000
CACHE 1;

CREATE TABLE measurement_units (
  measurement_units_id int DEFAULT nextval('seq_measurement_units_id') NOT NULL,
  measurement_description varchar,
  CONSTRAINT PK_measurement_units PRIMARY KEY (measurement_units_id)
);


CREATE SEQUENCE seq_measurement_qty_id
INCREMENT BY 1
NO MAXVALUE
START WITH 4000
CACHE 1;

CREATE TABLE measurement_qty (
  measurement_qty_id int DEFAULT nextval('seq_measurement_qty_id') NOT NULL,
  qty_amount varchar,
  CONSTRAINT PK_measurement_qty PRIMARY KEY (measurement_qty_id)
);


CREATE TABLE recipe_ingredients (
  recipe_id int NOT NULL,
  ingredient_id int NOT NULL,
  measurement_units_id int NOT NULL,
  measurement_qty_id int NOT NULL,
  CONSTRAINT FK_recipe FOREIGN KEY (recipe_id) REFERENCES recipe(recipe_id),
  CONSTRAINT FK_ingredient FOREIGN KEY (ingredient_id) REFERENCES ingredient(ingredient_id),
  CONSTRAINT FK_measurement_units FOREIGN KEY (measurement_units_id) REFERENCES measurement_units(measurement_units_id),
  CONSTRAINT FK_measurement_qty FOREIGN KEY (measurement_qty_id) REFERENCES measurement_qty(measurement_qty_id)
);


--CREATE SEQUENCE seq_user_id
--  INCREMENT BY 1
--  NO MAXVALUE
--  NO MINVALUE
--  CACHE 1;


--CREATE TABLE users (
--	user_id int DEFAULT nextval('seq_user_id'::regclass) NOT NULL,
--	username varchar(50) NOT NULL,
-- 	password_hash varchar(200) NOT NULL,
-- 	role varchar(50) NOT NULL,
-- 	CONSTRAINT PK_user PRIMARY KEY (user_id)
-- );

-- INSERT INTO users (username,password_hash,role) VALUES ('user','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_USER');
-- INSERT INTO users (username,password_hash,role) VALUES ('admin','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_ADMIN');


COMMIT TRANSACTION;