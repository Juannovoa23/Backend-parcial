package com.finca.arriendo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.finca.arriendo.model.Finca;
import com.finca.arriendo.model.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Usuario findByNombre(String nombre);
    Usuario findByCorreo(String correo);
    @Query("SELECT f FROM Finca f WHERE f.nombre = :nombre AND f.municipio = :municipio AND f.capacidad = :capacidad")
    List<Finca> findByNombreAndMunicipioAndCapacidad(@Param("nombre") String nombre, @Param("municipio") String municipio, @Param("capacidad") int capacidad);
}
