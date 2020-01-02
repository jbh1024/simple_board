package org.iptime.hoonyhoony.simple_board.exception;

import org.springframework.http.HttpStatus;

public enum ErrorCode {
    REQUEST_ERROR("잘못된 요청입니다.", HttpStatus.BAD_REQUEST),
    INTERNAL_ERROR("일시적인 에러입니다.", HttpStatus.INTERNAL_SERVER_ERROR),
    SAVE_ERROR("저장 실패했습니다.", HttpStatus.INTERNAL_SERVER_ERROR);

    ErrorCode(String errorMessage, HttpStatus status) {
        this.errorMessage = errorMessage;
        this.status = status;
    }

    private String errorMessage;

    private HttpStatus status;

    public String getErrorMessage() {
        return this.errorMessage;
    }

    public HttpStatus getStatus() {
        return status;
    }
}