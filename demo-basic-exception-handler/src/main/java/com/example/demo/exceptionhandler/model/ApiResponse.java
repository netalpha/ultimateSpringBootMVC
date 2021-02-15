package com.example.demo.exceptionhandler.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.util.Assert;

import java.io.Serializable;
import java.util.Objects;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class ApiResponse implements Serializable {

    private Integer code;

    private String message;

    private Object data;

    public static ApiResponse success(final Object response) {
        return of(null, null, response);
    }

    public static ApiResponse error(final Integer code, final String message) {
        Assert.isTrue(Objects.nonNull(code), "code must not be null!");
        return of(code, message, null);
    }

    public static ApiResponse error(final Integer code, final String message, final Object data) {
        return of(code, message, data);
    }

    private static ApiResponse of(final Integer code, final String message, final Object data) {
        return new ApiResponse(code, message, data);
    }
}
