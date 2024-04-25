package com.example;

import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.session.Session;

@Controller("/")
public class ReveiveController {
    @Get("/receive")
    public String receive(Session session) {
        String attributeValue = session.get("sharedAttribute", String.class)
                .orElse("Attribute not found");

        return "Shared attribute value: " + attributeValue;
//        return "successful";
    }
}
