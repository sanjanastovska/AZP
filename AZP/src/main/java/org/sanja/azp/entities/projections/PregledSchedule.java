/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.sanja.azp.entities.projections;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.Duration;
import java.util.Date;
import org.springframework.beans.factory.annotation.Value;

/**
 *
 * @author Sanja
 */
public interface PregledSchedule {
    public String getId();
    
    @Value("#{target.date}")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss", timezone = "CET")
    public Date getStart();
    
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss", timezone = "CET")
    @Value("#{T(java.util.Date).from(target.date.toInstant().plus(T(java.time.Duration).ofMinutes(30)))}")
    public Date getEnd();
    
    @Value("#{target.pacient.surname+ ' ' +target.pacient.name}")
    public String getTitle();
    
    @Value("#{'#443322'}")
    public String getBackgroundColor();
    
    @Value("#{'#FFF'}")
    public String getTextColor();
}
