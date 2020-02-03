package org.sanja.azp.start;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import lombok.extern.slf4j.Slf4j;
import org.sanja.azp.entities.Doctor;
import org.sanja.azp.entities.Specijalizacija;
import org.sanja.azp.repositories.DoctorRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;


@Component
@Slf4j
public class PopulateDb implements CommandLineRunner {
   
    private DoctorRepository docRepo;

    public PopulateDb(DoctorRepository docRepo) {
        this.docRepo = docRepo;
    }
    
    
   
    @Override
    public void run(String... strings) throws Exception {
        log.info("The app has started and we are populating the db now ----->");
        
        
        log.info("Population finished !!! -->>>");
    }
    
}
