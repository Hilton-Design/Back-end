package com.hilton.hibye.domain.user.exception;

import com.hilton.hibye.global.error.exception.BusinessException;
import com.hilton.hibye.global.error.exception.ErrorCode;

public class UserAlreadyExistsException extends BusinessException {

    public static final UserAlreadyExistsException EXCEPTION = new UserAlreadyExistsException();

    private UserAlreadyExistsException() {
        super(ErrorCode.USER_ALREADY_EXISTS);
    }
}
