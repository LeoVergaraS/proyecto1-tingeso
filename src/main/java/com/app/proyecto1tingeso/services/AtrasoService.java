package com.app.proyecto1tingeso.services;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.proyecto1tingeso.entities.AtrasoEntity;
import com.app.proyecto1tingeso.entities.IngresoSalidaEntity;
import com.app.proyecto1tingeso.repositories.AtrasoRepository;
import com.app.proyecto1tingeso.repositories.IngresoSalidaRepository;

@Service
public class AtrasoService {
    @Autowired
    AtrasoRepository atrasosRepository;

    @Autowired
    IngresoSalidaRepository ingresoSalidaRepository;
    
    public ArrayList<AtrasoEntity> obtenerAtrasos(){
        return (ArrayList<AtrasoEntity>) atrasosRepository.findAll();
    }

    public ArrayList<Integer> verificarAtrasosPorEmpleadoYFecha(int mes, int anio, String rut){
        ArrayList<Integer> tiempos = new ArrayList<>();
        AtrasoEntity atraso = atrasosRepository.findAtrasoEmpleadoByFecha(rut, mes, anio);
        if(atraso == null){
            tiempos.add(0);
            tiempos.add(0);
            tiempos.add(0);
        }else{
            tiempos.add(atraso.getAtraso_10min());
            tiempos.add(atraso.getAtraso_25min());
            tiempos.add(atraso.getAtraso_45min());
        }
        return tiempos;
    }

    public boolean guardarAtrasos(ArrayList<IngresoSalidaEntity> atrasos){
        String[] fechaSeparada = atrasos.get(0).getFecha().toString().split("-");
        int mes = Integer.valueOf(fechaSeparada[1]);
        int anio = Integer.valueOf(fechaSeparada[0]);
        for(IngresoSalidaEntity is:atrasos){
            String rut = is.getRut_empleado();
            AtrasoEntity a = atrasosRepository.findAtrasoEmpleadoByFecha(rut, mes, anio);
            if(a == null){
                int atrasos1 = ingresoSalidaRepository.findAtrasosUno(rut);
                int atrasos2 = ingresoSalidaRepository.findAtrasosDos(rut);
                int atrasos3 = ingresoSalidaRepository.findAtrasosTres(rut);
                AtrasoEntity atraso = new AtrasoEntity(null, mes, anio, atrasos1, atrasos2, atrasos3, rut);
                atrasosRepository.save(atraso);
            }
        }
        return true;
    }
}
