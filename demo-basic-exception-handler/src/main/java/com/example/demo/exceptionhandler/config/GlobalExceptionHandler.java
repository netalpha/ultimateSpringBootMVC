package com.example.demo.exceptionhandler.config;

import com.example.demo.exceptionhandler.exception.ServiceException;
import com.example.demo.exceptionhandler.exception.ServiceExceptionEnum;
import com.example.demo.exceptionhandler.model.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.stream.Collectors;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(value = ServiceException.class)
    public ApiResponse serviceExceptionHandler(HttpServletResponse response, ServiceException ex) {
        log.debug("[serviceExceptionHandler]: {}", ex.toString());
        if (ex.isSystemException()) {
            response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        } else {
            response.setStatus(HttpStatus.BAD_REQUEST.value());
        }
        return ApiResponse.ofKnownFailure(ex);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiResponse constraintViolationException(ConstraintViolationException ex) {
        log.debug("[constraintViolationExceptionHandler]: {}", ex.toString());
        var detailedMsg = ex.getConstraintViolations().stream().map(ConstraintViolation::getMessage).collect(Collectors.joining(";"));
        return ApiResponse.ofKnownFailure(new ServiceException(ServiceExceptionEnum.BAD_REQUEST_PARAM_ERROR, detailedMsg));
    }

    /**
     * The ultimate exception handler in case of system error.
     *
     * @param ex the exception
     * @return the response
     */
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ApiResponse exceptionHandler(Exception ex) {
        log.error("[exceptionHandler]: {}", ex.toString());
        return ApiResponse.ofFatalFailure(ex);
    }
}
