package com.hilton.hibye.domain.commute.exception;

import com.hilton.hibye.global.error.exception.BusinessException;
import com.hilton.hibye.global.error.exception.ErrorCode;

public class AlreadyGetOffWorkException extends BusinessException {

    public final static AlreadyGetOffWorkException EXCEPTION = new AlreadyGetOffWorkException();

    private AlreadyGetOffWorkException() {
        super(ErrorCode.ALREADY_GET_OFF_WORK);
    }
}
