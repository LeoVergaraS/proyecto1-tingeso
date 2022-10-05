package com.app.proyecto1tingeso.controllers;

import com.app.proyecto1tingeso.services.CargaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/archivos")
public class CargaController {

    @Autowired
    private CargaService cargaService;

    @GetMapping("/leer")
    public String read(){
        return "archivo/leer";
    }

    @PostMapping("/cargar")
    public String carga(@RequestParam("archivos") MultipartFile archivo, RedirectAttributes ms){
        if(archivo.getOriginalFilename() == null){
            if(archivo.getOriginalFilename().equals("data.txt")){
                cargaService.guardarArchivo(archivo);
                return "redirect:/ingresos_salidas/guardar";
            }else{
                ms.addFlashAttribute("mensaje","Nombre del archivo incorrecto");
                return "redirect:/archivos/leer";
            }
        }else{
            ms.addFlashAttribute("mensaje","Formato del archivo incorrecto");
            return "redirect:/archivos/leer";
        }
    }
}
