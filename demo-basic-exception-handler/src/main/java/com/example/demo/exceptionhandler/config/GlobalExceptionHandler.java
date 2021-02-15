package com.example.demo.exceptionhandler.config;

import com.example.demo.exceptionhandler.exception.ServiceException;
import com.example.demo.exceptionhandler.exception.ServiceExceptionEnum;
import com.example.demo.exceptionhandler.model.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(value = ServiceException.class)
    public ApiResponse serviceExceptionHandler(final HttpServletResponse response, final ServiceException ex) {
        if (ex.isSystemException()) {
            response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
            log.error("[serviceExceptionHandler]: {}", ex.getDetails(), ex);
        } else {
            response.setStatus(HttpStatus.BAD_REQUEST.value());
            log.info("[serviceExceptionHandler]: {}", ex.getDetails());
        }
        return ApiResponse.error(ex.getErrorEnum().getCode(), ex.getErrorEnum().getMessage(), ex.getDetails());
    }

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiResponse constraintViolationException(ConstraintViolationException ex) {
        log.info("[constraintViolationExceptionHandler]: {}", ex.toString());
        var detailedMsg = ex.getConstraintViolations().stream().map(ConstraintViolation::getMessage).collect(Collectors.joining(";"));
        return ApiResponse
            .error(ServiceExceptionEnum.BAD_REQUEST_PARAM_ERROR.getCode(), ServiceExceptionEnum.BAD_REQUEST_PARAM_ERROR.getMessage(), detailedMsg);
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiResponse bindExceptionHandler(final MethodArgumentNotValidException ex) {
        log.info("[bindExceptionHandler]: {}", ex.getMessage());
        final List<String> errors = new ArrayList<>();
        for (final FieldError error : ex.getBindingResult().getFieldErrors()) {
            errors.add(error.getField() + ": " + error.getDefaultMessage());
        }
        for (final ObjectError error : ex.getBindingResult().getGlobalErrors()) {
            errors.add(error.getObjectName() + ": " + error.getDefaultMessage());
        }

        return ApiResponse
            .error(ServiceExceptionEnum.BAD_REQUEST_PARAM_ERROR.getCode(), ServiceExceptionEnum.BAD_REQUEST_PARAM_ERROR.getMessage(), errors.toString());
    }

    /**
     * The ultimate exception handler in case of system error.
     *
     * @param ex the exception
     * @return the response
     */
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ApiResponse exceptionHandler(final Exception ex) {
        log.error("[exceptionHandler]: {}", ex.getMessage(), ex);
        return ApiResponse.error(ServiceExceptionEnum.SYS_ERROR.getCode(), ServiceExceptionEnum.SYS_ERROR.getMessage(), ex.getMessage());
    }
}
