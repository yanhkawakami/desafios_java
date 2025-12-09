package com.desafio_em_memoria.to_do_list.dto;

import com.desafio_em_memoria.to_do_list.entities.Task;
import jakarta.validation.constraints.NotNull;

public class TaskDTO {

    public Long id;
    @NotNull(message = "The field 'description' should be filled")
    public String description;
    public String status;

    public TaskDTO() {}

    public TaskDTO(Long id, String description, String status) {
        this.id = id;
        this.description = description;
        this.status = status;
    }

    public TaskDTO(Task entity) {
        this.id = entity.getId();
        this.description = entity.getDescription();
        this.status = entity.getStatus().name();
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
