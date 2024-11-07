package com.finca.arriendo;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;

import com.finca.arriendo.controllers.SolicitudController;
import com.finca.arriendo.dto.SolicitudDto;
import com.finca.arriendo.services.SolicitudService;

class SolicitudControllerTests {

    @InjectMocks
    private SolicitudController solicitudController;

    @Mock
    private SolicitudService solicitudService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetList() {
        SolicitudDto solicitudDto1 = new SolicitudDto();
        SolicitudDto solicitudDto2 = new SolicitudDto();

        when(solicitudService.obtenerTodasLasSolicitudes()).thenReturn(List.of(solicitudDto1, solicitudDto2));

        List<SolicitudDto> result = solicitudController.list();
        assertEquals(List.of(solicitudDto1, solicitudDto2), result);
    }

    @Test
    void testGetById() {
        SolicitudDto solicitudDto = new SolicitudDto();
        solicitudDto.setId(1L);

        when(solicitudService.obtenerSolicitudPorId(1L)).thenReturn(Optional.of(solicitudDto));

        Optional<SolicitudDto> result = solicitudController.get(1L);
        assertTrue(result.isPresent());
        assertEquals(solicitudDto, result.get());
    }

    @Test
    void testCreate() {
        SolicitudDto solicitudDto = new SolicitudDto();

        when(solicitudService.crearSolicitud(any(SolicitudDto.class))).thenReturn(solicitudDto);

        SolicitudDto result = solicitudController.create(solicitudDto);
        assertEquals(solicitudDto, result);
    }

    @Test
    void testUpdate() {
        Long id = 1L;
        SolicitudDto solicitudDto = new SolicitudDto();
        solicitudDto.setId(id);

        when(solicitudService.actualizarSolicitud(anyLong(), any(SolicitudDto.class))).thenReturn(Optional.of(solicitudDto));

        Optional<SolicitudDto> result = solicitudController.update(id, solicitudDto);
        assertTrue(result.isPresent());
        assertEquals(solicitudDto, result.get());
    }

    @Test
    void testDelete() {
        Long id = 1L;

        when(solicitudService.eliminarSolicitud(id)).thenReturn(true);
        solicitudController.delete(id);

        verify(solicitudService, times(1)).eliminarSolicitud(id);
    }

    @Test
    void testCalificar() throws Exception {
        Long id = 1L;
        SolicitudDto calificacionDto = new SolicitudDto();
        calificacionDto.setCalifArrendatario(5);
        calificacionDto.setCalifFinca(4);

        when(solicitudService.calificar(id, calificacionDto)).thenReturn(Optional.of(calificacionDto));

        Optional<SolicitudDto> result = solicitudController.calificar(id, calificacionDto);
        assertTrue(result.isPresent());
        assertEquals(calificacionDto, result.get());
    }

    @Test
    void testPagar() throws Exception {
        Long id = 1L;
        String numeroCuenta = "123456789";
        String banco = "Banco Test";
        SolicitudDto solicitudDto = new SolicitudDto();
        solicitudDto.setId(id);

        when(solicitudService.realizarPago(id, numeroCuenta, banco)).thenReturn(Optional.of(solicitudDto));

        Optional<SolicitudDto> result = solicitudController.pagar(id, numeroCuenta, banco);
        assertTrue(result.isPresent());
        assertEquals(solicitudDto, result.get());
    }
}
