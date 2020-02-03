/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.sanja.azp.entities;

import java.util.Date;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 *
 * @author Sanja
 */
@MappedSuperclass
@Data
public class BasicEntity {
    @Id
     @GeneratedValue(
        strategy = GenerationType.IDENTITY
    )
    public Long id;
    
//    @CreatedDate
    @EqualsAndHashCode.Exclude
    private Date createdOn;
    
//    @LastModifiedDate
    @EqualsAndHashCode.Exclude
    private Date modifiedOn;
    
    @PrePersist
    public void prePersist(){
       this.createdOn = new Date();
       this.modifiedOn = new Date();
    }
    
    @PreUpdate
    public void preUpdate(){
        this.modifiedOn = new Date();
    }
    
    
}
