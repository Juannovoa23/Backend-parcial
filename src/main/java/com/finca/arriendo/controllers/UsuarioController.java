package com.finca.arriendo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.finca.arriendo.dto.UsuarioDto;
import com.finca.arriendo.services.UsuarioService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/usuario")
@CrossOrigin
@Validated
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    // Listar todos los usuarios
    @GetMapping("/list")
    public ResponseEntity<List<UsuarioDto>> get() {
        try {
            List<UsuarioDto> usuarios = usuarioService.list();
            return new ResponseEntity<>(usuarios, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Obtener usuario por ID
    @GetMapping("/get/{id}")
    public ResponseEntity<UsuarioDto> get(@PathVariable Long id) {
        try {
            UsuarioDto usuario = usuarioService.get(id);
            if (usuario != null) {
                return new ResponseEntity<>(usuario, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Obtener usuario por nombre
    @GetMapping("/getNom/{nombre}")
    public ResponseEntity<UsuarioDto> getByNombre(@PathVariable String nombre) {
        try {
            UsuarioDto usuario = usuarioService.get(nombre);
            if (usuario != null) {
                return new ResponseEntity<>(usuario, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Crear un nuevo usuario (Registro)
    @PostMapping("/create")
    public ResponseEntity<UsuarioDto> save(@Valid @RequestBody UsuarioDto usuarioDto) {
        try {
            UsuarioDto usuarioCreado = usuarioService.saveNew(usuarioDto);
            return new ResponseEntity<>(usuarioCreado, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Actualizar un usuario existente
    @PutMapping("/update")
    public ResponseEntity<UsuarioDto> update(@Valid @RequestBody UsuarioDto usuarioDto) {
        try {
            UsuarioDto usuarioActualizado = usuarioService.save(usuarioDto);
            return new ResponseEntity<>(usuarioActualizado, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Eliminar un usuario
    @DeleteMapping("/delete")
    public ResponseEntity<Void> delete(@Valid @RequestBody UsuarioDto usuarioDto) {
        try {
            usuarioService.delete(usuarioDto);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Endpoint del método de autenticación 


    @PostMapping("/login") // Ruta para iniciar sesión
        public ResponseEntity<String> autenticar(@RequestParam String nombre, @RequestParam String contrasena) {
            boolean autenticado = usuarioService.autenticar(nombre, contrasena); 
    
            if (autenticado) {
                return ResponseEntity.ok("Autenticación exitosa");
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciales inválidas");
            }
        }
    
}
