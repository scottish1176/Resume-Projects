SELECT title, ingredient_name, measurement_description, qty_amount FROM recipe
JOIN recipe_ingredients ON recipe.recipe_id = recipe_ingredients.recipe_id
JOIN ingredient ON recipe_ingredients.ingredient_id = ingredient.ingredient_id
JOIN measurement_units ON recipe_ingredients.measurement_units_id = measurement_units.measurement_units_id
JOIN measurement_qty ON recipe_ingredients.measurement_qty_id = measurement_qty.measurement_qty_id
