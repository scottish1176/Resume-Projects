BEGIN TRANSACTION;

DROP TABLE IF EXISTS users;
DROP SEQUENCE IF EXISTS seq_recipe_id;
DROP TABLE IF EXISTS recipe;


CREATE SEQUENCE seq_recipe_id
INCREMENT BY 1
NO MAXVALUE
START WITH 1000
CACHE 1;

CREATE TABLE recipe (
  recipe_id int DEFAULT nextval('seq_recipe_id') NOT NULL,
  title varchar,
  description varchar,
  directions varchar,
  notes_from_author varchar,
  original_source varchar
)

INSERT INTO recipe 
(title, description, directions, notes_from_author, original_source)
VALUES ('Taco Pie', "Annie's favorite recipie", 
"1. Combine all dry crust ingredents in a bowl. 
2.Whisk the egg then combine with dry ingredients.
3. knead the mixture with your hands and form the dough into a ball. Press the dough into pie plate as evenly as possible, Bake at 350 for 10-15 minutes (until slightly browned). Set aside.
4. Brown the ground beef and then add the spice mixture, water, and salsa (if desired). cook for about 5 more minutes
5. Pour into the prepared crust and bake at 350° far about 15 minutes.
Serve with guacamole and pico!",
'This is the first recipe in this database :)',
'Maxliving.com'); 


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
