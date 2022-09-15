package com.app.proyecto1tingeso.services;

import com.app.proyecto1tingeso.entities.IngresoSalidaEntity;
import com.app.proyecto1tingeso.repositories.IngresoSalidaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class IngresoSalidaService {

    @Autowired
    IngresoSalidaRepository ingresoSalidaRepository;

    public ArrayList<IngresoSalidaEntity> obtenerIngresoSalida(){
        return (ArrayList<IngresoSalidaEntity>) ingresoSalidaRepository.findAll();
    }
}
