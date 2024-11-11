package com.finca.arriendo.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.finca.arriendo.dto.UsuarioDto;
import com.finca.arriendo.model.Estado;
import com.finca.arriendo.model.Finca;
import com.finca.arriendo.model.Solicitud;
import com.finca.arriendo.model.Usuario;
import com.finca.arriendo.repository.FincaRepository;
import com.finca.arriendo.repository.SolicitudRepository;
import com.finca.arriendo.repository.UsuarioRepository;

import jakarta.transaction.Transactional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private FincaRepository fincaRepository;  // Repositorio para manejar fincas

    @Autowired
    private SolicitudRepository solicitudRepository;  // Repositorio para manejar solicitudes de arriendo

    // Obtener un usuario por ID
    public UsuarioDto get(Long id) {
        Optional<Usuario> usuario = usuarioRepository.findById(id);
        return usuario.map(value -> modelMapper.map(value, UsuarioDto.class)).orElse(null);
    }

    // Obtener un usuario por nombre
    public UsuarioDto get(String nombre) {
        Usuario usuario = usuarioRepository.findByNombre(nombre);
        return usuario != null ? modelMapper.map(usuario, UsuarioDto.class) : null;
    }

    // Listar todos los usuarios
    public List<UsuarioDto> list() {
        List<Usuario> usuarios = usuarioRepository.findAll();
        return usuarios.stream()
                .map(usuario -> modelMapper.map(usuario, UsuarioDto.class))
                .collect(Collectors.toList());
    }

    // Guardar nuevo usuario (Registro)
    @Transactional
    public UsuarioDto saveNew(UsuarioDto usuarioDto) {
        Usuario usuario = modelMapper.map(usuarioDto, Usuario.class);
        usuario.validar();  // Validar campos
        usuario = usuarioRepository.save(usuario);  // Guardar nuevo usuario
        return modelMapper.map(usuario, UsuarioDto.class);
    }

    // Actualizar usuario existente
    @Transactional
    public UsuarioDto save(UsuarioDto usuarioDto) {
        Usuario usuario = modelMapper.map(usuarioDto, Usuario.class);
        usuario = usuarioRepository.save(usuario);  // Actualizar usuario existente
        return modelMapper.map(usuario, UsuarioDto.class);
    }

    // Eliminar usuario 
    @Transactional
    public void delete(UsuarioDto usuarioDto) {
        Usuario usuario = modelMapper.map(usuarioDto, Usuario.class);
        usuarioRepository.delete(usuario);  // Eliminación lógica
    }

    // Calificar arrendatario 
    @Transactional
    public UsuarioDto calificarArrendatario(Long usuarioId, float calificacion, String comentario) {
        Optional<Usuario> usuarioOpt = usuarioRepository.findById(usuarioId);
        if (usuarioOpt.isPresent()) {
            Usuario usuario = usuarioOpt.get();
            usuario.agregarCalificacion(calificacion);  // Agregar la nueva calificación
            // Aquí podrías guardar el comentario si fuera parte del modelo o manejarlo de otra manera
            usuarioRepository.save(usuario);
            return modelMapper.map(usuario, UsuarioDto.class);
        }
        return null;
    }

    // Creación de cuenta 
    @Transactional
    public UsuarioDto registrarUsuario(UsuarioDto usuarioDto) {
        Usuario usuario = modelMapper.map(usuarioDto, Usuario.class);
        usuario = usuarioRepository.save(usuario);
        return modelMapper.map(usuario, UsuarioDto.class);
    }

    // Activación de cuenta (HUA.14)
    @Transactional
    public boolean activarCuenta(Long usuarioId) {
        Optional<Usuario> usuarioOpt = usuarioRepository.findById(usuarioId);
        if (usuarioOpt.isPresent()) {
            Usuario usuario = usuarioOpt.get();
            // Aquí deberías implementar el cambio de estado que indica que la cuenta está activa
            usuarioRepository.save(usuario);
            return true;
        }
        return false;
    }

    // Autenticación 
    public boolean autenticar(String nombre, String contrasena) {
        Usuario usuario = usuarioRepository.findByNombre(nombre);
        return usuario != null && usuario.getContrasena().equals(contrasena);
    }

    // Buscar propiedades 
    public List<String> buscarPropiedades(String nombre, String municipio, int personas) {
        // Busca las fincas que coincidan con el nombre, municipio, y tengan capacidad suficiente
        List<Finca> fincas = usuarioRepository.findByNombreAndMunicipioAndCapacidad(nombre, municipio, personas);
        return fincas.stream()
                .map(Finca::getNombre)  // Extrae los nombres de las propiedades
                .collect(Collectors.toList());
    }

    // Solicitar arriendo 
    @Transactional
    public boolean solicitarArriendo(Long usuarioId, Long fincaId, String fechaInicio, String fechaFin, int cantidadPersonas) {
        // Verifica que el usuario y la finca existan
        Optional<Usuario> usuarioOpt = usuarioRepository.findById(usuarioId);
        Optional<Finca> fincaOpt = fincaRepository.findById(fincaId);

        if (usuarioOpt.isPresent() && fincaOpt.isPresent()) {
            Usuario usuario = usuarioOpt.get();
            Finca finca = fincaOpt.get();

            // Validaciones adicionales como disponibilidad de fechas y capacidad
            if (finca.estaDisponible(fechaInicio, fechaFin) && finca.getCapacidad() >= cantidadPersonas) {
                // Crear la solicitud de arriendo
                Solicitud solicitud = new Solicitud();
                solicitud.setArrendatario(usuario);
                solicitud.setFinca(finca);
                try {
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    Date fechaInicioDate = sdf.parse(fechaInicio);
                    solicitud.setFechaInicio(fechaInicioDate);
                } catch (ParseException e) {
                    // Manejar la excepción, por ejemplo, imprimiendo un mensaje de error
                    System.out.println("Error al parsear la fecha de inicio: " + e.getMessage());
                }
                solicitud.setCantPersonas(cantidadPersonas);
                solicitud.setEstado(Estado.valueOf("EN_TRAMITE"));

                solicitudRepository.save(solicitud);  // Guarda la solicitud
                return true;
            }
        }
        return false;  // Si no se cumple
    }

    // Ver solicitudes de arriendo 
    public boolean pagarArriendo(Long solicitudId) {
        // Busca la solicitud por su ID
        Optional<Solicitud> solicitudOpt = solicitudRepository.findById(solicitudId);
    
    if (solicitudOpt.isPresent()) {
        Solicitud solicitud = solicitudOpt.get();
        
        // Cambiar el estado a EN_PAGO si es aplicable
        if (solicitud.getEstado() == Estado.EN_TRAMITE) {
            solicitud.setEstado(Estado.EN_PAGO);
            solicitudRepository.save(solicitud);
            return true;
        }
    }
    return false; 
    }
}
