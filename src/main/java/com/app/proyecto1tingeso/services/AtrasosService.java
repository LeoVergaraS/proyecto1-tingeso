package com.app.proyecto1tingeso.services;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.proyecto1tingeso.entities.AtrasosEntity;
import com.app.proyecto1tingeso.repositories.AtrasosRepository;

@Service
public class AtrasosService {
    @Autowired
    AtrasosRepository atrasosRepository;
    
    public ArrayList<AtrasosEntity> obtenerAtrasos(){
        return (ArrayList<AtrasosEntity>) atrasosRepository.findAll();
    }
}
