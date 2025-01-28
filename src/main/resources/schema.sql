CREATE TABLE IF NOT EXISTS recipe(
    id   BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    name TEXT NOT NULL
);

CREATE TABLE IF NOT EXISTS ingredient(
    id   BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    name TEXT NOT NULL
);

CREATE TABLE IF NOT EXISTS recipe_ingredient
(
    recipe_id     BIGINT REFERENCES recipe (id) ON DELETE CASCADE,
    ingredient_id BIGINT REFERENCES ingredient (id),
    quantity      DOUBLE PRECISION NOT NULL,
    unit          TEXT NOT NULL,
    PRIMARY KEY (recipe_id, ingredient_id)
);