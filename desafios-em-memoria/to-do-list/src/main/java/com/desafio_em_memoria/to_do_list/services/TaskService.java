package com.desafio_em_memoria.to_do_list.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import com.desafio_em_memoria.to_do_list.dto.TaskDTO;
import com.desafio_em_memoria.to_do_list.entities.Task;
import com.desafio_em_memoria.to_do_list.enums.TaskStatus;
import com.desafio_em_memoria.to_do_list.services.exceptions.ResourceNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class TaskService {

    private List<Task> tasks = new ArrayList<>();

    public TaskDTO createTask(TaskDTO dto){
        Task newTask = new Task((long) (tasks.size() + 1), dto.getDescription(), TaskStatus.PENDING);
        tasks.add(newTask);
        return new TaskDTO(newTask);
    }

    public TaskDTO updateTaskToCompleted(Long id, TaskDTO dto) {
        for (Task task : tasks){
            if (Objects.equals(task.getId(), id)){
                task.setDescription(dto.getDescription());
                task.setStatus(TaskStatus.COMPLETED);
                return new TaskDTO(task);
            }
        }
        throw new ResourceNotFoundException("Task with ID " + id + " not found");
    }

    public List<TaskDTO> getAllTasks() {
        return tasks.stream().map(TaskDTO::new).collect(Collectors.toList());
    }

    public List<TaskDTO> getPendingTasks() {
        return tasks.stream().filter(x-> x.getStatus().equals(TaskStatus.PENDING)).map(TaskDTO::new).collect(Collectors.toList());
    }
}
