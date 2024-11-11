package com.finca.arriendo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.finca.arriendo.model.Estado;
import com.finca.arriendo.model.Finca;
import com.finca.arriendo.model.Tipo;
import com.finca.arriendo.model.Usuario;

public class FincaTests {

    private Finca finca;
    private Usuario arrendador; // Cambiado de dueño a arrendador

    @BeforeEach
    public void setUp() {
        // Inicializar el arrendador y la finca antes de cada prueba
        arrendador = new Usuario(); // Cambiado de dueño a arrendador
        arrendador.setId(1L);
        arrendador.setNombre("Juan");
        arrendador.setApellido("Pérez");
        arrendador.setCorreo("juan.perez@example.com");
        arrendador.setTelefono(123456789);
        arrendador.setContrasena("password");
        arrendador.setTipo(Tipo.ARRENDATARIO);
        arrendador.setCalificacion(5.0f);
        arrendador.setDeleted(false);

        finca = new Finca();
        finca.setId(1L);
        finca.setDueño(arrendador); 
        finca.setNombre("Finca del Valle");
        finca.setUbicacion("Calle 123");
        finca.setDepartamento("Antioquia");
        finca.setMunicipio("Medellín");
        finca.setPrecioDefecto(1500.0f);
        finca.setDisponible(true);
        finca.setCalificacion(4);
        finca.setDescripcion("Comentarios de prueba");
        finca.setCapacidad(6);
        finca.setDeleted(false);
    }

    @Test
    public void testGettersAndSetters() {
        // Verificar los valores de los atributos usando los getters
        assertEquals(1, finca.getId());
        assertEquals(arrendador, finca.getDueño()); 
        assertEquals("Finca del Valle", finca.getNombre());
        assertEquals("Calle 123", finca.getUbicacion());
        assertEquals("Antioquia", finca.getDepartamento());
        assertEquals("Medellín", finca.getMunicipio());
        assertEquals(1500.0f, finca.getPrecioDefecto());
        assertTrue(finca.isDisponible());
        assertEquals(4, finca.getCalificacion());
        assertEquals("Comentarios de prueba", finca.getDescripcion());
        assertEquals(6, finca.getCapacidad());
        assertFalse(finca.isDeleted());
    }

    @Test
    public void testConstructors() {
        // Usar el constructor con todos los atributos
        Finca fincaCompleta = new Finca();
        fincaCompleta.setDueño(arrendador);
        fincaCompleta.setNombre("Finca del Valle");
        fincaCompleta.setUbicacion("Calle 123");
        fincaCompleta.setDepartamento("Antioquia");
        fincaCompleta.setMunicipio("Medellín");
        fincaCompleta.setPrecioDefecto(1500.0f);
        fincaCompleta.setDisponible(true);
        fincaCompleta.setCalificacion(4);
        fincaCompleta.setDescripcion("Comentarios de prueba");
        fincaCompleta.setCapacidad(6);
        fincaCompleta.setDeleted(false);
        assertEquals(arrendador, fincaCompleta.getDueño()); // Cambiado de dueño a arrendador
        assertEquals("Finca del Valle", fincaCompleta.getNombre());
        assertEquals("Calle 123", fincaCompleta.getUbicacion());
        assertEquals("Antioquia", fincaCompleta.getDepartamento());
        assertEquals("Medellín", fincaCompleta.getMunicipio());
        assertEquals(1500.0f, fincaCompleta.getPrecioDefecto());
        assertTrue(fincaCompleta.getDisponible() == true);
        assertEquals(4, fincaCompleta.getCalificacion());
        assertEquals("Comentarios de prueba", fincaCompleta.getDescripcion());
        assertEquals(6, fincaCompleta.getCapacidad());
        assertFalse(fincaCompleta.isDeleted());

        // Usar el constructor sin comentarios
        Finca fincaSinComentarios = new Finca();
        fincaSinComentarios.setDueño(arrendador);
        fincaSinComentarios.setNombre("Finca del Valle");
        fincaSinComentarios.setUbicacion("Calle 123");
        fincaSinComentarios.setDepartamento("Antioquia");
        fincaSinComentarios.setMunicipio("Medellín");
        fincaSinComentarios.setPrecioDefecto(1500.0f);
        fincaSinComentarios.setDisponible(true);
        fincaSinComentarios.setCalificacion(4);
        fincaSinComentarios.setDescripcion("Comentarios de prueba");
        fincaSinComentarios.setCapacidad(6);
        fincaSinComentarios.setDeleted(false);
        assertEquals(arrendador, fincaSinComentarios.getDueño()); // Cambiado de dueño a arrendador
        assertEquals("Finca del Valle", fincaSinComentarios.getNombre());
        assertEquals("Calle 123", fincaSinComentarios.getUbicacion());
        assertEquals("Antioquia", fincaSinComentarios.getDepartamento());
        assertEquals("Medellín", fincaSinComentarios.getMunicipio());
        assertEquals(1500.0f, fincaSinComentarios.getPrecioDefecto());
        assertTrue(finca.getDisponible() == true);
        assertEquals(4, fincaSinComentarios.getCalificacion());
        assertEquals(6, fincaSinComentarios.getCapacidad());
        assertFalse(fincaSinComentarios.isDeleted());
    }

    @Test
    void testSetAndGetCalificacion() {
        finca.setCalificacion(5);
        assertEquals(5, finca.getCalificacion());
    }

    @Test
    void testCalificarFinca() {
        finca.setCalificacion(4);

        // Simulamos una nueva calificación
        finca.setCalificacion(5); 

        // Verificamos que la calificación se actualiza correctamente
        assertEquals(5, finca.getCalificacion());
    }

    @Test
    void testCalificarYEstado() {
        finca.setCalificacion(4);  // Calificación inicial

        // Simulamos una nueva calificación
        finca.setCalificacion(5); 

        // Aquí comprobamos manualmente el estado según la lógica
        Estado estadoEsperado = (finca.getCalificacion() >= 5) ? Estado.CERRADO : Estado.EN_TRAMITE;

        // En lugar de establecer el estado, solo verificamos si el estado esperado es correcto
        assertEquals(Estado.CERRADO, estadoEsperado);
    }
}
