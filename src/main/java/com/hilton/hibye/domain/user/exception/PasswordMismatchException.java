package com.hilton.hibye.domain.user.exception;

import com.hilton.hibye.global.error.exception.BusinessException;
import com.hilton.hibye.global.error.exception.ErrorCode;

public class PasswordMismatchException extends BusinessException {

    public final static PasswordMismatchException EXCEPTION = new PasswordMismatchException();

    private PasswordMismatchException() {
        super(ErrorCode.PASSWORD_MISMATCH);
    }
}
