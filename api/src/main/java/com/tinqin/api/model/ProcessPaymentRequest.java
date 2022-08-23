package com.tinqin.api.model;

import com.tinqin.api.base.OperationInput;
import lombok.*;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter(AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
public class ProcessPaymentRequest implements OperationInput {

    private String uuid;
    private String cardNumber;
    private LocalDate expiryDate;
    private String cvc;

}
