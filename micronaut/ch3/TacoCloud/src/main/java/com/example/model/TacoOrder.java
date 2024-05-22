package com.example.model;

import io.micronaut.serde.annotation.Serdeable;
import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import com.example.dto.DesignOrderForm;
//import org.hibernate.validator.constraints.CreditCardNumber;

@Data
@Serdeable
@Entity
public class TacoOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private Date placedAt = new Date();

    @NotBlank(message="Delivery name is required")
    private String deliveryName;

    @NotBlank(message="Street is required")
    private String deliveryStreet;

    @NotBlank(message="City is required")
    private String deliveryCity;

    @NotBlank(message="State is required")
    private String deliveryState;

    @NotBlank(message="Zip code is required")
    private String deliveryZip;

//    @CreditCardNumber(message="Not a valid credit card number")
    @NotBlank(message="Credit Card Number is required")
    @Digits(integer=16, fraction=6)
    private String ccNumber;

    @Pattern(regexp="^(0[1-9]|1[0-2])([\\/])([2-9][0-9])$",
            message="Must be formatted MM/YY")
    private String ccExpiration;

    @Digits(integer=3, fraction=0, message="Invalid CVV")
    private String ccCVV;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Taco> tacos = new ArrayList<>();

    public void addTaco(Taco taco) {
        this.tacos.add(taco);
    }

    // copy DesignOrderForm to TacoOrder
    public void copyFromDesignOrderForm(DesignOrderForm designOrderForm) {
        this.deliveryName = designOrderForm.getDeliveryName();
        this.deliveryStreet = designOrderForm.getDeliveryStreet();
        this.deliveryCity = designOrderForm.getDeliveryCity();
        this.deliveryState = designOrderForm.getDeliveryState();
        this.deliveryZip = designOrderForm.getDeliveryZip();
        this.ccNumber = designOrderForm.getCcNumber();
        this.ccExpiration = designOrderForm.getCcExpiration();
        this.ccCVV = designOrderForm.getCcCVV();
    }
}
