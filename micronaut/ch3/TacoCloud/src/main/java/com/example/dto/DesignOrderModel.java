package com.example.dto;

import com.example.model.TacoOrder;
import io.micronaut.serde.annotation.Serdeable;
import jakarta.inject.Singleton;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Singleton
@Data
@Serdeable
public class DesignOrderModel {
    private Map<String, Object> model = new HashMap<>();

    public DesignOrderModel() {
        model.put("tacoOrder", new TacoOrder());
    }

    public void put(String key, Object value){
        model.put(key, value);
    }

    public void clearAll(){
        model.clear();
    }

}
