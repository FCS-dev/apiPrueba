package com.fcs.apiPrueba.services;

import com.fcs.apiPrueba.exceptions.ApiExceptionEntity;
import com.fcs.apiPrueba.models.Correo;
import com.fcs.apiPrueba.models.Persona;
import com.fcs.apiPrueba.models.Telefono;
import com.fcs.apiPrueba.models.dto.PersonaCrudDTO;
import com.fcs.apiPrueba.models.dto.PersonaDTO;
import com.fcs.apiPrueba.repositories.CorreoRepository;
import com.fcs.apiPrueba.repositories.PersonaRepository;
import com.fcs.apiPrueba.repositories.TelefonoRepository;
import com.fcs.apiPrueba.utils.HashGenerator;
import com.fcs.apiPrueba.utils.Mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class PersonaService {

    private final PersonaRepository personaRepository;
    private final TelefonoRepository telefonoRepository;
    private final CorreoRepository correoRepository;


    @Autowired
    public PersonaService(PersonaRepository personaRepository,
                          TelefonoRepository telefonoRepository,
                          CorreoRepository correoRepository
    ) {
        this.personaRepository = personaRepository;
        this.telefonoRepository = telefonoRepository;
        this.correoRepository = correoRepository;
    }

    // Find all
    public List<PersonaDTO> getAllPersonas() {
        List<Persona> listadoPersonas = personaRepository.findAllByOrderByIdAsc();
        return Mapper.toListadoPersonaDTO(listadoPersonas);
    }


    // Insert
    public PersonaDTO insertPersona(PersonaCrudDTO personaCrudDTO) {
        String password = HashGenerator.generateHash(personaCrudDTO.password());
        Persona persona = new Persona(
                personaCrudDTO.nombres(),
                personaCrudDTO.apellidoPaterno(),
                personaCrudDTO.apellidoMaterno(),
                personaCrudDTO.fechaNacimiento(),
                0,
                password
        );
        personaRepository.save(persona);
        personaCrudDTO.telefonos().forEach(telefono -> {
                    Telefono tlf = new Telefono(telefono.numeroTelefono(), persona);
                    telefonoRepository.save(tlf);
                }
        );
        personaCrudDTO.correos().forEach(correo -> {
                    Correo email = new Correo(correo.correo(), persona);
                    correoRepository.save(email);
                }
        );
        return Mapper.toPersonaDTO(personaCrudDTO);
    }

    // Update
    @Transactional
    public PersonaDTO updatePersona(Long idPersona, PersonaCrudDTO personaCrudDTO) {
        Optional<Persona> optPersona = personaRepository.findById(idPersona);
        if (optPersona.isEmpty()) {
            throw new ApiExceptionEntity(
                    "Persona con id " + idPersona + " no encontrada",
                    "BPNE",
                    HttpStatus.BAD_REQUEST);
        }

        List<Telefono> tlfsxVerificar = optPersona.get().getTelefonos();
        tlfsxVerificar.forEach(tlfEnBD -> {
            Long idGuardado = tlfEnBD.getId();
            boolean existe = false;
            existe = personaCrudDTO.telefonos().stream().anyMatch(idtlf -> Objects.equals(idtlf.idTelefono(), idGuardado));
            if (!existe) {
                telefonoRepository.deleteById(tlfEnBD.getId());
            }
        });
        List<Correo> correoxVerificar = optPersona.get().getCorreos();
        correoxVerificar.forEach(correoEnBD -> {
            Long idGuardado = correoEnBD.getId();
            boolean existe = false;
            existe = personaCrudDTO.correos().stream().anyMatch(idcorreo -> Objects.equals(idcorreo.idCorreo(), idGuardado));
            if (!existe) {
                correoRepository.deleteById(correoEnBD.getId());
            }
        });

        if (personaCrudDTO.telefonos().isEmpty()) {
            telefonoRepository.deleteByPersonaId(idPersona);
        } else {
            personaCrudDTO.telefonos().forEach(telefono -> {
                        boolean tlfEnBlanco = false;
                        boolean tlfNuevo = false;
                        if (telefono.numeroTelefono().isEmpty()) {
                            tlfEnBlanco = true;
                        }
                        if (telefono.idTelefono() == null || telefono.idTelefono() == 0) {
                            tlfNuevo = true;
                        }
                        if (tlfEnBlanco && tlfNuevo) {
                            throw new ApiExceptionEntity(
                                    "No se puede agregar un telefono sin datos", // le faltan ambos datos
                                    "BTEB",
                                    HttpStatus.BAD_REQUEST);
                        }
                        if (!(tlfEnBlanco || tlfNuevo)) {
                            Optional<Telefono> tlf = telefonoRepository.findById(telefono.idTelefono());
                            tlf.ifPresent(value -> value.setNumeroTelefono(telefono.numeroTelefono()));
                        } else {
                            if (tlfEnBlanco) {
                                telefonoRepository.deleteById(telefono.idTelefono());
                            }
                            if (tlfNuevo) {
                                Telefono tlf = new Telefono(telefono.numeroTelefono(), optPersona.get());
                                Telefono saveTelefono = telefonoRepository.save(tlf);
                            }
                        }
                    }
            );
        }
        if (personaCrudDTO.correos().isEmpty()) {
            correoRepository.deleteByPersonaId(optPersona.get().getId());
        } else {
            personaCrudDTO.correos().forEach(correo -> {
                        boolean correoEnBlanco = false;
                        boolean correoNuevo = false;
                        if (correo.correo().isEmpty()) {
                            correoEnBlanco = true;
                        }
                        if (correo.idCorreo() == null || correo.idCorreo() == 0) {
                            correoNuevo = true;
                        }
                        if (correoEnBlanco && correoNuevo) {
                            throw new ApiExceptionEntity(
                                    "No se puede agregar un correo sin datos", // le faltan ambos datos
                                    "BTEB",
                                    HttpStatus.BAD_REQUEST);
                        }
                        if (!(correoEnBlanco || correoNuevo)) {
                            Optional<Correo> corre = correoRepository.findById(correo.idCorreo());
                            corre.ifPresent(value -> value.setCorreo(correo.correo()));
                        } else {
                            if (correoEnBlanco) {
                                correoRepository.deleteById(correo.idCorreo());
                            }
                            if (correoNuevo) {
                                Correo corre = new Correo(correo.correo(), optPersona.get());
                                Correo saveCorreo = correoRepository.save(corre);
                            }
                        }
                    }
            );
        }
        Persona newPersona = optPersona.get();
        newPersona.setNombres(personaCrudDTO.nombres());
        newPersona.setApellidoPaterno(personaCrudDTO.apellidoPaterno());
        newPersona.setApellidoMaterno(personaCrudDTO.apellidoMaterno());
        newPersona.setFechaNacimiento(personaCrudDTO.fechaNacimiento());

        return Mapper.toPersonaDTO(personaCrudDTO);
    }

    // Find 1
//    public PersonaDTO getPersona(Long idPersona) {
//        Optional<Persona> personaOpt = personaRepository.findById(idPersona);
//        if (personaOpt.isPresent())
//            return Mapper.toPersonaDTO(personaOpt.get());
//        return null;
//    }

    // Find 1 UPD
    public PersonaCrudDTO getPersonaUpd(Long idPersona) {
        Optional<Persona> personaOpt = personaRepository.findById(idPersona);
        if (personaOpt.isPresent())
            return Mapper.toPersonaUpdDTO(personaOpt.get());
        return null;
    }

    // Delete
    public void deletePersona(Long idPersona) {
        Optional<Persona> persona = personaRepository.findById(idPersona);
        if (persona.isEmpty()) {
            throw new RuntimeException("Persona con id " + idPersona + " no existe");
        }
        personaRepository.deleteById(idPersona);
    }

    // Find with
    public List<PersonaDTO> getPersonasLike(String cadenaBuscar) {
        List<Persona> personasCustom = personaRepository.findAllCustomNames(cadenaBuscar);
        if (personasCustom.isEmpty()) {
            throw new RuntimeException("No hay personas que contengan el nombre: " + cadenaBuscar);
        }
        return Mapper.toListadoPersonaDTO(personasCustom);
    }

}
