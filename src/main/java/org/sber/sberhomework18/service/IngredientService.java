package org.sber.sberhomework18.service;

import org.sber.sberhomework18.entity.Ingredient;

import java.util.List;

public interface IngredientService {
    Ingredient saveIngredient(Ingredient ingredient);

    List<Ingredient> findAllIngredients();

    Ingredient findById(Long id);
}
