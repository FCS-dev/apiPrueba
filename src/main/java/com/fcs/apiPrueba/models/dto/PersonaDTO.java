package com.fcs.apiPrueba.models.dto;

import java.util.List;

public record PersonaDTO(
        Long id,
        String nombreCompleto,
        java.time.LocalDate fechaNacimiento,
        List<TelefonoDTO> telefonos,
        List<CorreoDTO> correos,
        Integer edad) {
}
