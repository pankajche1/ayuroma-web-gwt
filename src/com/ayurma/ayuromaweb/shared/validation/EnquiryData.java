package com.ayurma.ayuromaweb.shared.validation;

import java.io.Serializable;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

@SuppressWarnings("serial")
public class EnquiryData implements Serializable {
	@NotEmpty(message="Please write a message")
	@Size(max=500,message="Message must be less than 500 letters.")
	private String message;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
}
