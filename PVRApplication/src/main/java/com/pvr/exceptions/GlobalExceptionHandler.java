package com.pvr.exceptions;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;


@ControllerAdvice
public class GlobalExceptionHandler {
		
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<MyErrorDetails> MobileNumberException(MethodArgumentNotValidException loginException ){
		MyErrorDetails errorDetails = new MyErrorDetails(LocalDateTime.now(),"valadiation error",loginException.getBindingResult().getFieldError().getDefaultMessage());
		return new ResponseEntity<MyErrorDetails>(errorDetails,HttpStatus.BAD_REQUEST);
	}
	@ExceptionHandler(Exception.class)
	public ResponseEntity<MyErrorDetails> Exceptions(Exception exception , WebRequest request){
		MyErrorDetails errorDetails = new MyErrorDetails(LocalDateTime.now(), exception.getMessage(), request.getDescription(false));
		return new ResponseEntity<MyErrorDetails>(errorDetails,HttpStatus.BAD_REQUEST);
	}
	@ExceptionHandler(CustomerException.class)
	public ResponseEntity<MyErrorDetails> customerException(CustomerException exception , WebRequest request){
		MyErrorDetails errorDetails = new MyErrorDetails(LocalDateTime.now(), exception.getMessage(), request.getDescription(false));
		return new ResponseEntity<MyErrorDetails>(errorDetails,HttpStatus.BAD_REQUEST);
	}
	@ExceptionHandler(AdminException.class)
	public ResponseEntity<MyErrorDetails> adminException(AdminException exception , WebRequest request){
		MyErrorDetails errorDetails = new MyErrorDetails(LocalDateTime.now(), exception.getMessage(), request.getDescription(false));
		return new ResponseEntity<MyErrorDetails>(errorDetails,HttpStatus.BAD_REQUEST);
	}
	@ExceptionHandler(MovieException.class)
	public ResponseEntity<MyErrorDetails> movieException(MovieException exception , WebRequest request){
		MyErrorDetails errorDetails = new MyErrorDetails(LocalDateTime.now(), exception.getMessage(), request.getDescription(false));
		return new ResponseEntity<MyErrorDetails>(errorDetails,HttpStatus.BAD_REQUEST);
	}
	@ExceptionHandler(TheaterException.class)
	public ResponseEntity<MyErrorDetails> theaterException(TheaterException exception , WebRequest request){
		MyErrorDetails errorDetails = new MyErrorDetails(LocalDateTime.now(), exception.getMessage(), request.getDescription(false));
		return new ResponseEntity<MyErrorDetails>(errorDetails,HttpStatus.BAD_REQUEST);
	}
	@ExceptionHandler(TicketException.class)
	public ResponseEntity<MyErrorDetails> ticketException(TicketException exception , WebRequest request){
		MyErrorDetails errorDetails = new MyErrorDetails(LocalDateTime.now(), exception.getMessage(), request.getDescription(false));
		return new ResponseEntity<MyErrorDetails>(errorDetails,HttpStatus.BAD_REQUEST);
	}
	@ExceptionHandler(TicketSerilizationException.class)
	public ResponseEntity<MyErrorDetails> ticketSerilizationException(TicketSerilizationException exception , WebRequest request){
		MyErrorDetails errorDetails = new MyErrorDetails(LocalDateTime.now(), exception.getMessage(), request.getDescription(false));
		return new ResponseEntity<MyErrorDetails>(errorDetails,HttpStatus.BAD_REQUEST);
	}
}
