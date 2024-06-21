package com.cristian.trabajoIntegrador.repositories;

import com.cristian.trabajoIntegrador.entities.Patient;

import org.springframework.data.jpa.repository.JpaRepository;

public interface IPatientRepository extends JpaRepository<Patient, Long> {
}
