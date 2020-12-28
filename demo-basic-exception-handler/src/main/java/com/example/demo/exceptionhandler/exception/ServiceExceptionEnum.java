package com.example.demo.exceptionhandler.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ServiceExceptionEnum {
    /**
     * Code Pattern: A_BBB_CCC_DDD
     * A: [1|2] -> 1: Business error; 2: System error
     * BBB: 001-999 -> MicroService Type
     * CCC: 001-999 -> Module Type
     * DDD: 000-999 -> Self increment
     */

    /**
     * System related errors.
     */
    SYS_ERROR(2001001000, "internal system error"),
    EXTERNAL_SERVICE_ERROR(2001001001, "error happens when calling external services."),

    /**
     * Business related errors.
     */
    BAD_REQUEST_PARAM_ERROR(1001001001, "request parameter invalid.");

    /**
     * The error code
     */
    private int code;

    /**
     * The error message
     */
    private String message;

    public boolean isSystemError() {
        return String.valueOf(code).startsWith("2");
    }
}
