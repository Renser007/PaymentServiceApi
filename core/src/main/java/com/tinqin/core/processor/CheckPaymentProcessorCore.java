package com.tinqin.core.processor;

import com.tinqin.api.base.Error;
import com.tinqin.api.error.NoSuchPaymentError;
import com.tinqin.api.model.CheckPaymentRequest;
import com.tinqin.api.model.CheckPaymentResponse;
import com.tinqin.api.operation.CheckPaymentProcessor;
import com.tinqin.core.exception.NoSuchPaymentException;
import com.tinqin.domain.data.repository.PaymentRepository;
import io.vavr.control.Either;
import io.vavr.control.Try;
import org.springframework.stereotype.Service;

import java.util.stream.Stream;

@Service
public class CheckPaymentProcessorCore implements CheckPaymentProcessor {

    private final PaymentRepository paymentRepository;

    public CheckPaymentProcessorCore(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    @Override
    public Either<Error, CheckPaymentResponse> process(CheckPaymentRequest input) {
        return Try.of(() -> {

            return Stream.of(paymentRepository.getPaymentById(input.getUuid())
                    .orElseThrow(NoSuchPaymentException::new))
                    .map(p -> {
                        return CheckPaymentResponse.builder()
                                .status(p.getStatus())
                                .build();
                    })
                    .findFirst()
                    .orElseThrow();


        }).toEither()
                .mapLeft(throwable -> {
                    if (throwable instanceof NoSuchPaymentException){
                        return new NoSuchPaymentError();
                    }
                    return new NoSuchPaymentError();
                });
    }
}
