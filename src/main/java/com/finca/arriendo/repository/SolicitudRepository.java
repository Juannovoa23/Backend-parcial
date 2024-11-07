package com.finca.arriendo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.finca.arriendo.model.Estado;
import com.finca.arriendo.model.Solicitud;

@Repository
public interface SolicitudRepository extends JpaRepository<Solicitud, Long> {
    List<Solicitud> findByArrendatarioId(Long arrendatarioId); // Método para obtener solicitudes por arrendatario
    List<Solicitud> findByFincaId(Long fincaId); // Método para obtener solicitudes por finca
    List<Solicitud> findByEstado(Estado estado); // Método para obtener solicitudes por estado
}
