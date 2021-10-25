package com.site.todolist.list;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;

@Service
public class TodoListService {

    private final TodoListRepository repository;

    @Autowired
    public TodoListService(TodoListRepository repository) {
        this.repository = repository;
    }

    public void addNewTask(TodoList list) {
        System.out.println(list);
        repository.save(list);
    }

    public List<TodoList> getTodoList() {
        return repository.findAll();
    }

    public void deleteTask(Long id) {
        boolean exists = repository.existsById(id);
        if (!exists) {
            throw new IllegalStateException("Task with id " + id + "does not exist");
        }
        repository.deleteById(id);
    }

    @Transactional
    public void updateTask(long id, String task, LocalDate date) {
        TodoList entity = repository.findById(id).orElseThrow(() -> new IllegalStateException(
                "task with id " + id + " does not exist"
        ));

        if (task != null && task.length() > 0) {
            entity.setTask(task);
        }

        if (date != null) {
            entity.setDate(date);
        }

    }
}
