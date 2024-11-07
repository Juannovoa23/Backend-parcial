package com.finca.arriendo.dto;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioDto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // ID del usuario
    private String nombre; // Nombre del usuario
    private String apellido; // Apellido del usuario
    private String correo; // Correo electrónico del usuario

    private Integer telefono; // Teléfono del usuario (Integer para permitir null)

    private String contrasena; // Contraseña del usuario

    private Float calificacion; // Calificación del usuario (Float para representar números reales)

    private String tipo; // Tipo de usuario (ARRENDADOR, ARRENDATARIO)
}

