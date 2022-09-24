package com.app.proyecto1tingeso.services;

import com.app.proyecto1tingeso.entities.EmpleadoEntity;
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

    private boolean verificarInasistencia(InasistenciaEntity inasistencia){
        String rut = inasistencia.getRut_empleado();
        int mes = inasistencia.getMes();
        int anio = inasistencia.getAnio();
        InasistenciaEntity i = inasistenciaRepository.encontrarInasistenciaPorEmpleado(rut, mes, anio);
        if(inasistencia.getId() == null){
            if(i == null){return true;}
            else{return false;}
        }else{
            if(i == null){return true;}
            else if(inasistencia.getId() == i.getId()){return true;}
            else{return false;}
        }
        
    }

    public InasistenciaEntity guardarInasistencia(InasistenciaEntity inasistencia, EmpleadoEntity empleado){
        // Se crea
        if(inasistencia.getId() == null){
            if(empleado != null && verificarInasistencia(inasistencia)){
                return inasistenciaRepository.save(inasistencia);
            }else{return null;}
        }
        // Se actualiza
        else{
            if(empleado != null && verificarInasistencia(inasistencia)){
                return inasistenciaRepository.save(inasistencia);}
            else{return null;}
        }        
    }

    public Optional<InasistenciaEntity> obtenerPorId(long id){
        return inasistenciaRepository.findById(id);
    }

    public void actualizarJustificativo(long id, int d){
        inasistenciaRepository.updateInasistenciaByJustificados(d, id);
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
