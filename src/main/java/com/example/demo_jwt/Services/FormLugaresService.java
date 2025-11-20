package com.example.demo_jwt.Services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo_jwt.Repository.FormLugaresRepository;
import com.example.demo_jwt.entities.FormLugares;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor

public class FormLugaresService {

    private final FormLugaresRepository formLugaresRepository;

        
    @Transactional // <--- Agrega esto para asegurar el commit en la DB
    public FormLugares crearFormLugares(FormLugares formLugares){
        return formLugaresRepository.save(formLugares);
    }


    


}
