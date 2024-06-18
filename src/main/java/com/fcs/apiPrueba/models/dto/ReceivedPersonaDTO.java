package com.fcs.apiPrueba.models.dto;

public record ReceivedPersonaDTO(
        String nombres,
        String apellidoPaterno,
        String apellidoMaterno,
        java.time.LocalDate fechaNacimiento,
        String correo,
        String password) {
}
