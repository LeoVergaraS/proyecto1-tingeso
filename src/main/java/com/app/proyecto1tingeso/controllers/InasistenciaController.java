package com.app.proyecto1tingeso.controllers;

import com.app.proyecto1tingeso.entities.EmpleadoEntity;
import com.app.proyecto1tingeso.entities.InasistenciaEntity;
import com.app.proyecto1tingeso.services.InasistenciaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Optional;

@Controller
@RequestMapping("/inasistencias")
public class InasistenciaController {

    @Autowired
    InasistenciaService inasistenciaService;

    @GetMapping("/listar")
    public String listar(Model model){
        ArrayList<InasistenciaEntity>inasistencias=inasistenciaService.obtenerInasistencias();
        model.addAttribute("inasistencias",inasistencias);
        return "listarina";
    }

    @GetMapping("/nuevo")
    public String nuevo(Model model){
        model.addAttribute("inasistencia",new InasistenciaEntity());
        return "formina";
    }

    @PostMapping("/guardar")
    public String crear(InasistenciaEntity inasistencia){
        System.out.println(inasistencia);
        inasistenciaService.guardarInasistencia(inasistencia);
        return "redirect:/inasistencias/listar";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable long id, Model model){
        Optional<InasistenciaEntity> inasistencia=inasistenciaService.obtenerPorId(id);
        model.addAttribute("inasistencia",inasistencia.get());
        return "formina";

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
        return "justificacion";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable long id){
        inasistenciaService.eliminarInasistencia(id);
        return "redirect:/inasistencias/listar";
    }
}
