package com.app.proyecto1tingeso.services;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.proyecto1tingeso.entities.HoraExtraEntity;
import com.app.proyecto1tingeso.repositories.HoraExtraRepository;

@Service
public class HoraExtraService {

    @Autowired
    HoraExtraRepository horaExtraRepository;

    public ArrayList<HoraExtraEntity> obtenerHorasExtras(){
        return (ArrayList<HoraExtraEntity>) horaExtraRepository.findAll();
    }

    public HoraExtraEntity guardarHoraExtra(HoraExtraEntity horaExtra){
        return horaExtraRepository.save(horaExtra);
    }

    public Optional<HoraExtraEntity> obtenerHoraExtraPorId(long id){
        return horaExtraRepository.findById(id);
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
