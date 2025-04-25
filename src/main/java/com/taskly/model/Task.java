package com.taskly.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

public class Task {

	 @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;

	    @NotBlank(message = "La descripción no puede estar vacía")
	    private String description;

	    @NotNull(message = "El estado no puede ser nulo")
	    @Enumerated(EnumType.STRING)
	    private TaskStatus status;
	    
}

enum TaskStatus {
    PENDIENTE, EN_PROGRESO, COMPLETADA
}