package com.fcs.apiPrueba.models.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ErrorDTO {
    private String codigoError;
    private String mensaje;
}
