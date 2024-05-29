package com.example;


import com.example.adapter.OrderRepository;
import com.example.model.Ingredient;
import com.example.model.Ingredient.Type;
import com.example.model.Taco;
import com.example.model.TacoOrder;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;

import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

@MicronautTest
public class OrderRepositoryTests {

  @Inject
  OrderRepository orderRepo;
    
  @Test
  public void saveOrderWithTwoTacos() {
    TacoOrder order = new TacoOrder();
    order.setDeliveryName("Test McTest");
    order.setDeliveryStreet("1234 Test Lane");
    order.setDeliveryCity("Testville");
    order.setDeliveryState("CO");
    order.setDeliveryZip("80123");
    order.setCcNumber("4111111111111111");
    order.setCcExpiration("10/23");
    order.setCcCVV("123");
    Taco taco1 = new Taco();
    taco1.setName("Taco One");
    taco1.addIngredient(new Ingredient("FLTO", "Flour Tortilla", Type.WRAP));
    taco1.addIngredient(new Ingredient("GRBF", "Ground Beef", Type.PROTEIN));
    taco1.addIngredient(new Ingredient("CHED", "Shredded Cheddar", Type.CHEESE));
    order.addTaco(taco1);
    Taco taco2 = new Taco();
    taco2.setName("Taco Two");
    taco2.addIngredient(new Ingredient("COTO", "Corn Tortilla", Type.WRAP));
    taco2.addIngredient(new Ingredient("CARN", "Carnitas", Type.PROTEIN));
    taco2.addIngredient(new Ingredient("JACK", "Monterrey Jack", Type.CHEESE));
    order.addTaco(taco2);
    System.out.println("order: " + order.toString());
    
    TacoOrder savedOrder = orderRepo.save(order);
    assertNotNull(savedOrder.getId());


    TacoOrder fetchedOrder = orderRepo.findById(savedOrder.getId()).get();
    System.out.println("fetchedOrder: " + fetchedOrder.toString());
    assertEquals(fetchedOrder.getDeliveryName(), "Test McTest");
    assertEquals(fetchedOrder.getDeliveryStreet(), "1234 Test Lane");
    assertEquals(fetchedOrder.getDeliveryCity(), "Testville");
    assertEquals(fetchedOrder.getDeliveryState(), "CO");
    assertEquals(fetchedOrder.getDeliveryZip(), "80123");
    assertEquals(fetchedOrder.getCcExpiration(), "10/23");
    assertEquals(fetchedOrder.getCcNumber(), "4111111111111111");
    assertEquals(fetchedOrder.getCcCVV(), "123");
    assertEquals(fetchedOrder.getPlacedAt(), savedOrder.getPlacedAt());
    List<Taco> tacos = fetchedOrder.getTacos();
    assertEquals(tacos.size(), 2);
    assertTrue(tacos.contains(taco1));
    assertTrue(tacos.contains(taco2));

  }
  
}
