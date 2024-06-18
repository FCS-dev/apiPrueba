package com.fcs.apiPrueba.utils;

import com.fcs.apiPrueba.models.Persona;
import com.fcs.apiPrueba.models.dto.DeliveredPersonaDTO;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class Mapper {

    public static Persona toPersona(
            String nombres,
            String apellidoPaterno,
            String apellidoMaterno,
            LocalDate fechaNacimiento,
            String correo,
            String password) {
        return new Persona(
                nombres,
                apellidoPaterno,
                apellidoMaterno,
                fechaNacimiento,
                correo,
                0,
                HashGenerator.generateHash(password));
    }

    public static DeliveredPersonaDTO toPersonaDTO(Persona persona) {
        return new DeliveredPersonaDTO(
                persona.getIdPersona(),
                persona.getNombres() + " " +
                        persona.getApellidoPaterno() + " " +
                        persona.getApellidoMaterno(),
                persona.getFechaNacimiento(),
                persona.getCorreo(),
                persona.getEdad());
    }

    public static List<DeliveredPersonaDTO> toListadoPersonaDTO(List<Persona> listadoPersonas) {
        return listadoPersonas.stream()
                .map(Mapper::toPersonaDTO)
                .collect(Collectors.toList());
    }
}
