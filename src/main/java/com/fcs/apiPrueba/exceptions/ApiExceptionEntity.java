package com.fcs.apiPrueba.exceptions;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class ApiExceptionEntity extends RuntimeException {

    private String codigoError;
    private HttpStatus httpStatus;

    public ApiExceptionEntity(String message, String codigoError, HttpStatus httpStatus) {
        super(message);
        this.codigoError = codigoError;
        this.httpStatus = httpStatus;
    }
}
