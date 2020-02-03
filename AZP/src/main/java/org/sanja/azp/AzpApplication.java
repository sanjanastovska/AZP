package org.sanja.azp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//@EnableJpaAuditing
public class AzpApplication {

    public static void main(String[] args) {
        SpringApplication.run(AzpApplication.class, args);
    }

//    @Bean
//    Jackson2ObjectMapperBuilderCustomizer jacksonCustomizer() {
//        return (mapperBuilder) -> mapperBuilder.modulesToInstall(new JaxbAnnotationModule());
//    }
}
