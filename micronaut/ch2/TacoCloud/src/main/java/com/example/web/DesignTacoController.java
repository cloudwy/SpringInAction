package com.example.web;

import com.example.errorhandling.MessageSource;
import com.example.model.*;
import io.micronaut.core.convert.ConversionContext;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.MediaType;
import io.micronaut.http.MutableHttpResponse;
import io.micronaut.http.annotation.*;
import io.micronaut.http.annotation.Error;
import io.micronaut.views.ModelAndView;
import io.micronaut.views.View;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URI;
import java.util.*;

import io.micronaut.session.Session;


@Controller("/")
public class DesignTacoController {
    private static final Logger log = LoggerFactory.getLogger(DesignTacoController.class);
    private DesignOrderModel designOrderModel = new DesignOrderModel();

    private IngredientByIdConverter ingredientByIdConverter = new IngredientByIdConverter();

    private final MessageSource messageSource = new MessageSource();

    @View("design.html")
    @Get("/design")
    public ModelAndView index(Session session) {
//        return new ModelAndView("design", designOrderModel.getModel());
        return new ModelAndView("design", new DesignOrderModel().getModel());
    }


    @View("design.html")
    @Error(exception = ConstraintViolationException.class)
    public ModelAndView submitFailed(HttpRequest request, ConstraintViolationException ex){
        designOrderModel.put("errors", messageSource.violationsMessages(ex.getConstraintViolations()));
        Optional<DesignTacoForm> cmd = request.getBody(DesignTacoForm.class);
        return new ModelAndView("design", designOrderModel.getModel());
    }


    @Post("/design") // if no "design", controller will redirect to "/"
    @Consumes({MediaType.APPLICATION_FORM_URLENCODED, MediaType.APPLICATION_JSON})
    public MutableHttpResponse<?> processTaco(
            Session session,
            @Valid @Body DesignTacoForm designTacoForm
            ) {
        List<Ingredient> convertIngredients = new ArrayList<>();
        List<String> ingredientsId = designTacoForm.getIngredientsId();
        if (ingredientsId.size() != 0) {
            for (String item : ingredientsId) {
                Optional<Ingredient> rel = ingredientByIdConverter.convert(item, Ingredient.class, ConversionContext.DEFAULT);
                if (rel.isPresent()) {
                    convertIngredients.add(rel.get());
                }
            }
        }
        Taco taco = new Taco(designTacoForm.getName(), convertIngredients);
        designOrderModel.addTacoToOrder(taco);
        log.info("Processing taco: ()", taco);
        // put in session
        session.put("tacoOrder", designOrderModel.getModel().get("tacoOrder"));
        // Redirect in Micronaut
        return HttpResponse.redirect(URI.create("/orders/current"));
    }

}
