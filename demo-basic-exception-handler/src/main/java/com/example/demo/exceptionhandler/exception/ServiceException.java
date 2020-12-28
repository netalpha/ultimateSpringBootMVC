package com.example.demo.exceptionhandler.exception;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class ServiceException extends RuntimeException {

    /**
     * The error enum
     */
    private ServiceExceptionEnum errorEnum;

    /**
     * The details of this error
     */
    private String details;

    public boolean isSystemException() {
        return this.errorEnum.isSystemError();
    }

    public boolean isBusinessException() {
        return !isSystemException();
    }
}