package org.sber.sberhomework18.entity;

import lombok.Data;

@Data
public class RecipeIngredient {
    private Recipe recipe;
    private Ingredient ingredient;
    private Double quantity;
    private String unit;
}