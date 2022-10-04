package com.app.proyecto1tingeso.services;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.proyecto1tingeso.entities.SueldoEntity;
import com.app.proyecto1tingeso.repositories.SueldoRepository;

@Service
public class SueldoService {

    @Autowired
    SueldoRepository sueldoRepository;

    public SueldoEntity guardarSueldo(SueldoEntity sueldo){
        return sueldoRepository.save(sueldo);
    }

    public ArrayList<SueldoEntity> obtenerSueldosPorFecha(int mes, int anio){
        return sueldoRepository.findSueldosbyFecha(mes, anio);
    }

    public void eliminarSueldos(){
       sueldoRepository.deleteAll(); 
    }
}
