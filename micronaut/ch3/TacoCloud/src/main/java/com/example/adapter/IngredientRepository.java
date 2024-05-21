package com.example.adapter;

import com.example.model.Ingredient;

import io.micronaut.data.annotation.Query;
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.jpa.repository.JpaRepository;
import io.micronaut.data.repository.CrudRepository;

//@Repository
//interface IngredientRepository extends JpaRepository<Ingredient, String> {
//    default void createIngredietIfNotExists(Ingredient ingredient){
//        createIngredientIfNotExists(ingredient.getId(), ingredient.getName(), ingredient.getType());
//    }
//
//    @Query(
//            value = "insert into ingredients(id, name, type) values(:id, :name, :type) ON CONFLICT DO NOTHING",
//            nativeQuery = true
//    )
//    void createIngredientIfNotExists(String id, String name, Ingredient.Type type);
//}

@Repository
public interface IngredientRepository
        extends CrudRepository<Ingredient, String> {

}

