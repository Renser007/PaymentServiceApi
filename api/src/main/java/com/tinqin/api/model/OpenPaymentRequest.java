package com.tinqin.api.model;

import com.tinqin.api.base.OperationInput;
import lombok.*;

@Getter
@Setter(AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
public class OpenPaymentRequest implements OperationInput {

    private Double cost;
    private String label;
    private String id;

}
