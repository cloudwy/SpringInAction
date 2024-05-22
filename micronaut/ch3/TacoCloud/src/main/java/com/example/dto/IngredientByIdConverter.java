package com.example.dto;

import com.example.adapter.IngredientRepository;
import com.example.model.Ingredient;
import io.micronaut.core.convert.ConversionContext;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import io.micronaut.core.convert.TypeConverter;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Singleton
public class IngredientByIdConverter implements TypeConverter<String, Ingredient> {
    private IngredientRepository ingredientRepository;
    @Inject
    public IngredientByIdConverter(IngredientRepository ingredientRepository) {
        this.ingredientRepository = ingredientRepository;
    }

    @Override
    public Optional<Ingredient> convert(String id, Class<Ingredient> targetType, ConversionContext context) {
        return Optional.ofNullable(ingredientRepository.findById(id).orElse(null));
    }
}

//@Singleton
//public class IngredientByIdConverter implements TypeConverter<String, Ingredient> {
//    private final Map<String, Ingredient> ingredientMap = new HashMap<>();
//
//    public IngredientByIdConverter() {
//        ingredientMap.put("FLTO", new Ingredient("FLTO", "Flour Tortilla", Ingredient.Type.WRAP));
//        ingredientMap.put("COTO", new Ingredient("COTO", "Corn Tortilla", Ingredient.Type.WRAP));
//        ingredientMap.put("GRBF", new Ingredient("GRBF", "Ground Beef", Ingredient.Type.PROTEIN));
//        ingredientMap.put("TMTO", new Ingredient("TMTO", "Diced Tomatoes", Ingredient.Type.VEGGIES));
//        ingredientMap.put("LETC", new Ingredient("LETC", "Lettuce", Ingredient.Type.VEGGIES));
//        ingredientMap.put("CHED", new Ingredient("CHED", "Cheddar", Ingredient.Type.CHEESE));
//        ingredientMap.put("JACK", new Ingredient("JACK", "Monterry Jack", Ingredient.Type.CHEESE));
//        ingredientMap.put("SLSA", new Ingredient("SLSA", "Salsa", Ingredient.Type.SAUCE));
//        ingredientMap.put("SRCR", new Ingredient("SRCR", "Sour Cream", Ingredient.Type.SAUCE));
//    }
//
//    @Override
//    public Optional<Ingredient> convert(String id, Class<Ingredient> targetType, ConversionContext context) {
//        return Optional.ofNullable(ingredientMap.get(id));
//    }
//}


