package com.app.proyecto1tingeso.services;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.proyecto1tingeso.entities.HoraExtraEntity;
import com.app.proyecto1tingeso.entities.IngresoSalidaEntity;
import com.app.proyecto1tingeso.repositories.HoraExtraRepository;

@Service
public class HoraExtraService {

    @Autowired
    HoraExtraRepository horaExtraRepository;

    public ArrayList<HoraExtraEntity> obtenerHorasExtras(){
        return (ArrayList<HoraExtraEntity>) horaExtraRepository.findAll();
    }

    public void actualizarAutorizado(long id, int a){
        horaExtraRepository.updateHorasExtrasByAutorizados(a, id);
    }

    public HoraExtraEntity obtenerHoraExtraPorEmpleadoYFecha(int mes, int anio, String rut){
        return horaExtraRepository.findHoraExtraEmpleadoByFecha(mes, anio, rut);
    }

    private double calculoHorasExtras(IngresoSalidaEntity ingresoSalida){
        int horas = ingresoSalida.getHora().getHours();
        int min = ingresoSalida.getHora().getMinutes();
        int total = horas * 60 + min;
        double calculo = (total - 1080)/60;
        return calculo;
    }

    public double verificarHorasExtras(int mes, int anio, String rut){
        HoraExtraEntity horaExtra = horaExtraRepository.findHoraExtraEmpleadoByFecha(mes, anio, rut);
        if(horaExtra == null){
            return 0;
        }else{
            if(horaExtra.getAutorizado() == 1){
                return horaExtra.getCantidad_horas_extras();
            }else{
                return 0;
            }
        }
    }

    public boolean guardarHorasExtrasPorEmpleado(ArrayList<IngresoSalidaEntity> ingresoSalida){
        String[] fechaSeparada = ingresoSalida.get(0).getFecha().toString().split("-");
        int mes = Integer.valueOf(fechaSeparada[1]);
        int anio = Integer.valueOf(fechaSeparada[0]);
        for(IngresoSalidaEntity i:ingresoSalida){
            String rut = i.getRut_empleado();
            HoraExtraEntity horaExtra = horaExtraRepository.findHoraExtraEmpleadoByFecha(mes, anio, rut);
            if(horaExtra != null){
                double cantidad_horas_extras = horaExtra.getCantidad_horas_extras() + Math.floor(calculoHorasExtras(i));        
                horaExtra.setCantidad_horas_extras(cantidad_horas_extras);
                horaExtraRepository.save(horaExtra);
            }else{
                double cantidad_horas_extras = Math.floor(calculoHorasExtras(i));
                horaExtraRepository.save(new HoraExtraEntity(null, mes, anio, cantidad_horas_extras, 0, rut)); 
            }
        }
        return true;
    }


    public boolean eliminarHoraExtra(long id){
        try{
            horaExtraRepository.deleteById(id);
            return true;
        }catch(Exception err){
            return false;
        }    
    }
    
}
