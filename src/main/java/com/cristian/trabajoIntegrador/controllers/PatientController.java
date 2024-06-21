package com.cristian.trabajoIntegrador.controllers;

import com.cristian.trabajoIntegrador.entities.Patient;
import com.cristian.trabajoIntegrador.exceptions.ResourceNotFoundException;
import com.cristian.trabajoIntegrador.services.IPatientService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("paciente")
public class PatientController {

    public IPatientService pacienteService;

    public PatientController(IPatientService pacienteService) {
        this.pacienteService = pacienteService;
    }

    @PostMapping
    public ResponseEntity<Patient>  registrarPatient(@Valid @RequestBody Patient paciente){
        Patient pacienteARetornar = pacienteService.savePatient(paciente);
        return ResponseEntity.ok(pacienteARetornar);
    }

    @GetMapping
    public ResponseEntity<List<Patient>>  buscarTodos(){
        List<Patient> allPatients = pacienteService.findAllPatients();
        return ResponseEntity.ok(allPatients);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Patient> buscarPatientPorId(@Valid @PathVariable Long id) throws ResourceNotFoundException {
        Patient paciente = pacienteService.findPatientById(id);
        return ResponseEntity.ok(paciente);
    }

    @PutMapping
    public ResponseEntity<String>  actualizarPatient(@Valid @RequestBody Patient paciente) throws ResourceNotFoundException {
        pacienteService.updatePatient(paciente);
        return ResponseEntity.ok("{\"message\": \"paciente actualizado\"}");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String>  borrarPatient(@Valid @PathVariable Long id) throws ResourceNotFoundException {
        pacienteService.deletePatientById(id);
        return ResponseEntity.ok("{\"message\": \"paciente eliminado\"}");
    }

}
