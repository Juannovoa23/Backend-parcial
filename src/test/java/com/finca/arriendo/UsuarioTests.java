package com.finca.arriendo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.finca.arriendo.model.Tipo;
import com.finca.arriendo.model.Usuario;

public class UsuarioTests {

    private Usuario usuario;

    @BeforeEach
    public void setUp() {
        // Crear una instancia de Usuario antes de cada prueba
        usuario = new Usuario();
        usuario.setId(1L);
        usuario.setNombre("Juan");
        usuario.setApellido("Pérez");
        usuario.setCorreo("juan.perez@example.com");
        usuario.setTelefono(123456789);
        usuario.setContrasena("password");
        usuario.setTipo(Tipo.ARRENDATARIO);
        usuario.setCalificacion(5.0f);
        usuario.setDeleted(false);
    }

    @Test
    public void testGettersAndSetters() {
        // Verificar los valores de los atributos usando los getters
        assertEquals(1L, usuario.getId());
        assertEquals("Juan", usuario.getNombre());
        assertEquals("Pérez", usuario.getApellido());
        assertEquals("juan.perez@example.com", usuario.getCorreo());
        assertEquals(123456789, usuario.getTelefono());
        assertEquals("password", usuario.getContrasena());
        assertEquals(5.0f, usuario.getCalificacion());
        assertFalse(usuario.isDeleted());
    }

    @Test
    public void testAgregarCalificacion() {
        // Verificar que se pueda agregar una nueva calificación
        usuario.agregarCalificacion(4.0f);
        assertEquals(4.5f, usuario.getCalificacion(), 0.01); // Promedio de 5.0 y 4.0
    }

    @Test
    public void testRegistrarUsuario() {
        // Registrar un nuevo usuario
        usuario.registrarUsuario("Ana", "García", "ana.garcia@example.com", "newpassword", 987654321, Tipo.ARRENDADOR);
        assertEquals("Ana", usuario.getNombre());
        assertEquals("García", usuario.getApellido());
        assertEquals("ana.garcia@example.com", usuario.getCorreo());
        assertEquals("newpassword", usuario.getContrasena());
        assertEquals(987654321, usuario.getTelefono());
        assertEquals(Tipo.ARRENDADOR, usuario.getTipo());
    }

    @Test
    public void testObtenerSolicitudes() {
        // Verificar que las solicitudes se obtengan correctamente según el tipo
        assertTrue(usuario.esSolicitante()); // Usuario es arrendatario
        assertFalse(usuario.esArrendador());

        usuario.setTipo(Tipo.ARRENDADOR); // Cambiar a arrendador
        assertTrue(usuario.esArrendador());
        assertFalse(usuario.esSolicitante());
    }

    @Test
    public void testValidacionCampos() {
        // Validar que se lance una excepción si el correo es nulo
        Usuario usuarioInvalido = new Usuario();
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            usuarioInvalido.setContrasena("password");
            usuarioInvalido.setCorreo(null);
            usuarioInvalido.validar(); // Llamar a validate para activar la validación
        });

        assertEquals("El correo no puede estar vacío.", exception.getMessage());
    }

    @Test
    public void testValidarContrasenaNula() {
        // Validar que se lance una excepción si la contraseña es nula
        Usuario usuarioInvalido = new Usuario();
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            usuarioInvalido.setCorreo("correo@example.com");
            usuarioInvalido.setContrasena(null);
            usuarioInvalido.validar(); // Llamar a validate para activar la validación
        });

        assertEquals("La contraseña no puede estar vacía.", exception.getMessage());
    }

    @Test
    public void testIsDeleted() {
        // Verificar que la propiedad deleted funcione correctamente
        usuario.setDeleted(true);
        assertTrue(usuario.isDeleted());
    }
}
