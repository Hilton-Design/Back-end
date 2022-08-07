package com.hilton.hibye.domain.commute.exception;

import com.hilton.hibye.global.error.exception.BusinessException;
import com.hilton.hibye.global.error.exception.ErrorCode;

public class CommuteNotFoundException extends BusinessException {

    public static final CommuteNotFoundException EXCEPTION = new CommuteNotFoundException();

    private CommuteNotFoundException() {
        super(ErrorCode.COMMUTE_NOT_FOUND);
    }
}
