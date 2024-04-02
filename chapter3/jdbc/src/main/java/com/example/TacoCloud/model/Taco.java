package com.example.TacoCloud.model;

import com.example.TacoCloud.data.IngredientRef;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Data
@Table
public class Taco {
    // add id and createdAt
    @Id
    private Long id;
    private Date createdAt = new Date();

    @NotNull
    @Size(min=5, message="Name must be at least 5 characters long")
    private String name;

    @NotNull
    @Size(min=1, message="You must choose at least 1 ingredient")
    private List<IngredientRef> ingredients = new ArrayList<>(); //important!
}
