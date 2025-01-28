package org.sber.sberhomework18.service;

import org.sber.sberhomework18.entity.Ingredient;
import org.sber.sberhomework18.entity.Recipe;
import org.sber.sberhomework18.entity.RecipeIngredient;

import java.util.List;

public interface RecipeService {
    List<Recipe> findAllRecipes();

    List<Recipe> findRecipesByNameLike(String name);

    RecipeIngredient addIngredientToRecipe(Recipe recipe,
                                           Ingredient ingredient,
                                           Double quantity,
                                           String measure);

    List<RecipeIngredient> findRecipeIngredientsByRecipe(Recipe recipe);

    boolean deleteRecipe(Recipe recipe);

    Recipe saveRecipe(Recipe recipe);

    Recipe findRecipeById(Long id);
}
