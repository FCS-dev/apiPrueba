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
            personaRepository.saveAll(List.of(p1, p2));
        };
    }

    ;

}
