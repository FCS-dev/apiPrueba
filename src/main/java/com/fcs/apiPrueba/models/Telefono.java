package com.fcs.apiPrueba.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "telefonos")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Telefono {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idTelefono;
    @Column(nullable = false, length = 20)
    private String numeroTelefono;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idPersona")
    private Persona persona;

}
