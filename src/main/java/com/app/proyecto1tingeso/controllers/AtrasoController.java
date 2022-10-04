package com.app.proyecto1tingeso.controllers;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.app.proyecto1tingeso.entities.AtrasoEntity;
import com.app.proyecto1tingeso.entities.IngresoSalidaEntity;
import com.app.proyecto1tingeso.services.AtrasoService;
import com.app.proyecto1tingeso.services.IngresoSalidaService;

@Controller
@RequestMapping("/atrasos")
public class AtrasoController {
    @Autowired
    AtrasoService atrasoService;

    @Autowired
    IngresoSalidaService ingresoSalidaService;

    @GetMapping("/listar")
    public String listar(Model model){
        ArrayList<AtrasoEntity> atrasos = atrasoService.obtenerAtrasos();
        model.addAttribute("atrasos", atrasos);
        return "atraso/listar";
    }

    @GetMapping("/crear")
    public String crear(RedirectAttributes ms){
        ArrayList<IngresoSalidaEntity> atrasos = ingresoSalidaService.obtenerAtrasosDeIngresoSalida();
        atrasoService.guardarAtrasos(atrasos);
        ms.addFlashAttribute("mensaje","Archivo guardado correctamente!!");
        return "redirect:/archivos/leer";
    }
}
