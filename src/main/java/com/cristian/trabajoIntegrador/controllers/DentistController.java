package com.cristian.trabajoIntegrador.controllers;

import com.cristian.trabajoIntegrador.entities.Dentist;
import com.cristian.trabajoIntegrador.exceptions.ResourceNotFoundException;
import com.cristian.trabajoIntegrador.services.impl.DentistService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/odontologo")
public class DentistController {
    private DentistService dentistService;

    public DentistController(DentistService dentistService) {
        this.dentistService = dentistService;
    }

    @PostMapping
    public ResponseEntity<Dentist> registrarDentist(@Valid @RequestBody Dentist odontologo){
        Dentist newDentist = dentistService.saveDentist(odontologo);
        return ResponseEntity.status(HttpStatus.CREATED).body(odontologo);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Dentist>  buscarDentistPorId(@PathVariable Long id) throws ResourceNotFoundException {

        Dentist odontologo = dentistService.findDentistById(id);
        return ResponseEntity.ok(odontologo);
    }

    @PutMapping
    public ResponseEntity<String> modificarDentist(@Valid @RequestBody Dentist odontologo) throws ResourceNotFoundException {
        Dentist updatedDentist = dentistService.updateDentist(odontologo);
        return ResponseEntity.ok("{\"message\": \"odontologo modificado\"}");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarDentist(@PathVariable Long id) throws ResourceNotFoundException {
            dentistService.deleteDentistById(id);
            return ResponseEntity.ok("{\"message\": \"odontologo eliminado\"}");
    }

    @GetMapping
    public ResponseEntity<List<Dentist>> buscarTodos(){
        List<Dentist> allDentists = dentistService.findAllDentists();
        return ResponseEntity.ok(allDentists);
    }

    @GetMapping("/apellido/{apellido}")
    public ResponseEntity<List<Dentist>> buscarPorApellido(@Valid @PathVariable String apellido){
        List<Dentist> listaDentists = dentistService.findDentistByLastName(apellido);
        return ResponseEntity.ok(listaDentists);
    }

    @GetMapping("/nombre/{nombre}")
    public ResponseEntity<List<Dentist>> buscarPorNombre(@Valid @PathVariable String nombre){
        List<Dentist> listaDentists = dentistService.findDentistByName(nombre);
        return ResponseEntity.ok(listaDentists);
    }
}
