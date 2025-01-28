package org.sber.sberhomework18.repository;

import lombok.RequiredArgsConstructor;
import org.sber.sberhomework18.entity.Ingredient;
import org.sber.sberhomework18.entity.Recipe;
import org.sber.sberhomework18.entity.RecipeIngredient;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class RecipeIngredientRepositoryImpl implements RecipeIngredientRepository {
    private static final String SELECT_FROM_RECIPE_INGREDIENT_WHERE_RECIPE_ID = """
            SELECT \
            i.id AS ingredient_id, i.name AS ingredient_name, \
            ri.quantity AS quantity, ri.unit AS unit \
            FROM recipe AS r \
            LEFT JOIN recipe_ingredient AS ri \
            ON r.id = ri.recipe_id AND r.id=? \
            JOIN ingredient AS i \
            ON i.id = ri.ingredient_id""";
    private static final String INSERT_INTO_RECIPE_INGREDIENT = """
            INSERT INTO recipe_ingredient (recipe_id, ingredient_id, quantity, unit) \
            VALUES (?,?,?,?)""";

    private final JdbcTemplate jdbcTemplate;

    @Override
    public List<RecipeIngredient> findByRecipe(Recipe recipe) {
        return jdbcTemplate.query(
                SELECT_FROM_RECIPE_INGREDIENT_WHERE_RECIPE_ID,
                (rs, rowNum) -> {
                    Long ingredientId = rs.getLong("ingredient_id");
                    String ingredientName = rs.getString("ingredient_name");
                    Double quantity = rs.getDouble("quantity");
                    String unit = rs.getString("unit");

                    Ingredient ingredient = new Ingredient();
                    ingredient.setId(ingredientId);
                    ingredient.setName(ingredientName);

                    RecipeIngredient recipeIngredient = new RecipeIngredient();
                    recipeIngredient.setRecipe(recipe);
                    recipeIngredient.setIngredient(ingredient);
                    recipeIngredient.setQuantity(quantity);
                    recipeIngredient.setUnit(unit);

                    return recipeIngredient;
                },
                recipe.getId()
        );
    }

    @Override
    public RecipeIngredient save(RecipeIngredient recipeIngredient) {
        jdbcTemplate.update(
                INSERT_INTO_RECIPE_INGREDIENT,
                recipeIngredient.getRecipe().getId(),
                recipeIngredient.getIngredient().getId(),
                recipeIngredient.getQuantity(),
                recipeIngredient.getUnit()
        );
        return recipeIngredient;
    }
}