package com.example.TacoCloud.repository;

import com.example.TacoCloud.model.Ingredient;

import java.util.Optional;

public interface IngredientRepository {
    Iterable<Ingredient> findAll();
    Optional<Ingredient> findById();
    Ingredient save(Ingredient ingredient);
}
