package com.finca.arriendo.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.finca.arriendo.dto.SolicitudDto;
import com.finca.arriendo.model.Estado;
import com.finca.arriendo.model.Solicitud;
import com.finca.arriendo.repository.SolicitudRepository;

@Service
public class SolicitudService {

    private final SolicitudRepository solicitudRepository;

    
    public SolicitudService(SolicitudRepository solicitudRepository) {
        this.solicitudRepository = solicitudRepository;
    }

    // Crear una nueva solicitud
    public SolicitudDto crearSolicitud(SolicitudDto solicitudDto) {
        Solicitud solicitud = new Solicitud();
        // Mapear DTO a entidad
        mapToEntity(solicitudDto, solicitud);
        Solicitud nuevaSolicitud = solicitudRepository.save(solicitud);
        return mapToDto(nuevaSolicitud);
    }

    // Obtener todas las solicitudes
    public List<SolicitudDto> obtenerTodasLasSolicitudes() {
        List<Solicitud> solicitudes = solicitudRepository.findAll();
        return solicitudes.stream().map(this::mapToDto).collect(Collectors.toList());
    }

    // Obtener solicitud por ID
    public Optional<SolicitudDto> obtenerSolicitudPorId(Long id) {
        return solicitudRepository.findById(id).map(this::mapToDto);
    }

    // Actualizar solicitud
    public Optional<SolicitudDto> actualizarSolicitud(Long id, SolicitudDto solicitudDto) {
        Optional<Solicitud> optionalSolicitud = solicitudRepository.findById(id);
        if (optionalSolicitud.isPresent()) {
            Solicitud solicitud = optionalSolicitud.get();
            mapToEntity(solicitudDto, solicitud);
            Solicitud updatedSolicitud = solicitudRepository.save(solicitud);
            return Optional.of(mapToDto(updatedSolicitud));
        }
        return Optional.empty();
    }

    // Eliminar solicitud
    public boolean eliminarSolicitud(Long id) {
        if (solicitudRepository.existsById(id)) {
            solicitudRepository.deleteById(id);
            return true;
        }
        return false;
    }

    // Calificar arrendatario
    public Optional<SolicitudDto> calificarArrendatario(Long id, int calificacion) {
        Optional<Solicitud> optionalSolicitud = solicitudRepository.findById(id);
        if (optionalSolicitud.isPresent()) {
            Solicitud solicitud = optionalSolicitud.get();
            solicitud.setCalifArrendatario(calificacion);
            // Cambiar el estado si la calificación se ha completado
            if (solicitud.getCalifFinca() != null) {
                solicitud.setEstado(Estado.CERRADO);
            }
            Solicitud updatedSolicitud = solicitudRepository.save(solicitud);
            return Optional.of(mapToDto(updatedSolicitud));
        }
        return Optional.empty();
    }

    // Calificar finca
    public Optional<SolicitudDto> calificarFinca(Long id, int calificacion) {
        Optional<Solicitud> optionalSolicitud = solicitudRepository.findById(id);
        if (optionalSolicitud.isPresent()) {
            Solicitud solicitud = optionalSolicitud.get();
            solicitud.setCalifFinca(calificacion);
            // Cambiar el estado si la calificación se ha completado
            if (solicitud.getCalifArrendatario() != null) {
                solicitud.setEstado(Estado.CERRADO);
            }
            Solicitud updatedSolicitud = solicitudRepository.save(solicitud);
            return Optional.of(mapToDto(updatedSolicitud));
        }
        return Optional.empty();
    }

    // Métodos privados para mapear entre DTO y entidad
    private SolicitudDto mapToDto(Solicitud solicitud) {
        SolicitudDto dto = new SolicitudDto();
        dto.setId(solicitud.getId());
        dto.setFechaInicio(solicitud.getFechaInicio());
        dto.setFechaFin(solicitud.getFechaFin());
        dto.setCalifFinca(solicitud.getCalifFinca());
        dto.setCalifArrendatario(solicitud.getCalifArrendatario());
        dto.setPrecio(solicitud.getPrecio());
        dto.setCantPersonas(solicitud.getCantPersonas());
        dto.setNumeroCuenta(solicitud.getNumeroCuenta());
        dto.setBanco(solicitud.getBanco());
        dto.setEstado(solicitud.getEstado().name());
        // Aquí puedes incluir la lógica para establecer la visibilidad
        dto.setPagoVisible(calcularPagoVisible(dto.getEstado()));
        dto.setCalificacionVisible(calcularCalificacionVisible(dto.getEstado()));
        return dto;
    }

    private void mapToEntity(SolicitudDto dto, Solicitud solicitud) {
        solicitud.setFechaInicio(dto.getFechaInicio());
        solicitud.setFechaFin(dto.getFechaFin());
        solicitud.setCalifFinca(dto.getCalifFinca());
        solicitud.setCalifArrendatario(dto.getCalifArrendatario());
        solicitud.setPrecio(dto.getPrecio());
        solicitud.setCantPersonas(dto.getCantPersonas());
        solicitud.setNumeroCuenta(dto.getNumeroCuenta());
        solicitud.setBanco(dto.getBanco());
        // Convertir el String a un objeto de tipo Estado
        Estado estado = Estado.valueOf(dto.getEstado());
        solicitud.setEstado(estado);
    }

    private boolean calcularPagoVisible(String estado) {
        return estado.equals("EN_PAGO") && !estado.equals("VENCIDA");
    }

    private boolean calcularCalificacionVisible(String estado) {
        return estado.equals("CERRADO");
    }

    public List<SolicitudDto> findByArrendatarioId(Long id) {
        List<Solicitud> solicitudes = solicitudRepository.findByArrendatarioId(id);
        return solicitudes.stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    public Optional<SolicitudDto> calificar(Long id, SolicitudDto calificacionDto) {
        Solicitud solicitud = solicitudRepository.findById(id)
        .orElseThrow(() -> {
            throw new ResourceNotFoundException("No se encontró la solicitud con el ID: " + id);
        });
    
        // Realizar la calificación
        solicitud.setCalifFinca(calificacionDto.getCalifFinca());
        solicitud.setCalifArrendatario(calificacionDto.getCalifArrendatario());
    
        // Actualizar la solicitud con la calificación
        Solicitud solicitudActualizada = solicitudRepository.save(solicitud);
    
        // Mapear la solicitud actualizada a un objeto de tipo SolicitudDto
        SolicitudDto solicitudDto = mapToDto(solicitudActualizada);
    
        return Optional.of(solicitudDto);
    }

    public class ResourceNotFoundException extends RuntimeException {
        public ResourceNotFoundException(String message) {
            super(message);
        }
    }

    public Optional<SolicitudDto> realizarPago(Long id, String numeroCuenta, String banco) {
        Optional<Solicitud> optionalSolicitud = solicitudRepository.findById(id);
        
        if (optionalSolicitud.isPresent()) {
            Solicitud solicitud = optionalSolicitud.get();
            
            // Aquí puedes establecer el estado de la solicitud como "EN_PAGO"
            solicitud.setEstado(Estado.EN_PAGO); 
            solicitud.setNumeroCuenta(numeroCuenta); 
            solicitud.setBanco(banco);
            
            // Guarda los cambios en la base de datos
            Solicitud updatedSolicitud = solicitudRepository.save(solicitud);
            return Optional.of(mapToDto(updatedSolicitud)); 
        }
        return Optional.empty(); 
    }
}
