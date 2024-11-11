package com.finca.arriendo;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.ArgumentMatchers.anyFloat;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;

import com.finca.arriendo.dto.UsuarioDto;
import com.finca.arriendo.model.Estado;
import com.finca.arriendo.model.Finca;
import com.finca.arriendo.model.Solicitud;
import com.finca.arriendo.model.Tipo;
import com.finca.arriendo.model.Usuario;
import com.finca.arriendo.repository.FincaRepository;
import com.finca.arriendo.repository.SolicitudRepository;
import com.finca.arriendo.repository.UsuarioRepository;
import com.finca.arriendo.services.UsuarioService;

class UsuarioServiceTests {

    @InjectMocks
    private UsuarioService usuarioService;

    @Mock
    private UsuarioRepository usuarioRepository;

    @Mock
    private FincaRepository fincaRepository;

    @Mock
    private SolicitudRepository solicitudRepository;

    @Mock
    private ModelMapper modelMapper;
    
    @Mock
    private Usuario usuario;



    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetById() {
        Usuario usuario = new Usuario();
        usuario.setId(1L);
        UsuarioDto usuarioDto = new UsuarioDto();
        usuarioDto.setId(1L);

        when(usuarioRepository.findById(1L)).thenReturn(Optional.of(usuario));
        when(modelMapper.map(usuario, UsuarioDto.class)).thenReturn(usuarioDto);

        UsuarioDto result = usuarioService.get(1L);
        assertEquals(usuarioDto, result);
    }

    @Test
    void testGetByNombre() {
        Usuario usuario = new Usuario();
        usuario.setNombre("Juan");
        UsuarioDto usuarioDto = new UsuarioDto();
        usuarioDto.setNombre("Juan");

        when(usuarioRepository.findByNombre("Juan")).thenReturn(usuario);
        when(modelMapper.map(usuario, UsuarioDto.class)).thenReturn(usuarioDto);

        UsuarioDto result = usuarioService.get("Juan");
        assertEquals(usuarioDto, result);
    }

    @Test
    void testList() {
        Usuario usuario1 = new Usuario();
        usuario1.setId(1L);
        Usuario usuario2 = new Usuario();
        usuario2.setId(2L);

        UsuarioDto usuarioDto1 = new UsuarioDto();
        usuarioDto1.setId(1L);
        UsuarioDto usuarioDto2 = new UsuarioDto();
        usuarioDto2.setId(2L);

        when(usuarioRepository.findAll()).thenReturn(List.of(usuario1, usuario2));
        when(modelMapper.map(usuario1, UsuarioDto.class)).thenReturn(usuarioDto1);
        when(modelMapper.map(usuario2, UsuarioDto.class)).thenReturn(usuarioDto2);

        List<UsuarioDto> result = usuarioService.list();
        assertEquals(List.of(usuarioDto1, usuarioDto2), result);
    }

    @Test
    void testSaveNew() {
         // Crear un DTO de usuario con valores válidos
    UsuarioDto usuarioDto = new UsuarioDto();
    usuarioDto.setId(1L);
    usuarioDto.setCorreo("correo@ejemplo.com");  // Establecer un correo válido
    usuarioDto.setNombre("Juan");
    usuarioDto.setApellido("Pérez");
    usuarioDto.setContrasena("contrasena123");
    usuarioDto.setTipo("ARRENDADOR");

    // Crear una entidad Usuario con los mismos valores
    Usuario usuario = new Usuario();
    usuario.setId(1L);
    usuario.setCorreo("correo@ejemplo.com");
    usuario.setNombre("Juan");
    usuario.setApellido("Pérez");
    usuario.setContrasena("contrasena123");
    usuario.setTipo(Tipo.ARRENDADOR);  // Asumir que "ARRENDADOR" es un valor válido del enum Tipo

    // Configurar los mocks
    when(modelMapper.map(usuarioDto, Usuario.class)).thenReturn(usuario);
    when(usuarioRepository.save(usuario)).thenReturn(usuario);
    when(modelMapper.map(usuario, UsuarioDto.class)).thenReturn(usuarioDto);

    // Llamar al servicio
    UsuarioDto result = usuarioService.saveNew(usuarioDto);

    // Verificar que el resultado sea el esperado
    assertEquals(usuarioDto, result);
    }

