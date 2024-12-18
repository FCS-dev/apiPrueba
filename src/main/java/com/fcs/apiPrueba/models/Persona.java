package com.fcs.apiPrueba.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;

@Entity
@Table(name = "personas")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Persona {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(nullable = false, length = 100)
    private String nombres;
    @Column(nullable = false, length = 100)
    private String apellidoPaterno;
    @Column(nullable = false, length = 100)
    private String apellidoMaterno;
    @Column(nullable = false)
    private java.time.LocalDate fechaNacimiento;
    @Transient
    private Integer edad;
    @Column(nullable = false, length = 60)
    private String password;
    @OneToMany(mappedBy = "persona", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Correo> Correos;
    @OneToMany(mappedBy = "persona", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Telefono> Telefonos;


    public Persona(
            String nombres,
            String apellidoPaterno,
            String apellidoMaterno,
            LocalDate fechaNacimiento,
            Integer edad,
            List<Telefono> telefonos,
            List<Correo> correos
    ) {
        this.nombres = nombres;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
        this.fechaNacimiento = fechaNacimiento;
        this.edad = edad;
        this.password = password;
        this.Telefonos = telefonos;
        this.Correos = correos;
    }

    public Persona(
            String nombres,
            String apellidoPaterno,
            String apellidoMaterno,
            LocalDate fechaNacimiento,
            Integer edad,
            String password) {
        this.nombres = nombres;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
        this.fechaNacimiento = fechaNacimiento;
        this.edad = edad;
        this.password = password;
    }

    public Integer getEdad() {
        return Period.between(this.fechaNacimiento, LocalDate.now()).getYears();
    }

    @Override
    public String toString() {
        return "Persona{" +
                "id=" + id +
                ", nombres='" + nombres + '\'' +
                ", apellidoPaterno='" + apellidoPaterno + '\'' +
                ", apellidoMaterno='" + apellidoMaterno + '\'' +
                ", fechaNacimiento=" + fechaNacimiento +
                ", edad=" + edad +
                ", password='" + password + '\'' +
                ", Correos=" + Correos +
                ", Telefonos=" + Telefonos +
                '}';
    }
}
