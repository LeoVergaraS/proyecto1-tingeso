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

    private ArrayList<IngresoSalidaEntity> ingresosEmpleados(ArrayList<String[]> infoSeparada,ArrayList<IngresoSalidaEntity> ingresosSalidas) throws ParseException{
        int n = infoSeparada.size();
        for(int i=0;i<n/2;i++){
            Date fecha = new Date(new SimpleDateFormat("yyyy/MM/dd").parse(infoSeparada.get(i)[0]).getTime());
            Time hora_ingreso = new Time((new SimpleDateFormat("HH:mm").parse(infoSeparada.get(i)[1])).getTime());
            String rut = infoSeparada.get(i)[2];
            IngresoSalidaEntity is = new IngresoSalidaEntity(null, fecha, hora_ingreso, null, rut);
            ingresosSalidas.add(is);
        }
        return ingresosSalidas;
    }

    private ArrayList<IngresoSalidaEntity> salidaEmpleados(ArrayList<String[]> infoSeparada,ArrayList<IngresoSalidaEntity> ingresosSalidas) throws ParseException{
        int n = infoSeparada.size();
        for(int i=n/2;i<n;i++){
            Time hora_salida = new Time((new SimpleDateFormat("HH:mm").parse(infoSeparada.get(i)[1])).getTime());
            String rut = infoSeparada.get(i)[2];
            for(int j=0;j < ingresosSalidas.size();j++){
                if(ingresosSalidas.get(j).getRut_empleado().equals(rut)){
                    ingresosSalidas.get(j).setHora_salida(hora_salida);
                    break;
                }
            }
        }
        return ingresosSalidas;
    }

    private ArrayList<IngresoSalidaEntity> stringAIngresoSalidaEntity(String texto) throws ParseException {
        ArrayList<IngresoSalidaEntity> ingresosSalidas = new ArrayList<>();
        ArrayList<String[]> informacionSeparada = new ArrayList<>();
        String[] informacionPorLinea = texto.split("\n");
        for(String elemento:informacionPorLinea){
            informacionSeparada.add(elemento.split(";"));
        }
        ingresosSalidas = ingresosEmpleados(informacionSeparada,ingresosSalidas);
        ingresosSalidas = salidaEmpleados(informacionSeparada,ingresosSalidas);
        return ingresosSalidas;
    }
    
    public ArrayList<IngresoSalidaEntity> transformarInformacion(){
        String temp;
        String texto = "";
        try{
            BufferedReader br = new BufferedReader(new FileReader("cargas//data.txt"));
            temp = br.readLine();
            while (temp != null){
                texto = texto + temp + "\n";
                temp = br.readLine();
            }
            return stringAIngresoSalidaEntity(texto);
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
