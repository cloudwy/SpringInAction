package com.example.web;


import com.example.adapter.IngredientRepository;
import io.micronaut.http.annotation.*;
import jakarta.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.example.dto.DesignTacoModel;
import io.micronaut.views.ModelAndView;
import io.micronaut.views.View;
import io.micronaut.session.Session;
import com.example.dto.IngredientByIdConverter;
import com.example.errorhandling.MessageSource;
import com.example.dto.DesignTacoForm;
import com.example.model.*;
import io.micronaut.core.convert.ConversionContext;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.MediaType;
import io.micronaut.http.MutableHttpResponse;
import io.micronaut.http.annotation.Error;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Valid;
import java.net.URI;
import java.util.*;



@Controller("/")
public class DesignTacoController {
    // DI wrong, so IngredientRepository.findAll() is null
//    private static final Logger log = LoggerFactory.getLogger(DesignTacoController.class);
//    private MessageSource messageSource = new MessageSource();
//    private IngredientRepository ingredientRepository;
//    private DesignTacoModel designTacoModel = new DesignTacoModel(ingredientRepository);
//    private IngredientByIdConverter ingredientByIdConverter = new IngredientByIdConverter(ingredientRepository);
////    private IngredientRepository ingredientRepository;

    private static final Logger log = LoggerFactory.getLogger(DesignTacoController.class);
    @Inject
    private MessageSource messageSource;
    @Inject
    private DesignTacoModel designTacoModel;
    @Inject
    private IngredientByIdConverter ingredientByIdConverter;
    @Inject
    private IngredientRepository ingredientRepository;

    @View("design.html")
    @Get("/design")
    public ModelAndView index() {
        return new ModelAndView("design", new DesignTacoModel(ingredientRepository).getModel());
    }


    @View("design.html")
    @Error(exception = ConstraintViolationException.class)
    public ModelAndView submitFailed(
            HttpRequest request,
            ConstraintViolationException ex
            ){
        designTacoModel.put("errors", messageSource.violationsMessages(ex.getConstraintViolations()));
        Optional<DesignTacoForm> cmd = request.getBody(DesignTacoForm.class);
        cmd.ifPresent(designTacoForm -> designTacoModel.put("designTacoForm", designTacoForm));
        return new ModelAndView("design", designTacoModel.getModel());
    }


    @Post("/design") // if no "design", controller will redirect to "/"
    @Consumes({MediaType.APPLICATION_FORM_URLENCODED, MediaType.APPLICATION_JSON})
    public MutableHttpResponse<?> processTaco(
            Session session,
            @Valid @Body DesignTacoForm designTacoForm
            ) {
        designTacoModel.put("designTacoForm", designTacoForm);
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
        designTacoModel.addTacoToOrder(taco);
        log.info("Processing taco: ()", taco);
        // put in session
        session.put("tacoOrder", designTacoModel.getModel().get("tacoOrder"));
        // Redirect in Micronaut
        return HttpResponse.redirect(URI.create("/orders/current"));
    }
}
