package com.example.web;

import com.example.errorhandling.MessageSource;
import com.example.dto.DesignOrderForm;
import com.example.dto.DesignOrderModel;
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

import java.util.Optional;

@Slf4j
@Controller("/orders")
public class OrderController {
    private DesignOrderModel designOrderModel = new DesignOrderModel();
    private final MessageSource messageSource = new MessageSource();
    @View("orderForm.html")
    @Get("/current")
    public ModelAndView orderForm(Session session){
        TacoOrder tacoOrder = session.get("tacoOrder", TacoOrder.class).orElse(new TacoOrder());
        designOrderModel.put("tacoOrder", tacoOrder);
        System.out.println("tacoOrder Info: " + designOrderModel.getModel().get("tacoOrder"));
        return new ModelAndView("order", designOrderModel.getModel());
    }


    @View("orderForm.html")
    @Error(exception = ConstraintViolationException.class)
    public ModelAndView submitFailed(HttpRequest request, ConstraintViolationException ex){
        designOrderModel.put("errors", messageSource.violationsMessages(ex.getConstraintViolations()));
        Optional<DesignOrderForm> cmd = request.getBody(DesignOrderForm.class);
        cmd.ifPresent(designOrderForm ->
                {
                    TacoOrder tacoOrder = (TacoOrder) designOrderModel.getModel().get("tacoOrder");
                    tacoOrder.copyFromDesignOrderForm(designOrderForm);
                    designOrderModel.put("tacoOrder", tacoOrder);
                });
        return new ModelAndView("order", designOrderModel.getModel());
    }



    @Post()
    @Consumes({MediaType.APPLICATION_FORM_URLENCODED, MediaType.APPLICATION_JSON})
    public HttpResponse<?> processOrder(
            @Valid @Body DesignOrderForm designOrderForm,
            Session session
    ) {
        TacoOrder tacoOrder = (TacoOrder) session.get("tacoOrder", TacoOrder.class).orElse(new TacoOrder());
        tacoOrder.copyFromDesignOrderForm(designOrderForm);
        session.put("tacoOrder", tacoOrder);
        log.info("Order submitted: {}", tacoOrder);
        return HttpResponse.ok(session.get("tacoOrder", TacoOrder.class).orElse(null).toString());
//        session.clear();
//        return HttpResponse.redirect(URI.create("/"));
    }
}
