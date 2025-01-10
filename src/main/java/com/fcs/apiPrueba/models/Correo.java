package com.fcs.apiPrueba.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "correos")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Correo {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(length = 150)
    private String correo;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "personaId")
    private Persona persona;

    public Correo(
            String correo,
            Persona persona) {
        this.correo = correo;
        this.persona = persona;
    }

    public Correo(
            Long id,
            String correo) {
        this.id = id;
        this.correo = correo;
    }

}
