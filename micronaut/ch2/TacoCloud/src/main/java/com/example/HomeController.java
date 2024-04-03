package com.example;

import io.micronaut.core.annotation.NonNull;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.views.View;
import jakarta.validation.constraints.NotBlank;

import java.util.Map;

import static io.micronaut.http.MediaType.TEXT_HTML;

@Controller("/")
public class HomeController {
    private final MessageService messageService;

    public HomeController(MessageService messageService) {
        this.messageService = messageService;
    }
    @View("home.html")
    @Get(value = "/hello/{name}", produces = TEXT_HTML) //Spring: @GetMapping
    Map<String, String> index(@NonNull @NotBlank String name) {
        return Map.of("message", messageService.sayHello(name));
    }
}

