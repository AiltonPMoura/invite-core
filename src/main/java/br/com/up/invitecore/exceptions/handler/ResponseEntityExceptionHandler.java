package br.com.up.invitecore.exceptions.handler;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import br.com.up.invitecore.exceptions.ExceptionResponse;
import br.com.up.invitecore.exceptions.NotFoundException;

@ControllerAdvice
public class ResponseEntityExceptionHandler {
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ExceptionResponse> InternalServerErrorException(Exception ex, WebRequest request) {
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
				.body(ExceptionResponse.builder()
						.dateTime(LocalDateTime.now())
						.message(ex.getMessage())
						.details(request.getDescription(false))
						.build()
				);
	}

	@ExceptionHandler(NotFoundException.class)
	public ResponseEntity<ExceptionResponse> notFoundException(Exception ex, WebRequest request) {
		return ResponseEntity.status(HttpStatus.NOT_FOUND)
				.body(ExceptionResponse.builder()
						.dateTime(LocalDateTime.now())
						.message(ex.getMessage())
						.details(request.getDescription(false))
						.build()
				);
	}
	
}
