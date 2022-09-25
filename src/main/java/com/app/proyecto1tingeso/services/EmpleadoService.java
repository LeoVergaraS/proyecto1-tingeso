package com.app.proyecto1tingeso.services;

import com.app.proyecto1tingeso.entities.EmpleadoEntity;
import com.app.proyecto1tingeso.repositories.EmpleadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class EmpleadoService {

    @Autowired
    EmpleadoRepository empleadoRepository;

    public ArrayList<EmpleadoEntity> obtenerEmpleados(){
        return (ArrayList<EmpleadoEntity>) empleadoRepository.findAll();
    }

    public EmpleadoEntity obtenerEmpleadoPorRut(String rut){
        return empleadoRepository.findByRut(rut);
    }

    public EmpleadoEntity guardarEmpleado(EmpleadoEntity empleado){
        EmpleadoEntity e = empleadoRepository.findByRut(empleado.getRut());
        // Se crea
        if(empleado.getId() == null){
            if(e == null){return empleadoRepository.save(empleado);}
            else{return null;}
        }
        // Se actualiza
        else{
            if(e == null){return empleadoRepository.save(empleado);}
            else if(e.getId() == empleado.getId()){return empleadoRepository.save(empleado);}
            else{return null;}     
        }
    }

    public Optional<EmpleadoEntity> obtenerPorId(Long id){
        return empleadoRepository.findById(id);}

    public boolean eliminarEmpleado(Long id){
        try{
            empleadoRepository.deleteById(id);
            return true;
        }catch(Exception err){
            return false;
        }
    }
}
