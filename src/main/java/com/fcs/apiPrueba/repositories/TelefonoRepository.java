package com.fcs.apiPrueba.repositories;

import com.fcs.apiPrueba.models.Telefono;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface TelefonoRepository extends JpaRepository<Telefono, Long> {

    void deleteByPersonaId(Long personaId);
}
