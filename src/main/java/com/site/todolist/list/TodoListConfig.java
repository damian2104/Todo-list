package com.site.todolist.list;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.List;

@Configuration
public class TodoListConfig {

    @Bean
    CommandLineRunner commandLineRunner(TodoListRepository repository) {
        return args -> {
            TodoList task1 = new TodoList("Zrobienie obiadu",
                    LocalDate.of(2021, 5, 12));

            TodoList task2 = new TodoList("Wyniesienie śmieci",
                    LocalDate.of(2022, 1, 7)
            );

            repository.saveAll(List.of(task1, task2));
        };
    }
}
