/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.sanja.azp.entities;

import javax.persistence.Entity;
import javax.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;



/**
 *
 * @author Sanja
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)

@Entity
@Table(name = "MEDICAL_PROCEDURE")
public class MedicalProcedure extends BasicEntity {
    private String description;
    
    
           
    
}
