package com.tinqin.domain.service;

import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class CardValidationServiceImpl implements CardValidationService{
    @Override
    public Boolean CheckCardValidation(String cardNumber, String cvc, LocalDate expDate) {

        return cardNumber.length() == 16 && cvc.length() == 3 && CheckExpDate(expDate);
    }

    public Boolean CheckExpDate(LocalDate expDate){

        LocalDate current = LocalDate.now();

        if(expDate.getYear() > current.getYear()){
            return true;
        }

        if (expDate.getYear() == current.getYear() && expDate.getMonth().getValue() >= current.getMonth().getValue()){
            return true;
        }

        return false;
    }
}
