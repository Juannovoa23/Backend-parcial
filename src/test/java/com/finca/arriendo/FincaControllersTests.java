package com.finca.arriendo;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.finca.arriendo.controllers.FincaController;
import com.finca.arriendo.dto.FincaDto;
import com.finca.arriendo.services.FincaService;

class FincaControllerTests {

    @InjectMocks
    private FincaController fincaController;

    @Mock
    private FincaService fincaService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetList() throws Exception {
        FincaDto fincaDto1 = new FincaDto();
        FincaDto fincaDto2 = new FincaDto();

        when(fincaService.list()).thenReturn(List.of(fincaDto1, fincaDto2));

        ResponseEntity<List<FincaDto>> result = fincaController.getAll();
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(List.of(fincaDto1, fincaDto2), result.getBody());
    }

    @Test
    void testGetById() throws Exception {
        FincaDto fincaDto = new FincaDto();

        when(fincaService.get(1L)).thenReturn(fincaDto);

        ResponseEntity<FincaDto> result = fincaController.getById(1L);
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(fincaDto, result.getBody());
    }

    @Test
    void testGetByNombre() throws Exception {
        FincaDto fincaDto = new FincaDto();

        when(fincaService.get("nombre")).thenReturn(fincaDto);

        ResponseEntity<FincaDto> result = fincaController.obtenerPorNombre("nombre");
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(fincaDto, result.getBody());
    }

    @Test
    void testSave() throws Exception {
        FincaDto fincaDto = new FincaDto();

        when(fincaService.saveNew(fincaDto)).thenReturn(fincaDto);

        ResponseEntity<FincaDto> result = fincaController.save(fincaDto);
        assertEquals(HttpStatus.CREATED, result.getStatusCode());
        assertEquals(fincaDto, result.getBody());
    }

    @Test
    void testUpdate() throws Exception {
        FincaDto fincaDto = new FincaDto();

        when(fincaService.saveNew(fincaDto)).thenReturn(fincaDto);

        ResponseEntity<FincaDto> result = fincaController.update(fincaDto);
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(fincaDto, result.getBody());
    }

    @Test
    void testDelete() throws Exception {
        FincaDto fincaDto = new FincaDto();

        doNothing().when(fincaService).delete(fincaDto);

        ResponseEntity<Void> result = fincaController.delete(fincaDto);
        assertEquals(HttpStatus.NO_CONTENT, result.getStatusCode());
        verify(fincaService, times(1)).delete(fincaDto);
    }

    @Test
void testCalificarFinca() throws Exception {
    FincaDto calificacionDto = new FincaDto();
    calificacionDto.setCalificacion(5);
    calificacionDto.setComentarios("Excelente");

    when(fincaService.calificarFinca(1L, 5, new String[] {"Excelente"})).thenReturn(calificacionDto);

    ResponseEntity<FincaDto> result = fincaController.calificarFinca(1L, calificacionDto);

    assertEquals(HttpStatus.OK, result.getStatusCode());
    assertEquals(calificacionDto, result.getBody());
}

@Test
void testCalificarArrendatario() throws Exception {
    FincaDto calificacionDto = new FincaDto();
    calificacionDto.setCalificacion(4);
    calificacionDto.setComentarios("Buena experiencia");

    doNothing().when(fincaService).calificarArrendatario(1L, 4, new String[] {"Buena experiencia"});

    ResponseEntity<Void> result = fincaController.calificarArrendatario(1L, calificacionDto);
    
    assertEquals(HttpStatus.OK, result.getStatusCode());
    doNothing().when(fincaService).calificarArrendatario(1L, 4, new String[] {"Buena experiencia"});
}
}
