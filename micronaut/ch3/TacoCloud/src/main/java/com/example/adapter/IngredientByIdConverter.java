package com.example.adapter;

import com.example.model.Ingredient;
import io.micronaut.core.convert.ConversionContext;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import io.micronaut.core.convert.TypeConverter;

import java.util.Optional;

@Singleton
public class IngredientByIdConverter implements TypeConverter<String, Ingredient> {

    @Inject
    private IngredientRepository ingredientRepository;


    @Override
    public Optional<Ingredient> convert(String id, Class<Ingredient> targetType, ConversionContext context) {
        return Optional.ofNullable(ingredientRepository.findById(id).orElse(null));
    }
}


