//package com.example.dto;
//
//import io.micronaut.serde.annotation.Serdeable;
//import jakarta.validation.constraints.Digits;
//import jakarta.validation.constraints.NotBlank;
//import jakarta.validation.constraints.Pattern;
//import lombok.Data;
////import org.hibernate.validator.constraints.CreditCardNumber;
//
//@Data
//@Serdeable
//public class DesignOrderForm {
//    @NotBlank(message="Delivery name is required")
//    private String deliveryName;
//
//    @NotBlank(message="Street is required")
//    private String deliveryStreet;
//
//    @NotBlank(message="City is required")
//    private String deliveryCity;
//
//    @NotBlank(message="State is required")
//    private String deliveryState;
//
//    @NotBlank(message="Zip code is required")
//    private String deliveryZip;
//
////    @CreditCardNumber(message="Not a valid credit card number") //didn't work in micronaut
//    @NotBlank(message="Credit Card Number is required")
//    private String ccNumber;
//
//    @Pattern(regexp="^(0[1-9]|1[0-2])([\\/])([2-9][0-9])$",
//            message="Must be formatted MM/YY")
//    private String ccExpiration;
//
//    @Digits(integer=3, fraction=0, message="Invalid CVV")
//    private String ccCVV;
//}
