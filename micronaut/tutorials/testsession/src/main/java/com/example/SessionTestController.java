package com.example;

import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.session.Session;

@Controller("/")
public class SessionTestController {
    @Get("/session")
    public String checkSessionAttribute(Session session) {

        session.put("test", "1.2.3...test my session");
        return session.get("test").orElse(null).toString();
    }
}
