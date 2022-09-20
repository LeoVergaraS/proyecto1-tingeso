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
    
    public ArrayList<IngresoSalidaEntity> transformarInformacion(){
        String linea;
        ArrayList<IngresoSalidaEntity> ingresosSalidas = new ArrayList<>();
        try{
            BufferedReader br = new BufferedReader(new FileReader("cargas//data.txt"));
            linea = br.readLine();
            while (linea != null){
                ingresosSalidas.add(lineaAIngresoSalidaEntity(linea));
                linea = br.readLine();
            }
            br.close();
            return ingresosSalidas;
        }catch(FileNotFoundException ex){System.err.println(ex.getMessage());}
        catch(IOException ex){System.err.println(ex.getMessage());}
        catch (ParseException ex){System.err.println(ex.getMessage());}
        return null;
    }

    public boolean guardarIngresoSalidaDeData(ArrayList<IngresoSalidaEntity> is){
        if(!is.isEmpty()){
            for(IngresoSalidaEntity ingresoSalida:is){
                ingresoSalidaRepository.save(ingresoSalida);
            }
            return true;
        }else{
            return false;
        }
    }

    
}
