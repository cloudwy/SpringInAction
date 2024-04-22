package com.example.model;

import io.micronaut.serde.annotation.Serdeable;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.List;

@Data
@Serdeable
public class DesignForm {
    @NotNull
    private String name;

    @NotNull
    private List<String> ingredientsId;
}
