package com.finca.arriendo;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.finca.arriendo.controllers.UsuarioController;
import com.finca.arriendo.dto.UsuarioDto;
import com.finca.arriendo.services.UsuarioService;

class UsuarioControllerTests {

    @InjectMocks
    private UsuarioController usuarioController;

    @Mock
    private UsuarioService usuarioService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetList() throws Exception {
        UsuarioDto usuarioDto1 = new UsuarioDto();
        UsuarioDto usuarioDto2 = new UsuarioDto();

        when(usuarioService.list()).thenReturn(List.of(usuarioDto1, usuarioDto2));

        ResponseEntity<List<UsuarioDto>> response = usuarioController.get();
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(List.of(usuarioDto1, usuarioDto2), response.getBody());
    }

    @Test
    void testGetById() throws Exception {
        UsuarioDto usuarioDto = new UsuarioDto();

        when(usuarioService.get(1L)).thenReturn(usuarioDto);

        ResponseEntity<UsuarioDto> response = usuarioController.get(1L);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(usuarioDto, response.getBody());
    }

    @Test
    void testGetByNombre() throws Exception {
        UsuarioDto usuarioDto = new UsuarioDto();

        when(usuarioService.get("nombre")).thenReturn(usuarioDto);

        ResponseEntity<UsuarioDto> response = usuarioController.getByNombre("nombre");
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(usuarioDto, response.getBody());
    }

    @Test
    void testSave() throws Exception {
        UsuarioDto usuarioDto = new UsuarioDto();

        when(usuarioService.saveNew(usuarioDto)).thenReturn(usuarioDto);

        ResponseEntity<UsuarioDto> response = usuarioController.save(usuarioDto);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(usuarioDto, response.getBody());
    }

    @Test
    void testUpdate() throws Exception {
        UsuarioDto usuarioDto = new UsuarioDto();

        when(usuarioService.save(usuarioDto)).thenReturn(usuarioDto);

        ResponseEntity<UsuarioDto> response = usuarioController.update(usuarioDto);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(usuarioDto, response.getBody());
    }

    @Test
    void testDelete() throws Exception {
        UsuarioDto usuarioDto = new UsuarioDto();

        doNothing().when(usuarioService).delete(usuarioDto);

        ResponseEntity<Void> response = usuarioController.delete(usuarioDto);
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(usuarioService, times(1)).delete(usuarioDto);
    }
}
