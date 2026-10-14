package com.campuscollab.dto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class ApplicationRequest {

	@NotBlank(message = "Application message is required")
	@Size(max = 2000, message = "Message must not exceed 2000 characters")
	private String message;

    public ApplicationRequest() {
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}