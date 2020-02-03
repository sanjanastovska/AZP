
package org.sanja.azp.controllers;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.List;
import java.util.Set;
import jdk.nashorn.internal.runtime.Specialization;
import org.sanja.azp.entities.Specijalizacija;
import org.sanja.azp.repositories.SpecijalizacijaRepository;
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
@RequestMapping("/specijalizacija")
public class SpecijalizacijaController {
    
    private SpecijalizacijaRepository specRepo;

    public SpecijalizacijaController(SpecijalizacijaRepository specRepo) {
        this.specRepo = specRepo;
    }
    
    
    
    @GetMapping()
    public List<Specijalizacija> list() {
        return specRepo.findAll();
    }
    
    @GetMapping("/{id}")
    public Specijalizacija get(@PathVariable Long id) {
        return specRepo.findById(id).orElse(new Specijalizacija());
    }
       
    
    @PostMapping
    public ResponseEntity<?> post(@RequestBody Specijalizacija spec) {
        Specijalizacija returnSpec = specRepo.save(spec);
        
        return ResponseEntity.ok(returnSpec);
    }
    
    @PostMapping("/list")
    public ResponseEntity<?> post(@RequestBody List<Specijalizacija> specs) {
        specRepo.saveAll(specs);
        
        return ResponseEntity.ok("Vashite podatoci ... listata ... se zachuvani vo temp bazata shto ne e oracle");
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        specRepo.deleteById(id);
        return ResponseEntity.ok("Podatokot e izbrishan"); 
    }
    
    @ExceptionHandler(Exception.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR, reason = "Error message")
    public void handleError() {
    }
    
}
