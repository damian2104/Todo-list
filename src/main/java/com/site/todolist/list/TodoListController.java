package com.site.todolist.list;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;

import java.time.LocalDate;


//@RequestMapping(path = "api/todolist")
@Controller
public class TodoListController {
    private final TodoListService service;

    @Autowired
    public TodoListController(TodoListService service) {
        this.service = service;
    }

    @PutMapping(path = "{taskId}")
    public void updateTask(
            @PathVariable("taskId") Long id,
            @RequestParam(required = false) String task,
            @RequestParam(required = false) String date) {
        LocalDate localDate = LocalDate.parse(date);
        service.updateTask(id, task, localDate);
    }

    @GetMapping("/todolist")
    public String index(Model model) {
        model.addAttribute("something", "this is some string");
        model.addAttribute("tasks", service.getTodoList());
        model.addAttribute("newtask", new TodoList());
        return "index";
    }

    @PostMapping("/todolist")
    public String submitTask(@RequestBody TodoList task, Model model) {
        model.addAttribute("task", task);
        service.addNewTask(task);
        return "result";
    }

    @DeleteMapping(path = "todolist/{taskId}")
    public String eraseTask(@PathVariable("taskId") Long id) {
        System.out.println("HEJ");
        service.deleteTask(id);
        return "index2";
    }

    @PutMapping(path = "todolist/{taskId}")
    public String modify(
            @PathVariable("taskId") Long id,
            @RequestParam(required = false) String task,
            @RequestParam(required = false) String date) {
        LocalDate localDate = LocalDate.parse(date);
        service.updateTask(id, task, localDate);
        return "index2";
    }
}
