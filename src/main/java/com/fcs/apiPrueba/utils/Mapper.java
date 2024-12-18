package com.fcs.apiPrueba.utils;

import com.fcs.apiPrueba.models.Correo;
import com.fcs.apiPrueba.models.Persona;
import com.fcs.apiPrueba.models.Telefono;
import com.fcs.apiPrueba.models.dto.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Mapper {

    public static PersonaDTO toPersonaDTO(Persona persona) {
        return new PersonaDTO(
                persona.getId(),
                persona.getNombres() + " " +
                        persona.getApellidoPaterno() + " " +
                        persona.getApellidoMaterno(),
                persona.getFechaNacimiento(),
                toTelefonoDTO(persona.getTelefonos()),
                toCorreoDTO(persona.getCorreos()),
                persona.getEdad());
    }

    public static PersonaDTO toPersonaDTO(PersonaCrudDTO personaCrudDTO) {
        return new PersonaDTO(
                null,
                personaCrudDTO.nombres() + " " +
                        personaCrudDTO.apellidoPaterno() + " " +
                        personaCrudDTO.apellidoMaterno(),
                personaCrudDTO.fechaNacimiento(),
                personaCrudDTO.telefonos(),
                personaCrudDTO.correos(),
                null);
    }

    public static PersonaCrudDTO toPersonaUpdDTO(Persona persona) {
        return new PersonaCrudDTO(
                persona.getId(),
                persona.getNombres(),
                persona.getApellidoPaterno(),
                persona.getApellidoMaterno(),
                persona.getFechaNacimiento(),
                null,
                toTelefonoDTO(persona.getTelefonos()),
                toCorreoDTO(persona.getCorreos()));
    }

    public static List<PersonaDTO> toListadoPersonaDTO(List<Persona> listadoPersonas) {
        return listadoPersonas.stream()
                .map(Mapper::toPersonaDTO)
                .collect(Collectors.toList());
    }

    public static List<TelefonoDTO> toTelefonoDTO(List<Telefono> telefonos) {
        List<TelefonoDTO> sinTelefonos = new ArrayList<>();
        return (telefonos == null || telefonos.isEmpty())
                ? sinTelefonos
                : telefonos
                .stream()
                .map(telefono ->
                        new TelefonoDTO(telefono.getId(), telefono.getNumeroTelefono()))
                .collect(Collectors.toList());
    }

    public static List<CorreoDTO> toCorreoDTO(List<Correo> correos) {
        List<CorreoDTO> sinCorreos = new ArrayList<>();
        return (correos == null || correos.isEmpty())
                ? sinCorreos
                : correos.stream()
                .map(correo ->
                        new CorreoDTO(correo.getId(), correo.getCorreo()))
                .collect(Collectors.toList());
    }
}
