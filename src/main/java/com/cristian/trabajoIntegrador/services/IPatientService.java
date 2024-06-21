package com.cristian.trabajoIntegrador.services;

import com.cristian.trabajoIntegrador.entities.Patient;
import com.cristian.trabajoIntegrador.exceptions.ResourceNotFoundException;


import java.util.List;
import java.util.Optional;

public interface IPatientService {

    Patient savePatient(Patient patient);

    Patient findPatientById(Long id) throws ResourceNotFoundException;

    List<Patient> findAllPatients();
    void updatePatient(Patient patient) throws ResourceNotFoundException;
    void deletePatientById(Long id) throws ResourceNotFoundException;
}
