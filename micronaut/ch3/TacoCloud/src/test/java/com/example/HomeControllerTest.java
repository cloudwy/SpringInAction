package com.example;

import com.example.adapter.IngredientRepository;
import com.example.adapter.OrderRepository;
import io.micronaut.context.annotation.Property;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.client.HttpClient;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.test.annotation.MockBean;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Collections;

@MicronautTest
@Property(name = "micronaut.server.port", value = "8080")
public class HomeControllerTest {
    @Inject
    @Client("/")
    HttpClient client;

    @Inject
    IngredientRepository ingredientRepository;

    @Inject
    OrderRepository orderRepository;

    @MockBean(IngredientRepository.class)
    IngredientRepository ingredientRepository() {
        return Mockito.mock(IngredientRepository.class);
    }

    @MockBean(OrderRepository.class)
    OrderRepository orderRepository() {
        return Mockito.mock(OrderRepository.class);
    }

    @Test
    public void testHomePage() {
        HttpResponse<String> response = client.toBlocking().exchange("/", String.class);
        Assertions.assertEquals(200, response.code());
        Assertions.assertTrue(response.body().contains("Welcome to..."));
    }
}
