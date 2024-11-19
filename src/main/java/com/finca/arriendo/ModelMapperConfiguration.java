package com.finca.arriendo;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.finca.arriendo.dto.ContratoDto;
import com.finca.arriendo.dto.FincaDto;
import com.finca.arriendo.dto.SolicitudDto;
import com.finca.arriendo.dto.UsuarioDto; // Importar el nuevo DTO
import com.finca.arriendo.model.Contrato;
import com.finca.arriendo.model.Finca;
import com.finca.arriendo.model.Solicitud;
import com.finca.arriendo.model.Tipo;
import com.finca.arriendo.model.Usuario; // Importar la nueva entidad

@Configuration
public class ModelMapperConfiguration {

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        // Mapeo para Usuario y UsuarioDto
        modelMapper.createTypeMap(Usuario.class, UsuarioDto.class).addMappings(mapping -> {
            mapping.map(Usuario::getId, UsuarioDto::setId);
            mapping.map(Usuario::getNombre, UsuarioDto::setNombre);
            mapping.map(Usuario::getApellido, UsuarioDto::setApellido);
            mapping.map(Usuario::getCorreo, UsuarioDto::setCorreo);
            mapping.map(Usuario::getTelefono, UsuarioDto::setTelefono);
            mapping.map(Usuario::getContrasena, UsuarioDto::setContrasena);
            mapping.map(Usuario::getCalificacion, UsuarioDto::setCalificacion);
            mapping.map(src -> src.getTipo() != null ? src.getTipo().name() : "DESCONOCIDO", UsuarioDto::setTipo);
        });

        modelMapper.createTypeMap(UsuarioDto.class, Usuario.class).addMappings(mapping -> {
            mapping.map(UsuarioDto::getId, Usuario::setId);
            mapping.map(UsuarioDto::getNombre, Usuario::setNombre);
            mapping.map(UsuarioDto::getApellido, Usuario::setApellido);
            mapping.map(UsuarioDto::getCorreo, Usuario::setCorreo);
            mapping.map(UsuarioDto::getTelefono, Usuario::setTelefono);
            mapping.map(UsuarioDto::getContrasena, Usuario::setContrasena);
            mapping.map(UsuarioDto::getCalificacion, Usuario::setCalificacion);
            mapping.map(dto -> dto.getTipo() != null ? Tipo.valueOf(dto.getTipo()) : Tipo.ARRENDATARIO, Usuario::setTipo);
        });

        // Mapeo para Solicitud y SolicitudDto
        modelMapper.createTypeMap(Solicitud.class, SolicitudDto.class).addMappings(mapping -> {
            mapping.map(Solicitud::getId, SolicitudDto::setId);
            mapping.map(Solicitud::getFechaInicio, SolicitudDto::setFechaInicio);
            mapping.map(Solicitud::getFechaFin, SolicitudDto::setFechaFin);
            mapping.map(Solicitud::getCalifFinca, SolicitudDto::setCalifFinca);
            mapping.map(Solicitud::getCalifArrendatario, SolicitudDto::setCalifArrendatario);
            mapping.map(Solicitud::getPrecio, SolicitudDto::setPrecio);
            mapping.map(Solicitud::getCantPersonas, SolicitudDto::setCantPersonas);
        });

        modelMapper.createTypeMap(SolicitudDto.class, Solicitud.class).addMappings(mapping -> {
            mapping.map(SolicitudDto::getId, Solicitud::setId);
            mapping.map(SolicitudDto::getFechaInicio, Solicitud::setFechaInicio);
            mapping.map(SolicitudDto::getFechaFin, Solicitud::setFechaFin);
            mapping.map(SolicitudDto::getCalifFinca, Solicitud::setCalifFinca);
            mapping.map(SolicitudDto::getCalifArrendatario, Solicitud::setCalifArrendatario);
            mapping.map(SolicitudDto::getPrecio, Solicitud::setPrecio);
            mapping.map(SolicitudDto::getCantPersonas, Solicitud::setCantPersonas);
        });

