package com.taskly.dto;

import com.taskly.model.TaskStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class TaskRequest (
	
	@NotBlank String description,
	@NotNull TaskStatus status

){}
