package com.app.proyecto1tingeso.services;

import com.app.proyecto1tingeso.entities.EmpleadoEntity;
import com.app.proyecto1tingeso.entities.InasistenciaEntity;
import com.app.proyecto1tingeso.entities.IngresoSalidaEntity;
import com.app.proyecto1tingeso.repositories.EmpleadoRepository;
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
        InasistenciaEntity i = inasistenciaRepository.findInasistenciaEmpleadoByFecha(rut, mes, anio);
        if(inasistencia.getId() == null){
            if(i == null){return true;}
            else{return false;}
        }else{
            if(i == null){return true;}
            else if(inasistencia.getId() == i.getId()){return true;}
            else{return false;}
        }
        
    }

    public InasistenciaEntity obtenerInasistenciaPorEmpleadoYFecha(int mes, int anio, String rut){
        return inasistenciaRepository.findInasistenciaEmpleadoByFecha(rut, mes, anio);
    }

    public boolean crearInasistencias(ArrayList<IngresoSalidaEntity> inasistencias){
        if(inasistencias != null){
            String[] fechaSeparada = inasistencias.get(0).getFecha().toString().split("-");
            int anio = Integer.valueOf(fechaSeparada[0]);
            int mes = Integer.valueOf(fechaSeparada[1]);
            for(IngresoSalidaEntity i:inasistencias){
                InasistenciaEntity inasistencia = inasistenciaRepository.findInasistenciaEmpleadoByFecha(i.getRut_empleado(), mes, anio);
                if(inasistencia == null){
                    inasistenciaRepository.save(new InasistenciaEntity(null,mes,anio,1,0,i.getRut_empleado()));
                }else{
                    inasistencia.setCantidad_de_dias(inasistencia.getCantidad_de_dias() + 1);
                    inasistenciaRepository.save(inasistencia);
                }
            }
            return true;
        }else{
            return false;
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
