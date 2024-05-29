package com.example.adapter;

import com.example.model.Ingredient;
import io.micronaut.context.annotation.Context;
import jakarta.inject.Inject;
import com.example.model.Ingredient.Type;

import static io.micronaut.data.model.query.factory.Projections.count;

@Context
public class DataLoader {
    private IngredientRepository ingredientRepository;

    @Inject
    public DataLoader(IngredientRepository ingredientRepository) {
        this.ingredientRepository = ingredientRepository;
        if (ingredientRepository.count() == 0) {
            loadData();
        }
        System.out.println("Loaded " + ingredientRepository.count() + " ingredients");
//        System.out.println(ingredientRepository.findAll());
    }

    private void loadData() {
        ingredientRepository.save(new Ingredient("FLTO", "Flour Tortilla", Type.WRAP));
        ingredientRepository.save(new Ingredient("COTO", "Corn Tortilla", Type.WRAP));
        ingredientRepository.save(new Ingredient("GRBF", "Ground Beef", Type.PROTEIN));
        ingredientRepository.save(new Ingredient("CARN", "Carnitas", Type.PROTEIN));
        ingredientRepository.save(new Ingredient("TMTO", "Diced Tomatoes", Type.VEGGIES));
        ingredientRepository.save(new Ingredient("LETC", "Lettuce", Type.VEGGIES));
        ingredientRepository.save(new Ingredient("CHED", "Cheddar", Type.CHEESE));
        ingredientRepository.save(new Ingredient("JACK", "Monterrey Jack", Type.CHEESE));
        ingredientRepository.save(new Ingredient("SLSA", "Salsa", Type.SAUCE));
        ingredientRepository.save(new Ingredient("SRCR", "Sour Cream", Type.SAUCE));
    }
}
