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

    public AppointmentService(IDentistRepository odontologoRepository, IPatientRepository pacienteRepository, IAppointmentRepository turnoRepository, ModelMapper modelMapper) {
        this.dentistRepository = odontologoRepository;
        this.patientRepository = pacienteRepository;
        this.appointmentRepository = turnoRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public Appointment saveAppointment(AppointmentRequestDto newAppointmentDto) throws ResourceNotFoundException {

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
            throw new ResourceNotFoundException("{\"message\": \"paciente y/u odontólogo no encontrado(s)\"}");
        }
    }

    @Override
    public Appointment findAppointmentById(Long id) throws ResourceNotFoundException {

        Optional<Appointment> appointmentOptional = appointmentRepository.findById(id);
        if(appointmentOptional.isPresent()){
            return appointmentOptional.get();
        }else {
            throw new ResourceNotFoundException("{\"message\": \"turno no encontrado\"}");
        }
    }

    @Override
    public List<Appointment> findAllAppointments() {
        List<Appointment> allAppointments = appointmentRepository.findAll();
        return allAppointments;
    }

    @Override
    public void updateAppointment(Long id, AppointmentRequestDto updatedAppointmentDto) throws ResourceNotFoundException {

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
        } else {
            throw new ResourceNotFoundException("{\"message\": \"paciente y/u odontólogo y/o turno no encontrado(s)\"}");
        }
    }

    @Override
    public void deleteAppointmentById(Long id) throws ResourceNotFoundException {
        Appointment appointment = findAppointmentById(id);
        if(appointment != null)
            appointmentRepository.deleteById(id);
        else
            throw new ResourceNotFoundException("{\"message\": \"turno no encontrado\"}");
    }

    @Override
    public List<Appointment> findAppointmentsBetweenDates(LocalDate startDate, LocalDate endDate) {
        List<Appointment> appointments = appointmentRepository.findBetweenDates(startDate, endDate);
        return appointments;
    }

}
