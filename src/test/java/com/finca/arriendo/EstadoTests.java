package com.finca.arriendo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

import com.finca.arriendo.model.Estado;

public class EstadoTests {

    @Test
    public void testEnumValues() {
        // Verificar que los valores del enum están presentes
        Estado[] estados = Estado.values();
        assertEquals(5, estados.length); 
        assertTrue(contains(estados, Estado.ACEPTADA));
        assertTrue(contains(estados, Estado.EN_TRAMITE));
        assertTrue(contains(estados, Estado.VENCIDA));
        assertTrue(contains(estados, Estado.CERRADO));
        assertTrue(contains(estados, Estado.EN_PAGO)); 
    }

    @Test
    public void testEnumNames() {
        // Verificar que los nombres del enum son correctos
        assertEquals("ACEPTADA", Estado.ACEPTADA.name());
        assertEquals("EN_TRAMITE", Estado.EN_TRAMITE.name());
        assertEquals("VENCIDA", Estado.VENCIDA.name());
        assertEquals("CERRADO", Estado.CERRADO.name());
        assertEquals("EN_PAGO", Estado.EN_PAGO.name()); 
    }

    @Test
    public void testEnumToString() {
        // Verificar que los valores del enum tienen los nombres esperados
        assertEquals("ACEPTADA", Estado.ACEPTADA.toString());
        assertEquals("EN_TRAMITE", Estado.EN_TRAMITE.toString());
        assertEquals("VENCIDA", Estado.VENCIDA.toString());
        assertEquals("CERRADO", Estado.CERRADO.toString());
        assertEquals("EN_PAGO", Estado.EN_PAGO.toString()); 
    }

    @Test
    public void testEnumOrdinal() {
        // Verificar que los ordinales de los valores del enum son correctos
        assertEquals(0, Estado.ACEPTADA.ordinal());
        assertEquals(1, Estado.EN_TRAMITE.ordinal());
        assertEquals(2, Estado.VENCIDA.ordinal());
        assertEquals(3, Estado.CERRADO.ordinal());
        assertEquals(4, Estado.EN_PAGO.ordinal());
    }

    private boolean contains(Estado[] array, Estado value) {
        for (Estado e : array) {
            if (e == value) {
                return true;
            }
        }
        return false;
    }
}
