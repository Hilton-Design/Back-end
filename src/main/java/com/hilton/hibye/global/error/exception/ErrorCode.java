package com.hilton.hibye.global.error.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    INTERNAL_SERVER_ERROR(500, "서버에 오류가 발생했습니다."),
    BAD_REQUEST(400, "잘못된 요청입니다."),

    USER_ALREADY_EXISTS(422, "사용자가 이미 존재합니다."),
    USER_NOT_FOUND(402, "사용자를 찾을 수 없습니다." ),

    NOBODY_COMMUTE_EXCEPTION(402, "아직 아무도 출석하지 않았습니다." ),
    ALREADY_GO_TO_WORK(422, "이미 출석하셨습니다." ),
    ALREADY_GET_OFF_WORK(422, "이미 퇴근 하셨습니다."),
    GO_TO_WORK_YET(402, "아직 출근하지 않으셨습니다."),
    COMMUTE_NOT_FOUND(402, "출퇴근 내역을 찾을 수 없습니다."),

    EXPIRED_TOKEN(401, "만료된 토큰입니다."),
    INVALID_TOKEN(401, "유효하지 않은 토큰입니다."),
    PASSWORD_MISMATCH(401, "비밀번호가 틀렸습니다."),

    ;

    private final int status;
    private final String message;


}
