/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.sanja.azp.controllers;

import java.util.Collection;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.sanja.azp.entities.Doctor;
import org.sanja.azp.entities.Specijalizacija;
import org.sanja.azp.repositories.DoctorRepository;
import org.sanja.azp.repositories.SpecijalizacijaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 *
 * @author Sanja
 */
@RestController
@RequestMapping("/doctor")
public class DoctorController {

    private DoctorRepository doctor;
    private SpecijalizacijaRepository specRepo;
    public DoctorController(DoctorRepository doctor,SpecijalizacijaRepository specijalizacijaRepository) {
        this.doctor = doctor;
        this.specRepo = specijalizacijaRepository;
    }

    @GetMapping()
    public List<Doctor> list() {
        return doctor.findAll();
    }

    @GetMapping("/{id}")
    public Doctor get(@PathVariable Long id) {
        return doctor.findById(id).get();
    }

    @GetMapping("/name/{name}")
    public List<Doctor> getbyDoctor(@PathVariable String name) {
        return Stream.of(doctor.findByName(name), doctor.findBySurname(name))
                .flatMap(Collection<Doctor>::stream)
                .collect(Collectors.toList());

    }
    
     @GetMapping("/spec/{specId}")
    public List<Doctor> getbySpecId(@PathVariable Long specId) {
        
     return doctor.findBySpecId(specId);

    }

    @PostMapping
    public ResponseEntity<?> post(@RequestBody Doctor input) throws Exception {
        if (input.getSpec() !=null && input.getSpec().getId() != null) {
            Specijalizacija special = specRepo.findById(input.getSpec().getId()).orElseThrow(()->new NoSuchElementException());
            
            input.setSpec(special);
        }
        
        return ResponseEntity.ok(doctor.save(input));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        doctor.deleteById(id);
        return ResponseEntity.ok("Vasiot podatok e izbrisan");

    }
    @ExceptionHandler(NoSuchElementException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Ne postoi takov element")
    public void handleError() {
    }

}

