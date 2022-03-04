package com.example.demo.student;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Configuration
public class StudentConfig {

    @Bean
    CommandLineRunner commandLineRunner(StudentRepository repository) {
        return args -> {
            Student rof = new Student(
                    "Rofayda",
                    "rof@gmail.com",
                    LocalDate.of(1995, Month.MAY, 27)
            );
            Student brahim = new Student(
                    "Brahim",
                    "brahim@gmail.com",
                    LocalDate.of(1995, Month.OCTOBER, 7)
            );
            repository.saveAll(
                    List.of(brahim, rof)
            );

        };
    }
}
