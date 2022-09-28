package com.app.proyecto1tingeso.services;

import com.app.proyecto1tingeso.entities.IngresoSalidaEntity;
import com.app.proyecto1tingeso.repositories.IngresoSalidaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Date;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

@Service
public class IngresoSalidaService {

    @Autowired
    IngresoSalidaRepository ingresoSalidaRepository;

    public ArrayList<IngresoSalidaEntity> obtenerIngresoSalida(){
        return (ArrayList<IngresoSalidaEntity>) ingresoSalidaRepository.findAll();
    }

    public ArrayList<IngresoSalidaEntity> obtenerInasistenciasDeIngresoSalida(){
        return ingresoSalidaRepository.findInasistencias();
    }

    public ArrayList<Integer> obtenerAtrasosEmpleado(String rut){
        ArrayList<Integer> atrasos = new ArrayList<>();
        atrasos.add(ingresoSalidaRepository.findAtrasosUno(rut));
        atrasos.add(ingresoSalidaRepository.findAtrasosDos(rut));
        atrasos.add(ingresoSalidaRepository.findAtrasosTres(rut));
        atrasos.add(ingresoSalidaRepository.findAtrasosCuatro(rut));
        return atrasos;
    }

    public ArrayList<IngresoSalidaEntity> obtenerSalidas(){
        return (ArrayList<IngresoSalidaEntity>) ingresoSalidaRepository.findSalidas();
    }

    public IngresoSalidaEntity lineaAIngresoSalidaEntity(String linea) throws ParseException{
        // Se separa la linea por los ;
        String[] lineaSeparada =  linea.split(";");

        Date fecha = new Date(new SimpleDateFormat("yyyy/MM/dd").parse(lineaSeparada[0]).getTime());
        Time hora  = new Time((new SimpleDateFormat("HH:mm").parse(lineaSeparada[1])).getTime());
        String rut = lineaSeparada[2];

        IngresoSalidaEntity ingresoSalida = new IngresoSalidaEntity(null, fecha, hora, rut);
        return ingresoSalida;
    }
    
    public boolean guardarIngresoSalidaDeData(){
        String linea;
        try{
            BufferedReader br = new BufferedReader(new FileReader("cargas//data.txt"));
            linea = br.readLine();
            while (linea != null){
                ingresoSalidaRepository.save(lineaAIngresoSalidaEntity(linea));
                linea = br.readLine();
            }
            br.close();
            return true;
        }catch(FileNotFoundException ex){System.err.println(ex.getMessage());}
        catch(IOException ex){System.err.println(ex.getMessage());}
        catch (ParseException ex){System.err.println(ex.getMessage());}
        return false;
    }  
}
