package com.cristian.trabajoIntegrador.services.impl;

import com.cristian.trabajoIntegrador.entities.Dentist;
import com.cristian.trabajoIntegrador.exceptions.ResourceNotFoundException;
import com.cristian.trabajoIntegrador.repositories.IDentistRepository;
import com.cristian.trabajoIntegrador.services.IDentistService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DentistService implements IDentistService {

    private IDentistRepository dentistRepository;

    public DentistService(IDentistRepository dentistRepository) {
        this.dentistRepository = dentistRepository;
    }

    Logger logger
            = LoggerFactory.getLogger(DentistService.class);

    @Override
    public Dentist saveDentist(Dentist odontologo) {

        logger.info("Guardando odontólogo: {}", odontologo);
        return dentistRepository.save(odontologo);
    }

    @Override
    public Dentist findDentistById(Long id) throws ResourceNotFoundException {

        logger.info("Buscando odontólogo con id: {}", id);

        Optional<Dentist> optionalDentist = dentistRepository.findById(id);
        if (optionalDentist.isPresent()) {
            logger.info("Odontólogo econtrado: {}", optionalDentist.get());

            return optionalDentist.get();
        } else {
            logger.error("Odontólogo con id: {} no encontrado", id);
            throw new ResourceNotFoundException("{\"message\": \"odontólogo no encontrado\"}");
        }
    }
    @Override
    public List<Dentist> findAllDentists(){
        logger.info("Buscando todos los odontólogos");

        return dentistRepository.findAll();
    }


    @Override
    public Dentist updateDentist(Dentist updatedDentist) throws ResourceNotFoundException {

        logger.info("Actualizando odontólogo: {}", updatedDentist);

        Dentist oldDentist = findDentistById(updatedDentist.getId());
        oldDentist.setNombre(updatedDentist.getNombre());
        oldDentist.setApellido(updatedDentist.getApellido());
        oldDentist.setNroMatricula(updatedDentist.getNroMatricula());

        Dentist updatedDbDentist = dentistRepository.save(oldDentist);
        logger.info("Odontólogo actualizado: {}", updatedDbDentist);

        return updatedDbDentist;
    }

    @Override
    public void deleteDentistById(Long id) throws ResourceNotFoundException {
        logger.info("Buscando odontólogo con id: {}", id);

        Dentist dentistToDelete = findDentistById(id);
        logger.info("Odontólogo con id: {} encontrado: {}", id, dentistToDelete);

        dentistRepository.deleteById(id);
        logger.info("Odontólogo con id: {} eliminado", id);

    }

    @Override
    public List<Dentist> findDentistByLastName(String lastName) {
        logger.info("Buscando odontólogos con apellido: {}", lastName);
        List<Dentist> posiblesOdontologos = dentistRepository.findByLastname(lastName);
        int cantidadOdontologos = posiblesOdontologos.size();

        if (cantidadOdontologos > 0) {
            logger.info("Se encontraron {} odontólogos con apellido {}", cantidadOdontologos, lastName);
        } else {
            logger.info("Se encontraron 0 odontólogos con apellido {}", lastName);
        }
        return posiblesOdontologos;
    }

    @Override
    public List<Dentist> findDentistByName(String nombre) {
        logger.info("Buscando odontólogos con nombre: {}", nombre);
        List<Dentist> posiblesOdontologos = dentistRepository.findByLastname(nombre);
        int cantidadOdontologos = posiblesOdontologos.size();

        if (cantidadOdontologos > 0) {
            logger.info("Se encontraron {} odontólogos con nombre {}", cantidadOdontologos, nombre);
        } else {
            logger.info("Se encontraron 0 odontólogos con nombre {}", nombre);
        }
        return posiblesOdontologos;

    }
}
