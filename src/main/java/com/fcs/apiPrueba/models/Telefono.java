package com.fcs.apiPrueba.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "telefonos")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Telefono {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(length = 20)
    private String numeroTelefono;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "personaId")
    private Persona persona;

    public Telefono(
            String numeroTelefono,
            Persona persona) {
        this.numeroTelefono = numeroTelefono;
        this.persona = persona;
    }

    public Telefono(
            Long id,
            String numeroTelefono) {
        this.id = id;
        this.numeroTelefono = numeroTelefono;
    }

}
