package com.finca.arriendo;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import com.finca.arriendo.dto.FincaDto;
import com.finca.arriendo.model.Finca;
import com.finca.arriendo.repository.FincaRepository;
import com.finca.arriendo.services.FincaService;

@ExtendWith(MockitoExtension.class)  // Asegura que los mocks se inicialicen correctamente
class FincaServiceTests {

    @InjectMocks
    private FincaService fincaService;

    private Finca finca;
    private FincaDto fincaDto;

    @Mock
    private FincaRepository fincaRepository;

    @Mock
    private ModelMapper modelMapper;

    @BeforeEach
    public void setUp() {
        finca = new Finca();
        finca.setId(1);
        finca.setCalificacion(0);
        fincaDto = new FincaDto();
        fincaDto.setId(1L);
        fincaDto.setCalificacion(5);
    }

    @Test
    void testGetById() {
        when(fincaRepository.findById(1L)).thenReturn(Optional.of(finca));
        when(modelMapper.map(finca, FincaDto.class)).thenReturn(fincaDto);

        FincaDto result = fincaService.get(1L);
        assertEquals(fincaDto, result);
    }

    @Test
    void testGetByNombre() {
        finca.setNombre("Finca A");
        fincaDto.setNombre("Finca A");

        when(fincaRepository.findById(1L)).thenReturn(Optional.of(finca));
        when(modelMapper.map(finca, FincaDto.class)).thenReturn(fincaDto);

        FincaDto result = fincaService.get("Finca A");
        assertEquals(fincaDto, result);
    }

    @Test
    void testList() {
        Finca finca1 = new Finca();
        finca1.setId(1);
        Finca finca2 = new Finca();
        finca2.setId(2);

        FincaDto fincaDto1 = new FincaDto();
        fincaDto1.setId(1L);
        FincaDto fincaDto2 = new FincaDto();
        fincaDto2.setId(2L);

        when(fincaRepository.findAll()).thenReturn(List.of(finca1, finca2));
        when(modelMapper.map(finca1, FincaDto.class)).thenReturn(fincaDto1);
        when(modelMapper.map(finca2, FincaDto.class)).thenReturn(fincaDto2);

        List<FincaDto> result = fincaService.list();
        assertEquals(List.of(fincaDto1, fincaDto2), result);
    }

    @Test
    void testSaveNew() {
        fincaDto.setId(1L);

        when(modelMapper.map(fincaDto, Finca.class)).thenReturn(finca);
        when(fincaRepository.save(finca)).thenReturn(finca);
        when(modelMapper.map(finca, FincaDto.class)).thenReturn(fincaDto);

        FincaDto result = fincaService.saveNew(fincaDto);
        assertEquals(fincaDto, result);
    }

    @Test
    void testSave() {
        fincaDto.setId(1L);

        when(modelMapper.map(fincaDto, Finca.class)).thenReturn(finca);
        when(fincaRepository.save(finca)).thenReturn(finca);
        when(modelMapper.map(finca, FincaDto.class)).thenReturn(fincaDto);

        FincaDto result = fincaService.saveNew(fincaDto);
        assertEquals(fincaDto, result);
    }

    @Test
    void testDelete() {
        when(modelMapper.map(fincaDto, Finca.class)).thenReturn(finca);
        doNothing().when(fincaRepository).delete(finca);

        fincaService.delete(fincaDto);
        verify(fincaRepository, times(1)).delete(finca);
    }

    @Test
    void testCalificarFinca() {
        when(fincaRepository.findById(1L)).thenReturn(Optional.of(finca));
        when(fincaRepository.save(finca)).thenReturn(finca);
        when(modelMapper.map(finca, FincaDto.class)).thenReturn(fincaDto);

        FincaDto result = fincaService.calificarFinca(1L, 5);

        // Verifica que la calificación se haya actualizado
        assertEquals(5, finca.getCalificacion());
        assertEquals(fincaDto, result);
    }

    @Test
    void testCalificarFincaNotFound() {
        when(fincaRepository.findById(1L)).thenReturn(Optional.empty());

        FincaDto result = fincaService.calificarFinca(1L, 5);
        assertNull(result); // Debe devolver null si no se encuentra la finca
    }
}
