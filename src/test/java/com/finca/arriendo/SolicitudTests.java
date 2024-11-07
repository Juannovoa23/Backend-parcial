package com.finca.arriendo;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

import com.finca.arriendo.model.Estado;
import com.finca.arriendo.model.Finca;
import com.finca.arriendo.model.Solicitud;
import com.finca.arriendo.model.Usuario;

public class SolicitudTests {

    @Test
    public void testGettersAndSetters() {
        // Crear instancias de Usuario y Finca
        Usuario arrendatario = new Usuario();
        Usuario arrendador = new Usuario();
        Finca finca = new Finca();

        // Crear una instancia de Solicitud usando el constructor
        Solicitud solicitud = new Solicitud();
        solicitud.setId(1L);
        solicitud.setEstado(Estado.ACEPTADA);
        solicitud.setArrendatario(arrendatario);
        solicitud.setArrendador(arrendador);
        solicitud.setFinca(finca);
        solicitud.setFechaInicio(new Date());
        solicitud.setFechaFin(new Date());
        solicitud.setCalifFinca(4);
        solicitud.setCalifArrendatario(5);
        solicitud.setPrecio(1500.00f);
        solicitud.setCantPersonas(4);
        solicitud.setDeleted(false);

        // Verificar los valores de los atributos usando los getters
        assertEquals(1L, solicitud.getId());
        assertEquals(Estado.ACEPTADA, solicitud.getEstado());
        assertEquals(arrendatario, solicitud.getArrendatario());
        assertEquals(arrendador, solicitud.getArrendador());
        assertEquals(finca, solicitud.getFinca());
        assertEquals(4, solicitud.getCalifFinca());
        assertEquals(5, solicitud.getCalifArrendatario());
        assertEquals(1500.00f, solicitud.getPrecio());
        assertEquals(4, solicitud.getCantPersonas());
        assertFalse(solicitud.isDeleted());
    }

    @Test
    public void testIniciarPago() {
        Usuario arrendatario = new Usuario();
        Usuario arrendador = new Usuario();
        Finca finca = new Finca();

        Solicitud solicitud = new Solicitud(arrendatario, arrendador, finca, new Date(), new Date(),
                                             null, null, 1500.00f, 4, false, Estado.EN_PAGO);
        
        solicitud.iniciarPago(2000.00f, "1234567890", "Banco Ejemplo");

        // Verificar que el precio, número de cuenta y banco se han actualizado
        assertEquals(2000.00f, solicitud.getPrecio());
        assertEquals("1234567890", solicitud.getNumeroCuenta());
        assertEquals("Banco Ejemplo", solicitud.getBanco());
    }

    @Test
    public void testIniciarPagoWhenEstadoNoEsEnPago() {
        Usuario arrendatario = new Usuario();
        Usuario arrendador = new Usuario();
        Finca finca = new Finca();

        Solicitud solicitud = new Solicitud(arrendatario, arrendador, finca, new Date(), new Date(),
                                             null, null, 1500.00f, 4, false, Estado.ACEPTADA);
        
        solicitud.iniciarPago(2000.00f, "1234567890", "Banco Ejemplo");

        // Verificar que el precio, número de cuenta y banco no se han actualizado
        assertEquals(1500.00f, solicitud.getPrecio());
        assertNull(solicitud.getNumeroCuenta());
        assertNull(solicitud.getBanco());
    }

    @Test
    public void testIsPagoVisible() {
        Usuario arrendatario = new Usuario();
        Usuario arrendador = new Usuario();
        Finca finca = new Finca();

        Solicitud solicitud = new Solicitud(arrendatario, arrendador, finca, new Date(), new Date(),
                                             null, null, 1500.00f, 4, false, Estado.EN_PAGO);
        
        assertTrue(solicitud.isPagoVisible());

        solicitud.setEstado(Estado.VENCIDA);
        assertFalse(solicitud.isPagoVisible());
    }

    @Test
    public void testIsCalificacionVisible() {
        Usuario arrendatario = new Usuario();
        Usuario arrendador = new Usuario();
        Finca finca = new Finca();

        Solicitud solicitud = new Solicitud(arrendatario, arrendador, finca, new Date(), new Date(),
                                             null, null, 1500.00f, 4, false, Estado.CERRADO);
        
        assertTrue(solicitud.isCalificacionVisible());

        solicitud.setEstado(Estado.ACEPTADA);
        assertFalse(solicitud.isCalificacionVisible());
    }

    @Test
    public void testIsCalificarVisible() {
        Usuario arrendatario = new Usuario();
        Usuario arrendador = new Usuario();
        Finca finca = new Finca();
        Date fechaEntrega = new Date(System.currentTimeMillis() - 1000 * 60 * 60 * 24); // Ayer

        Solicitud solicitud = new Solicitud(arrendatario, arrendador, finca, fechaEntrega, new Date(),
                                             null, null, 1500.00f, 4, false, Estado.CERRADO);
        
        assertTrue(solicitud.isCalificarVisible());

        solicitud.setFechaFin(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24)); // Mañana
        assertFalse(solicitud.isCalificarVisible());
    }
}
