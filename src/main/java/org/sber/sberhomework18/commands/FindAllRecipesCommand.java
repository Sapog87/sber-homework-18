package org.sber.sberhomework18.commands;

import lombok.RequiredArgsConstructor;
import org.sber.sberhomework18.entity.Recipe;
import org.sber.sberhomework18.service.RecipeService;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class FindAllRecipesCommand implements ConsoleCommand {
    private final RecipeService recipeService;

    @Override
    public String getText() {
        return "Показать список всех рецептов";
    }

    @Override
    public void execute() {
        System.out.println("----------------");
        List<Recipe> recipes = recipeService.findAllRecipes();
        if (recipes.isEmpty()) {
            System.out.println("Пусто");
        } else {
            recipes.forEach(recipe ->
                    System.out.printf(
                            "%d | %s%n",
                            recipe.getId(),
                            recipe.getName()
                    )
            );
        }
        System.out.println("----------------");
    }
}
