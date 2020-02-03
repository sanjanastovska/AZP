/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.sanja.azp.entities;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
/**
 *
 * @author Sanja
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Entity
@Table(name = "DOCTOR")
public class Doctor extends BasicEntity {
    
    private String name;
    private String surname;
    
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "SPECIALIZATION_ID")
    private Specijalizacija spec;
    
}
