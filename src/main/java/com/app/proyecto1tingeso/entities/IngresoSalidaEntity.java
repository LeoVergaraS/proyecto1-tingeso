package com.app.proyecto1tingeso.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Time;

@Entity
@Table(name = "ingresos_salidas")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class IngresoSalidaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;

    private Date fecha;
    private Time hora_ingreso;
    private Time hora_salida;
    private String rut_empleado;
}