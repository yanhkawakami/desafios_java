package com.desafio_em_memoria.to_do_list.entities;

import com.desafio_em_memoria.to_do_list.enums.TaskStatus;


public class Task {

    Long id;
    String description;
    TaskStatus status;

    public Task(Long id, String description, TaskStatus status) {
        this.id = id;
        this.description = description;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public TaskStatus getStatus() {
        return status;
    }

    public void setStatus(TaskStatus status) {
        this.status = status;
    }
}
