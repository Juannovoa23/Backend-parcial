package com.finca.arriendo;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.finca.arriendo.model.Estado;
import com.finca.arriendo.model.Finca;
import com.finca.arriendo.model.Solicitud;
import com.finca.arriendo.model.Tipo;
import com.finca.arriendo.model.Usuario;
import com.finca.arriendo.repository.FincaRepository;
import com.finca.arriendo.repository.SolicitudRepository;
import com.finca.arriendo.repository.UsuarioRepository;


@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private SolicitudRepository solicitudRepository;

    @Autowired
    private FincaRepository fincaRepository;

    @Override
    public void run(String... args) throws Exception {

        List<Usuario> usuarios = Arrays.asList(
        // Crear usuarios de prueba
            new Usuario(null, Tipo.ARRENDADOR, "John", "Doe", "john@example.com", 123456789, "password1", 4.5f, null, null, null, false),
            new Usuario(null, Tipo.ARRENDATARIO, "Jane", "Smith", "jane@example.com", 987654321, "password2", 5.0f, null, null, null, false),
            new Usuario(null, Tipo.ARRENDADOR, "Carlos", "Ramirez", "carlos@example.com", 111222333, "password3", 4.8f, null, null, null, false),
            new Usuario(null, Tipo.ARRENDATARIO, "Laura", "Gomez", "laura@example.com", 444555666, "password4", 4.2f, null, null, null, false),
            new Usuario(null, Tipo.ARRENDADOR, "Pedro", "Lopez", "pedro@example.com", 555666777, "password5", 3.9f, null, null, null, false),
            new Usuario(null, Tipo.ARRENDATARIO, "Maria", "Perez", "maria@example.com", 666777888, "password6", 4.7f, null, null, null, false),
            new Usuario(null, Tipo.ARRENDADOR, "Luis", "Martinez", "luis@example.com", 777888999, "password7", 4.0f, null, null, null, false),
            new Usuario(null, Tipo.ARRENDATARIO, "Elena", "Torres", "elena@example.com", 888999000, "password8", 4.3f, null, null, null, false),
            new Usuario(null, Tipo.ARRENDADOR, "Francisco", "Garcia", "francisco@example.com", 999000111, "password9", 4.1f, null, null, null, false),
            new Usuario(null, Tipo.ARRENDATARIO, "Clara", "Castro", "clara@example.com", 111000222, "password10", 4.6f, null, null, null, false),
            new Usuario(null, Tipo.ARRENDADOR, "Javier", "Ortega", "javier@example.com", 222111333, "password11", 4.4f, null, null, null, false),
            new Usuario(null, Tipo.ARRENDATARIO, "Natalia", "Vega", "natalia@example.com", 333222444, "password12", 4.9f, null, null, null, false),
            new Usuario(null, Tipo.ARRENDADOR, "Diego", "Jimenez", "diego@example.com", 444333555, "password13", 3.8f, null, null, null, false),
            new Usuario(null, Tipo.ARRENDATARIO, "Raul", "Rivera", "raul@example.com", 555444666, "password14", 4.5f, null, null, null, false),
            new Usuario(null, Tipo.ARRENDADOR, "Patricia", "Suarez", "patricia@example.com", 666555777, "password15", 4.2f, null, null, null, false),
            new Usuario(null, Tipo.ARRENDATARIO, "Felipe", "Morales", "felipe@example.com", 777666888, "password16", 5.0f, null, null, null, false),
            new Usuario(null, Tipo.ARRENDADOR, "Sandra", "Fernandez", "sandra@example.com", 888777999, "password17", 4.3f, null, null, null, false),
            new Usuario(null, Tipo.ARRENDATARIO, "Fernando", "Blanco", "fernando@example.com", 999888111, "password18", 4.4f, null, null, null, false),
            new Usuario(null, Tipo.ARRENDADOR, "Andrea", "Mejia", "andrea@example.com", 111222333, "password19", 4.7f, null, null, null, false),
            new Usuario(null, Tipo.ARRENDATARIO, "Manuel", "Gutierrez", "manuel@example.com", 222333444, "password20", 4.1f, null, null, null, false)
        );
        // Guardar los usuarios en la base de datos
        usuarioRepository.saveAll(usuarios);

        // Crear fincas de prueba
        List<Finca> fincas = Arrays.asList(
    new Finca(null, "Finca La Estrella", "Ubicación 1", "Medellín", "Antioquia", true, 5, 
        "Finca amplia con piscina, rodeada de montañas y naturaleza. Ofrece un espacio tranquilo ideal para descansar y disfrutar en familia. Tiene áreas verdes amplias, zona de BBQ, y acceso cercano a rutas de senderismo.", 
        10, 1500.0f, null, null, usuarios.get(0), false),
    
    new Finca(null, "Finca El Paraíso", "Ubicación 2", "Santa Marta", "Magdalena", true, 4, 
        "Finca con vista al mar, ideal para disfrutar de la brisa marina y el paisaje tropical. Cuenta con espacios modernos y cómodos, y ofrece una experiencia única para aquellos que buscan relajarse frente al mar.", 
        8, 2000.0f, null, null, usuarios.get(2), false),
    
    new Finca(null, "Finca Bella Vista", "Ubicación 3", "Cali", "Valle del Cauca", true, 5, 
        "Finca rodeada de naturaleza y vegetación exótica, perfecta para aquellos que buscan un ambiente natural. La finca incluye una zona de avistamiento de aves, senderos ecológicos y áreas de descanso al aire libre.", 
        15, 1800.0f, null, null, usuarios.get(0), false),
    
    new Finca(null, "Finca Los Álamos", "Ubicación 4", "Bogotá", "Cundinamarca", true, 3, 
        "Ideal para eventos especiales, la finca ofrece una gran sala de reuniones y espacios amplios. Su ubicación estratégica permite un fácil acceso a la ciudad y a la vez proporciona un ambiente campestre y acogedor.", 
        12, 1300.0f, null, null, usuarios.get(3), false),
    
    new Finca(null, "Finca La Cascada", "Ubicación 5", "Manizales", "Caldas", true, 5, 
        "Con cascada natural, esta finca es un paraíso escondido perfecto para quienes buscan un escape a la naturaleza. Incluye una gran piscina al aire libre, áreas de picnic, y caminos para explorar la flora local.", 
        20, 2100.0f, null, null, usuarios.get(1), false),
    
    new Finca(null, "Finca Los Cerezos", "Ubicación 1", "Medellín", "Antioquia", true, 5, 
        "Ideal para eventos familiares, esta finca ofrece amplios espacios y jardines encantadores. Cuenta con instalaciones para actividades deportivas, zona de juegos para niños y un hermoso jardín de cerezos.", 
        20, 2500.0f, null, null, usuarios.get(0), false),
    
    new Finca(null, "Finca El Encanto", "Ubicación 2", "Cali", "Valle del Cauca", true, 4, 
        "Rodeada de naturaleza, esta finca es el lugar perfecto para aquellos que desean conectarse con la flora y fauna local. Sus áreas verdes, junto con un hermoso lago, proporcionan una experiencia de paz y tranquilidad.", 
        15, 2000.0f, null, null, usuarios.get(1), false),
    
    new Finca(null, "Finca La Esperanza", "Ubicación 3", "Bogotá", "Cundinamarca", true, 6, 
        "Con piscina y BBQ, esta finca es ideal para reuniones grandes. Ofrece instalaciones modernas, un área de juegos y entretenimiento, y un gran salón para eventos, todo en un ambiente natural.", 
        25, 3000.0f, null, null, usuarios.get(2), false),
    
    new Finca(null, "Finca El Bosque", "Ubicación 15", "Barranquilla", "Atlántico", true, 5, 
        "Finca rodeada de árboles y fauna, un lugar ideal para los amantes de la naturaleza. Ofrece cabañas rústicas, áreas de camping, y actividades al aire libre como senderismo y observación de vida silvestre.", 
        16, 1700.0f, null, null, usuarios.get(14), false),
    
    new Finca(null, "Finca Tierra Blanca", "Ubicación 16", "Cali", "Valle del Cauca", true, 4, 
        "Ideal para eventos corporativos, la finca cuenta con espacios de reunión, proyector, y salón de conferencias. Rodeada de un entorno verde, brinda un ambiente perfecto para reuniones empresariales.", 
        12, 1400.0f, null, null, usuarios.get(15), false),
    
    new Finca(null, "Finca La Palma", "Ubicación 17", "Medellín", "Antioquia", true, 3, 
        "Con área para camping, esta finca es perfecta para los aventureros. Ofrece instalaciones básicas y está rodeada de paisajes montañosos, ideal para noches bajo las estrellas y fogatas.", 
        8, 1200.0f, null, null, usuarios.get(16), false),
    
    new Finca(null, "Finca Sagrado Corazón", "Ubicación 18", "Manizales", "Caldas", true, 5, 
        "Finca con cabañas y naturaleza, proporciona un ambiente hogareño en medio de la naturaleza. Ideal para retiros familiares, cuenta con una pequeña capilla y áreas de descanso al aire libre.", 
        14, 1600.0f, null, null, usuarios.get(17), false),
    
    new Finca(null, "Finca El Sol", "Ubicación 19", "Cali", "Valle del Cauca", true, 4, 
        "Finca con vista panorámica de la región, ofrece terrazas donde se pueden disfrutar de atardeceres impresionantes. Cuenta con jardines, piscina, y una zona de descanso con vista a las montañas.", 
        18, 2000.0f, null, null, usuarios.get(18), false),
    
    new Finca(null, "Finca La Esperanza", "Ubicación 20", "Cúcuta", "Norte de Santander", true, 5, 
        "Finca ideal para vacaciones familiares, equipada con una gran piscina, canchas deportivas y área de recreación. Un lugar perfecto para disfrutar de días soleados en un ambiente tranquilo.", 
        20, 2200.0f, null, null, usuarios.get(19), false)
);

        //Guardar fincas
        fincaRepository.saveAll(fincas);

        // Crear solicitudes de prueba
        List<Solicitud> solicitudes = Arrays.asList(
            new Solicitud(usuarios.get(1), usuarios.get(0), fincas.get(0), new Date(), new Date(), null, null, 1200.0f, 5, false, Estado.EN_TRAMITE),
            new Solicitud(usuarios.get(3), usuarios.get(2), fincas.get(1), new Date(), new Date(), null, null, 1900.0f, 3, false, Estado.ACEPTADA),
            new Solicitud(usuarios.get(1), usuarios.get(0), fincas.get(2), new Date(), new Date(), null, null, 1750.0f, 8, false, Estado.CERRADO),
            new Solicitud(usuarios.get(4), usuarios.get(0), fincas.get(3), new Date(), new Date(), null, null, 1400.0f, 7, false, Estado.VENCIDA),
            new Solicitud(usuarios.get(2), usuarios.get(3), fincas.get(4), new Date(), new Date(), null, null, 1650.0f, 4, false, Estado.EN_TRAMITE),
            new Solicitud(usuarios.get(5), usuarios.get(1), fincas.get(5), new Date(), new Date(), null, null, 1550.0f, 6, false, Estado.EN_TRAMITE),
            new Solicitud(usuarios.get(6), usuarios.get(2), fincas.get(6), new Date(), new Date(), null, null, 2000.0f, 2, false, Estado.ACEPTADA),
            new Solicitud(usuarios.get(7), usuarios.get(3), fincas.get(7), new Date(), new Date(), null, null, 1100.0f, 9, false, Estado.CERRADO),
            new Solicitud(usuarios.get(8), usuarios.get(4), fincas.get(8), new Date(), new Date(), null, null, 1700.0f, 10, false, Estado.EN_TRAMITE),
            new Solicitud(usuarios.get(9), usuarios.get(5), fincas.get(9), new Date(), new Date(), null, null, 1300.0f, 1, false, Estado.VENCIDA),
            new Solicitud(usuarios.get(1), usuarios.get(6), fincas.get(0), new Date(), new Date(), null, null, 1800.0f, 8, false, Estado.EN_TRAMITE),
            new Solicitud(usuarios.get(2), usuarios.get(7), fincas.get(1), new Date(), new Date(), null, null, 1600.0f, 4, false, Estado.ACEPTADA),
            new Solicitud(usuarios.get(3), usuarios.get(8), fincas.get(2), new Date(), new Date(), null, null, 1900.0f, 5, false, Estado.CERRADO),
            new Solicitud(usuarios.get(4), usuarios.get(9), fincas.get(3), new Date(), new Date(), null, null, 1500.0f, 7, false, Estado.VENCIDA),
            new Solicitud(usuarios.get(5), usuarios.get(0), fincas.get(4), new Date(), new Date(), null, null, 1200.0f, 3, false, Estado.EN_TRAMITE),
            new Solicitud(usuarios.get(6), usuarios.get(1), fincas.get(5), new Date(), new Date(), null, null, 1400.0f, 9, false, Estado.ACEPTADA),
            new Solicitud(usuarios.get(7), usuarios.get(2), fincas.get(6), new Date(), new Date(), null, null, 1800.0f, 2, false, Estado.CERRADO),
            new Solicitud(usuarios.get(8), usuarios.get(3), fincas.get(7), new Date(), new Date(), null, null, 2000.0f, 6, false, Estado.EN_TRAMITE),
            new Solicitud(usuarios.get(9), usuarios.get(4), fincas.get(8), new Date(), new Date(), null, null, 1700.0f, 10, false, Estado.VENCIDA)
               );

        solicitudRepository.saveAll(solicitudes);

        System.out.println("Datos de prueba creados.");
    }
    
}
