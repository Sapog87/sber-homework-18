package org.sber.sberhomework18.service;

import lombok.RequiredArgsConstructor;
import org.sber.sberhomework18.entity.Ingredient;
import org.sber.sberhomework18.entity.Recipe;
import org.sber.sberhomework18.entity.RecipeIngredient;
import org.sber.sberhomework18.repository.RecipeIngredientRepository;
import org.sber.sberhomework18.repository.RecipeRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RecipeServiceImpl implements RecipeService {
    private final RecipeRepository recipeRepository;
    private final RecipeIngredientRepository recipeIngredientRepository;

    @Override
    public List<Recipe> findAllRecipes() {
        return recipeRepository.findAll();
    }

    @Override
    public List<Recipe> findRecipesByNameLike(String name) {
        return recipeRepository.findByNameLike(name);
    }

    @Override
    public RecipeIngredient addIngredientToRecipe(Recipe recipe,
                                                  Ingredient ingredient,
                                                  Double quantity,
                                                  String unit) {
        RecipeIngredient ri = new RecipeIngredient();
        ri.setRecipe(recipe);
        ri.setIngredient(ingredient);
        ri.setQuantity(quantity);
        ri.setUnit(unit);
        return recipeIngredientRepository.save(ri);
    }

    @Override
    public List<RecipeIngredient> findRecipeIngredientsByRecipe(Recipe recipe) {
        return recipeIngredientRepository.findByRecipe(recipe);
    }

    @Override
    public boolean deleteRecipe(Recipe recipe) {
        return recipeRepository.delete(recipe);
    }

    @Override
    public Recipe saveRecipe(Recipe recipe) {
        return recipeRepository.save(recipe);
    }

    @Override
    public Recipe findRecipeById(Long id) {
        return recipeRepository.findById(id);
    }
}
