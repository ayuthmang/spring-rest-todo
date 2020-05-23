package com.digitalacademy.todo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
class LoadDatabase {
    @Bean
    CommandLineRunner initDatabase(TodoRepository repository) {
        return args -> {
            log.info("Preloading " + repository.save(new Todo("Learn React", true)));
            log.info("Preloading " + repository.save(new Todo("Learn Redux", false)));
        };
    }
}
