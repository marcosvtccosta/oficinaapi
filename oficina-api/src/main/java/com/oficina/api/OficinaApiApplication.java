package com.oficina.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.oficina.api.domain.repository")
@EntityScan(basePackages = "com.oficina.api.domain.entity")
public class OficinaApiApplication {
    public static void main(String[] args) {
        SpringApplication.run(OficinaApiApplication.class, args);
    }
}
