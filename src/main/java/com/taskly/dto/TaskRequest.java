package com.taskly.dto;

import com.taskly.model.TaskStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class TaskRequest {

	@NotBlank
    private String title;
  
    @NotBlank
    private String description;
  
    @NotNull
    private TaskStatus status;

    public TaskRequest(String title, String description, TaskStatus status) {
        this.title = title;
    	this.description = description;
        this.status = status;
    }
    
    public String getTitle() {
    	return title;
    }
    
    public void setTitle(String title) {
    	this.title = title;
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
