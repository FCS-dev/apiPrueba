package com.fcs.apiPrueba.models.dto;

public record DeliveredPersonaDTO(
        Long idPersona,
        String nombreCompleto,
        java.time.LocalDate fechaNacimiento,
        String correo,
        Integer edad) {
}