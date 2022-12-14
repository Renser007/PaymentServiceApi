package com.tinqin.rest.controller;

import com.tinqin.api.base.Error;
import com.tinqin.api.model.*;
import com.tinqin.api.operation.CheckPaymentProcessor;
import com.tinqin.api.operation.OpenPaymentProcessor;
import com.tinqin.api.operation.ProcessPaymentProcessor;
import io.vavr.control.Either;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PaymentController {

    private final OpenPaymentProcessor openPaymentProcessor;
    private final ProcessPaymentProcessor processPaymentProcessor;
    private final CheckPaymentProcessor checkPaymentProcessor;

    public PaymentController(OpenPaymentProcessor openPaymentProcessor, ProcessPaymentProcessor processPaymentProcessor, CheckPaymentProcessor checkPaymentProcessor) {
        this.openPaymentProcessor = openPaymentProcessor;
        this.processPaymentProcessor = processPaymentProcessor;
        this.checkPaymentProcessor = checkPaymentProcessor;
    }


    @PostMapping("/openPayment")
    public ResponseEntity<?> openPayment(@RequestBody final OpenPaymentRequest openPaymentRequest){
        Either<Error, OpenPaymentResponse> response=openPaymentProcessor.process(openPaymentRequest);
        if(response.isLeft()){
            return ResponseEntity.status(response.getLeft().getCode()).body(response.getLeft().getMessage());
        }
        return ResponseEntity.status(HttpStatus.OK).body(response.get());
    }

    @PostMapping("/processPayment")
    public ResponseEntity<?> processPayment(@RequestBody final ProcessPaymentRequest processPaymentRequest){
        Either<Error, ProcessPaymentResponse> response=processPaymentProcessor.process(processPaymentRequest);
        if(response.isLeft()){
            return ResponseEntity.status(response.getLeft().getCode()).body(response.getLeft().getMessage());
        }
        return ResponseEntity.status(HttpStatus.OK).body(response.get());
    }

    @PostMapping("/checkPayment")
    public ResponseEntity<?> checkPayment(@RequestBody final CheckPaymentRequest checkPaymentRequest){
        Either<Error, CheckPaymentResponse> response=checkPaymentProcessor.process(checkPaymentRequest);
        if(response.isLeft()){
            return ResponseEntity.status(response.getLeft().getCode()).body(response.getLeft().getMessage());
        }
        return ResponseEntity.status(HttpStatus.OK).body(response.get());
    }

}
