package com.cristian.trabajoIntegrador.services.impl;

import com.cristian.trabajoIntegrador.entities.Patient;
import com.cristian.trabajoIntegrador.exceptions.ResourceNotFoundException;
import com.cristian.trabajoIntegrador.repositories.IPatientRepository;
import com.cristian.trabajoIntegrador.services.IPatientService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PatientService implements IPatientService {
    private final IPatientRepository patientRepository;
    private static final Logger logger = LoggerFactory.getLogger(PatientService.class);

    public PatientService(IPatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    @Override
    public Patient savePatient(Patient paciente) {
        logger.info("Guardando paciente: {}", paciente);
        Patient savedPatient = patientRepository.save(paciente);
        logger.info("Paciente guardado con ID: {}", savedPatient.getId());
        return savedPatient;
    }

    @Override
    public Patient findPatientById(Long id) throws ResourceNotFoundException {
        logger.info("Buscando paciente con ID: {}", id);
        Optional<Patient> patientOptional = patientRepository.findById(id);
        if (patientOptional.isPresent()) {
            logger.info("Paciente encontrado: {}", patientOptional.get());
            return patientOptional.get();
        } else {
            logger.warn("Paciente con ID {} no encontrado", id);
            throw new ResourceNotFoundException("{\"mensaje\": \"Paciente no encontrado\"}");
        }
    }

    @Override
    public List<Patient> findAllPatients() {
        logger.info("Buscando todos los pacientes");
        List<Patient> patients = patientRepository.findAll();
        logger.info("Total de pacientes encontrados: {}", patients.size());
        return patients;
    }

    @Override
    public void updatePatient(Patient paciente) throws ResourceNotFoundException {
        logger.info("Actualizando paciente con ID: {}", paciente.getId());
        Patient patientInDb = findPatientById(paciente.getId());
        patientRepository.save(paciente);
        logger.info("Paciente con ID {} actualizado exitosamente", paciente.getId());
    }

    @Override
    public void deletePatientById(Long id) throws ResourceNotFoundException {
        logger.info("Eliminando paciente con ID: {}", id);
        Patient patientInDb = findPatientById(id);
        patientRepository.deleteById(id);
        logger.info("Paciente con ID {} eliminado exitosamente", id);
    }
}