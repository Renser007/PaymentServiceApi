package com.tinqin.core.processor;


import com.tinqin.api.base.Error;
import com.tinqin.api.model.CheckPaymentRequest;
import com.tinqin.api.model.CheckPaymentResponse;
import com.tinqin.api.operation.CheckPaymentProcessor;
import io.vavr.control.Either;
import org.springframework.stereotype.Service;

@Service
public class CheckPaymentProcessorCore implements CheckPaymentProcessor {


    @Override
    public Either<Error, CheckPaymentResponse> process(CheckPaymentRequest input) {
        return null;
    }
}
