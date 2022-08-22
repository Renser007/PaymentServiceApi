package com.tinqin.api.model;

import com.tinqin.api.base.OperationResult;
import lombok.*;

import java.util.UUID;

@Builder
@Getter
@Setter(AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
public class OpenPaymentResponse implements OperationResult {

    private UUID id;

}
