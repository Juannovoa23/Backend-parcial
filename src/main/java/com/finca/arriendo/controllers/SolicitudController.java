package com.finca.arriendo.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.finca.arriendo.dto.SolicitudDto;
import com.finca.arriendo.services.SolicitudService;

@RestController
@RequestMapping("/api/solicitudes")
@CrossOrigin
public class SolicitudController {

    private final SolicitudService solicitudService;

    public SolicitudController(SolicitudService solicitudService) {
        this.solicitudService = solicitudService;
    }

    // Listar todas las solicitudes
    @GetMapping("/list")
    public List<SolicitudDto> list() {
        return solicitudService.obtenerTodasLasSolicitudes();
    }

    // Obtener solicitud por ID
    @GetMapping("/get/{id}")
    public Optional<SolicitudDto> get(@PathVariable Long id) {
        return solicitudService.obtenerSolicitudPorId(id);
    }

    // Obtener solicitudes por ID del arrendatario
    @GetMapping("/arrendatario/{id}")
    public List<SolicitudDto> findByArrendatarioId(@PathVariable Long id) {
        return solicitudService.findByArrendatarioId(id);
    }

    // Crear una nueva solicitud
    @PostMapping("/create")
    public SolicitudDto create(@RequestBody SolicitudDto solicitudDto) {
        return solicitudService.crearSolicitud(solicitudDto);
    }

    // Actualizar una solicitud
    @PutMapping("/update/{id}")
    public Optional<SolicitudDto> update(@PathVariable Long id, @RequestBody SolicitudDto solicitudDto) {
        return solicitudService.actualizarSolicitud(id, solicitudDto);
    }

    // Eliminar una solicitud
    @DeleteMapping("/delete/{id}")
    public boolean delete(@PathVariable Long id) {
        return solicitudService.eliminarSolicitud(id);
    }

    // Calificar una solicitud
    @PutMapping("/calificar/{id}")
    public Optional<SolicitudDto> calificar(@PathVariable Long id, @RequestBody SolicitudDto calificacionDto) throws Exception {
        return solicitudService.calificar(id, calificacionDto);
    }

    // Realizar el pago de una solicitud
    @PutMapping("/pagar/{id}")
    public Optional<SolicitudDto> pagar(@PathVariable Long id, @RequestParam String numeroCuenta, @RequestParam String banco) throws Exception {
        return solicitudService.realizarPago(id, numeroCuenta, banco);
    }
}
