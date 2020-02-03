package org.sanja.azp.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;



@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "SPECIALIZATION")     
public class Specijalizacija extends BasicEntity {
    @Column(unique=true)
    private String description;

}
