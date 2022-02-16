package com.example.yammarket;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class YammarketApplication {

    public static void main(String[] args) {
        SpringApplication.run(YammarketApplication.class, args);
    }

}
