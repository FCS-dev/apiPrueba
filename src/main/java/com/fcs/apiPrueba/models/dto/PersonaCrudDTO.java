package com.fcs.apiPrueba.models.dto;

import java.util.List;

public record PersonaCrudDTO(
        Long idPersona,
        String nombres,
        String apellidoPaterno,
        String apellidoMaterno,
        java.time.LocalDate fechaNacimiento,
        String password,
        List<TelefonoDTO> telefonos,
        List<CorreoDTO> correos) {
}
