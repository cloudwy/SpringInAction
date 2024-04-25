package com.example.model;

import io.micronaut.serde.annotation.Serdeable;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Data
@Serdeable
public class DesignTacoForm {
    @NotNull
    private String name;

    @NotNull
    private List<String> ingredientsId;
}
