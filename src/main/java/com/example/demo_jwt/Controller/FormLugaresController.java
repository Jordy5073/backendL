package com.example.demo_jwt.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo_jwt.Services.FormLugaresService;
import com.example.demo_jwt.entities.FormLugares;

import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/lugares")
@RequiredArgsConstructor

public class FormLugaresController {

    private final FormLugaresService formLugaresService;
    
    @PostMapping("/crear")
    public ResponseEntity<FormLugares> save(@RequestBody FormLugares formLugares) {

        System.out.println("Datos recibidos en back: " + formLugares.toString());
        return ResponseEntity.ok(formLugaresService.crearFormLugares(formLugares));
    }
    


}
