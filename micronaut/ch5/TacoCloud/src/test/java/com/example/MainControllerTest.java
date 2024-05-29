//package com.example;
//
//import io.micronaut.http.client.HttpClient;
//import io.micronaut.http.client.annotation.Client;
//import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
//import jakarta.inject.Inject;
//import org.junit.jupiter.api.Test;
//
//import static org.junit.jupiter.api.Assertions.assertTrue;
//
//@MicronautTest
//public class MainControllerTest {
//    @Inject
//    @Client("/hello")
//    HttpClient client;
//
//    @Test
//    void testItWorks() {
//        String html = client.toBlocking().retrieve("/Tim", String.class);
//        assertTrue(html.contains("""
//                <h1>Hello Tim!</h1>"""));
//    }
//}
