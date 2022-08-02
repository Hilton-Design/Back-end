package com.hilton.hibye.domain.commute.exception;

import com.hilton.hibye.global.error.exception.BusinessException;
import com.hilton.hibye.global.error.exception.ErrorCode;

public class NobodyCommuteException extends BusinessException {

    public final static NobodyCommuteException EXCEPTION = new NobodyCommuteException();

    private NobodyCommuteException() {
        super(ErrorCode.NOBODY_COMMUTE_EXCEPTION);
    }
}
