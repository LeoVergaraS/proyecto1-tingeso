package com.app.proyecto1tingeso.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "horas_extras")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class HoraExtraEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;

    private int mes;
    private int anio;
    private double cantidad_horas_extras;
    private int autorizado=0;
    private String rut_empleado;
}
