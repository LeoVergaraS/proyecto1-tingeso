package com.app.proyecto1tingeso.controllers;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.app.proyecto1tingeso.entities.EmpleadoEntity;
import com.app.proyecto1tingeso.entities.HoraExtraEntity;
import com.app.proyecto1tingeso.entities.InasistenciaEntity;
import com.app.proyecto1tingeso.entities.SueldoEntity;
import com.app.proyecto1tingeso.services.AtrasoService;
import com.app.proyecto1tingeso.services.EmpleadoService;
import com.app.proyecto1tingeso.services.HoraExtraService;
import com.app.proyecto1tingeso.services.InasistenciaService;
import com.app.proyecto1tingeso.services.IngresoSalidaService;
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

    @Autowired
    IngresoSalidaService ingresoSalidaService;

    @Autowired
    AtrasoService atrasoService;

    @GetMapping("/calcular")
    public String calcular(@RequestParam("mesanio") String mesyanio){
        if(!mesyanio.isBlank()){
            sueldoService.eliminarSueldos();
            System.out.println(mesyanio);
            String[] fechaSeparada = mesyanio.split("-");
            int mes = Integer.valueOf(fechaSeparada[1]);
            int anio = Integer.valueOf(fechaSeparada[0]);
            ArrayList<Integer> tiempoAtraso = new ArrayList<>();
            ArrayList<EmpleadoEntity> empleados = empleadoService.obtenerEmpleados();
            for(EmpleadoEntity e:empleados){
                tiempoAtraso = atrasoService.verificarAtrasosPorEmpleadoYFecha(mes, anio, e.getRut());
                tiempoAtraso = inasistenciaService.verificarInasistenciaPorEmepleadoYRut(mes, anio, e.getRut(), tiempoAtraso);
                double horaExtra = horaExtraService.verificarHorasExtras(mes, anio, e.getRut()); 
                int sueldoMensualFijo = rrhhService.sueldoMensualFijoEmpleado(e);
                double bonificacionTiempoServicio = rrhhService.bonificacionTiempoServicio(e, sueldoMensualFijo);
                double pagoHorasExtras = rrhhService.montoHorasExtrasEmpleado(e, horaExtra);
                double descuentoPorAtraso = rrhhService.descuentoPorAtraso(tiempoAtraso, sueldoMensualFijo);
                double sueldoBruto = sueldoMensualFijo + bonificacionTiempoServicio + pagoHorasExtras - descuentoPorAtraso;
                double descuentoPrevisional = rrhhService.descuentosPrevisional(sueldoBruto);
                double descuentoSalud = rrhhService.descuentoSalud(sueldoBruto);
                double sueldoFinal = sueldoBruto - descuentoPrevisional - descuentoSalud;
                SueldoEntity sueldo = new SueldoEntity(null, e.getRut(), sueldoMensualFijo, bonificacionTiempoServicio, pagoHorasExtras, descuentoPorAtraso, sueldoBruto, descuentoPrevisional, descuentoSalud, sueldoFinal, mes, anio);
                sueldoService.guardarSueldo(sueldo);
            }
            return "redirect:/";
        }else{return "redirect:/";}
    }

    @GetMapping("/listar")
    public String listar(@RequestParam("mesanio") String mesyanio, Model model){
        if(!mesyanio.isBlank()){
            String[] fechaSeparada = mesyanio.split("-");
            int mes = Integer.valueOf(fechaSeparada[1]);
            int anio = Integer.valueOf(fechaSeparada[0]);
            ArrayList<SueldoEntity> sueldos = sueldoService.obtenerSueldosPorFecha(mes, anio);
            ArrayList<EmpleadoEntity> empleados = new ArrayList<>();
            for(SueldoEntity s:sueldos){
                empleados.add(empleadoService.obtenerEmpleadoPorRut(s.getRut()));
            }
            model.addAttribute("sueldos", sueldos);
            model.addAttribute("empleados", empleados);
            return "sueldo/listar";
        }else{
            return "redirect:/";
        }
        
    }
}
