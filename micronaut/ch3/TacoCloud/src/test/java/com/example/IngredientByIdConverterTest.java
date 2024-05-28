package com.example;

import com.example.adapter.IngredientRepository;
import com.example.model.Ingredient;
import com.example.adapter.IngredientByIdConverter;
import io.micronaut.core.convert.ConversionContext;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@MicronautTest
public class IngredientByIdConverterTest {

    @Inject
    IngredientRepository ingredientRepository;

    @Inject
    IngredientByIdConverter ingredientByIdConverter;

    @BeforeEach
    @Test
    void checkIngredientRepository() {
        assertEquals(ingredientRepository.findAll().size(), 10);
    }

    @Test
    void testConvertWithExistingIdFLTO() {
        String existingId = "FLTO";
        Optional<Ingredient> result = ingredientByIdConverter.convert(existingId, Ingredient.class, ConversionContext.DEFAULT);
        assertTrue(result.isPresent());
        assertEquals("Flour Tortilla", result.get().getName());
    }

    @Test
    void testConvertWithExistingIdCOTO() {
        String existingId = "COTO";
        Optional<Ingredient> result = ingredientByIdConverter.convert(existingId, Ingredient.class, ConversionContext.DEFAULT);
        assertTrue(result.isPresent());
        assertEquals("Corn Tortilla", result.get().getName());
    }

    @Test
    void testConvertWithNonExistingId() {
        String nonExistingId = "UNKNOWN";
        Optional<Ingredient> result = ingredientByIdConverter.convert(nonExistingId, Ingredient.class, ConversionContext.DEFAULT);
        assertFalse(result.isPresent());
    }
}
