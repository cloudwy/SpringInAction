package com.example.model;

import java.util.Date;
import java.util.List;

//import io.micronaut.serde.annotation.Serdeable;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Taco {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private Date createdAt = new Date();

    @NotNull
    @Size(min=5, message="Name must be at least 5 characters long")
    private String name;

    @Size(min=1, message="You must choose at least 1 ingredient")
    @ManyToMany()
    private List<Ingredient> ingredients;

    public void addIngredient(Ingredient ingredient){
        this.ingredients.add(ingredient);
    }

}
