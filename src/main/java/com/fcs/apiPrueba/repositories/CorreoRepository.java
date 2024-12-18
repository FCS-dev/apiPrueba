package com.fcs.apiPrueba.repositories;

import com.fcs.apiPrueba.models.Correo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CorreoRepository extends JpaRepository<Correo,Long> {
    void deleteByPersonaId(Long personaId);
}
