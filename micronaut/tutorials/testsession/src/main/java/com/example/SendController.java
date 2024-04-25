package com.example;

import io.micronaut.http.HttpResponse;
import io.micronaut.http.MediaType;
import io.micronaut.http.MutableHttpResponse;
import io.micronaut.http.annotation.*;
import io.micronaut.session.Session;

import java.net.URI;

@Controller("/")
public class SendController {
    @Get("/send")
    public String checkSessionAttribute(Session session) {
        session.put("start", "start a test");
        return session.get("test").orElse(null).toString();
    }

    @Post("/send")
    @Consumes({MediaType.APPLICATION_FORM_URLENCODED, MediaType.APPLICATION_JSON})
    public MutableHttpResponse<?> send(
            Session session,
            @Body RequestData data
            ){
        session.put("sharedAttribute", data.getValue());
        return HttpResponse.redirect(URI.create("/receive"));
    }
}
