package com.finca.arriendo.model;

import java.util.Date;

import io.micrometer.common.lang.Nullable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor

public class Solicitud {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private Estado estado;

    @ManyToOne
    @JoinColumn(name = "arrendatario_id", nullable = false)
    private Usuario arrendatario;

    @ManyToOne
    @JoinColumn(name = "arrendador_id", nullable = false) 
    private Usuario arrendador;

    @ManyToOne
    @JoinColumn(name = "finca_id", nullable = false)
    private Finca finca;

    @Column(nullable = false)
    private Date fechaInicio;

    @Column(nullable = false) 
    private Date fechaFin;


    @Nullable
    @Column
    private Integer califFinca; // Cambiado a Integer para manejar valores nulos

    @Nullable
    @Column
    private Integer califArrendatario; // Cambiado a Integer para manejar valores nulos

    @Column(nullable = false) 
    private float precio;

    @Column(nullable = false)
    private int cantPersonas;

    @Column(nullable = false)
    private boolean deleted = false;

    @Nullable
    @Column(length = 20)
    private String numeroCuenta; // Para almacenar el número de cuenta

    @Column(length = 50)
    private String banco; // Para almacenar el  nombre del banco

    public Solicitud(Usuario arrendatario, Usuario arrendador, Finca finca, Date fechaInicio, Date fechaFin,
                     Integer califFinca, Integer califArrendatario, float precio, int cantPersonas, boolean deleted, Estado estado) {
        this.arrendatario = arrendatario;
        this.arrendador = arrendador;
        this.finca = finca;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.califFinca = califFinca;
        this.califArrendatario = califArrendatario;
        this.precio = precio;
        this.cantPersonas = cantPersonas;
        this.deleted = deleted;
        this.estado = estado; 
    }

    public void iniciarPago(float nuevoPrecio, String numeroCuenta, String banco) {
        if (this.estado == Estado.EN_PAGO && !this.estado.equals(Estado.VENCIDA)) {
            this.precio = nuevoPrecio; // Actualizar precio
            this.numeroCuenta = numeroCuenta; // Actualizar número de cuenta
            this.banco = banco; // Actualizar banco
        }
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public boolean isPagoVisible() {
        return this.estado == Estado.EN_PAGO && !this.estado.equals(Estado.VENCIDA);
    }

    public boolean isCalificacionVisible() {
        return this.estado == Estado.CERRADO; // Ejemplo de lógica para calificación
    }

    public boolean isCalificarVisible() {
        return this.estado == Estado.CERRADO && this.fechaFin.before(new Date());
    }

    public void setId(Long id) {
        this.id = id;
    }
    public Long getId() {
        return id;
    }

    public void setArrendatario(Usuario arrendatario) {
        this.arrendatario = arrendatario;
    }
    
    public void setArrendador(Usuario arrendador) {
        this.arrendador = arrendador;
    }
    
    public void setFinca(Finca finca) {
        this.finca = finca;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }
    
    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }
    
    public Date getFechaFin() {
        return fechaFin;
    }
    
    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }
    


    public void setCalifFinca(Integer califFinca) {
        this.califFinca = califFinca;
    }
    
    public void setCalifArrendatario(Integer califArrendatario) {
        this.califArrendatario = califArrendatario;
    }
    
    public void setPrecio(float precio) {
        this.precio = precio;
    }
    
    public void setCantPersonas(int cantPersonas) {
        this.cantPersonas = cantPersonas;
    }
    
    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public Integer getCalifArrendatario() {
        return this.califArrendatario;
    }

    public void calificarArrendatario(int calificacion) {
        this.califArrendatario = calificacion;
    }

    public void setNumeroCuenta(String numeroCuenta) {
        this.numeroCuenta = numeroCuenta;
    }

    public String getNumeroCuenta() {
        return numeroCuenta;
    }

    public void setBanco(String banco) {
        this.banco = banco;
    }

    public String getBanco() {
        return banco;
    }

    public Integer getCalifFinca() {
        return this.califFinca;
    }

    public Estado getEstado() {
        return this.estado;
    }
    
    
}
