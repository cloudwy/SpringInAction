package com.example.web;

import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.views.View;

import static io.micronaut.http.MediaType.TEXT_HTML;

@Controller("/")
public class HomeController {

    @View("home.html")
    @Get(produces = TEXT_HTML) //Spring: @GetMapping
    void index() {}

}

