package com.cafe.manager.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories("com.cafe.manager.repository")
@EntityScan("com.cafe.manager.repository.entities")
@SpringBootApplication
public class CafeManagerApplication {

    public static void main(String[] args) {
        SpringApplication.run(CafeManagerApplication.class, args);
    }
}