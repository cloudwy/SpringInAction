//package com.example;
//
//
//import com.example.web.MessageService;
//import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
//import jakarta.inject.Inject;
//import jakarta.validation.ConstraintViolationException;
//import org.junit.jupiter.api.Test;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertThrows;
//
//@MicronautTest(startApplication = false)
//class MessageServiceTest {
//
//    @Inject
//    MessageService service;
//
//    @Test
//    void testItWorks() {
//        assertEquals("Hello Tim!", service.sayHello("Tim"));
//    }
//
//    @Test
//    void testValidationWithNull() {
//        ConstraintViolationException exception = assertThrows(ConstraintViolationException.class, () -> service.sayHello(null));
//        assertEquals(1, exception.getConstraintViolations().size());
//        assertEquals("sayHello.name: must not be blank", exception.getLocalizedMessage());
//    }
//
//    @Test
//    void testValidationWithBlank() {
//        ConstraintViolationException exception = assertThrows(ConstraintViolationException.class, () -> service.sayHello("   "));
//        assertEquals(1, exception.getConstraintViolations().size());
//        assertEquals("sayHello.name: must not be blank", exception.getMessage());
//    }
//}
