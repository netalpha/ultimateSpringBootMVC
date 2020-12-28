package com.example.demo.exceptionhandler.model;

import com.example.demo.exceptionhandler.exception.ServiceException;
import com.example.demo.exceptionhandler.exception.ServiceExceptionEnum;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ApiResponse {

    private Integer code;

    private String message;

    private Object data;

    public static ApiResponse ofSuccess(Object response) {
        return of(null, null, response);
    }

    public static <T extends ServiceException> ApiResponse ofKnownFailure(T t) {
        return of(t.getErrorEnum().getCode(), t.getErrorEnum().getMessage(), t.getDetails());
    }

    public static <T extends Exception> ApiResponse ofFatalFailure(T t) {
        return of(ServiceExceptionEnum.SYS_ERROR.getCode(), ServiceExceptionEnum.SYS_ERROR.getMessage(), t.getLocalizedMessage());
    }

    private static ApiResponse of(Integer code, String message, Object data) {
        return new ApiResponse(code, message, data);
    }
}
