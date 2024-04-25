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
import java.util.List;
import static io.micronaut.http.MediaType.TEXT_HTML;

@Slf4j
@Controller("/orders")
public class OrderController {
//    @View("orderForm.html")
    @Get("/current")
//    public ModelAndView orderForm(
    public HttpResponse<?> orderForm(Session session) {
        if (!session.contains("tacoOrder")) {
            return HttpResponse.ok("tacoOrder doesn't exist");
        }else{
            return HttpResponse.ok(session.get("tacoOrder", TacoOrder.class).toString());
        }
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
