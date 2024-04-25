package com.example.web;

import com.example.model.DesignOrderModel;
import com.example.model.Taco;
import com.example.model.TacoOrder;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Post;
import io.micronaut.http.annotation.RequestAttribute;
import io.micronaut.session.Session;
import io.micronaut.views.ModelAndView;
import io.micronaut.views.View;
import lombok.extern.slf4j.Slf4j;
import jakarta.validation.Valid;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.micronaut.http.MediaType.TEXT_HTML;

@Slf4j
@Controller("/orders")
public class OrderController {
    private Map<String, Object> orderModel = new HashMap<>();
    @View("orderForm.html")
    @Get("/current")
    public ModelAndView orderForm(Session session){
//    public HttpResponse<?> orderForm(Session session) {
        TacoOrder tacoOrder = session.get("tacoOrder", TacoOrder.class).orElse(new TacoOrder());
        orderModel.put("tacoOrder", tacoOrder);
//        System.out.println("tacoOrder Info: " + newModel);
        return new ModelAndView("order", orderModel);
//        return HttpResponse.ok(newModel.getTacos().toString());
    }

    @Post
    public String processOrder(@Valid TacoOrder order) {
//        if (errors.hasErrors()) {
//            return "orderForm";
//        }

        log.info("Order submitted: {}", order);
//        sessionStatus.setComplete();

        return "redirect:/";
    }
}
