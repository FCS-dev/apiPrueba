package com.fcs.apiPrueba.repositories;

import com.fcs.apiPrueba.models.Correo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface CorreoRepository extends JpaRepository<Correo, Long> {

    @Modifying
    @Transactional
    @Query(value = "Delete from Correo c where c.id=?1")
    void deleteById(Long id);

    void deleteByPersonaId(Long personaId);
}
