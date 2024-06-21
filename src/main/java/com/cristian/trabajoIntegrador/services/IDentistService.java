package com.cristian.trabajoIntegrador.services;


import com.cristian.trabajoIntegrador.entities.Dentist;
import com.cristian.trabajoIntegrador.exceptions.ResourceNotFoundException;

import java.util.List;
import java.util.Optional;

public interface IDentistService {
    Dentist saveDentist(Dentist dentist);

    Dentist findDentistById(Long id) throws ResourceNotFoundException;
    List<Dentist> findAllDentists();

    Dentist updateDentist(Dentist updatedDentist) throws ResourceNotFoundException;
    void deleteDentistById(Long id) throws ResourceNotFoundException;

    // Metodos con HQL
    List<Dentist> findDentistByLastName(String lastName);
    List<Dentist> findDentistByName(String name);
}
