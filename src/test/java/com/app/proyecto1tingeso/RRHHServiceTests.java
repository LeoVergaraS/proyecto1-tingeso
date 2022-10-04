package com.app.proyecto1tingeso;


import com.app.proyecto1tingeso.services.RRHHService;
import com.app.proyecto1tingeso.services.SueldoService;
import org.junit.jupiter.api.Test;

import com.app.proyecto1tingeso.entities.EmpleadoEntity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.sql.Date;
import java.util.ArrayList;

public class RRHHServiceTests {
    EmpleadoEntity empleadoEntity = new EmpleadoEntity();
    RRHHService rrhhService = new RRHHService();
    @Test
    void sueldoMensualFijoEmpleado() throws ParseException {
        Date fecha_ingreso = new Date(new SimpleDateFormat("yyyy-MM-dd").parse("2009-01-12").getTime());
        Date fecha_nacimiento = new Date(new SimpleDateFormat("yyyy-MM-dd").parse("1990-09-01").getTime());
        empleadoEntity.setRut("11.111.111-1");
        empleadoEntity.setNombre("Juan");
        empleadoEntity.setApellido("Perez");
        empleadoEntity.setFecha_de_ingreso(fecha_ingreso);
        empleadoEntity.setFecha_de_nacimiento(fecha_nacimiento);
        empleadoEntity.setCategoria('C');
        int sueldo = rrhhService.sueldoMensualFijoEmpleado(empleadoEntity);
        assert(sueldo == 800000);

        empleadoEntity.setCategoria('B');
        sueldo = rrhhService.sueldoMensualFijoEmpleado(empleadoEntity);
        assert(sueldo == 1200000);

        empleadoEntity.setCategoria('A');
        sueldo = rrhhService.sueldoMensualFijoEmpleado(empleadoEntity);
        assert(sueldo == 1700000);
    }

    @Test
    void montoHorasExtrasEmpleado() throws ParseException{
        Date fecha_ingreso = new Date(new SimpleDateFormat("yyyy-MM-dd").parse("2009-01-12").getTime());
        Date fecha_nacimiento = new Date(new SimpleDateFormat("yyyy-MM-dd").parse("1990-09-01").getTime());
        empleadoEntity.setRut("11.111.111-1");
        empleadoEntity.setNombre("Juan");
        empleadoEntity.setApellido("Perez");
        empleadoEntity.setFecha_de_ingreso(fecha_ingreso);
        empleadoEntity.setFecha_de_nacimiento(fecha_nacimiento);
        empleadoEntity.setCategoria('C');

        int horasExtras = 4;
        double montoHorasExtras = rrhhService.montoHorasExtrasEmpleado(empleadoEntity,horasExtras);
        assert(montoHorasExtras == 40000);

        empleadoEntity.setCategoria('B');
        horasExtras = 10;
        montoHorasExtras = rrhhService.montoHorasExtrasEmpleado(empleadoEntity, horasExtras);
        assert (montoHorasExtras == 200000);

        empleadoEntity.setCategoria('A');
        horasExtras = 12;
        montoHorasExtras = rrhhService.montoHorasExtrasEmpleado(empleadoEntity, horasExtras);
        assert (montoHorasExtras == 300000);
    }

    @Test
    void descuentosPrevisional(){
        double sueldoFinal = 1100000;
        double descuentoPrevisional = rrhhService.descuentosPrevisional(sueldoFinal);
        assert(descuentoPrevisional == 110000);
    }

    @Test
    void descuentoSalud(){
        double sueldoFinal = 890000;
        double descuentoSalud = rrhhService.descuentoSalud(sueldoFinal);
        assert(descuentoSalud == 71200);
    }

    @Test
    void bonificacionTiempoServicio() throws ParseException{
        Date fecha_ingreso = new Date(new SimpleDateFormat("yyyy-MM-dd").parse("1990-01-12").getTime());
        Date fecha_nacimiento = new Date(new SimpleDateFormat("yyyy-MM-dd").parse("1960-09-01").getTime());
        empleadoEntity.setRut("11.111.111-1");
        empleadoEntity.setNombre("Juan");
        empleadoEntity.setApellido("Perez");
        empleadoEntity.setFecha_de_ingreso(fecha_ingreso);
        empleadoEntity.setFecha_de_nacimiento(fecha_nacimiento);
        empleadoEntity.setCategoria('C');

        int sueldoMensualFijo = 800000;
        double bonificacionTiempoServicio = rrhhService.bonificacionTiempoServicio(empleadoEntity, sueldoMensualFijo);
        assert(bonificacionTiempoServicio == 136000);

        fecha_ingreso = new Date(new SimpleDateFormat("yyyy-MM-dd").parse("2000-01-12").getTime());
        empleadoEntity.setFecha_de_ingreso(fecha_ingreso);
        sueldoMensualFijo = 1700000;
        bonificacionTiempoServicio = rrhhService.bonificacionTiempoServicio(empleadoEntity, sueldoMensualFijo);
        assert(bonificacionTiempoServicio == 238000);

        fecha_ingreso = new Date(new SimpleDateFormat("yyyy-MM-dd").parse("2006-01-12").getTime());
        empleadoEntity.setFecha_de_ingreso(fecha_ingreso);
        sueldoMensualFijo = 1700000;
        bonificacionTiempoServicio = rrhhService.bonificacionTiempoServicio(empleadoEntity, sueldoMensualFijo);
        assert(bonificacionTiempoServicio == 187000);

        fecha_ingreso = new Date(new SimpleDateFormat("yyyy-MM-dd").parse("2010-01-12").getTime());
        empleadoEntity.setFecha_de_ingreso(fecha_ingreso);
        sueldoMensualFijo = 800000;
        bonificacionTiempoServicio = rrhhService.bonificacionTiempoServicio(empleadoEntity, sueldoMensualFijo);
        assert(bonificacionTiempoServicio == 64000);

        fecha_ingreso = new Date(new SimpleDateFormat("yyyy-MM-dd").parse("2016-01-12").getTime());
        empleadoEntity.setFecha_de_ingreso(fecha_ingreso);
        sueldoMensualFijo = 1200000;
        bonificacionTiempoServicio = rrhhService.bonificacionTiempoServicio(empleadoEntity, sueldoMensualFijo);
        assert(bonificacionTiempoServicio == 60000);

        fecha_ingreso = new Date(new SimpleDateFormat("yyyy-MM-dd").parse("2020-01-12").getTime());
        empleadoEntity.setFecha_de_ingreso(fecha_ingreso);
        sueldoMensualFijo = 1700000;
        bonificacionTiempoServicio = rrhhService.bonificacionTiempoServicio(empleadoEntity, sueldoMensualFijo);
        assert(bonificacionTiempoServicio == 0);
    }

    @Test
    void descuentoPorAtraso(){
        ArrayList<Integer> tiempoAtrasos = new ArrayList<>();
        tiempoAtrasos.add(1);
        tiempoAtrasos.add(2);
        tiempoAtrasos.add(4);
        tiempoAtrasos.add(1);

        int sueldoMensualFijo = 800000;
        double descuentoPorAtraso = rrhhService.descuentoPorAtraso(tiempoAtrasos,sueldoMensualFijo);
        assert(descuentoPorAtraso == 368000);
    }
}
