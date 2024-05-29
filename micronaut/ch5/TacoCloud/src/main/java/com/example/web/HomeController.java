package com.example.web;

import com.example.dto.DesignOrderModel;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.cookie.Cookie;
import io.micronaut.session.http.HttpSessionConfiguration;
import io.micronaut.views.View;
import io.micronaut.session.Session;
import jakarta.inject.Inject;

import java.util.UUID;

import static io.micronaut.http.MediaType.TEXT_HTML;

@Controller("/")
public class HomeController {

    @Inject
    private HttpSessionConfiguration sessionConfiguration;

    @View("home.html")
    @Get(produces = TEXT_HTML) //Spring: @GetMapping
    public void index(Session session) {}

    // change another cookie, but now not work
//    @View("home.html")
//    @Get(produces = TEXT_HTML) //Spring: @GetMapping
//    public HttpResponse<?> index(Session session) {
//        session.clear();
//        String newSessionId = UUID.randomUUID().toString();
//        // create a new session cookie
//        String sessionCookieName = sessionConfiguration.getCookieName();
//        Cookie sessionCookie = Cookie.of(sessionCookieName, newSessionId);
//        sessionCookie.path("/");
//        return HttpResponse.ok().cookie(sessionCookie).header("Location", "/home");
//    }

}

