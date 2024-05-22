package com.example.dto;

import com.example.adapter.IngredientRepository;
//import com.example.dto.DesignTacoForm;
import com.example.model.Ingredient;
import com.example.model.Taco;
import com.example.model.TacoOrder;
import io.micronaut.serde.annotation.Serdeable;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import lombok.Data;

import java.util.*;
import java.util.stream.Collectors;

@Singleton
@Data
@Serdeable
public class DesignTacoModel {

    private Map<String, Object> model = new HashMap<>();
    private IngredientRepository ingredientRepo;

    @Inject
    public DesignTacoModel(IngredientRepository ingredientRepo) {
        this.ingredientRepo = ingredientRepo;
        addIntegredientList();
        model.put("designTacoForm", new DesignTacoForm());
        model.put("taco", new Taco());
        model.put("tacoOrder", new TacoOrder());
    }

    // why this method didn't work?
//    @Inject
//    private IngredientRepository ingredientRepo;
//    public DesignTacoModel() {
//        addIntegredientList();
//        model.put("designTacoForm", new DesignTacoForm());
//        model.put("taco", new Taco());
//        model.put("tacoOrder", new TacoOrder());
//    }

    private void addIntegredientList(){
        List<Ingredient> ingredients = new ArrayList<>();
        ingredientRepo.findAll().forEach(i -> ingredients.add(i));
        System.out.println("ingredients: " + ingredients);
        Ingredient.Type[] types = Ingredient.Type.values();
        for (Ingredient.Type type : types) {
            model.put(type.toString().toLowerCase(),
                    filterByType(ingredients, type));
        }
    }


    private Iterable<Ingredient> filterByType(
            List<Ingredient> ingredients, Ingredient.Type type) {
        return ingredients
                .stream()
                .filter(x -> x.getType().equals(type))
                .collect(Collectors.toList());
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


    public void clearTacoFromOrder(){
        model.put("tacoOrder", new TacoOrder());
    }


    public void clearAll(){
        model.clear();
    }

}
