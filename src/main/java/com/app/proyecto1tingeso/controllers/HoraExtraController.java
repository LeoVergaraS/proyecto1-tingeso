package com.app.proyecto1tingeso.controllers;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.app.proyecto1tingeso.entities.HoraExtraEntity;
import com.app.proyecto1tingeso.entities.IngresoSalidaEntity;
import com.app.proyecto1tingeso.services.HoraExtraService;
import com.app.proyecto1tingeso.services.IngresoSalidaService;

@Controller
@RequestMapping("/horas_extras")
public class HoraExtraController {
    
    @Autowired
    HoraExtraService horaExtraService;

    @Autowired
    IngresoSalidaService ingresoSalidaService;

    @GetMapping("/listar")
    public String listar(Model model){
        ArrayList<HoraExtraEntity> horasExtras = horaExtraService.obtenerHorasExtras();
        model.addAttribute("horasExtras", horasExtras);
        return "listarhe";
    }

    @GetMapping("/guardar")
    public String guardar(){
        ArrayList<IngresoSalidaEntity> ingresosSalidas = ingresoSalidaService.obtenerSalidas();
        ArrayList<HoraExtraEntity> horasExtras = horaExtraService.calculoHorasExtrasPorEmpleado(ingresosSalidas);
        horaExtraService.guardarHoraExtra(horasExtras);
        return "redirect:/inasistencias/guardar_automatico";
    }

    @GetMapping("/autorizar/{id}")
    public String autorizar(@PathVariable long id, Model model){
        Optional<HoraExtraEntity> horaExtra = horaExtraService.obtenerHoraExtraPorId(id);
        model.addAttribute("horaExtra", horaExtra.get());
        return "autorizacion";
    }

    @PostMapping("/editar_autorizacion/{id}")
    public String editarAutorizacion(@PathVariable long id,@RequestParam("autorizado") int a){
        horaExtraService.actualizarAutorizado(id, a);
        return "redirect:/horas_extras/listar";
    }
}
