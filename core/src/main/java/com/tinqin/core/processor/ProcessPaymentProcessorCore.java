package com.tinqin.core.processor;


import com.tinqin.api.base.Error;
import com.tinqin.api.error.InvalidCardError;
import com.tinqin.api.error.NoSuchPaymentError;
import com.tinqin.api.error.PaymentUnavailableError;
import com.tinqin.api.model.ProcessPaymentRequest;
import com.tinqin.api.model.ProcessPaymentResponse;
import com.tinqin.api.operation.ProcessPaymentProcessor;
import com.tinqin.core.exception.InvalidCardException;
import com.tinqin.core.exception.NoSuchPaymentException;
import com.tinqin.core.exception.PaymentUnavailableException;
import com.tinqin.domain.data.entity.Payment;
import com.tinqin.domain.data.repository.PaymentRepository;
import com.tinqin.domain.data.repository.WalletRepository;
import com.tinqin.domain.service.CardValidationService;
import io.vavr.control.Either;
import io.vavr.control.Try;
import org.springframework.stereotype.Service;

import java.util.stream.Stream;

@Service
public class ProcessPaymentProcessorCore implements ProcessPaymentProcessor {

    private final PaymentRepository paymentRepository;
    private final WalletRepository walletRepository;
    private final CardValidationService cardValidationService;

    public ProcessPaymentProcessorCore(PaymentRepository paymentRepository, WalletRepository walletRepository, CardValidationService cardValidationService) {
        this.paymentRepository = paymentRepository;
        this.walletRepository = walletRepository;
        this.cardValidationService = cardValidationService;
    }

    @Override
    public Either<Error, ProcessPaymentResponse> process(final ProcessPaymentRequest input) {
        return Try.of(()-> {

            if(!cardValidationService.CheckCardValidation(input.getCardNumber(), input.getCvc(), input.getExpiryDate())) {
                throw new InvalidCardException();
            }

            return Stream.of(walletRepository.getWalletByCardNumberAndCvc(input.getCardNumber(), input.getCvc())
                    .orElseThrow(InvalidCardException::new))
                    .map(w -> {
                        final Payment payment = paymentRepository.getPaymentById(input.getId())
                                .orElseThrow(NoSuchPaymentException::new);
                        final Double newAmount = w.getAmount() - payment.getCost();

                        w.setAmount(newAmount);

                        walletRepository.save(w);

                        payment.setStatus("Payment succeeded!");

                        paymentRepository.save(payment);

                        return ProcessPaymentResponse.builder()
                                .status(payment.getStatus())
                                .build();
                    })
                    .findFirst()
                    .orElseThrow(PaymentUnavailableException::new);



        }).toEither()
                .mapLeft(throwable -> {
                    if (throwable instanceof InvalidCardException){
                        return new InvalidCardError();
                    }
                    if (throwable instanceof NoSuchPaymentException)
                    {
                        return new NoSuchPaymentError();
                    }
                    if (throwable instanceof PaymentUnavailableException){
                        return  new PaymentUnavailableError();
                    }
                    return new PaymentUnavailableError();
                });
    }
}
