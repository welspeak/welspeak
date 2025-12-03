package com.assessment.sasing;

import com.assessment.sasing.model.User;
import com.assessment.sasing.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SasingApplication {

    public static void main(String[] args) {
        SpringApplication.run(SasingApplication.class, args);
    }

    @Bean
    public CommandLineRunner initUsers(UserRepository userRepository) {
        return args -> {
            if (userRepository.findByUsername("teacher1").isEmpty()) {
                userRepository.save(new User("teacher1", "teachpass", "TEACHER", "Mr. Teacher"));
            }
            if (userRepository.findByUsername("student1").isEmpty()) {
                userRepository.save(new User("student1", "studpass", "STUDENT", "John Student"));
            }
        };
    }
}
