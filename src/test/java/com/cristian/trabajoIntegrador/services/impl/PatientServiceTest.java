package com.cristian.trabajoIntegrador.services.impl;

import com.cristian.trabajoIntegrador.entities.Dentist;
import com.cristian.trabajoIntegrador.entities.Patient;
import com.cristian.trabajoIntegrador.exceptions.ResourceNotFoundException;
import com.cristian.trabajoIntegrador.services.IDentistService;
import com.cristian.trabajoIntegrador.services.IPatientService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
class PatientServiceTest {

    @Autowired
    IPatientService patientService;

    @Test
    void ShouldNotUpdateNonExistentPatient() {
        Patient emptyPatient = new Patient();
        emptyPatient.setId(Long.valueOf(-1));
        assertThrows(ResourceNotFoundException.class, () -> patientService.updatePatient(emptyPatient));
    }
}