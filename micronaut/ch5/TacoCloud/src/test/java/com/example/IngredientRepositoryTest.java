package com.example;

import com.example.adapter.IngredientRepository;
import com.example.model.Ingredient;
import com.example.model.Ingredient.Type;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;


import java.util.List;
import java.util.Optional;

@MicronautTest
public class IngredientRepositoryTest {

    @Inject
    IngredientRepository ingredientRepository;

    @Test
    public void findAll(){
        List<Ingredient> ingredients = ingredientRepository.findAll();
        Assertions.assertNotNull(ingredients);
        Assertions.assertEquals(ingredients.size(), 10);
    }

    @Test
    public void findById() {
        Optional<Ingredient> flto = ingredientRepository.findById("FLTO");
        Assertions.assertTrue(flto.isPresent());
        Assertions.assertEquals(flto.get(), new Ingredient("FLTO", "Flour Tortilla", Type.WRAP));
        Optional<Ingredient> xxxx = ingredientRepository.findById("XXXX");
        Assertions.assertTrue(xxxx.isEmpty());
    }

}
