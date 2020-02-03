/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.sanja.azp.entities;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author Sanja
 */
@Data
@NoArgsConstructor
@Entity
@Table(name = "MEDICAL_PROCEDURE_SPECIALIZATION")
public class MedicalProcedureSpecializacija extends BasicEntity{
    @ManyToOne
    @JoinColumn(name = "procedure_id")
    private MedicalProcedure procedure;
    
    @ManyToOne
    @JoinColumn(name = "specialization_id")
    private Specijalizacija specijalizacija;
  
}
