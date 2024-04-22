package com.example.web;

import com.example.model.*;
import com.example.model.Ingredient.Type;
import io.micronaut.core.convert.ConversionContext;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.MediaType;
import io.micronaut.http.MutableHttpResponse;
import io.micronaut.http.annotation.*;
import io.micronaut.views.ModelAndView;
import io.micronaut.views.View;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;
import java.net.URI;
import java.util.*;
import java.util.stream.Collectors;

@Controller("/")
public class DesignTacoController {
    private static final Logger log = LoggerFactory.getLogger(DesignTacoController.class);
    private DesignModel designModel = new DesignModel();

    private IngredientByIdConverter ingredientByIdConverter = new IngredientByIdConverter();

    @View("design.html")
    @Get("/design")
    public ModelAndView index() {
        return new ModelAndView("design", designModel.getModel());
    }
    @Post("/design") // if no "design", controller will redirect to "/"
    @Consumes({MediaType.APPLICATION_FORM_URLENCODED, MediaType.APPLICATION_JSON})
    public MutableHttpResponse<?> processTaco(
            @Body DesignForm designForm
            ) {
        List<Ingredient> convertIngredients = new ArrayList<>();
        List<String> ingredientsId = designForm.getIngredientsId();
        if (ingredientsId.size() != 0) {
            for(String item : ingredientsId){
                Optional<Ingredient> rel = ingredientByIdConverter.convert(item, Ingredient.class, ConversionContext.DEFAULT);
                if (rel.isPresent()) {
                    convertIngredients.add(rel.get());
                }
            }
        }
        Taco taco = new Taco(designForm.getName(), convertIngredients);
        designModel.addTacoToOrder(taco);
        log.info("Processing taco: ()", taco);
        // Redirect in Micronaut
//        return HttpResponse.redirect(URI.create("/orders/current"));

        return HttpResponse.ok(designModel.getModel().get("tacoOrder"));
    }

}


//@Controller("/")
//public class DesignTacoController {
//    private static final Logger log = LoggerFactory.getLogger(DesignTacoController.class);
//
//    @View("design.html")
//    @Get("/design")
//    public ModelAndView index() {
//        Map<String, Object> model = new HashMap<>();
//        model.put("tacoOrder", new TacoOrder());
//        model.put("taco", new Taco());
//        List<Ingredient> ingredients = Arrays.asList(
//                new Ingredient("FLTO", "Flour Tortilla", Type.WRAP),
//                new Ingredient("COTO", "Corn Tortilla", Type.WRAP),
//                new Ingredient("GRBF", "Ground Beef", Type.PROTEIN),
//                new Ingredient("CARN", "Carnitas", Type.PROTEIN),
//                new Ingredient("TMTO", "Diced Tomatoes", Type.VEGGIES),
//                new Ingredient("LETC", "Lettuce", Type.VEGGIES),
//                new Ingredient("CHED", "Cheddar", Type.CHEESE),
//                new Ingredient("JACK", "Monterrey Jack", Type.CHEESE),
//                new Ingredient("SLSA", "Salsa", Type.SAUCE),
//                new Ingredient("SRCR", "Sour Cream", Type.SAUCE)
//        );
//        Type[] types = Ingredient.Type.values();
//        for (Type type : types) {
//            model.put(type.toString().toLowerCase(),
//                    filterByType(ingredients, type));
//        }
//        System.out.println("model: " + model);
//        return new ModelAndView("design", model);
//    }
//
//    @Post("/design") // if no "design", controller will redirect to "/"
//    @Consumes({MediaType.APPLICATION_FORM_URLENCODED, MediaType.APPLICATION_JSON})
//    public MutableHttpResponse<?> processTaco(
////            @Valid @Body Taco taco,
////            TacoOrder tacoOrder
//    ) {
////        tacoOrder.addTaco(taco);
////        log.info("Processing taco: ()", taco);
//        // Redirect in Micronaut
//        return HttpResponse.redirect(URI.create("/orders/current"));
//    }
//
//
//    private Iterable<Ingredient> filterByType(
//            List<Ingredient> ingredients, Type type) {
//        return ingredients
//                .stream()
//                .filter(x -> x.getType().equals(type))
//                .collect(Collectors.toList());
//    }
//
//}

//@Controller("/")
//public class DesignTacoController {
//    private static final Logger log = LoggerFactory.getLogger(DesignTacoController.class);
//
//    private Map<String, Object> model = new HashMap<>();
//    private TacoOrder tacoOrder = new TacoOrder();
//    private Taco taco = new Taco();
//    private DesignForm designForm = new DesignForm();
//    private IngredientByIdConverter ingredientByIdConverter = new IngredientByIdConverter();
//
//    public DesignTacoController() {
//        model.put("tacoOrder", tacoOrder);
//        model.put("taco", taco);
//        model.put("tacoFrontEnd", designForm);
//        List<Ingredient> ingredients = Arrays.asList(
//                new Ingredient("FLTO", "Flour Tortilla", Type.WRAP),
//                new Ingredient("COTO", "Corn Tortilla", Type.WRAP),
//                new Ingredient("GRBF", "Ground Beef", Type.PROTEIN),
//                new Ingredient("CARN", "Carnitas", Type.PROTEIN),
//                new Ingredient("TMTO", "Diced Tomatoes", Type.VEGGIES),
//                new Ingredient("LETC", "Lettuce", Type.VEGGIES),
//                new Ingredient("CHED", "Cheddar", Type.CHEESE),
//                new Ingredient("JACK", "Monterrey Jack", Type.CHEESE),
//                new Ingredient("SLSA", "Salsa", Type.SAUCE),
//                new Ingredient("SRCR", "Sour Cream", Type.SAUCE)
//        );
//        Type[] types = Ingredient.Type.values();
//        for (Type type : types) {
//            model.put(type.toString().toLowerCase(),
//                    filterByType(ingredients, type));
//        }
//        model.put("ingredientList", ingredients);
//    }
//
//    @View("design.html")
//    @Get("/design")
//    public ModelAndView index() {
//        return new ModelAndView("design", model);
//    }
//
//    @Post("/design") // if no "design", controller will redirect to "/"
//    @Consumes({MediaType.APPLICATION_FORM_URLENCODED, MediaType.APPLICATION_JSON})
//    public MutableHttpResponse<?> processTaco(
////    public void processTaco(
//            @Body DesignForm designForm
//            ) {
//        List<Ingredient> convertIngredients = new ArrayList<>();
//        for (String item : designForm.getIngredients()){
//            convertIngredients.add(ingredientByIdConverter.convert(item));
//        }
//        taco.setIngredients(convertIngredients);
//        taco.setName(designForm.getName());
//        tacoOrder.addTaco(taco);
//        System.out.println("Received order form: " + designForm);
////        tacoOrder.addTaco(taco);
////        model.put("tacoOrder", tacoOrder);
////        model.put("taco", taco);
////        log.info("Processing taco: ()", taco);
////      // Redirect in Micronaut
////        return HttpResponse.redirect(URI.create("/orders/current"));
//        return HttpResponse.ok("Design received successfully!");
//    }
//
//
//    private Iterable<Ingredient> filterByType(
//            List<Ingredient> ingredients, Type type) {
//        return ingredients
//                .stream()
//                .filter(x -> x.getType().equals(type))
//                .collect(Collectors.toList());
//    }
//
//
//}
