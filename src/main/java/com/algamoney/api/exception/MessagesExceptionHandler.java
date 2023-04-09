package com.algamoney.api.exception;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class MessagesExceptionHandler {

	private Date timestamp;
	private Integer status;
	private List<String> errors;

	public MessagesExceptionHandler(Date timestamp, Integer status, List<String> errors) {
		super();
		this.timestamp = timestamp;
		this.status = status;
		this.errors = errors;
	}

	public MessagesExceptionHandler(Date timestamp, Integer status, String error) {
		super();
		this.timestamp = timestamp;
		this.status = status;
		this.errors = Arrays.asList(error);
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public List<String> getErrors() {
		return errors;
	}

	public void setErrors(List<String> errors) {
		this.errors = errors;
	}

}
