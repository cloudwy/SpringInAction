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
