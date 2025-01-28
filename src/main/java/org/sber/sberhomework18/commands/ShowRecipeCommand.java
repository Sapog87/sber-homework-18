package org.sber.sberhomework18.commands;

import lombok.RequiredArgsConstructor;
import org.sber.sberhomework18.entity.Recipe;
import org.sber.sberhomework18.entity.RecipeIngredient;
import org.sber.sberhomework18.service.RecipeService;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Scanner;

import static org.sber.sberhomework18.utils.ReadUnit.readLong;

@Component
@RequiredArgsConstructor
public class ShowRecipeCommand implements ConsoleCommand {
    private final RecipeService recipeService;
    private final Scanner scanner;

    @Override
    public String getText() {
        return "Показать рецепт";
    }

    @Override
    public void execute() {
        long recipeId = readLong(scanner, "Введите номер рецепта: ");

        Recipe recipe = recipeService.findRecipeById(recipeId);
        if (recipe == null) {
            System.out.println("Такого рецепта не существует");
            return;
        }

        List<RecipeIngredient> ingredients = recipeService.findRecipeIngredientsByRecipe(recipe);
        System.out.println("----------------");
        if (ingredients.isEmpty()) {
            System.out.println("Пусто");
        } else {
            for (RecipeIngredient recipeIngredient : ingredients) {
                System.out.printf(
                        "%d | %s | %s | %s%n",
                        recipeIngredient.getIngredient().getId(),
                        recipeIngredient.getIngredient().getName(),
                        recipeIngredient.getQuantity(),
                        recipeIngredient.getUnit()
                );
            }
        }
        System.out.println("----------------");
    }
}
