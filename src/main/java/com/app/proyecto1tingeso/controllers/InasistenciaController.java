package com.app.proyecto1tingeso.controllers;

import com.app.proyecto1tingeso.entities.EmpleadoEntity;
import com.app.proyecto1tingeso.entities.InasistenciaEntity;
import com.app.proyecto1tingeso.entities.IngresoSalidaEntity;
import com.app.proyecto1tingeso.services.EmpleadoService;
import com.app.proyecto1tingeso.services.InasistenciaService;
import com.app.proyecto1tingeso.services.IngresoSalidaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.Optional;

@Controller
@RequestMapping("/inasistencias")
public class InasistenciaController {

    @Autowired
    InasistenciaService inasistenciaService;

    @Autowired
    EmpleadoService empleadoService;

    @Autowired
    IngresoSalidaService ingresoSalidaService;

    @GetMapping("/listar")
    public String listar(Model model){
        ArrayList<InasistenciaEntity>inasistencias=inasistenciaService.obtenerInasistencias();
        model.addAttribute("inasistencias",inasistencias);
        return "inasistencia/listar";
    }

    @GetMapping("/nuevo")
    public String nuevo(Model model){
        model.addAttribute("inasistencia",new InasistenciaEntity());
        return "inasistencia/form";
    }

    @PostMapping("/guardar")
    public String crear(InasistenciaEntity inasistencia){
        String rut = inasistencia.getRut_empleado();
        EmpleadoEntity empleado = empleadoService.obtenerEmpleadoPorRut(rut);
        inasistenciaService.guardarInasistencia(inasistencia, empleado);
        return "redirect:/inasistencias/listar";
    }

    @GetMapping("/guardar_automatico")
    public String guardar(RedirectAttributes ms){
        ArrayList<IngresoSalidaEntity> inasistencias = ingresoSalidaService.obtenerInasistenciasDeIngresoSalida();
        inasistenciaService.crearInasistencias(inasistencias);
        ms.addFlashAttribute("mensaje","Archivo guardado correctamente!!");
        return "redirect:/archivos/leer";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable long id, Model model){
        Optional<InasistenciaEntity> inasistencia=inasistenciaService.obtenerPorId(id);
        model.addAttribute("inasistencia",inasistencia.get());
        return "inasistencia/form";

    }

    @PostMapping("/editar_justificativos/{id}")
    public String mandar(@PathVariable long id, @RequestParam("dias_justificados") int d){
        inasistenciaService.actualizarJustificativo(id, d);
        return "redirect:/inasistencias/listar";
    }

    @GetMapping("/justificar/{id}")
    public String justificar(@PathVariable long id, Model model){
        Optional<InasistenciaEntity> inasistencia=inasistenciaService.obtenerPorId(id);
        model.addAttribute("inasistencia",inasistencia.get());
        return "inasistencia/justificacion";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable long id){
        inasistenciaService.eliminarInasistencia(id);
        return "redirect:/inasistencias/listar";
    }
}
