package org.sber.sberhomework18.repository;

import org.sber.sberhomework18.entity.Recipe;
import org.sber.sberhomework18.entity.RecipeIngredient;

import java.util.List;

public interface RecipeIngredientRepository {
    List<RecipeIngredient> findByRecipe(Recipe recipe);

    RecipeIngredient save(RecipeIngredient recipeIngredient);
}
