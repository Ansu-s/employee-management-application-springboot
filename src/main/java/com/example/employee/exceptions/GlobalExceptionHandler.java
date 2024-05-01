package com.example.employee.exceptions;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(AppRuntimeException.class)
    @ResponseBody
    public ExceptionInfo resolveRuntimeExceptions(AppRuntimeException appRuntimeException) {
        log.error("Error caused at: ", appRuntimeException);
        String errorMessage = appRuntimeException.getMessage();
        ExceptionInfo exceptionInfo = new ExceptionInfo();
        exceptionInfo.setExceptionMessage(errorMessage);
        return exceptionInfo;
    }
}
