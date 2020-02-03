/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.sanja.azp.formModels;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Sanja
 */
@Setter
@Getter
public class PregledForm {

    private Long pacientId;
    @NotNull
    private String patientname;
    @NotNull
    private String patientsurname;
    @NotNull
    private String patientaddress;
    @NotNull
    private String patientsocialnum;
    @NotNull
    @JsonFormat
      (shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy",timezone="CET")
    private Date patientbirth;
    @NotNull
    private Long doctorName;
    @NotNull
    @JsonFormat
      (shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss",timezone="CET")
    private Date termininfo;
}
