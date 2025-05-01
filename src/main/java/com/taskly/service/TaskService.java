package com.taskly.service;

import com.taskly.dto.TaskRequest;
import com.taskly.dto.TaskResponse;
import com.taskly.model.Task;
import com.taskly.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    public TaskResponse createTask(TaskRequest taskRequest) {
        Task task = new Task();
        task.setDescription(taskRequest.getDescription());
        task.setStatus(taskRequest.getStatus());

        Task savedTask = taskRepository.save(task);

        return new TaskResponse(savedTask.getId(), savedTask.getDescription(), savedTask.getStatus());
    }

    public List<TaskResponse> getAllTasks() {
        List<Task> tasks = taskRepository.findAll();

        return tasks.stream()
                .map(task -> new TaskResponse(task.getId(), task.getDescription(), task.getStatus()))
                .toList();
    }

    public TaskResponse getTaskById(Long id) {
        Optional<Task> task = taskRepository.findById(id);

        if (task.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Tarea no encontrada con id: " + id);
        }

        return new TaskResponse(task.get().getId(), task.get().getDescription(), task.get().getStatus());
    }

    public TaskResponse updateTask(Long id, TaskRequest taskRequest) {
        Optional<Task> task = taskRepository.findById(id);

        if (task.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Tarea no encontrada con id: " + id);
        }

        Task existingTask = task.get();
        existingTask.setDescription(taskRequest.getDescription());
        existingTask.setStatus(taskRequest.getStatus());

        Task updatedTask = taskRepository.save(existingTask);

        return new TaskResponse(updatedTask.getId(), updatedTask.getDescription(), updatedTask.getStatus());
    }

    public void deleteTask(Long id) {
        Optional<Task> task = taskRepository.findById(id);

        if (task.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Tarea no encontrada con id: " + id);
        }

        taskRepository.deleteById(id);
    }
}
