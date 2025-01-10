package com.fcs.apiPrueba.repositories;

import com.fcs.apiPrueba.models.Telefono;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Repository
public interface TelefonoRepository extends JpaRepository<Telefono, Long> {

    @Modifying
    @Transactional
    @Query(value = "Delete from Telefono t where t.id=?1")
    void deleteById(Long id);

    void deleteByPersonaId(Long personaId);
}