        // Mapeo para Finca y FincaDto
        modelMapper.createTypeMap(Finca.class, FincaDto.class).addMappings(mapping -> {
            mapping.map(Finca::getId, FincaDto::setId);
            mapping.map(Finca::getNombre, FincaDto::setNombre);
            mapping.map(Finca::getUbicacion, FincaDto::setUbicacion); // Asegúrate de que los tipos sean compatibles
            mapping.map(Finca::getDepartamento, FincaDto::setDepartamento);
            mapping.map(Finca::getMunicipio, FincaDto::setMunicipio);
            mapping.map(Finca::getPrecioDefecto, FincaDto::setPrecioDefecto);
            mapping.map(Finca::getDisponible, FincaDto::setDisponible);
            mapping.map(Finca::getCalificacion, FincaDto::setCalificacion);
            mapping.map(Finca::getCapacidad, FincaDto::setCapacidad);
            mapping.map(Finca::getDescripcion, FincaDto::setDescripcion);
        });

        modelMapper.createTypeMap(FincaDto.class, Finca.class).addMappings(mapping -> {
            mapping.map(FincaDto::getId, Finca::setId);
            mapping.map(FincaDto::getNombre, Finca::setNombre);
            mapping.map(FincaDto::getUbicacion, Finca::setUbicacion); // Asegúrate de que los tipos sean compatibles
            mapping.map(FincaDto::getDepartamento, Finca::setDepartamento);
            mapping.map(FincaDto::getMunicipio, Finca::setMunicipio);
            mapping.map(FincaDto::getPrecioDefecto, Finca::setPrecioDefecto);
            mapping.map(FincaDto::getDisponible, Finca::setDisponible);
            mapping.map(FincaDto::getCalificacion, Finca::setCalificacion);
            mapping.map(FincaDto::getCapacidad, Finca::setCapacidad);
            mapping.map(FincaDto::getDescripcion, Finca::setDescripcion);
        });

        // Mapeo para Contrato y ContratoDto
        modelMapper.createTypeMap(Contrato.class, ContratoDto.class).addMappings(mapping -> {
            mapping.map(Contrato::getId, ContratoDto::setId);
            mapping.map(Contrato::getIdentificador, ContratoDto::setIdentificador);
            mapping.map(Contrato::getValor, ContratoDto::setValor);
            mapping.map(Contrato::getNombreContratante, ContratoDto::setNombreContratante);
            mapping.map(Contrato::getDocumentoContratante, ContratoDto::setDocumentoContratante);
            mapping.map(Contrato::getNombreContratantista, ContratoDto::setNombreContratantista);
            mapping.map(Contrato::getDocumentoContratantista, ContratoDto::setDocumentoContratantista);
            mapping.map(Contrato::getFechaInicial, ContratoDto::setFechaInicial);
            mapping.map(Contrato::getFechaFinal, ContratoDto::setFechaFinal);
        });

        modelMapper.createTypeMap(ContratoDto.class, Contrato.class).addMappings(mapping -> {
            mapping.map(ContratoDto::getId, Contrato::setId);
            mapping.map(ContratoDto::getIdentificador, Contrato::setIdentificador);
            mapping.map(ContratoDto::getValor, Contrato::setValor);
            mapping.map(ContratoDto::getNombreContratante, Contrato::setNombreContratante);
            mapping.map(ContratoDto::getDocumentoContratante, Contrato::setDocumentoContratante);
            mapping.map(ContratoDto::getNombreContratantista, Contrato::setNombreContratantista);
            mapping.map(ContratoDto::getDocumentoContratantista, Contrato::setDocumentoContratantista);
            mapping.map(ContratoDto::getFechaInicial, Contrato::setFechaInicial);
            mapping.map(ContratoDto::getFechaFinal, Contrato::setFechaFinal);
        });

        return modelMapper;
    }
}
