package com.example.web;

import com.example.errorhandling.MessageSource;
import com.example.model.DesignOrderForm;
import com.example.model.DesignOrderModel;
import com.example.model.Taco;
import com.example.model.TacoOrder;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.*;
import io.micronaut.http.annotation.Error;
import io.micronaut.session.Session;
import io.micronaut.views.ModelAndView;
import io.micronaut.views.View;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import jakarta.validation.Valid;

import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static io.micronaut.http.MediaType.TEXT_HTML;

@Slf4j
@Controller("/orders")
public class OrderController {
    private Map<String, Object> orderModel = new HashMap<>();
    private final MessageSource messageSource = new MessageSource();
    @View("orderForm.html")
    @Get("/current")
    public ModelAndView orderForm(Session session){
        TacoOrder tacoOrder = session.get("tacoOrder", TacoOrder.class).orElse(new TacoOrder());
        orderModel.put("tacoOrder", tacoOrder);
//        System.out.println("tacoOrder Info: " + newModel);
        return new ModelAndView("order", orderModel);
    }

    @View("orderForm.html")
    @Error(exception = ConstraintViolationException.class)
    public ModelAndView submitFailed(HttpRequest request, ConstraintViolationException ex){
        orderModel.put("errors", messageSource.violationsMessages(ex.getConstraintViolations()));
        Optional<DesignOrderForm> cmd = request.getBody(DesignOrderForm.class);
        return new ModelAndView("design", orderModel);
    }

    @Post
    @Consumes({MediaType.APPLICATION_FORM_URLENCODED, MediaType.APPLICATION_JSON})
    public HttpResponse<?> processOrder(
            @Body DesignOrderForm designOrderForm,
            Session session
    ) {
        TacoOrder tacoOrder = (TacoOrder) session.get("tacoOrder", TacoOrder.class).orElse(new TacoOrder());
        tacoOrder.copyFromDesignOrderForm(designOrderForm);
        session.put("tacoOrder", tacoOrder);
//        if (errors.hasErrors()) {
//            return "orderForm";
//        }

        log.info("Order submitted: {}", tacoOrder);
//        session.clear();

        return HttpResponse.ok(session.get("tacoOrder", TacoOrder.class).orElse(null).toString());
//        return HttpResponse.redirect(URI.create("/"));
    }
}
