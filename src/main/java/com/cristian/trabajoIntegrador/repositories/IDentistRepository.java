package com.cristian.trabajoIntegrador.repositories;

import com.cristian.trabajoIntegrador.entities.Dentist;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IDentistRepository extends JpaRepository<Dentist, Long> {
    // Buscar odontologos por apellido
    @Query("Select d from Dentist d where LOWER(d.apellido) = LOWER(:lastName)")
    List<Dentist> findByLastname(String lastName);

    // Buscar odontologos por nombre
    @Query("Select d from Dentist d where LOWER(d.nombre) LIKE LOWER(CONCAT('%',:name,'%'))")
    List<Dentist> findByNameLike(@Param("name") String name);

}
