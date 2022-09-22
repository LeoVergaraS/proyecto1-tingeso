package com.app.proyecto1tingeso.controllers;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.app.proyecto1tingeso.entities.EmpleadoEntity;
import com.app.proyecto1tingeso.entities.HoraExtraEntity;
import com.app.proyecto1tingeso.entities.InasistenciaEntity;
import com.app.proyecto1tingeso.entities.SueldoEntity;
import com.app.proyecto1tingeso.services.EmpleadoService;
import com.app.proyecto1tingeso.services.HoraExtraService;
import com.app.proyecto1tingeso.services.InasistenciaService;
import com.app.proyecto1tingeso.services.RRHHService;
import com.app.proyecto1tingeso.services.SueldoService;

@Controller
@RequestMapping("/sueldos")
public class SueldoController {
    
    @Autowired
    SueldoService sueldoService;

    @Autowired
    RRHHService rrhhService;

    @Autowired
    EmpleadoService empleadoService;

    @Autowired
    HoraExtraService horaExtraService;

    @Autowired
    InasistenciaService inasistenciaService;

    @GetMapping("/calcular")
    public String calcular(){
        ArrayList<EmpleadoEntity> empleados = empleadoService.obtenerEmpleados();
        ArrayList<HoraExtraEntity> horasExtras = horaExtraService.obtenerHorasExtras();
        ArrayList<InasistenciaEntity> inasistencias = inasistenciaService.obtenerInasistencias();
        double horaExtra = 0;

        for(EmpleadoEntity e:empleados){
            for(HoraExtraEntity h:horasExtras){
                if(h.getRut_empleado().equals(e.getRut())){
                    horaExtra = h.getCantidad_horas_extras();
                }
            }
            int sueldoMensualFijo = rrhhService.sueldoMensualFijoEmpleado(e);
            double bonificacionTiempoServicio = rrhhService.bonificacionTiempoServicio(e, sueldoMensualFijo);
            double pagoHorasExtras = rrhhService.montoHorasExtrasEmpleado(e, horaExtra);
            //double descuentoPorAtraso = rrhhService.descuentoPorAtraso(tiempoAtraso, sueldoMensualFijo);
            double sueldoBruto = sueldoMensualFijo + bonificacionTiempoServicio + pagoHorasExtras;
            double descuentoPrevisional = rrhhService.descuentosPrevisional(sueldoBruto);
            double descuentoSalud = rrhhService.descuentoSalud(sueldoBruto);
            double sueldoFinal = sueldoBruto - descuentoPrevisional - descuentoSalud;
            SueldoEntity sueldo = new SueldoEntity(null, e.getRut(), sueldoMensualFijo, bonificacionTiempoServicio, pagoHorasExtras, 0, sueldoBruto, descuentoPrevisional, descuentoSalud, sueldoFinal, 0, 0);
            sueldoService.guardarSueldo(sueldo);
        }

        return "redirect:/";
    }
}
