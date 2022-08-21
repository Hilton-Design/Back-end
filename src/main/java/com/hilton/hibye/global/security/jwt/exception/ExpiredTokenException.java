package com.hilton.hibye.global.security.jwt.exception;

import com.hilton.hibye.global.error.exception.BusinessException;
import com.hilton.hibye.global.error.exception.ErrorCode;

public class ExpiredTokenException extends BusinessException {

    public final static ExpiredTokenException EXCEPTION = new ExpiredTokenException();

    private ExpiredTokenException() {
        super(ErrorCode.EXPIRED_TOKEN);
    }
}
