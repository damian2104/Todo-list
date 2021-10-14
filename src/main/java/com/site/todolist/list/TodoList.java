package com.site.todolist.list;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table
public class TodoList {
    @Id
    @SequenceGenerator(
            name = "todolist_sequence",
            sequenceName = "todolist_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
         strategy = GenerationType.SEQUENCE,
         generator = "todolist_sequence"
    )
    private Long id;
    private String task;
    private LocalDate date;

    public TodoList() {
    }

    public TodoList(Long id, String task, LocalDate date) {
        this.id = id;
        this.task = task;
        this.date = date;
    }

    public TodoList(String task, LocalDate date) {
        this.task = task;
        this.date = date;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "TodoList{" +
                "id=" + id +
                ", task='" + task + '\'' +
                ", date=" + date +
                '}';
    }
}
