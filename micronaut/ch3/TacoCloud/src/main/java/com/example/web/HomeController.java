package com.example.web;

import com.example.dto.DesignOrderModel;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.views.View;
import io.micronaut.session.Session;
import jakarta.inject.Inject;

import static io.micronaut.http.MediaType.TEXT_HTML;

@Controller("/home")
public class HomeController {

    @View("home.html")
    @Get(produces = TEXT_HTML) //Spring: @GetMapping
    void index() {
    }
}

