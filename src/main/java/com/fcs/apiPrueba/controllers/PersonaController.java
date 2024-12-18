package com.fcs.apiPrueba.controllers;

import com.fcs.apiPrueba.exceptions.ApiExceptionEntity;
import com.fcs.apiPrueba.models.dto.PersonaCrudDTO;
import com.fcs.apiPrueba.models.dto.PersonaDTO;
import com.fcs.apiPrueba.services.PersonaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/v1/personas")
@CrossOrigin(origins = "*")
public class PersonaController {

    private final PersonaService personaService;

    @Autowired
    public PersonaController(PersonaService personaService) {
        this.personaService = personaService;
    }

    // Find all
    @GetMapping("/")
    public ResponseEntity<List<PersonaDTO>> getAllPersonas() {
        String msg = null;
        List<PersonaDTO> listado = null;
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


    // Insert. ok? PersonaDTO : ApiExceptionEntity
    @PostMapping(value="/",consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity newPersona(@RequestBody PersonaCrudDTO personaCrudDTO) {
        if (personaCrudDTO.nombres() == null || personaCrudDTO.nombres().isBlank())
            throw new ApiExceptionEntity("VPN", "'NOMBRES' no válido", HttpStatus.BAD_REQUEST);
        if (personaCrudDTO.apellidoPaterno() == null || personaCrudDTO.apellidoPaterno().isBlank())
            throw new ApiExceptionEntity("VPAP", "'APELLIDO PATERNO' no válido", HttpStatus.BAD_REQUEST);
        if (personaCrudDTO.apellidoMaterno() == null || personaCrudDTO.apellidoMaterno().isBlank())
            throw new ApiExceptionEntity("VPAM", "'APELLIDO MATERNO' no válido", HttpStatus.BAD_REQUEST);
        if (personaCrudDTO.fechaNacimiento() == null)
            throw new ApiExceptionEntity("VPFN", "'FECHA DE NACIMIENTO' no válido", HttpStatus.BAD_REQUEST);
        if (personaCrudDTO.password() == null || personaCrudDTO.password().isBlank())
            throw new ApiExceptionEntity("VPP", "'PASSWORD' no válido", HttpStatus.BAD_REQUEST);
        return ResponseEntity.status(HttpStatus.CREATED).body(personaService.insertPersona(personaCrudDTO));
    }

    // Update
    @PutMapping(path = "/{idPersona}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public PersonaDTO updPersona(@PathVariable("idPersona") Long idPersona, @RequestBody PersonaCrudDTO personaCrudDTO) {
        return personaService.updatePersona(idPersona, personaCrudDTO);
    }

    // Delete
    @DeleteMapping("/{idPersona}")
    public void deletePersona(@PathVariable("idPersona") Long idPersona) {
        personaService.deletePersona(idPersona);
    }

    // Find with
    @GetMapping(value = "/buscar/{cadenaBuscar}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<PersonaDTO> getPersonasCustom(@PathVariable("cadenaBuscar") String cadenaBuscar) {
        return personaService.getPersonasLike(cadenaBuscar);
    }


    // Find 1 for UPD
    @GetMapping(value = "/{idPersona}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PersonaCrudDTO> getPersonaUpd(@PathVariable("idPersona") Long idPersona) {
        String msg = null;
        PersonaCrudDTO personaCrudDTO = null;
        HttpStatus httpStatus = HttpStatus.OK;
        try {
            personaCrudDTO = personaService.getPersonaUpd(idPersona);
            if (personaCrudDTO == null) {
                msg = "No existe el id " + idPersona + " en la Base de Datos.";
                httpStatus = HttpStatus.BAD_REQUEST;
            }
        } catch (RuntimeException e) {
            msg = e.getMessage();
        }
        return ResponseEntity.status(httpStatus).body(personaCrudDTO);
    }
}