package com.example.model;

import io.micronaut.serde.annotation.Serdeable;
import lombok.Data;
import lombok.Getter;

@Data
@Serdeable
public class Ingredient {

    private final String id;
    private final String name;
    private final Type type;

    public enum Type {
        WRAP, PROTEIN, VEGGIES, CHEESE, SAUCE
    }
}
