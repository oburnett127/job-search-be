package com.oburnett127.jobsearch;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@SpringBootApplication
@EnableJpaRepositories
public class JobsearchApplication {

    public static void main(String[] args) {
        SpringApplication.run(JobsearchApplication.class, args);
    }
}