    @Test
    void testCalificarArrendatario() {
        // Configurar el mock del repositorio para devolver un usuario mockeado
        when(usuarioRepository.findById(1L)).thenReturn(Optional.of(usuario));

        // Configurar que no haga nada cuando se llame al método agregarCalificacion
        doNothing().when(usuario).agregarCalificacion(anyFloat());

        // Crear el DTO de usuario que se mapeará
        UsuarioDto usuarioDto = new UsuarioDto();
        when(modelMapper.map(usuario, UsuarioDto.class)).thenReturn(usuarioDto);

        // Llamada al servicio
        UsuarioDto result = usuarioService.calificarArrendatario(1L, 4.5f, "Buen arrendatario");

        // Verificar que el resultado sea el esperado
        assertEquals(usuarioDto, result);

        // Verificar que el repositorio haya guardado al usuario
        verify(usuarioRepository).save(usuario);
    }

    @Test
    void testRegistrarUsuario() {
        UsuarioDto usuarioDto = new UsuarioDto();
        usuarioDto.setNombre("Pedro");
        usuarioDto.setApellido("Pérez");
        usuarioDto.setCorreo("pedro@example.com");
        usuarioDto.setContrasena("password123");
        usuarioDto.setTelefono(123456789);
        usuarioDto.setTipo("ARRENDADOR");

        Usuario usuario = new Usuario();
        usuario.setNombre("Pedro");
        usuario.setApellido("Pérez");
        usuario.setCorreo("pedro@example.com");
        usuario.setContrasena("password123");
        usuario.setTelefono(123456789);
        usuario.setTipo(Tipo.ARRENDADOR);

        when(modelMapper.map(usuarioDto, Usuario.class)).thenReturn(usuario);
        when(usuarioRepository.save(usuario)).thenReturn(usuario);
        when(modelMapper.map(usuario, UsuarioDto.class)).thenReturn(usuarioDto);

        UsuarioDto result = usuarioService.registrarUsuario(usuarioDto);

        assertNotNull(result, "El resultado no puede ser null");
        assertEquals(usuarioDto.getNombre(), result.getNombre(), "Los nombres no coinciden");
        assertEquals(usuarioDto.getApellido(), result.getApellido(), "Los apellidos no coinciden");
        assertEquals(usuarioDto.getCorreo(), result.getCorreo(), "Los correos no coinciden");
        assertEquals(usuarioDto.getTelefono(), result.getTelefono(), "Los teléfonos no coinciden");
        assertEquals(usuarioDto.getTipo(), result.getTipo(), "Los tipos no coinciden");
    }

    @Test
    void testActivarCuenta() {
        Usuario usuario = new Usuario();
        usuario.setId(1L);

        when(usuarioRepository.findById(1L)).thenReturn(Optional.of(usuario));

        boolean result = usuarioService.activarCuenta(1L);
        assertTrue(result);
        verify(usuarioRepository).save(usuario);
    }

    @Test
    void testAutenticar() {
        Usuario usuario = new Usuario();
        usuario.setNombre("testUser"); 
        usuario.setContrasena("password");
        when(usuarioRepository.findByNombre("testUser")).thenReturn(usuario); 
        boolean result = usuarioService.autenticar("testUser", "password");
        assertTrue(result);
    }

    @Test
    void testBuscarPropiedades() {
        Finca finca = new Finca();
        finca.setNombre("Finca Bella");
        when(usuarioRepository.findByNombreAndMunicipioAndCapacidad(anyString(), anyString(), anyInt()))
                .thenReturn(List.of(finca));

        List<String> result = usuarioService.buscarPropiedades("Finca Bella", "Medellín", 5);
        assertEquals(List.of("Finca Bella"), result);
    }

    @Test
    void testSolicitarArriendo() throws Exception {
        Usuario usuario = new Usuario();
    usuario.setId(1L);
    Finca finca = spy(new Finca());  
    finca.setId(1L);
    finca.setCapacidad(10);

    when(usuarioRepository.findById(1L)).thenReturn(Optional.of(usuario));
    when(fincaRepository.findById(1L)).thenReturn(Optional.of(finca));
    
    doReturn(true).when(finca).estaDisponible("2024-10-01", "2024-10-10");

    boolean result = usuarioService.solicitarArriendo(1L, 1L, "2024-10-01", "2024-10-10", 5);
    assertTrue(result);
    }

    @Test
    void testPagarArriendo() {
        Solicitud solicitud = new Solicitud();
        solicitud.setEstado(Estado.valueOf("EN_TRAMITE"));

        when(solicitudRepository.findById(1L)).thenReturn(Optional.of(solicitud));

        boolean result = usuarioService.pagarArriendo(1L);
        assertTrue(result);
        verify(solicitudRepository).save(solicitud);
    }
}
