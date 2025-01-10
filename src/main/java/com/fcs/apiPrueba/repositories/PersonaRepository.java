package com.fcs.apiPrueba.repositories;

import com.fcs.apiPrueba.models.Persona;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonaRepository extends JpaRepository<Persona, Long> {

     List<Persona> findAllByOrderByIdAsc();

    @Query("Select P " +
            "from Persona P " +
            "where" +
            " lower(P.nombres) like lower(concat('%',?1,'%'))" +
            " or lower(P.apellidoPaterno) like lower(concat('%',?1,'%'))" +
            " or lower(P.apellidoMaterno) like lower(concat('%',?1,'%'))")
    public List<Persona> findAllCustomNames(String cadenaBuscar);

}
