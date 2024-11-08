package com.finca.arriendo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.finca.arriendo.dto.FincaDto;
import com.finca.arriendo.services.FincaService;

@RestController
@RequestMapping("/api/finca")
@CrossOrigin
public class FincaController {
    
    @Autowired
    private FincaService fincaService;

    @GetMapping("/list")
    public ResponseEntity<List<FincaDto>> getAll() {
        try {
            List<FincaDto> fincas = fincaService.list();
            return new ResponseEntity<>(fincas, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<FincaDto> getById(@PathVariable Long id) {
        try {
            FincaDto finca = fincaService.get(id);
            if (finca != null) {
                return new ResponseEntity<>(finca, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/getNom/{nombre}")
    public ResponseEntity<FincaDto> obtenerPorNombre(@PathVariable String nombre) {
        try {
            FincaDto finca = fincaService.get(nombre);
            if (finca != null) {
                return new ResponseEntity<>(finca, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/create")
    public ResponseEntity<FincaDto> save(@RequestBody FincaDto fincaDto) {
        try {
            FincaDto savedFinca = fincaService.saveNew(fincaDto);
            return new ResponseEntity<>(savedFinca, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/update")
public ResponseEntity<FincaDto> update(@RequestBody FincaDto fincaDto) {
    try {
        // Llamamos al método `update` en lugar de `save`
        FincaDto updatedFinca = fincaService.update(fincaDto);
        return new ResponseEntity<>(updatedFinca, HttpStatus.OK);
    } catch (Exception e) {
        if (e.getMessage().equals("Finca no encontrada para el ID especificado")) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}


    @DeleteMapping("/delete")
    public ResponseEntity<Void> delete(@RequestBody FincaDto fincaDto) {
        try {
            fincaService.delete(fincaDto);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/calificar/{id}")
    public ResponseEntity<FincaDto> calificarFinca(@PathVariable Long id, @RequestBody FincaDto calificacionDto) {
       try {
        // Llamada al servicio solo con la calificación, sin comentarios
        FincaDto calificada = fincaService.calificarFinca(id, (int) calificacionDto.getCalificacion());
        if (calificada != null) {
            return new ResponseEntity<>(calificada, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    } catch (Exception e) {
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}


    @PutMapping("/calificar-arrendatario/{solicitudId}")
    public ResponseEntity<Void> calificarArrendatario(@PathVariable Long solicitudId, @RequestBody FincaDto calificacionDto) {
        try {
            fincaService.calificarArrendatario(solicitudId, calificacionDto.getCalificacion());
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/user/{usuarioId}/arrendador")
    public ResponseEntity<List<FincaDto>> getFincasByUsuario(@PathVariable Long usuarioId) {
        try {
            List<FincaDto> fincas = fincaService.getFincasByUsuarioIdAndTipo(usuarioId);
            return new ResponseEntity<>(fincas, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{fincaId}/trasnferir/{id}")
    public ResponseEntity<String>transferirFinca(@PathVariable Long fincaId, @PathVariable Long usuarioId) {
     try {
         fincaService.transferirFinca(fincaId, usuarioId);
         return ResponseEntity.ok("Finca transferida exitosamente.");
     } catch (Exception e) {
     return ResponseEntity.badRequest().body("Error al transferir la finca." + e.getMessage());
         }   
       }
    }
