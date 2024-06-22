package com.cristian.trabajoIntegrador.services.impl;

import com.cristian.trabajoIntegrador.exceptions.ResourceNotFoundException;
import com.cristian.trabajoIntegrador.services.IAppointmentService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AppointmentServiceTest {

    @Autowired
    IAppointmentService appointmentService;

    @Test
    void notFoundAppointmentShouldThrowError() {
        assertThrows(ResourceNotFoundException.class, () -> appointmentService.findAppointmentById((long) -1));
    }
}