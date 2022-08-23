package com.tinqin.api.model;

import com.tinqin.api.base.OperationInput;
import lombok.*;

@Getter
@Setter(AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
public class CheckPaymentRequest implements OperationInput {

    private String uuid;

}
