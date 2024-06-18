package com.fcs.apiPrueba.services;

import com.fcs.apiPrueba.exceptions.ApiExceptionEntity;
import com.fcs.apiPrueba.models.Persona;
import com.fcs.apiPrueba.models.dto.DeliveredPersonaDTO;
import com.fcs.apiPrueba.models.dto.ReceivedPersonaDTO;
import com.fcs.apiPrueba.repositories.PersonaRepository;
import com.fcs.apiPrueba.utils.HashGenerator;
import com.fcs.apiPrueba.utils.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class PersonaService {

    private final PersonaRepository personaRepository;

    @Autowired
    public PersonaService(PersonaRepository personaRepository) {
        this.personaRepository = personaRepository;
    }


    // Find all
    public List<DeliveredPersonaDTO> getAllPersonas() {
        List<Persona> listadoPersonas = personaRepository.findAllByOrderByIdPersonaAsc();
        return Mapper.toListadoPersonaDTO(listadoPersonas);
    }

    // Insert
    public DeliveredPersonaDTO insertPersona(ReceivedPersonaDTO receivedPersonaDTO) {
        String password = HashGenerator.generateHash(receivedPersonaDTO.password());
        Persona persona = new Persona(
                receivedPersonaDTO.nombres(),
                receivedPersonaDTO.apellidoPaterno(),
                receivedPersonaDTO.apellidoMaterno(),
                receivedPersonaDTO.fechaNacimiento(),
                receivedPersonaDTO.correo(),
                0,
                password);
        personaRepository.save(persona);
        return Mapper.toPersonaDTO(persona);
    }

    // Update
    @Transactional
    public DeliveredPersonaDTO updatePersona(Long idPersona, ReceivedPersonaDTO receivedPersonaDTO) {
        Optional<Persona> oldPersona = personaRepository.findById(idPersona);
        if (oldPersona.isEmpty()) {
            throw new ApiExceptionEntity(
                    "Persona con id " + idPersona + " no encontrada",
                    "BPNE",
                    HttpStatus.BAD_REQUEST);
        }
        oldPersona.get().setNombres(receivedPersonaDTO.nombres());
        oldPersona.get().setApellidoPaterno(receivedPersonaDTO.apellidoPaterno());
        oldPersona.get().setApellidoMaterno(receivedPersonaDTO.apellidoMaterno());
        oldPersona.get().setFechaNacimiento(receivedPersonaDTO.fechaNacimiento());
        oldPersona.get().setCorreo(receivedPersonaDTO.correo());
        oldPersona.get().setPassword(HashGenerator.generateHash(receivedPersonaDTO.password()));
        return Mapper.toPersonaDTO(oldPersona.get());
    }

    // Find 1
    public DeliveredPersonaDTO getPersona(Long idPersona) {
        Optional<Persona> personaOpt = personaRepository.findById(idPersona);
        //return Mapper.toPersonaDTO(persona.get());
        //return Mapper.toPersonaDTO(personaRepository.findById(idPersona).get());
        if (personaOpt.isPresent())
            return Mapper.toPersonaDTO(personaOpt.get());
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
    public List<DeliveredPersonaDTO> getPersonasLike(String cadenaBuscar) {
        List<Persona> personasCustom = personaRepository.findAllCustomNames(cadenaBuscar);
        if (personasCustom.isEmpty()) {
            throw new RuntimeException("No hay personas que contengan el nombre: " + cadenaBuscar);
        }
        //return Mapper.toPersonaDTO(personasCustom.get());
        return Mapper.toListadoPersonaDTO(personasCustom);
    }

    ;
}
