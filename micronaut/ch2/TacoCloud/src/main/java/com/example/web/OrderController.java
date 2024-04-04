package com.example.web;

import com.example.model.TacoOrder;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Post;
import io.micronaut.views.View;
import lombok.extern.slf4j.Slf4j;
import jakarta.validation.Valid;

import static io.micronaut.http.MediaType.TEXT_HTML;

@Slf4j
@Controller("/orders")
public class OrderController {
    @View("orderForm.html")
    @Get("/current")
    public void orderForm() {

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
