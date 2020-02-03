
package org.sanja.azp.controllers;

import java.util.Collection;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.sanja.azp.entities.Pacient;
import org.sanja.azp.repositories.PatientRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;


@RestController
@RequestMapping("/patient")
public class PatientController {

    private PatientRepository patient;

    public PatientController(PatientRepository patient) {
        this.patient = patient;
    }

    @GetMapping()
    public List<Pacient> list() {
        return patient.findAll();
    }

    @GetMapping("/{id}")
    public Pacient get(@PathVariable Long id) {
        return patient.findById(id).get();
    }
    
    @GetMapping("/name/{name}")
    public List<Pacient> getbyPacient(@PathVariable String name) {
        return Stream.of(patient.findByName(name),patient.findBySurname(name),patient.findByAddress(name))
                .flatMap(Collection<Pacient>::stream)
                .collect(Collectors.toList());
    
    }


    @PostMapping
    public ResponseEntity<?> post(@RequestBody Pacient input) {
        return ResponseEntity.ok(patient.save(input));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        patient.deleteById(id);
        return ResponseEntity.ok("Vasiot podatok e izbrisan");
  
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR, reason = "Error message")
    public void handleError() {
    }

}
