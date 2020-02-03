package org.sanja.azp.repositories;

import java.util.List;
import org.sanja.azp.entities.Pacient;
import org.springframework.data.jpa.repository.JpaRepository;


public interface PatientRepository extends JpaRepository<Pacient, Long> {
    
    public List<Pacient> findByName(String name);
    public List<Pacient> findByAddress(String address);
    public List<Pacient> findBySurname(String surname);
    
}
