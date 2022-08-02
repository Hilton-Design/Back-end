package com.hilton.hibye.domain.commute.exception;

import com.hilton.hibye.global.error.exception.BusinessException;
import com.hilton.hibye.global.error.exception.ErrorCode;

public class GoToWorkYetException extends BusinessException {

    public final static GoToWorkYetException EXCEPTION = new GoToWorkYetException();

    private GoToWorkYetException() {
        super(ErrorCode.GO_TO_WORK_YET);
    }

}
