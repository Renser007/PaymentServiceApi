package com.tinqin.core.processor;

import antlr.StringUtils;
import com.tinqin.api.base.Error;
import com.tinqin.api.error.OpenPaymentError;
import com.tinqin.api.model.OpenPaymentRequest;
import com.tinqin.api.model.OpenPaymentResponse;
import com.tinqin.api.operation.OpenPaymentProcessor;
import com.tinqin.domain.data.entity.Payment;
import com.tinqin.domain.data.repository.PaymentRepository;
import io.vavr.control.Either;
import io.vavr.control.Try;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class OpenPaymentProcessorCore implements OpenPaymentProcessor {

    private final PaymentRepository paymentRepository;

    public OpenPaymentProcessorCore(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    @Override
    public Either<Error, OpenPaymentResponse> process(final OpenPaymentRequest input) {
        return Try.of(()->{

            final Payment payment = new Payment( RandomStringUtils.randomNumeric(10), input.getCost(), "false");
            paymentRepository.save(payment);

            return OpenPaymentResponse.builder()
                    .uuid(payment.getId().toString())
                    .build();

        }).toEither()
                .mapLeft(throwable -> {
                    return new OpenPaymentError();
                });
    }
}
