package com.site.todolist.list;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(path = "api/todolist")
public class TodoListController {
    private final TodoListService service;

    @Autowired
    public TodoListController(TodoListService service) {
        this.service = service;
    }

    @GetMapping
    public List<TodoList> getTodoList() {
        return service.getTodoList();
    }

    @PostMapping
    public void registerNewTask(@RequestBody TodoList list) {
        service.addNewTask(list);
    }

    @DeleteMapping(path = "{taskId}")
    public void deleteTask(@PathVariable("taskId") Long id) {
        service.deleteTask(id);
    }

    @PutMapping(path = "{taskId}")
    public void updateTask(
            @PathVariable("taskId") Long id,
            @RequestParam(required = false) String task,
            @RequestParam(required = false) String date) {
        LocalDate localDate = LocalDate.parse(date);
        service.updateTask(id, task, localDate);
    }
}
