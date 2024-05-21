//package com.example;
//
//import com.example.model.Ingredient;
//import com.example.dto.IngredientByIdConverter;
//import io.micronaut.core.convert.ConversionContext;
//import org.junit.jupiter.api.Test;
//
//import java.util.Optional;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//public class IngredientByIdConverterTest {
//    @Test
//    void testConvertWithExistingId() {
//        // 准备测试数据
//        IngredientByIdConverter converter = new IngredientByIdConverter();
//        String existingId = "FLTO";
//
//        // 调用 convert 方法
//        Optional<Ingredient> result = converter.convert(existingId, Ingredient.class, ConversionContext.DEFAULT);
//
//        // 验证结果
//        assertTrue(result.isPresent()); // 断言结果中包含值
//        assertEquals("Flour Tortilla", result.get().getName()); // 断言名称为 "Flour Tortilla"
//    }
//
//    @Test
//    void testConvertWithNonExistingId() {
//        // 准备测试数据
//        IngredientByIdConverter converter = new IngredientByIdConverter();
//        String nonExistingId = "UNKNOWN";
//
//        // 调用 convert 方法
//        Optional<Ingredient> result = converter.convert(nonExistingId, Ingredient.class, ConversionContext.DEFAULT);
//
//        // 验证结果
//        assertFalse(result.isPresent()); // 断言结果中不包含值
//    }
//}
