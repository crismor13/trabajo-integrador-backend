package com.cristian.trabajoIntegrador.services.impl;

import com.cristian.trabajoIntegrador.entities.Dentist;
import com.cristian.trabajoIntegrador.exceptions.ResourceNotFoundException;
import com.cristian.trabajoIntegrador.services.IAppointmentService;
import com.cristian.trabajoIntegrador.services.IDentistService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.bind.MethodArgumentNotValidException;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class DentistServiceTest {

    @Autowired
    IDentistService dentistService;

    @Test
    void ShouldNotUpdateNonExistentDentist() {
        Dentist emptyDentist = new Dentist();
        emptyDentist.setId(Long.valueOf(-1));
        assertThrows(ResourceNotFoundException.class, () -> dentistService.updateDentist(emptyDentist));
    }
}