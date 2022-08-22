package com.tinqin.domain.service;

import java.time.LocalDate;

public interface CardValidationService {

    Boolean CheckCardValidation(String cardNumber, String cvc, LocalDate expDate);

}
