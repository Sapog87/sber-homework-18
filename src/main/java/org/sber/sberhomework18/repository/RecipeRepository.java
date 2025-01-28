package org.sber.sberhomework18.repository;

import org.sber.sberhomework18.entity.Recipe;

import java.util.List;

public interface RecipeRepository {
    List<Recipe> findAll();

    List<Recipe> findByNameLike(String name);

    Recipe save(Recipe recipe);

    boolean delete(Recipe recipe);

    Recipe findById(Long id);
}