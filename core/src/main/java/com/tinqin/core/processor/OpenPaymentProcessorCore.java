package com.tinqin.core.processor;

import com.tinqin.api.base.Error;
import com.tinqin.api.error.OpenPaymentError;
import com.tinqin.api.model.OpenPaymentRequest;
import com.tinqin.api.model.OpenPaymentResponse;
import com.tinqin.api.operation.OpenPaymentProcessor;
import com.tinqin.core.generator.UuidGenerator;
import com.tinqin.domain.data.entity.Payment;
import com.tinqin.domain.data.repository.PaymentRepository;
import io.vavr.control.Either;
import io.vavr.control.Try;
import org.springframework.stereotype.Service;

@Service
public class OpenPaymentProcessorCore implements OpenPaymentProcessor {

    private final PaymentRepository paymentRepository;

    public OpenPaymentProcessorCore(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    @Override
    public Either<Error, OpenPaymentResponse> process(final OpenPaymentRequest input) {
        return Try.of(()->{

            final Payment payment = new Payment(new UuidGenerator().generate(), input.getCost(), "Payment pending");
            paymentRepository.save(payment);

            return OpenPaymentResponse.builder()
                    .uuid(payment.getId())
                    .status(payment.getStatus())
                    .build();

        }).toEither()
                .mapLeft(throwable -> new OpenPaymentError());
    }
}
