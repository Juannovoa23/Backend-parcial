package com.finca.arriendo;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.ArgumentMatchers.any;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;

import com.finca.arriendo.dto.SolicitudDto;
import com.finca.arriendo.model.Estado;
import com.finca.arriendo.model.Solicitud;
import com.finca.arriendo.repository.SolicitudRepository;
import com.finca.arriendo.services.SolicitudService;

class SolicitudServiceTests {

    @InjectMocks
    private SolicitudService solicitudService;

    @Mock
    private SolicitudRepository solicitudRepository;

    @Mock
    private ModelMapper modelMapper;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testObtenerSolicitudPorId() {
        Solicitud solicitud = new Solicitud();
        solicitud.setId(1L);
        SolicitudDto solicitudDto = new SolicitudDto();
        solicitudDto.setId(1L);

        when(solicitudRepository.findById(1L)).thenReturn(Optional.of(solicitud));
        when(modelMapper.map(solicitud, SolicitudDto.class)).thenReturn(solicitudDto);

        Optional<SolicitudDto> result = solicitudService.obtenerSolicitudPorId(1L);
        assertTrue(result.isPresent());
        assertEquals(solicitudDto.getId(), result.get().getId());
    }

    @Test
    void testObtenerTodasLasSolicitudes() {
        Solicitud solicitud1 = new Solicitud();
        solicitud1.setId(1L);
        Solicitud solicitud2 = new Solicitud();
        solicitud2.setId(2L);

        SolicitudDto solicitudDto1 = new SolicitudDto();
        solicitudDto1.setId(1L);
        SolicitudDto solicitudDto2 = new SolicitudDto();
        solicitudDto2.setId(2L);

        when(solicitudRepository.findAll()).thenReturn(List.of(solicitud1, solicitud2));
        when(modelMapper.map(solicitud1, SolicitudDto.class)).thenReturn(solicitudDto1);
        when(modelMapper.map(solicitud2, SolicitudDto.class)).thenReturn(solicitudDto2);

        List<SolicitudDto> result = solicitudService.obtenerTodasLasSolicitudes();
        assertEquals(2, result.size());
        assertEquals(solicitudDto1.getId(), result.get(0).getId());
        assertEquals(solicitudDto2.getId(), result.get(1).getId());
    }

    @Test
    void testCrearSolicitud() {
        // Crear un DTO de solicitud con valores básicos para la clase
    SolicitudDto solicitudDto = new SolicitudDto();
    solicitudDto.setId(1L);
    solicitudDto.setEstado(Estado.ACEPTADA);

    // Se va a configurar la entidad Solicitud con el estado
    Solicitud solicitud = new Solicitud();
    solicitud.setId(1L);
    solicitud.setEstado(Estado.ACEPTADA);

    // Se configura el comportamiento del mock para el repositorio
    when(solicitudRepository.save(any(Solicitud.class))).thenReturn(solicitud);

    // Llamar al servicio
    SolicitudDto result = solicitudService.crearSolicitud(solicitudDto);

    // Verificar que el ID y estado sean los correctos
    assertNotNull(result, "El resultado no debe ser null");
    assertEquals(solicitudDto.getId(), result.getId());
    assertEquals(solicitudDto.getEstado(), result.getEstado());

    // Verificar que el repositorio haya guardado la solicitud
    verify(solicitudRepository).save(any(Solicitud.class));
    }

    @Test
    void testEliminarSolicitud() {
        Long id = 1L;

        when(solicitudRepository.existsById(id)).thenReturn(true);
        doNothing().when(solicitudRepository).deleteById(id);

        boolean result = solicitudService.eliminarSolicitud(id);
        assertTrue(result);

        verify(solicitudRepository, times(1)).deleteById(id);
    }

    @Test
    void testActualizarSolicitud() {
        Long id = 1L;
        SolicitudDto solicitudDto = new SolicitudDto();
        solicitudDto.setId(id);

        Solicitud solicitud = new Solicitud();
        solicitud.setId(id);
        solicitud.setEstado(Estado.ACEPTADA);

        when(solicitudRepository.findById(id)).thenReturn(Optional.of(solicitud));
        when(solicitudRepository.save(solicitud)).thenReturn(solicitud);

        Optional<SolicitudDto> result = solicitudService.actualizarSolicitud(id, solicitudDto);
        assertTrue(result.isPresent());
        assertEquals(solicitudDto.getId(), result.get().getId());
    }

    @Test
    void testCalificarArrendatario() {
        Long id = 1L;
        int calificacion = 5;

        Solicitud solicitud = new Solicitud();
        solicitud.setId(id);

        when(solicitudRepository.findById(id)).thenReturn(Optional.of(solicitud));
        when(solicitudRepository.save(solicitud)).thenReturn(solicitud);

        Optional<SolicitudDto> result = solicitudService.calificarArrendatario(id, calificacion);
        assertTrue(result.isPresent());
        assertEquals(calificacion, result.get().getCalifArrendatario());
    }

    @Test
    void testCalificarFinca() {
        Long id = 1L;
        int calificacion = 4;

        Solicitud solicitud = new Solicitud();
        solicitud.setId(id);

        when(solicitudRepository.findById(id)).thenReturn(Optional.of(solicitud));
        when(solicitudRepository.save(solicitud)).thenReturn(solicitud);

        Optional<SolicitudDto> result = solicitudService.calificarFinca(id, calificacion);
        assertTrue(result.isPresent());
        assertEquals(calificacion, result.get().getCalifFinca());
    }

    @Test
    void testRealizarPago() {
        Long id = 1L;
        String numeroCuenta = "123456789";
        String banco = "Banco Test";

        Solicitud solicitud = new Solicitud();
        solicitud.setId(id);
        solicitud.setNumeroCuenta(""); //El numero de cuenta estará vacio inicialmente
        solicitud.setBanco(""); //El nombre del banco estará vacio inicialmente

        when(solicitudRepository.findById(id)).thenReturn(Optional.of(solicitud));
        when(solicitudRepository.save(solicitud)).thenAnswer(invocation -> {
            Solicitud savedSolicitud = invocation.getArgument(0);
            //Verificar que tanto el número de cuenta y el banco se hayan actualizado de forma correcta
            assertEquals(numeroCuenta,savedSolicitud.getNumeroCuenta());
            assertEquals(banco, savedSolicitud.getBanco());
            return savedSolicitud;
        });

        Optional<SolicitudDto> result = solicitudService.realizarPago(id, numeroCuenta, banco);
        assertTrue(result.isPresent());
        assertEquals(numeroCuenta, result.get().getNumeroCuenta()); 
        assertEquals(banco, result.get().getBanco()); 
    }
}
