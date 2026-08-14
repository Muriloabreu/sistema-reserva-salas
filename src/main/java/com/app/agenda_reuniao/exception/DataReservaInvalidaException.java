package com.app.agenda_reuniao.exception;

public class DataReservaInvalidaException extends RuntimeException {
	
	public DataReservaInvalidaException() {
        super("A data da reserva não pode estar no passado.");
    }

}
