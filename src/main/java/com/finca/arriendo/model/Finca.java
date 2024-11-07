package com.finca.arriendo.model;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class Finca {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id; // id de la finca
    private String nombre; // Nombre de la finca
    private String ubicacion; // Identificador de ubicación
    private String municipio; // Municipio de la finca
    private String departamento; // Departamento de la finca
    private Boolean disponible; // Cantidad disponible (1: disponible, 0: no disponible)
    private Integer calificacion; // Calificación de la finca
    private String descripcion; // Descripción de la finca
    private Integer capacidad; // Capacidad máxima de personas en la finca
    private Float precioDefecto; // Precio por defecto para el arriendo
    @Column(length = 500)
    private String comentarios[]; // Comentarios adicionales

    @OneToMany(mappedBy = "finca")
    private List<Solicitud> solicitudes; // Relación con las solicitudes de arriendo

    @ManyToOne // Relación con Usuario (dueño)
    @JoinColumn(name = "dueño_id", nullable = false) 
    private Usuario dueño;

    private boolean deleted;

    // Método para calificar la finca
    public void calificar(Integer calificacion) {
        // Verificar que la calificación esté en un rango válido (ejemplo: 1 a 5)
        if (calificacion >= 1 && calificacion <= 5) {
            this.calificacion = calificacion; // Asignar calificación
        } else {
            throw new IllegalArgumentException("La calificación debe estar entre 1 y 5.");
        }
    }

    public Integer getId() {
        return this.id;
    }

    // Método para verificar disponibilidad
    public boolean isDisponible() {
        return this.disponible == true; // Devuelve true si está disponible
    }

    // Método para actualizar la disponibilidad
    public void actualizarDisponibilidad(boolean nuevaDisponibilidad) {
        this.disponible = nuevaDisponibilidad; // Actualiza la disponibilidad
    }

    // Método para marcar la finca como eliminada
    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public void setComentarios(String comentarios[]) {
        this.comentarios = comentarios;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public boolean estaDisponible(String fechaInicio, String fechaFin) {
        // Verificar que la finca esté disponible
        if (!this.isDisponible()) {
            return false;
        }
    
        // Verificar que las fechas sean válidas
        if (fechaInicio == null || fechaInicio.isEmpty() || fechaFin == null || fechaFin.isEmpty()) {
            return false;
        }
    
        // Verificar que la fecha de inicio sea anterior o igual a la fecha de fin
        if (fechaInicio.compareTo(fechaFin) > 0) {
            return false;
        }
    
        // Si todas las condiciones se cumplen, la finca está disponible para el rango de fechas
        return true;
    }

}
