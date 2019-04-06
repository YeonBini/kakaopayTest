package com.kakaopay.housing;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class HousingApplication {

    public static void main(String[] args) {
        SpringApplication.run(HousingApplication.class, args);
    }

}
