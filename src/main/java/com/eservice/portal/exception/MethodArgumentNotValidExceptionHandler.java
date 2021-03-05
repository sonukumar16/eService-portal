package com.eservice.portal.exception;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.eservice.portal.utility.Response;

import lombok.extern.slf4j.Slf4j;

@Order(Ordered.HIGHEST_PRECEDENCE)
@RestControllerAdvice
@Slf4j
public class MethodArgumentNotValidExceptionHandler {
	private static final Logger logger = LoggerFactory.getLogger(MethodArgumentNotValidExceptionHandler.class);

	@Autowired
	private MessageSource messageSource;

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Response<Map<String, Object>>> errorHAndler(MethodArgumentNotValidException ex) {
		logger.error("Paramater validation error", ex);
		BindingResult result = ex.getBindingResult();
		List<FieldError> errors = result.getFieldErrors();
		Map<String, Object> response = new HashMap<>();
		for (FieldError error : errors)
			response.put(error.getField(), messageSource.getMessage(error, LocaleContextHolder.getLocale()));
		return new ResponseEntity<>(new Response<>(response), HttpStatus.BAD_REQUEST);
	}
}
