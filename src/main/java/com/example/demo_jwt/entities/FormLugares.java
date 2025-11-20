package com.example.demo_jwt.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "form_lugares")

public class FormLugares {
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    String nombre;
    String categoria;
    String direccion;
    Integer calificacion;
    @Column(columnDefinition = "TEXT")
    String fotoUrl;
    @Column(columnDefinition = "TEXT") // TEXT permite descripciones largas
    private String descripcion;
    String latitud;
    String longitud;
    String contacto;
    
}
