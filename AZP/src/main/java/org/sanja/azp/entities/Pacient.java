/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.sanja.azp.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
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
@Table(name = "PATIENT")
public class Pacient extends BasicEntity{
    private String name;
    private String surname;
    
  @JsonFormat
      (shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy",timezone="CET") 
    private Date dateOfBirth;
    private String socialSecNum;
    private String address;
    
}
