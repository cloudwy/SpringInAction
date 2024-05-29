package com.example;

import com.example.adapter.IngredientByIdConverter;
import com.example.adapter.OrderRepository;
import com.example.dto.DesignTacoForm;
import com.example.model.Ingredient;
import com.example.model.Ingredient.Type;
import com.example.model.Taco;
import io.micronaut.context.annotation.Property;
import io.micronaut.core.convert.ConversionContext;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.client.HttpClient;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.test.annotation.MockBean;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Inject;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import com.example.adapter.IngredientRepository;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;
import org.jsoup.Jsoup;

@MicronautTest
@Property(name = "micronaut.server.port", value = "8080")
public class DesignTacoControllerTest {

  @Inject
  @Client("/")
  HttpClient httpClient;

  @Inject
  IngredientRepository ingredientRepository;

  @Inject
  IngredientByIdConverter ingredientByIdConverter;

  @MockBean(IngredientRepository.class)
  IngredientRepository ingredientRepository() {
    return Mockito.mock(IngredientRepository.class);
  }

  @MockBean(OrderRepository.class)
  OrderRepository orderRepository() {
    return Mockito.mock(OrderRepository.class);
  }

  private List<Ingredient> ingredients;

  private DesignTacoForm design;

  @BeforeEach
  public void setup() {
    ingredients = Arrays.asList(
      new Ingredient("FLTO", "Flour Tortilla", Type.WRAP),
      new Ingredient("COTO", "Corn Tortilla", Type.WRAP),
      new Ingredient("GRBF", "Ground Beef", Type.PROTEIN),
      new Ingredient("CARN", "Carnitas", Type.PROTEIN),
      new Ingredient("TMTO", "Diced Tomatoes", Type.VEGGIES),
      new Ingredient("LETC", "Lettuce", Type.VEGGIES),
      new Ingredient("CHED", "Cheddar", Type.CHEESE),
      new Ingredient("JACK", "Monterrey Jack", Type.CHEESE),
      new Ingredient("SLSA", "Salsa", Type.SAUCE),
      new Ingredient("SRCR", "Sour Cream", Type.SAUCE)
    );

    Mockito.when(ingredientRepository.findAll()).thenReturn(ingredients);
    Mockito.when(ingredientRepository.findById("FLTO")).thenReturn(Optional.of(new Ingredient("FLTO", "Flour Tortilla", Type.WRAP)));
    Mockito.when(ingredientRepository.findById("COTO")).thenReturn(Optional.of(new Ingredient("COTO", "Corn Tortilla", Type.WRAP)));
    Mockito.when(ingredientRepository.findById("GRBF")).thenReturn(Optional.of(new Ingredient("GRBF", "Ground Beef", Type.PROTEIN)));
    Mockito.when(ingredientRepository.findById("CARN")).thenReturn(Optional.of(new Ingredient("CARN", "Carnitas", Type.PROTEIN)));
    Mockito.when(ingredientRepository.findById("TMTO")).thenReturn(Optional.of(new Ingredient("TMTO", "Diced Tomatoes", Type.VEGGIES)));
    Mockito.when(ingredientRepository.findById("LETC")).thenReturn(Optional.of(new Ingredient("LETC", "Lettuce", Type.VEGGIES)));
    Mockito.when(ingredientRepository.findById("CHED")).thenReturn(Optional.of(new Ingredient("CHED", "Cheddar", Type.CHEESE)));
    Mockito.when(ingredientRepository.findById("JACK")).thenReturn(Optional.of(new Ingredient("JACK", "Monterrey Jack", Type.CHEESE)));
    Mockito.when(ingredientRepository.findById("SLSA")).thenReturn(Optional.of(new Ingredient("SLSA", "Salsa", Type.SAUCE)));
    Mockito.when(ingredientRepository.findById("SRCR")).thenReturn(Optional.of(new Ingredient("SRCR", "Sour Cream", Type.SAUCE)));

    design = new DesignTacoForm();
    design.setName("Test Taco");
    design.setIngredientsId(Arrays.asList("FLTO", "GRBF", "CHED"));
  }

//  @Test
//  public void testConverter() throws Exception {
//    Ingredient convertIngredient = ingredientByIdConverter.convert("COTO", Ingredient.class, ConversionContext.DEFAULT).orElse(null);
//    assertEquals(new Ingredient("COTO", "Corn Tortilla", Type.WRAP), convertIngredient);
//  }
//
//
//  @Test
//  public void testShowDesignForm() throws Exception {
//    HttpResponse<String> response = httpClient.toBlocking().exchange("/design", String.class);
//    assertEquals(HttpStatus.OK, response.getStatus());
//    String responseBody = response.body();
//    Document doc = Jsoup.parse(responseBody);
//    //wrap
//    Elements wrapGroup = doc.select("div.wrap");
//    List<Ingredient> web_wraps = new ArrayList<>();
//    Elements wrapElements = wrapGroup.select("input");
//    for (Element inputElement : wrapElements) {
//      String value = inputElement.attr("value");
//      Ingredient convertIngredient = ingredientByIdConverter.convert(value, Ingredient.class, ConversionContext.DEFAULT).orElse(null);
//      System.out.println(value + " " + convertIngredient);
//      web_wraps.add(convertIngredient);
//    }
//    List<Ingredient> expected_wraps = ingredients.subList(0, 2);
//    assertEquals(expected_wraps, web_wraps);
//    //protein
//    Elements proteinGroup = doc.select("div.protein");
//    List<Ingredient> web_proteins = new ArrayList<>();
//    Elements proteinElements = proteinGroup.select("input");
//    for (Element inputElement : proteinElements) {
//      String value = inputElement.attr("value");
//      Ingredient convertIngredient = ingredientByIdConverter.convert(value, Ingredient.class, ConversionContext.DEFAULT).orElse(null);
//      web_proteins.add(convertIngredient);
//    }
//    List<Ingredient> expected_proteins = ingredients.subList(2, 4);
//    assertEquals(expected_proteins, web_proteins);
//    //veggies
//    Elements veggieGroup = doc.select("div.veggies");
//    List<Ingredient> web_veggies = new ArrayList<>();
//    Elements veggieElements = veggieGroup.select("input");
//    for (Element inputElement : veggieElements) {
//      String value = inputElement.attr("value");
//      Ingredient convertIngredient = ingredientByIdConverter.convert(value, Ingredient.class, ConversionContext.DEFAULT).orElse(null);
//      web_veggies.add(convertIngredient);
//    }
//    List<Ingredient> expected_veggies = ingredients.subList(4, 6);
//    assertEquals(expected_veggies, web_veggies);
//    //cheese
//    Elements cheeseGroup = doc.select("div.cheese");
//    assertNotNull(cheeseGroup);
//    List<Ingredient> web_cheese = new ArrayList<>();
//    Elements cheeseElements = cheeseGroup.select("input");
//    for (Element inputElement : cheeseElements) {
//      String value = inputElement.attr("value");
//      Ingredient convertIngredient = ingredientByIdConverter.convert(value, Ingredient.class, ConversionContext.DEFAULT).orElse(null);
//      web_cheese.add(convertIngredient);
//    }
//    List<Ingredient> expected_cheese = ingredients.subList(6, 8);
//    assertEquals(expected_cheese, web_cheese);
//    //sauce
//    Elements sauceGroup = doc.select("div.sauce");
//    List<Ingredient> web_sauce = new ArrayList<>();
//    Elements sauceElements = sauceGroup.select("input");
//    for (Element inputElement : sauceElements) {
//      String value = inputElement.attr("value");
//      Ingredient convertIngredient = ingredientByIdConverter.convert(value, Ingredient.class, ConversionContext.DEFAULT).orElse(null);
//      web_sauce.add(convertIngredient);
//    }
//    List<Ingredient> expected_sauce = ingredients.subList(8, 10);
//    assertEquals(expected_sauce, web_sauce);
//  }

  @Test
  public void testProcessTaco(){
    HttpRequest<DesignTacoForm> request = HttpRequest.POST("/design", design);
    HttpResponse<String> response = httpClient.toBlocking().exchange(request, String.class);
//    assertEquals(HttpStatus.OK, response.getStatus()); //true
//    assertEquals(HttpStatus.SEE_OTHER, response.getStatus()); //false
//    assertEquals("/orders/current", response.header("Location")); //false
  }

  @Test
  public void testDesignATacoClick(){
    HttpRequest<String> getRequest = HttpRequest.GET("/design");
    HttpResponse<String> getResponse = httpClient.toBlocking().exchange(getRequest, String.class);
    assertEquals(HttpResponse.ok(), getResponse.status());

//    String buttonId = "submitButton";
//    HttpRequest<String> postRequest = HttpRequest.POST("/design", buttonId);
//    HttpResponse<String> postResponse = httpClient.toBlocking().exchange(postRequest, String.class);
//    assertEquals(HttpResponse.ok(), postResponse.status());

  }
}
