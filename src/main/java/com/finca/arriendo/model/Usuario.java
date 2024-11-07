package com.finca.arriendo.model;

import java.util.List;

import io.micrometer.common.lang.Nullable;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // ID del usuario

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_usuario", nullable = false)
    private Tipo tipo; // Tipo de usuario (ARRENDADOR, ARRENDATARIO)

    @Column(name = "nombre", nullable = false, length = 50)
    private String nombre; // Nombre del usuario

    @Column(name = "apellido", nullable = false, length = 50) 
    private String apellido; // Apellido del usuario

    @Column(name = "correo", nullable = false, unique = true, length = 100)
    private String correo; // Correo electrónico del usuario

    @Nullable
    @Column(name = "telefono")
    private Integer telefono; // Teléfono del usuario 

    @Column(name = "contrasena", nullable = false)
    private String contrasena; // Contraseña del usuario

    @Nullable
    private Float calificacion; // Calificación del usuario (

    @OneToMany(mappedBy = "dueño", cascade = CascadeType.ALL)
    private List<Finca> fincas; // Lista de fincas del usuario

    @OneToMany(mappedBy = "arrendatario", cascade = CascadeType.ALL)
    private List<Solicitud> solicitudesArrendatario; // Solicitudes del arrendatario

    @OneToMany(mappedBy = "arrendador", cascade = CascadeType.ALL)
    private List<Solicitud> solicitudesArrendador; // Solicitudes del arrendador
    @Column(name = "deleted", nullable = false)
    private boolean deleted = false; // Marca para soft delete

    @PrePersist
    @PreUpdate
    private void validate() {
        if (correo == null || correo.isEmpty()) {
            throw new IllegalArgumentException("El correo no puede estar vacío.");
        }
        if (contrasena == null || contrasena.isEmpty()) {
            throw new IllegalArgumentException("La contraseña no puede estar vacía.");
        }
    }

    public void agregarCalificacion(float nuevaCalificacion) {
        if (calificacion == null) {
            calificacion = nuevaCalificacion;
        } else {
            calificacion = (calificacion + nuevaCalificacion) / 2; // Promedio simple
        }
    }

    public void registrarUsuario(String nombre, String apellido, String correo, String contrasena, Integer telefono, Tipo tipo) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.correo = correo;
        this.contrasena = contrasena;
        this.telefono = telefono;
        this.tipo = tipo;
    }

    public List<Solicitud> obtenerSolicitudes() {
        if (tipo == Tipo.ARRENDATARIO) {
            return solicitudesArrendatario;
        } else if (tipo == Tipo.ARRENDADOR) {
            return solicitudesArrendador;
        }
        return List.of(); // Retorna una lista vacía si el tipo es inválido
    }

    public boolean esSolicitante() {
        return tipo == Tipo.ARRENDATARIO;
    }

    public boolean esArrendador() {
        return tipo == Tipo.ARRENDADOR;
    }

    public void validar() {
        validate();
    }

    public Tipo getTipo() {
        return tipo;
    }

    public void setTipo(Tipo tipo) {
        this.tipo = tipo;
    }
}
