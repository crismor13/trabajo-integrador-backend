package com.cristian.trabajoIntegrador.services.impl;

import com.cristian.trabajoIntegrador.entities.Patient;
import com.cristian.trabajoIntegrador.exceptions.ResourceNotFoundException;
import com.cristian.trabajoIntegrador.repositories.IPatientRepository;
import com.cristian.trabajoIntegrador.services.IPatientService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PatientService implements IPatientService {
    private IPatientRepository patientRepository;

    public PatientService(IPatientRepository pacienteRepository) {
        this.patientRepository = pacienteRepository;
    }

    @Override
    public Patient savePatient(Patient paciente){

        return patientRepository.save(paciente);
    }

    @Override
    public Patient findPatientById(Long id) throws ResourceNotFoundException {

        Optional<Patient> patientOptional = patientRepository.findById(id);

        if(patientOptional.isPresent())
            return patientOptional.get();
        else
            throw new ResourceNotFoundException("{\"message\": \"paciente no encontrado\"}");

    }

    @Override
    public List<Patient> findAllPatients(){
        return patientRepository.findAll();
    }

    @Override
    public void updatePatient(Patient paciente) throws ResourceNotFoundException {
        Patient patientInDb = findPatientById(paciente.getId());
        patientRepository.save(paciente);
    }

    @Override
    public void deletePatientById(Long id) throws ResourceNotFoundException {
        Patient patientInDb = findPatientById(id);
        patientRepository.deleteById(id);
    }
}
