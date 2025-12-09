package com.desafio_em_memoria.to_do_list.controllers;

import com.desafio_em_memoria.to_do_list.dto.TaskDTO;
import com.desafio_em_memoria.to_do_list.services.TaskService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/tasks")
public class TaskController {

    @Autowired
    TaskService service;

    @PostMapping
    public ResponseEntity<TaskDTO> createTask(@Valid @RequestBody TaskDTO dto){
        dto = service.createTask(dto);
        return ResponseEntity.ok(dto);
    }

    @PutMapping(value = "/{id}/concluir")
    public ResponseEntity<TaskDTO> updateTask(@PathVariable Long id, @Valid @RequestBody TaskDTO dto){
        dto = service.updateTaskToCompleted(id, dto);
        return ResponseEntity.ok(dto);
    }

    @GetMapping
    public ResponseEntity<List<TaskDTO>> getAllTasks(){
        return ResponseEntity.ok(service.getAllTasks());
    }

    @GetMapping(value = "/pending")
    public ResponseEntity<List<TaskDTO>> getPendingTasks(){
        return ResponseEntity.ok(service.getPendingTasks());
    }
}
