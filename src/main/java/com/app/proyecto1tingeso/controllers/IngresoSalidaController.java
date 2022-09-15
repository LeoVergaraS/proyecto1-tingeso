package com.app.proyecto1tingeso.controllers;

import com.app.proyecto1tingeso.entities.IngresoSalidaEntity;
import com.app.proyecto1tingeso.services.IngresoSalidaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;

@Controller
@RequestMapping("/ingresos_salidas")
public class IngresoSalidaController {

    @Autowired
    IngresoSalidaService ingresoSalidaService;

    @GetMapping("/listar")
    public String listar(Model model){
        ArrayList<IngresoSalidaEntity> ingresoSalida=ingresoSalidaService.obtenerIngresoSalida();
        model.addAttribute("ingresoSalida",ingresoSalida);
        return "listarIS";
    }
}
