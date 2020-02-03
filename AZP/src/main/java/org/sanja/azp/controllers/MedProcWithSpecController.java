package org.sanja.azp.controllers;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.List;
import org.sanja.azp.entities.MedicalProcedureSpecializacija;
import org.sanja.azp.entities.Specijalizacija;
import org.sanja.azp.entities.projections.OnlyProcedures;
import org.sanja.azp.repositories.MedicalProcedureSpecializacijaRepository;
import org.springframework.data.domain.Example;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;


@RestController
@RequestMapping("/medProcWithSpec")
public class MedProcWithSpecController {
    
    private MedicalProcedureSpecializacijaRepository medSpecRepository;

    public MedProcWithSpecController(MedicalProcedureSpecializacijaRepository medSpecRepository) {
        this.medSpecRepository = medSpecRepository;
    }
    
    
    
    @GetMapping()
    public List<MedicalProcedureSpecializacija> list() {
        return medSpecRepository.findAll();
    }
    
    @GetMapping("/{id}")
    public MedicalProcedureSpecializacija get(@PathVariable Long id) {
        return medSpecRepository.findById(id).get();
    }
    @GetMapping("/specijalizacija/{id}")
    public List getBySpecijalizacija(@PathVariable Long id) {
//        return medSpecRepository.findBySpecijalizacijaId(id);
        return medSpecRepository.findAllBySpecijalizacijaId(id);
    }
      
    @PostMapping
    public ResponseEntity<?> post(@RequestBody MedicalProcedureSpecializacija input) {
        return ResponseEntity.ok(medSpecRepository.save(input));
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        medSpecRepository.deleteById(id);
        return ResponseEntity.ok("Podatokot e izbrisan");
    }
    
    @DeleteMapping("/specijalizacija/{id}")
    public ResponseEntity<?> deleteBySpec(@PathVariable Long id) {
//        medSpecRepository.deleteBySpecijalizacijaId(id);
        medSpecRepository.deleteBySpecId(id); 
        return ResponseEntity.ok("Podatokot e izbrisan");
    }
    
    @DeleteMapping("/procedure/{id}")
    public ResponseEntity<?> deleteByProc(@PathVariable Long id) {
//        medSpecRepository.deleteBySpecijalizacijaId(id);
        medSpecRepository.deleteByProcId(id); 
        return ResponseEntity.ok("Podatokot e izbrisan");
    }
    
}
