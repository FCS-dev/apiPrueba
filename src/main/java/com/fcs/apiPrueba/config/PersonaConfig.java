package com.fcs.apiPrueba.config;


import com.fcs.apiPrueba.models.Correo;
import com.fcs.apiPrueba.models.Persona;
import com.fcs.apiPrueba.models.Telefono;
import com.fcs.apiPrueba.repositories.CorreoRepository;
import com.fcs.apiPrueba.repositories.PersonaRepository;
import com.fcs.apiPrueba.repositories.TelefonoRepository;
import com.fcs.apiPrueba.utils.HashGenerator;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Configuration
public class PersonaConfig {
    @Bean
    CommandLineRunner runner(PersonaRepository personaRepository, TelefonoRepository telefonoRepository,
                             CorreoRepository correoRepository) {
        return args -> {
            Persona p1 = new Persona(
                    "Franco Paolo",
                    "Calderon",
                    "Sanchez",
                    LocalDate.of(1975, Month.OCTOBER, 3),
                    0,
                    HashGenerator.generateHash("franco"));
            Persona p2 = new Persona(
                    "Celinda Betty",
                    "Gamboa",
                    "Castro",
                    LocalDate.of(1970, Month.MARCH, 9),
                    0,
                    HashGenerator.generateHash("celinda"));
            Persona p3 = new Persona(
                    "Diego Miguel",
                    "Calderon",
                    "Gamboa",
                    LocalDate.of(2002, Month.JUNE, 11),
                    0,
                    HashGenerator.generateHash("diego"));
            Persona p4 = new Persona(
                    "Rodrigo Fernando",
                    "Calderon",
                    "Gamboa",
                    LocalDate.of(2004, Month.OCTOBER, 10),
                    0,
                    HashGenerator.generateHash("rodrigo"));
            Correo c1 = new Correo("fcalderon@gmail.com",p1);
            Correo c2 = new Correo("celinda@gmail.com",p2);
            Correo c3 = new Correo("diego@gmail.com",p3);
            Correo c4 = new Correo("roro@gmail.com",p4);
            Telefono t1 = new Telefono("643797492", p1);
            Telefono t2 = new Telefono("997107144", p1);
            personaRepository.saveAll(List.of(p1, p2, p3, p4));
            telefonoRepository.saveAll(List.of(t1, t2));
            correoRepository.saveAll(List.of(c1, c2, c3, c4));

        };
    }
}
