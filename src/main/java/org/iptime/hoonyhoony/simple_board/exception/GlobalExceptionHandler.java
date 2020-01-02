package org.iptime.hoonyhoony.simple_board.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {
    //custom exception 처리
    @ExceptionHandler(CustomException.class)
    @ResponseBody
    public Map handleCustomException(CustomException e, HttpServletResponse response, HttpServletRequest request) {
        response.setStatus(e.getErrorCode().getStatus().value());
        HashMap<String, String> message = new HashMap<>();
        message.put("message", e.getErrorCode().getErrorMessage());
        return message;
    }

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Map handleException(Exception e, HttpServletResponse response, HttpServletRequest request) {
        HashMap<String, String> message = new HashMap<>();
        message.put("message", ErrorCode.INTERNAL_ERROR.getErrorMessage());
        return message;
    }
}
