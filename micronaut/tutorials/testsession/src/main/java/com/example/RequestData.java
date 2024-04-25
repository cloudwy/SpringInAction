package com.example;

import io.micronaut.serde.annotation.Serdeable;
import lombok.Data;

@Data
@Serdeable
public class RequestData {
    private String value;

    // getters and setters
    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
