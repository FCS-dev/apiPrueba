package com.fcs.apiPrueba.config;


import com.fcs.apiPrueba.models.Persona;
import com.fcs.apiPrueba.repositories.PersonaRepository;
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
    CommandLineRunner runner(PersonaRepository personaRepository) {
        return args -> {
            Persona p1 = new Persona(
                    "Franco Paolo",
                    "Calderon",
                    "Sanchez",
                    LocalDate.of(1975, Month.OCTOBER, 3),
                    "fcalderon@gmail.com",
                    0,
                    HashGenerator.generateHash("franco"));
            Persona p2 = new Persona(
                    "Celinda Betty",
                    "Gamboa",
                    "Castro",
                    LocalDate.of(1970, Month.MARCH, 9),
                    "celinda@gmail.com",
                    0,
                    HashGenerator.generateHash("celinda"));
            Persona p3 = new Persona(
                    "Diego Miguel",
                    "Calderon",
                    "Gamboa",
                    LocalDate.of(2002, Month.JUNE, 11),
                    "diego@gmail.com",
                    0,
                    HashGenerator.generateHash("diego"));
            Persona p4 = new Persona(
                    "Rodrigo Fernando",
                    "Calderon",
                    "Gamboa",
                    LocalDate.of(2004, Month.OCTOBER, 10),
                    "roro@gmail.com",
                    0,
                    HashGenerator.generateHash("rodrigo"));
            personaRepository.saveAll(List.of(p1, p2, p3, p4));
        };
    }
}
