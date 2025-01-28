package org.sber.sberhomework18.repository;

import org.sber.sberhomework18.entity.Ingredient;

import java.util.List;

public interface IngredientRepository {
    List<Ingredient> findAll();

    Ingredient save(Ingredient ingredient);

    Ingredient findById(Long id);
}
