package org.sanja.azp.repositories;

import java.util.List;
import org.sanja.azp.entities.Pregled;
import org.sanja.azp.entities.projections.PregledSchedule;
import org.springframework.data.jpa.repository.JpaRepository;


public interface PregledRepository extends JpaRepository<Pregled, Long> {
    List<PregledSchedule> findAllByDoctorId(Long id);
}
