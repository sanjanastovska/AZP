/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.sanja.azp.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 *
 * @author Sanja
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "EXAM")
public class Pregled extends BasicEntity {

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss", timezone = "CET")
    private Date date;
    private String komentar;	//String
    private String simptomi;
    private String detailsOfPregled;

    @Transient
    @Getter(AccessLevel.NONE)
    private List<String> proceduriStringArray;

    @OneToOne(cascade = CascadeType.PERSIST)
    @NotNull
    private Pacient pacient;

    @OneToOne
    @NotNull
    private Doctor doctor;

    @Transient
    public List<String> getProceduriStringArray() {
        if (this.detailsOfPregled!=null && !this.detailsOfPregled.isEmpty()) {
            return Arrays.asList(this.detailsOfPregled.split(","));
        }
        else{
            return null;
        }
    }

}
