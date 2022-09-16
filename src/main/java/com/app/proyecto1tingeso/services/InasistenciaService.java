package com.app.proyecto1tingeso.services;

import com.app.proyecto1tingeso.entities.InasistenciaEntity;
import com.app.proyecto1tingeso.repositories.InasistenciaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class InasistenciaService {
    @Autowired
    InasistenciaRepository inasistenciaRepository;

    public ArrayList<InasistenciaEntity> obtenerInasistencias(){
        return (ArrayList<InasistenciaEntity>) inasistenciaRepository.findAll();
    }

    public InasistenciaEntity guardarInasistencia(InasistenciaEntity inasistencia){
        return inasistenciaRepository.save(inasistencia);
    }

    public Optional<InasistenciaEntity> obtenerPorId(long id){
        return inasistenciaRepository.findById(id);
    }

    public InasistenciaEntity obtenerPorRut(String rut){
        return inasistenciaRepository.findByRut(rut);
    }

    public boolean eliminarInasistencia(long id){
        try{
            inasistenciaRepository.deleteById(id);
            return true;
        }catch(Exception err){
           return false;
        }
    }
}
