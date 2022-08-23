package com.tinqin.api.error;

import com.tinqin.api.base.Error;
import org.springframework.http.HttpStatus;

public class TransactionAlreadyFinishedError implements Error {
    @Override
    public HttpStatus getCode() {
        return HttpStatus.ALREADY_REPORTED;
    }

    @Override
    public String getMessage() {
        return "Transaction had already completed!";
    }
}
