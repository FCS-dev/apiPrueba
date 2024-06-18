package com.fcs.apiPrueba.controllers;

import com.fcs.apiPrueba.exceptions.ApiExceptionEntity;
import com.fcs.apiPrueba.models.dto.DeliveredPersonaDTO;
import com.fcs.apiPrueba.models.dto.ReceivedPersonaDTO;
import com.fcs.apiPrueba.services.PersonaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/personas")
public class PersonaController {

    private final PersonaService personaService;

    @Autowired
    public PersonaController(PersonaService personaService) {
        this.personaService = personaService;
    }

    // Find all
    @GetMapping("/")
    public ResponseEntity<List<DeliveredPersonaDTO>> getAllPersonas() {
        String msg = null;
        List<DeliveredPersonaDTO> listado = null;
        HttpStatus httpStatus = HttpStatus.OK;
        try {
            listado = personaService.getAllPersonas();
            if (listado.isEmpty()) {
                msg = "";
                httpStatus = HttpStatus.NO_CONTENT;
            }
        } catch (RuntimeException e) {
            httpStatus = HttpStatus.BAD_REQUEST;
            msg = e.getMessage();
        }
        return ResponseEntity.status(httpStatus).body(listado);
    }

    // Find 1
    @GetMapping(value = "/{idPersona}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<DeliveredPersonaDTO> get1Persona(@PathVariable("idPersona") Long idPersona) {
        String msg = null;
        DeliveredPersonaDTO deliveredPersonaDTO = null;
        HttpStatus httpStatus = HttpStatus.OK;
        try {
            deliveredPersonaDTO = personaService.getPersona(idPersona);
            if (deliveredPersonaDTO == null) {
                msg = "No existe el id " + idPersona + " en la Base de Datos.";
                httpStatus = HttpStatus.BAD_REQUEST;
            }
        } catch (RuntimeException e) {
            msg = e.getMessage();
        }
        return ResponseEntity.status(httpStatus).body(deliveredPersonaDTO);
        //return personaService.getPersona(idPersona);
        //return null;
    }

    // Insert
    @PostMapping(path = "/", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity newPersona(@RequestBody ReceivedPersonaDTO receivedPersonaDTO) {
        if (receivedPersonaDTO.nombres() == null || receivedPersonaDTO.nombres().isBlank())
            throw new ApiExceptionEntity("VPN", "'NOMBRES' no válido", HttpStatus.BAD_REQUEST);
        if (receivedPersonaDTO.apellidoPaterno() == null || receivedPersonaDTO.apellidoPaterno().isBlank())
            throw new ApiExceptionEntity("VPAP", "'APELLIDO PATERNO' no válido", HttpStatus.BAD_REQUEST);
        if (receivedPersonaDTO.apellidoMaterno() == null || receivedPersonaDTO.apellidoMaterno().isBlank())
            throw new ApiExceptionEntity("VPAM", "'APELLIDO MATERNO' no válido", HttpStatus.BAD_REQUEST);
        if (receivedPersonaDTO.fechaNacimiento() == null)
            throw new ApiExceptionEntity("VPFN", "'FECHA DE NACIMIENTO' no válido", HttpStatus.BAD_REQUEST);
        if (receivedPersonaDTO.correo() == null || receivedPersonaDTO.correo().isBlank())
            throw new ApiExceptionEntity("VPC", "'CORREO' no válido", HttpStatus.BAD_REQUEST);
        if (receivedPersonaDTO.password() == null || receivedPersonaDTO.password().isBlank())
            throw new ApiExceptionEntity("VPP", "'PASSWORD' no válido", HttpStatus.BAD_REQUEST);
        return ResponseEntity.status(HttpStatus.CREATED).body(personaService.insertPersona(receivedPersonaDTO));
    }

    // Update
    @PutMapping(path = "/{idPersona}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public DeliveredPersonaDTO updPersona(@PathVariable("idPersona") Long idPersona, @RequestBody ReceivedPersonaDTO receivedPersonaDTO) {
        return personaService.updatePersona(idPersona, receivedPersonaDTO);
    }

    // Delete
    @DeleteMapping("/{idPersona}")
    public void deletePersona(@PathVariable("idPersona") Long idPersona) {
        personaService.deletePersona(idPersona);
    }

    // Find with
    @GetMapping(value = "custom/{cadenaBuscar}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<DeliveredPersonaDTO> getPersonasCustom(@PathVariable("cadenaBuscar") String cadenaBuscar) {
        return personaService.getPersonasLike(cadenaBuscar);
    }
}

