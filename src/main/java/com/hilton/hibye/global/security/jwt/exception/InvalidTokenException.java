package com.hilton.hibye.global.security.jwt.exception;

import com.hilton.hibye.global.error.exception.BusinessException;
import com.hilton.hibye.global.error.exception.ErrorCode;

public class InvalidTokenException extends BusinessException {

    public final static InvalidTokenException EXCEPTION = new InvalidTokenException();

    private InvalidTokenException() {
        super(ErrorCode.INVALID_TOKEN);
    }
}
