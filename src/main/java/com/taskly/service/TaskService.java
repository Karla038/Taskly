package com.taskly.service;

import com.taskly.dto.TaskRequest;
import com.taskly.dto.TaskResponse;
import com.taskly.exception.TaskNotFoundException;
import com.taskly.model.Task;
import com.taskly.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskService {

	 private final TaskRepository taskRepository;

	    public TaskResponse createTask(TaskRequest taskRequest) {
	        Task task = new Task();
	        task.setDescription(taskRequest.description());
	        task.setStatus(taskRequest.status());
	        
	        Task savedTask = taskRepository.save(task);
	        return new TaskResponse(savedTask.getId(), savedTask.getDescription(), savedTask.getStatus());
	    }

	    public List<TaskResponse> getAllTasks() {
	        return taskRepository.findAll().stream()
	                .map(task -> new TaskResponse(task.getId(), task.getDescription(), task.getStatus()))
	                .toList();
	    }

	    public TaskResponse getTaskById(Long id) {
	        Task task = taskRepository.findById(id)
	                .orElseThrow(() -> new TaskNotFoundException("Tarea no encontrada con id: " + id));
	        return new TaskResponse(task.getId(), task.getDescription(), task.getStatus());
	    }

	    @Transactional
	    public TaskResponse updateTask(Long id, TaskRequest taskRequest) {
	        Task task = taskRepository.findById(id)
	                .orElseThrow(() -> new TaskNotFoundException("Tarea no encontrada con id: " + id));
	        
	        task.setDescription(taskRequest.description());
	        task.setStatus(taskRequest.status());
	        
	        return new TaskResponse(task.getId(), task.getDescription(), task.getStatus());
	    }

	    public void deleteTask(Long id) {
	        if (!taskRepository.existsById(id)) {
	            throw new TaskNotFoundException("Tarea no encontrada con id: " + id);
	        }
	        taskRepository.deleteById(id);
	    }
	
}
