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
        solicitud.setEstado(Estado.EN_PAGO);

        // Verificar los valores de los atributos usando los getters
        assertEquals(1L, solicitud.getId());
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
                                             
        System.out.println("Estado de la solicitud: " + solicitud.getEstado()); 
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
        
                                             System.out.println(solicitud.getEstado());
        assertTrue(solicitud.isCalificacionVisible());

        solicitud.setEstado(Estado.ACEPTADA);
        assertFalse(solicitud.isCalificacionVisible());
    }

    @Test
    public void testIsCalificarVisible() {
        Usuario arrendatario = new Usuario();
        Usuario arrendador = new Usuario();
        Finca finca = new Finca();

        //Configurar la fecha del fin en el pasado
        Date fechaInicio = new Date();
        Date fechaFin = new Date(fechaInicio.getTime() - 86400000); 

        
        //Crear la solicitud
        Solicitud solicitud = new Solicitud(arrendatario, arrendador, finca, fechaInicio, fechaFin,4,5, 1500.00f, 4, false, Estado.CERRADO);
        //Verificar que el estado VENCIDA hace visible la opcion de calificar
        assertFalse(solicitud.isPagoVisible());
        assertTrue(solicitud.isCalificacionVisible());
        assertTrue(solicitud.isCalificarVisible());
    }  
}
