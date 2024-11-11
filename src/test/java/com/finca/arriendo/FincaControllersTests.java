package com.finca.arriendo;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.finca.arriendo.controllers.FincaController;
import com.finca.arriendo.dto.FincaDto;
import com.finca.arriendo.repository.FincaRepository;
import com.finca.arriendo.services.FincaService;

class FincaControllerTests {


    @InjectMocks
    private FincaController fincaController;

    @Mock
    private FincaService fincaService;

    @Autowired
    private FincaRepository fincaRepository;

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
        //Crear una instancia para Finca y la guardamos en la base de datos para obtener un ID
        FincaDto fincaDto = new FincaDto();
        fincaDto.setNombre("Finca de Prueba");
        fincaDto.setUbicacion("Ubicación de prueba");
        fincaDto.setId(1L); 

        // Configurar el mock para que fincaService.saveNew devuelva fincaDto con el ID asignado
        when(fincaService.saveNew(fincaDto)).thenReturn(fincaDto);

        //Guardar finca en la base de datos para generar el ID de forma automatica
        fincaDto = fincaService.saveNew(fincaDto);

        // Verificar que tiene un ID asignado 
        assertNotNull(fincaDto.getId(), "El ID de la finca no debe ser nulo");

        //Configurar el mock con tal de que fincaService.update devuelva el mismo FincaDto
        when(fincaService.update(fincaDto)).thenReturn(fincaDto);

        //Llamar al metodo update del controlador
        ResponseEntity<FincaDto> result = fincaController.update(fincaDto);

        //Verificar el estado de la respuesta
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

    when(fincaService.calificarFinca(1L, 5)).thenReturn(calificacionDto);

    ResponseEntity<FincaDto> result = fincaController.calificarFinca(1L, calificacionDto);

    assertEquals(HttpStatus.OK, result.getStatusCode());
    assertEquals(calificacionDto, result.getBody());
}

@Test
void testCalificarArrendatario() throws Exception {
    FincaDto calificacionDto = new FincaDto();
    calificacionDto.setCalificacion(4);

    doNothing().when(fincaService).calificarArrendatario(1L, 4);

    ResponseEntity<Void> result = fincaController.calificarArrendatario(1L, calificacionDto);
    
    assertEquals(HttpStatus.OK, result.getStatusCode());
    doNothing().when(fincaService).calificarArrendatario(1L, 4);
   }
}
