package com.example;

import io.micronaut.http.client.HttpClient;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


@MicronautTest
public class StaticResourceTest {
    @Test
    void stylesheetExists(@Client("/css") HttpClient client) {
        String css = client.toBlocking().retrieve("/style.css", String.class);
        assertTrue(css.contains("""
                html, body {"""));
    }

    @Test
    void imageExists(@Client("/images") HttpClient client) throws IOException {
        byte[] image = client.toBlocking().retrieve("/TacoCloud.png", byte[].class);
        int expectedLength = StaticResourceTest.class
                .getResourceAsStream("/static/images/TacoCloud.png")
                .readAllBytes().length;
        assertEquals(expectedLength, image.length);
    }

}
