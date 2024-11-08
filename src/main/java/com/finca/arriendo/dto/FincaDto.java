package com.finca.arriendo.dto;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FincaDto {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Identificador único de la finca

    private String nombre; // Nombre de la finca
    private String ubicacion; // Ubicación de la finca (posiblemente como texto)
    private Boolean disponible; // Estado de disponibilidad (true: disponible, false: no disponible)
    private int calificacion; // Calificación de la finca
    private String descripcion; // Comentarios adicionales sobre la finca
    private int capacidad; // Capacidad máxima de personas en la finca
    private String departamento; // Departamento donde se ubica la finca
    private String municipio; // Municipio donde se ubica la finca
    private Double precioDefecto; // Precio por defecto para el arriendo
}

