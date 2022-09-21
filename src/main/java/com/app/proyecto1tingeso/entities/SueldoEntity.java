package com.app.proyecto1tingeso.entities;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "sueldo")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SueldoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;

    private String rut;
    private int sueldo_fijo_mensual;
    private double bonificacion_tiempo_servicio;
    private double pago_horas_extras;
    private double descuentos_atrasos;
    private double sueldo_bruto;
    private double descuentos_previsional;
    private double descuentos_salud;
    private double sueldo_final;
    private int mes;
    private int anio;
}
