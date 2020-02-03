package org.sanja.azp.repositories;

import org.sanja.azp.entities.MedicalProcedure;
import org.springframework.data.jpa.repository.JpaRepository;


public interface MedicalProcedureRepository extends JpaRepository<MedicalProcedure, Long> {
    
}
