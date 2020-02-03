/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.sanja.azp.controllers;

import java.util.ArrayList;
import java.util.Arrays;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.List;
import javax.validation.Valid;
import org.apache.logging.log4j.util.Strings;
import org.sanja.azp.entities.Doctor;
import org.sanja.azp.entities.Pacient;
import org.sanja.azp.entities.Pregled;
import org.sanja.azp.entities.projections.PregledSchedule;
import org.sanja.azp.formModels.PregledForm;
import org.sanja.azp.formModels.ZacuvajForm;
import org.sanja.azp.repositories.DoctorRepository;
import org.sanja.azp.repositories.PatientRepository;
import org.sanja.azp.repositories.PregledRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

/**
 *
 * @author Sanja
 */
@RestController
@RequestMapping("/pregled")
public class PregledController {

    private PregledRepository pregledRep;
    private PatientRepository patientRepo;
    private DoctorRepository docRepo;

    public PregledController(PregledRepository pregledRep, PatientRepository pr, DoctorRepository dr) {
        this.pregledRep = pregledRep;
        this.patientRepo = pr;
        this.docRepo = dr;
    }

    @GetMapping()
    public List<Pregled> list() {
        return pregledRep.findAll();

    }
    @CrossOrigin
    @GetMapping("/doctor/{doctorId}")
    public List<PregledSchedule> listByDoctor(@PathVariable Long doctorId) {
        return pregledRep.findAllByDoctorId(doctorId);

    }

    @GetMapping("/{id}")
    public Pregled get(@PathVariable Long id) {
        return pregledRep.findById(id).get();
    }

//    @PutMapping("/{id}")
//    public ResponseEntity<?> put(@PathVariable String id, @RequestBody Object input) {
//        return null;
//    }
//    @PostMapping
//    public ResponseEntity<?> post(@RequestBody @Valid Pregled preg) {
//        Long pacientId= preg.getPacient().getId();
//        Long doctorId = preg.getDoctor().getId();
//        
//        if(pacientId!=null){
//           preg.setPacient(patientRepo.findById(pacientId).orElse(null));
//        }
//        
//        
//        return ResponseEntity.ok( pregledRep.save(preg));
//    }
    @PostMapping(value = "/zakazi")
    public ResponseEntity<?> zakazi(@RequestBody @Valid PregledForm preg) {
        Long pacientId = preg.getPacientId();
        Long doctorId = preg.getDoctorName();
        Doctor doc = docRepo.findById(doctorId).orElseGet(null);
        if (doc == null) {
            ResponseEntity.status(HttpStatus.NOT_FOUND).body("Invalid doctor");
        }

        Pregled novPreglet = new Pregled();
        novPreglet.setDate(preg.getTermininfo());

        if (pacientId != null) {
            novPreglet.setPacient(patientRepo.findById(pacientId).orElse(null));
        } else {
            Pacient novPacient = new Pacient();
            novPacient.setName(preg.getPatientname());
            novPacient.setSurname(preg.getPatientsurname());
            novPacient.setAddress(preg.getPatientaddress());
            novPacient.setDateOfBirth(preg.getPatientbirth());
            novPacient.setSocialSecNum(preg.getPatientsocialnum());
            novPreglet.setPacient(novPacient);
        }
        novPreglet.setDoctor(doc);

        return ResponseEntity.ok(pregledRep.save(novPreglet));
    }

    @PostMapping(value = "/zacuvaj")
    public ResponseEntity<?> zacuvajPregled(@RequestBody ZacuvajForm preg) {
        Pregled pregled = pregledRep.findById(preg.getId()).orElse(null);
        
        if (pregled != null) {
            pregled.setKomentar(preg.getKomentar());
            pregled.setSimptomi(preg.getSimptomi());
            
            pregled.setDetailsOfPregled(String.join(",",preg.getProceduri()));
            return ResponseEntity.ok(pregledRep.save(pregled));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Pogrsen Id na pregled");
        }

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        pregledRep.deleteById(id);
        return ResponseEntity.ok("Podatokot e izbrisan");
    }

}
