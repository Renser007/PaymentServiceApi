package com.tinqin.api.model;


import com.tinqin.api.base.OperationResult;
import lombok.*;

@Builder
@Getter
@Setter(AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
public class CheckPaymentResponse implements OperationResult {

    private String status;

}
