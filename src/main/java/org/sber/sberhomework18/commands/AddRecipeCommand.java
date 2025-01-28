package org.sber.sberhomework18.commands;

import lombok.RequiredArgsConstructor;
import org.sber.sberhomework18.entity.Ingredient;
import org.sber.sberhomework18.entity.Recipe;
import org.sber.sberhomework18.service.IngredientService;
import org.sber.sberhomework18.service.RecipeService;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Scanner;

import static org.sber.sberhomework18.utils.ReadUnit.readLong;
import static org.sber.sberhomework18.utils.ReadUnit.readString;

@Component
@RequiredArgsConstructor
public class AddRecipeCommand implements ConsoleCommand {
    private final RecipeService recipeService;
    private final IngredientService ingredientService;
    private final Scanner scanner;

    @Override
    public String getText() {
        return "Добавить рецепт";
    }

    @Override
    public void execute() {
        String recipeName = readString(scanner, "Введите название рецепта: ");

        Recipe recipe = new Recipe();
        recipe.setName(recipeName);
        recipe = recipeService.saveRecipe(recipe);
        System.out.println("Рецепт добавлен");

        List<Ingredient> ingredients = ingredientService.findAllIngredients();
        while (true) {
            System.out.println("""
                    0 | Завершить
                    1 | Добавить ингредиент""");

            long number = readLong(scanner, "Введите номер команды: ");
            if (number == 0) {
                break;
            } else if (number == 1) {
                addIngredient(ingredients, recipe);
            } else {
                System.out.println("Неверная команда");
            }
        }

        System.out.println("Рецепт сохранен");
    }

    private void addIngredient(List<Ingredient> ingredients, Recipe recipe) {
        System.out.println("0 | Новый ингредиент");
        for (Ingredient ingredient : ingredients) {
            System.out.printf("%d | %s%n", ingredient.getId(), ingredient.getName());
        }

        long ingredientId = readLong(scanner, "Введите номер ингредиента: ");
        if (ingredientId == 0) {
            String ingredientName = readString(scanner, "Введите название ингредиента: ");
            Ingredient ingredient = new Ingredient();
            ingredient.setName(ingredientName);
            ingredient = ingredientService.saveIngredient(ingredient);
            ingredients.add(ingredient);

            addRecipeIngredient(recipe, ingredient);
        } else {
            Ingredient ingredient = ingredientService.findById(ingredientId);
            if (ingredient == null) {
                System.out.println("Неверный номер ингредиента");
                return;
            }
            addRecipeIngredient(recipe, ingredient);
        }
        System.out.println("Ингредиент добавлен");
    }

    private void addRecipeIngredient(Recipe recipe, Ingredient ingredient) {
        String unit = readString(scanner, "Введите единицу измерения: ");

        double quantity;
        while (true) {
            System.out.print("Введите количество: ");
            quantity = scanner.nextDouble();
            if (quantity > 0) {
                break;
            }
            System.out.println("Неверное количество");
        }

        recipeService.addIngredientToRecipe(recipe, ingredient, quantity, unit);
    }
}
