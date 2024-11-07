package com.finca.arriendo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.finca.arriendo.model.Finca;
import com.finca.arriendo.model.Usuario;

@Repository
public interface FincaRepository extends JpaRepository<Finca, Long> {
    
    Optional<Finca> findByNombre(String nombre);
    
    List<Finca> findByUbicacion(String ubicacion);
    @Query("SELECT f FROM Finca f WHERE f.dueño = :dueño")
    List<Finca> findByDueño(@Param("dueño") Usuario dueño);
}