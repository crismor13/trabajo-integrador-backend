package com.cristian.trabajoIntegrador.services.impl;

import com.cristian.trabajoIntegrador.DTOs.AppointmentRequestDto;
import com.cristian.trabajoIntegrador.entities.Appointment;
import com.cristian.trabajoIntegrador.entities.Dentist;
import com.cristian.trabajoIntegrador.entities.Patient;
import com.cristian.trabajoIntegrador.exceptions.ResourceNotFoundException;
import com.cristian.trabajoIntegrador.repositories.IAppointmentRepository;
import com.cristian.trabajoIntegrador.repositories.IDentistRepository;
import com.cristian.trabajoIntegrador.repositories.IPatientRepository;
import com.cristian.trabajoIntegrador.services.IAppointmentService;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Service
public class AppointmentService implements IAppointmentService {
    private IDentistRepository dentistRepository;
    private IPatientRepository patientRepository;
    private IAppointmentRepository appointmentRepository;
    private ModelMapper modelMapper;

    Logger logger
            = LoggerFactory.getLogger(AppointmentService.class);

    public AppointmentService(IDentistRepository odontologoRepository, IPatientRepository pacienteRepository, IAppointmentRepository turnoRepository, ModelMapper modelMapper) {
        this.dentistRepository = odontologoRepository;
        this.patientRepository = pacienteRepository;
        this.appointmentRepository = turnoRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public Appointment saveAppointment(AppointmentRequestDto newAppointmentDto) throws ResourceNotFoundException {

        logger.info("Creando nuevo turno: {}", newAppointmentDto);

        Optional<Patient> patient = patientRepository.findById(newAppointmentDto.getPaciente_id());
        Optional<Dentist> dentist = dentistRepository.findById(newAppointmentDto.getOdontologo_id());
        Appointment newAppointment = new Appointment();

        if(patient.isPresent() && dentist.isPresent()) {

            newAppointment.setOdontologo(dentist.get());
            newAppointment.setPaciente(patient.get());
            newAppointment.setFecha(LocalDate.parse(newAppointmentDto.getFecha()));
            newAppointment.setHora(LocalTime.parse(newAppointmentDto.getHora()));

            return appointmentRepository.save(newAppointment);

        } else {
            logger.error("Turno no pudo ser creado: {}", newAppointmentDto);
            throw new ResourceNotFoundException("{\"message\": \"paciente y/u odontólogo no encontrado(s)\"}");
        }
    }

    @Override
    public Appointment findAppointmentById(Long id) throws ResourceNotFoundException {

        logger.info("Buscando turno con id: {}", id);


        Optional<Appointment> appointmentOptional = appointmentRepository.findById(id);
        if(appointmentOptional.isPresent()){

            logger.info("Turno econtrado: {}", appointmentOptional.get());
            return appointmentOptional.get();

        }else {
            logger.error("Turno con id {} no econtrado", id);
            throw new ResourceNotFoundException("{\"message\": \"turno no encontrado\"}");
        }
    }

    @Override
    public List<Appointment> findAllAppointments() {

        logger.info("Buscando todos los turnos");

        List<Appointment> allAppointments = appointmentRepository.findAll();
        return allAppointments;
    }

    @Override
    public void updateAppointment(Long id, AppointmentRequestDto updatedAppointmentDto) throws ResourceNotFoundException {

        logger.info("Actualizando nuevo turno: {} con id: {}", updatedAppointmentDto, id);


        Optional<Patient> patient = patientRepository.findById(updatedAppointmentDto.getPaciente_id());
        Optional<Dentist> dentist = dentistRepository.findById(updatedAppointmentDto.getOdontologo_id());
        Optional<Appointment> oldAppointmentOptional = appointmentRepository.findById(id);

        if(patient.isPresent() && dentist.isPresent() && oldAppointmentOptional.isPresent()){

            Appointment oldAppointment = oldAppointmentOptional.get();

            oldAppointment.setOdontologo(dentist.get());
            oldAppointment.setPaciente(patient.get());
            oldAppointment.setFecha(LocalDate.parse(updatedAppointmentDto.getFecha()));
            oldAppointment.setHora(LocalTime.parse(updatedAppointmentDto.getHora()));
            appointmentRepository.save(oldAppointment);
            logger.info("Turno con id {} guardado", id);

        } else {
            logger.error("No ha sido posible guardar turno con id {}", id);

            throw new ResourceNotFoundException("{\"message\": \"paciente y/u odontólogo y/o turno no encontrado(s)\"}");
        }
    }

    @Override
    public void deleteAppointmentById(Long id) throws ResourceNotFoundException {
        logger.info("Eliminando turno con id: {}", id);

        Appointment appointment = findAppointmentById(id);
        if(appointment != null) {
            appointmentRepository.deleteById(id);
            logger.info("Turno con id: {} eliminado", id);
        } else {
            logger.error("Turno con id: {} no pudo ser eliminado", id);
            throw new ResourceNotFoundException("{\"message\": \"turno no encontrado\"}");
        }
    }

    @Override
    public List<Appointment> findAppointmentsBetweenDates(LocalDate startDate, LocalDate endDate) {
        logger.info("Buscando citas entre {} y {}", startDate, endDate);
        List<Appointment> appointments = appointmentRepository.findBetweenDates(startDate, endDate);
        return appointments;
    }

}
