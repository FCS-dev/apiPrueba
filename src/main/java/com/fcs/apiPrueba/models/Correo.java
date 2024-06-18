package com.fcs.apiPrueba.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "correos")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Correo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idCorreo;
    @Column(nullable = false, length = 150)
    private String correo;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idPersona")
    private Persona persona;

}
