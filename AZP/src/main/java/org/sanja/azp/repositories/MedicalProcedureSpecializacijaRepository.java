package org.sanja.azp.repositories;

import java.util.List;
import org.sanja.azp.entities.MedicalProcedureSpecializacija;
import org.sanja.azp.entities.projections.OnlyProcedures;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;


public interface MedicalProcedureSpecializacijaRepository extends JpaRepository<MedicalProcedureSpecializacija, Long> {
    @Transactional
    public void deleteBySpecijalizacijaId(Long id);
    
    
    public List<MedicalProcedureSpecializacija> findBySpecijalizacijaId(Long id);
    
    public List<MedicalProcedureSpecializacija> findByProcedureId(Long id);
    
    public List<OnlyProcedures> findAllBySpecijalizacijaId(Long id);
    
    @Transactional 
    @Modifying
    @Query("DELETE FROM MedicalProcedureSpecializacija as ms WHERE ms.specijalizacija.id = :specId")
    public void deleteBySpecId(@Param(value = "specId")Long specId);
    
    @Transactional 
    @Modifying
    @Query("DELETE FROM MedicalProcedureSpecializacija as ms WHERE ms.procedure.id = :procedureId")
    public void deleteByProcId(@Param(value = "procedureId")Long procedureId);
} 
