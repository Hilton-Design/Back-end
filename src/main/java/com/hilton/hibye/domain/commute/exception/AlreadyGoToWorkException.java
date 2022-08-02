package com.hilton.hibye.domain.commute.exception;

import com.hilton.hibye.global.error.exception.BusinessException;
import com.hilton.hibye.global.error.exception.ErrorCode;

public class AlreadyGoToWorkException extends BusinessException {

    public final static AlreadyGoToWorkException EXCEPTION = new AlreadyGoToWorkException();

    private AlreadyGoToWorkException() {
        super(ErrorCode.ALREADY_GO_TO_WORK);
    }
}
