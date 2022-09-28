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

    public boolean guardarHoraExtra(ArrayList<HoraExtraEntity>  horasExtras){
        if(!horasExtras.isEmpty()){
            for(HoraExtraEntity he:horasExtras){
                double cantidad_horas_extras = Math.round(he.getCantidad_horas_extras()*100.0)/100.0;
                he.setCantidad_horas_extras(cantidad_horas_extras);
                horaExtraRepository.save(he);
            }
            return true;
        }else{
            return false;
        }
    
    }

    public Optional<HoraExtraEntity> obtenerHoraExtraPorId(long id){
        return horaExtraRepository.findById(id);
    }

    public HoraExtraEntity obtenerHoraExtraPorEmpleadoYFecha(int mes, int anio, String rut){
        return horaExtraRepository.findHoraExtraEmpleadoByFecha(mes, anio, rut);
    }

    private int verificarRut(String rut, ArrayList<HoraExtraEntity> horasExtras){
        for(int i=0;i<horasExtras.size();i++){
            if(horasExtras.get(i).getRut_empleado().equals(rut)){
                return i;
            }
        }
        return -1;
    }

    private double calculoHorasExtras(IngresoSalidaEntity ingresoSalida){
        int horas = ingresoSalida.getHora().getHours();
        int min = ingresoSalida.getHora().getMinutes();
        double total = horas * 60 + min;
        return (total - 1080)/60;
    }

    public ArrayList<HoraExtraEntity> calculoHorasExtrasPorEmpleado(ArrayList<IngresoSalidaEntity> ingresoSalida){
        ArrayList<HoraExtraEntity> horasExtras = new ArrayList<>();
        String[] fechaSeparada = ingresoSalida.get(0).getFecha().toString().split("-");
        int mes = Integer.valueOf(fechaSeparada[1]);
        int anio = Integer.valueOf(fechaSeparada[0]);
        for(IngresoSalidaEntity i:ingresoSalida){
            String rut = i.getRut_empleado();
            int posicion = verificarRut(rut, horasExtras);
            if(posicion >= 0){
                HoraExtraEntity he = horasExtras.get(posicion);
                double cantidad_horas_extras = he.getCantidad_horas_extras() + calculoHorasExtras(i);        
                he.setCantidad_horas_extras(cantidad_horas_extras);
            }else{
                double cantidad_horas_extras = calculoHorasExtras(i);
                horasExtras.add(new HoraExtraEntity(null, mes, anio, cantidad_horas_extras, 0, rut)); 
            }
        }
        return horasExtras;
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
