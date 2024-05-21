package com.example.model;

//import io.micronaut.serde.annotation.Serdeable;
import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
//import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import jakarta.persistence.Entity;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor(access = AccessLevel.PUBLIC, force = true)
@AllArgsConstructor
public class Ingredient {

    @Id
    private String id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false)
    private Type type;

//    public Ingredient() {}
//
//    public Ingredient(String id, String name, Type type) {
//        this.id = id;
//        this.name = name;
//        this.type = type;
//    }

//    @Override
//    public boolean isNew() {
//        return true;
//    }


//    public String getId() {
//        return id;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public Type getType() {
//        return type;
//    }

    public enum Type {
        WRAP, PROTEIN, VEGGIES, CHEESE, SAUCE
    }


}
