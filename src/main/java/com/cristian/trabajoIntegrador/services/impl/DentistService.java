package com.cristian.trabajoIntegrador.services.impl;

import com.cristian.trabajoIntegrador.entities.Dentist;
import com.cristian.trabajoIntegrador.exceptions.ResourceNotFoundException;
import com.cristian.trabajoIntegrador.repositories.IDentistRepository;
import com.cristian.trabajoIntegrador.services.IDentistService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DentistService implements IDentistService {

    private IDentistRepository dentistRepository;

    public DentistService(IDentistRepository dentistRepository) {
        this.dentistRepository = dentistRepository;
    }

    @Override
    public Dentist saveDentist(Dentist odontologo){
        return dentistRepository.save(odontologo);
    }

    @Override
    public Dentist findDentistById(Long id) throws ResourceNotFoundException {

        Optional<Dentist> optionalDentist = dentistRepository.findById(id);
        if (optionalDentist.isPresent()) {
            return optionalDentist.get();
        } else {
            throw new ResourceNotFoundException("{\"message\": \"odontólogo no encontrado\"}");
        }
    }
    @Override
    public List<Dentist> findAllDentists(){
        return dentistRepository.findAll();
    }


    @Override
    public Dentist updateDentist(Dentist updatedDentist) throws ResourceNotFoundException {

        Dentist oldDentist = findDentistById(updatedDentist.getId());
        oldDentist.setNombre(updatedDentist.getNombre());
        oldDentist.setApellido(updatedDentist.getApellido());
        oldDentist.setNroMatricula(updatedDentist.getNroMatricula());

        return dentistRepository.save(oldDentist);
    }

    @Override
    public void deleteDentistById(Long id) throws ResourceNotFoundException {
        Dentist dentistToDelete = findDentistById(id);
        dentistRepository.deleteById(id);
    }

    @Override
    public List<Dentist> findDentistByLastName(String lastName) {
        return dentistRepository.findByLastname(lastName);
    }

    @Override
    public List<Dentist> findDentistByName(String nombre) {
        return dentistRepository.findByNameLike(nombre);
    }
}
