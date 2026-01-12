package com.workshop.zarif;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class ZarifApplication {

    public static void main(String[] args) {
        SpringApplication.run(ZarifApplication.class, args);
    }

}
