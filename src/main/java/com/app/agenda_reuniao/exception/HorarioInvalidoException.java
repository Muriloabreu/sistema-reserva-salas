package com.app.agenda_reuniao.exception;

public class HorarioInvalidoException extends RuntimeException{
	
	public HorarioInvalidoException() {
        super("A hora inicial deve ser menor que a hora final.");
    }

}
