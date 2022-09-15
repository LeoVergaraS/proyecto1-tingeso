package com.app.proyecto1tingeso.services;

import com.app.proyecto1tingeso.entities.EmpleadoEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;

@Service
public class RRHHService {

    public int sueldoMensualFijoEmpleado(EmpleadoEntity empleado){
        if(empleado.getCategoria() == 'A'){
            return 1700000;
        }else if(empleado.getCategoria() == 'B'){
            return 1200000;
        }else{
            return 800000;
        }
    }

    public int montoHorasExtrasEmpleado(EmpleadoEntity empleado, int horasExtras){
        if(empleado.getCategoria() == 'A'){
            return horasExtras * 25000;
        }else if(empleado.getCategoria() == 'B'){
            return horasExtras * 20000;
        }else{
            return horasExtras * 10000;
        }
    }

    public double descuentosPrevisional(int sueldoFinal){
        return sueldoFinal * 0.1;
    }

    public double descuentoSalud(int sueldoFinal){
        return sueldoFinal * 0.08;
    }

    public double bonificacionTiempoServicio(EmpleadoEntity empleado, int sueldoMensualFijo){
        int anioIngreso = empleado.getFecha_de_ingreso().getYear();
        int anioActual = LocalDateTime.now().getYear();
        int anioServicio = anioActual - anioIngreso;

        if(anioServicio >= 25){return sueldoMensualFijo * 0.17;}
        else if(anioServicio >= 20){return sueldoMensualFijo * 0.14;}
        else if(anioServicio >= 15){return sueldoMensualFijo * 0.11;}
        else if(anioServicio >= 10){return sueldoMensualFijo * 0.08;}
        else if(anioServicio >= 5){return sueldoMensualFijo * 0.05;}
        else{return 0;}
    }

    public double descuentoPorAtraso(ArrayList<Integer> tiempoAtraso, int sueldoMensualFijo){
        double monto10 = tiempoAtraso.get(0) * 0.01;
        double monto25 = tiempoAtraso.get(1) * 0.03;
        double monto45 = tiempoAtraso.get(2) * 0.06;
        double monto70 = tiempoAtraso.get(3) * 0.15;
        return monto10 + monto25 + monto45 + monto70;
    }
}
