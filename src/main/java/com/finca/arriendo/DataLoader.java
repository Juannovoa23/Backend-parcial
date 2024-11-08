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
            10, 1500.0f, null, usuarios.get(0), false),
        new Finca(null, "Finca El Paraíso", "Ubicación 2", "Santa Marta", "Magdalena", true, 4,
            "Finca con vista al mar, ideal para disfrutar de la brisa marina y el paisaje tropical. Cuenta con espacios modernos y cómodos, y ofrece una experiencia única para aquellos que buscan relajarse frente al mar.",
            8, 2000.0f, null, usuarios.get(2), false),
        new Finca(null, "Finca Bella Vista", "Ubicación 3", "Cali", "Valle del Cauca", true, 5,
            "Finca rodeada de naturaleza y vegetación exótica, perfecta para aquellos que buscan un ambiente natural. La finca incluye una zona de avistamiento de aves, senderos ecológicos y áreas de descanso al aire libre.",
            15, 1800.0f, null, usuarios.get(0), false),
            new Finca(null, "Finca El Refugio", "Ubicación 6", "Villavicencio", "Meta", true, 4, 
        "Refugio tranquilo cerca de la selva, ideal para amantes de la naturaleza y el ecoturismo.", 
        8, 1800.0f, null, usuarios.get(4), false),

    new Finca(null, "Finca Monte Verde", "Ubicación 7", "Pereira", "Risaralda", true, 5, 
        "Rodeada de montañas y cafetales, ofrece un ambiente relajante para desconectar.", 
        12, 1700.0f, null, usuarios.get(5), false),

    new Finca(null, "Finca El Mirador", "Ubicación 8", "Cartagena", "Bolívar", true, 3, 
        "Finca con vistas panorámicas a la ciudad y al mar, perfecta para eventos.", 
        15, 2200.0f, null, usuarios.get(6), false),

    new Finca(null, "Finca Sol y Luna", "Ubicación 9", "Tunja", "Boyacá", true, 5, 
        "Finca en una zona montañosa, con acceso a rutas de senderismo y actividades de aventura.", 
        10, 1600.0f, null, usuarios.get(7), false),

    new Finca(null, "Finca La Brisa", "Ubicación 10", "Neiva", "Huila", true, 4, 
        "Finca cerca del río Magdalena, ideal para disfrutar de la brisa y las actividades acuáticas.", 
        8, 1400.0f, null, usuarios.get(8), false),

    new Finca(null, "Finca La Aurora", "Ubicación 11", "Bucaramanga", "Santander", true, 5, 
        "Con vistas a las montañas, esta finca es perfecta para un retiro tranquilo.", 
        15, 2100.0f, null, usuarios.get(9), false),

    new Finca(null, "Finca El Encanto Verde", "Ubicación 12", "Popayán", "Cauca", true, 4, 
        "Finca con jardines exóticos y un entorno natural para el descanso.", 
        7, 1350.0f, null, usuarios.get(10), false),

    new Finca(null, "Finca La Pradera", "Ubicación 13", "Armenia", "Quindío", true, 3, 
        "Finca campestre con amplios jardines y espacio para eventos familiares.", 
        20, 1550.0f, null, usuarios.get(11), false),

    new Finca(null, "Finca Buenavista", "Ubicación 14", "Montería", "Córdoba", true, 4, 
        "Finca con zona de BBQ, ideal para reuniones sociales y celebraciones.", 
        10, 1700.0f, null, usuarios.get(12), false),

    new Finca(null, "Finca La Paz", "Ubicación 15", "Leticia", "Amazonas", true, 5, 
        "Ubicada en el Amazonas, ofrece una experiencia única en contacto con la selva.", 
        5, 2500.0f, null, usuarios.get(13), false),

    new Finca(null, "Finca Tierra Bonita", "Ubicación 16", "Sincelejo", "Sucre", true, 3, 
        "Rodeada de naturaleza tropical, esta finca es ideal para días de descanso.", 
        12, 1450.0f, null, usuarios.get(14), false),

    new Finca(null, "Finca El Manantial", "Ubicación 17", "Florencia", "Caquetá", true, 5, 
        "Finca con acceso a un manantial natural y áreas de descanso al aire libre.", 
        8, 1900.0f, null, usuarios.get(15), false),

    new Finca(null, "Finca Vista Hermosa", "Ubicación 18", "Quibdó", "Chocó", true, 4, 
        "Ubicada en el corazón del Chocó, ofrece una experiencia de conexión con la biodiversidad.", 
        15, 1800.0f, null, usuarios.get(16), false),

    new Finca(null, "Finca Los Rosales", "Ubicación 19", "Pasto", "Nariño", true, 4, 
        "Finca con jardines de rosas y zonas de descanso para disfrutar la tranquilidad del campo.", 
        6, 1750.0f, null, usuarios.get(17), false),

    new Finca(null, "Finca Alto Cielo", "Ubicación 20", "Riohacha", "La Guajira", true, 3, 
        "Finca con vistas al desierto, ideal para explorar la región y descansar en un entorno único.", 
        8, 1600.0f, null, usuarios.get(18), false),

    new Finca(null, "Finca Las Margaritas", "Ubicación 21", "Arauca", "Arauca", true, 5, 
        "Finca con ambiente campestre, ideal para retiros y eventos familiares en contacto con la naturaleza.", 
        10, 1650.0f, null, usuarios.get(19), false)
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
