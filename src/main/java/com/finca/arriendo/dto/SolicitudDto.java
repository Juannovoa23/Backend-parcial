package com.finca.arriendo.dto;

import java.util.Date; // Asegúrate de importar java.util.Date en lugar de java.sql.Date

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
public class SolicitudDto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Cambiar a Long para mantener la consistencia

    private Date fechaInicio; // Mantener como Date
    private Date fechaFin; // Mantener como Date

    private Integer califFinca; // Cambiar a Integer para manejar valores nulos

    private Integer califArrendatario; // Mantener como Integer

    private float precio; // Mantener como float
    private int cantPersonas; // Mantener como int
    private String numeroCuenta; // Número de cuenta
    private String banco; // Banco

    private String estado; // Estado como String (debe ser el nombre del enum)

    // Campos adicionales para visibilidad
    private boolean isPagoVisible; // Visibilidad del pago
    private boolean isCalificacionVisible; // Visibilidad de la calificación

}
