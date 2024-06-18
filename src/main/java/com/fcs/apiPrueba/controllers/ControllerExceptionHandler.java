package com.fcs.apiPrueba.controllers;

import com.fcs.apiPrueba.exceptions.ApiExceptionEntity;
import com.fcs.apiPrueba.models.dto.ErrorDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(value = ApiExceptionEntity.class)
    public ResponseEntity<ErrorDTO> apiValidationExceptionHandler(ApiExceptionEntity e) {
        ErrorDTO errorDTO = ErrorDTO.builder()
                .codigoError(e.getCodigoError())
                .mensaje(e.getMessage())
                .build();
        return new ResponseEntity<>(errorDTO, e.getHttpStatus());
    }
}
