package com.tinqin.domain.data.entity;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "wallet")
@Getter
@Setter
public class Wallet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String cardNumber;

    private String cvc;

    private LocalDate expDate;

    private Double amount;

    public Wallet(Long id, String cardNumber, String cvc, LocalDate expDate, Double amount) {
        this.id = id;
        this.cardNumber = cardNumber;
        this.cvc = cvc;
        this.expDate = expDate;
        this.amount = amount;
    }

    public Wallet() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Wallet wallet = (Wallet) o;
        return Objects.equals(id, wallet.id) && Objects.equals(cardNumber, wallet.cardNumber) && Objects.equals(cvc, wallet.cvc) && Objects.equals(expDate, wallet.expDate) && Objects.equals(amount, wallet.amount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, cardNumber, cvc, expDate, amount);
    }
}
