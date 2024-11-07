package com.finca.arriendo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;
import org.junit.jupiter.api.Test;

import com.finca.arriendo.model.Tipo;

public class TipoTests {

    @Test
    public void testEnumValues() {
        // Verificar que todos los valores del enum están presentes
        assertNotNull(Tipo.ARRENDADOR);
        assertNotNull(Tipo.ARRENDATARIO);
    }

    @Test
    public void testEnumValueOf() {
        // Verificar que el método valueOf funciona correctamente
        assertEquals(Tipo.ARRENDADOR, Tipo.valueOf("ARRENDADOR"));
        assertEquals(Tipo.ARRENDATARIO, Tipo.valueOf("ARRENDATARIO"));
        
        // Verificar que se lanza una excepción si se pasa un valor no válido
    try {
        Tipo.valueOf("INVALIDO");
        fail("Debería lanzar una excepción");
    } catch (IllegalArgumentException e) {
        // Excepción esperada
    }
}

    @Test
    public void testEnumOrdinal() {
        // Verificar que los ordinales de los valores del enum son correctos
        assertEquals(0, Tipo.ARRENDADOR.ordinal());
        assertEquals(1, Tipo.ARRENDATARIO.ordinal());
    }

    @Test
    public void testEnumNames() {
        // Verificar que los nombres de los valores del enum son correctos
        assertEquals("ARRENDADOR", Tipo.ARRENDADOR.name());
        assertEquals("ARRENDATARIO", Tipo.ARRENDATARIO.name());
    }
    
    @Test
    public void testEnumToString() {
        // Verificar que el método toString devuelve el nombre del enum
        assertEquals("ARRENDADOR", Tipo.ARRENDADOR.toString());
        assertEquals("ARRENDATARIO", Tipo.ARRENDATARIO.toString());
    }
}
