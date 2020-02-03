package org.sanja.azp.controllers;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.List;
import org.sanja.azp.entities.MedicalProcedure;
import org.sanja.azp.repositories.MedicalProcedureRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@RestController
@RequestMapping("/procedure")
public class MedicalProcedureController {
    
    private MedicalProcedureRepository medRepo;

    public MedicalProcedureController(MedicalProcedureRepository medRepo) {
        this.medRepo = medRepo;
    }
    
    
    
    @GetMapping()
    public List<MedicalProcedure> list() {
        return medRepo.findAll();
    }
    
    @GetMapping("/{id}")
    public MedicalProcedure get(@PathVariable Long id) {
        return medRepo.findById(id).get();
    }
    
    @PostMapping
    public ResponseEntity<?> post(@RequestBody MedicalProcedure procedure) {
        
        return ResponseEntity.ok(medRepo.save(procedure));
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        medRepo.deleteById(id);
        return ResponseEntity.ok("Podatokot e izbrishan");
    }
    
    @ExceptionHandler(Exception.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR, reason = "Error message")
    public void handleError() {
    }
    
}
