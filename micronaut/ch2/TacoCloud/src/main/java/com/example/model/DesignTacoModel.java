package com.example.model;

import io.micronaut.serde.annotation.Serdeable;
import jakarta.inject.Singleton;
import lombok.Data;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Singleton
@Data
@Serdeable
public class DesignTacoModel {
    private Map<String, Object> model = new HashMap<>();

    public DesignTacoModel() {
        addIntegredientList();
        model.put("designTacoForm", new DesignTacoForm());
        model.put("taco", new Taco());
        model.put("tacoOrder", new TacoOrder());
    }

    private void addIntegredientList(){
        List<Ingredient> ingredients = Arrays.asList(
                new Ingredient("FLTO", "Flour Tortilla", Ingredient.Type.WRAP),
                new Ingredient("COTO", "Corn Tortilla", Ingredient.Type.WRAP),
                new Ingredient("GRBF", "Ground Beef", Ingredient.Type.PROTEIN),
                new Ingredient("CARN", "Carnitas", Ingredient.Type.PROTEIN),
                new Ingredient("TMTO", "Diced Tomatoes", Ingredient.Type.VEGGIES),
                new Ingredient("LETC", "Lettuce", Ingredient.Type.VEGGIES),
                new Ingredient("CHED", "Cheddar", Ingredient.Type.CHEESE),
                new Ingredient("JACK", "Monterrey Jack", Ingredient.Type.CHEESE),
                new Ingredient("SLSA", "Salsa", Ingredient.Type.SAUCE),
                new Ingredient("SRCR", "Sour Cream", Ingredient.Type.SAUCE)
        );
        Ingredient.Type[] types = Ingredient.Type.values();
        for (Ingredient.Type type : types) {
            model.put(type.toString().toLowerCase(),
                    filterByType(ingredients, type));
        }
    }

    public void put(String key, Object value){
        model.put(key, value);
    }

    public void addTacoToOrder(Taco taco){
        model.put("taco", taco);
        TacoOrder tacoOrder = (TacoOrder) model.get("tacoOrder");
        tacoOrder.addTaco(taco);
        model.put("tacoOrder", tacoOrder);
    }

    private Iterable<Ingredient> filterByType(
            List<Ingredient> ingredients, Ingredient.Type type) {
        return ingredients
                .stream()
                .filter(x -> x.getType().equals(type))
                .collect(Collectors.toList());
    }


}
