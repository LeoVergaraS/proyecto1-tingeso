package com.app.proyecto1tingeso.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


@Service
public class CargaService {
    private String carpeta = "cargas//";
    private final Logger logg = LoggerFactory.getLogger(CargaService.class);

    public String guardarArchivo(MultipartFile archivo){
        if(!archivo.isEmpty()){
            try {
                byte[] bytes = archivo.getBytes();
                Path path = Paths.get(carpeta+archivo.getOriginalFilename());
                Files.write(path, bytes);
                logg.info("Archivo guardado");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return "Archivo guardado correctamente";
    }
}
