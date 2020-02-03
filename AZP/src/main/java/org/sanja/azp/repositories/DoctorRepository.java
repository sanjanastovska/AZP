/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.sanja.azp.repositories;

import java.util.List;
import org.sanja.azp.entities.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;

/** 
 *
 * @author Sanja
 */
public interface DoctorRepository extends JpaRepository<Doctor, Long> {

    public List<Doctor> findByName(String name);
    public List<Doctor> findBySurname(String surname);
    public List<Doctor> findBySpecId (Long specId);
    
}
