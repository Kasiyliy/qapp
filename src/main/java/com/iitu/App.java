package com.iitu;

import com.iitu.entities.Interviewers;
import com.iitu.entities.Statuses;
import com.iitu.repositories.InterviewersRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;

/**
 * @author Assylkhan
 * on 28.11.2018
 * @project qapp
 */
@SpringBootApplication
public class App {
    private static final Logger log = LoggerFactory.getLogger(App.class);

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }
}
