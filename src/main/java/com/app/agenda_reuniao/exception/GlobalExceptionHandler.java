package com.app.agenda_reuniao.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


import com.app.agenda_reuniao.dto.ErroResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	//tratamento informa hora, tipo, status http
	@ExceptionHandler(ReservaNaoEncontradaException.class)
	public ResponseEntity<ErroResponse>
	tratarReservaNaoEncontrada(
	        ReservaNaoEncontradaException ex) {

	    ErroResponse erro =
	            new ErroResponse(
	                    LocalDateTime.now(),
	                    404,
	                    ex.getMessage());

	    return ResponseEntity
	            .status(HttpStatus.NOT_FOUND)
	            .body(erro);
	}

	@ExceptionHandler(ConflitoHorarioException.class)
	public ResponseEntity<ErroResponse>
	tratarConflito(ConflitoHorarioException ex) {

	    ErroResponse erro =
	            new ErroResponse(
	                    LocalDateTime.now(),
	                    400,
	                    ex.getMessage());

	    return ResponseEntity
	            .status(HttpStatus.BAD_REQUEST)
	            .body(erro);
	}
	
	@ExceptionHandler(SalaNaoEncontradaException.class)
	public ResponseEntity<ErroResponse>
	tratarSalaNaoEncontrada(SalaNaoEncontradaException ex) {

	    ErroResponse erro =
	            new ErroResponse(
	                    LocalDateTime.now(),
	                    404,
	                    ex.getMessage());

	    return ResponseEntity
	            .status(HttpStatus.NOT_FOUND)
	            .body(erro);
	}
	
	@ExceptionHandler(HorarioInvalidoException.class)
	public ResponseEntity<ErroResponse> tratarHorarioInvalido(
	        HorarioInvalidoException ex) {

	    ErroResponse erro = new ErroResponse(
	            LocalDateTime.now(),
	            HttpStatus.BAD_REQUEST.value(),
	            ex.getMessage()
	    );

	    return ResponseEntity
	            .status(HttpStatus.BAD_REQUEST)
	            .body(erro);
	}
    
    

}
