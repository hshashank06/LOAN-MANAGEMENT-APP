package com.example.advice;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionHandlerValidations {
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public Map<String,String> handleValidationExceptions(MethodArgumentNotValidException exception){
		Map<String,String> errorsMap = new HashMap<>();
		exception.getBindingResult().getFieldErrors().forEach(error -> {
			errorsMap.put(error.getField(),error.getDefaultMessage());
		});
		return errorsMap;
		}
	}


