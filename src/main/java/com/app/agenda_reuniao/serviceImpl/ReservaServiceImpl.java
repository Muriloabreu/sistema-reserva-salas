package com.app.agenda_reuniao.serviceImpl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.app.agenda_reuniao.exception.ReservaNaoEncontradaException;
import com.app.agenda_reuniao.exception.ConflitoHorarioException;
import com.app.agenda_reuniao.exception.HorarioInvalidoException;
import com.app.agenda_reuniao.models.Reserva;
import com.app.agenda_reuniao.repository.ReservaRepository;
import com.app.agenda_reuniao.service.ReservaService;

@Service
public class ReservaServiceImpl implements ReservaService {
	
	
	private final ReservaRepository reservaRepository;
	
	public ReservaServiceImpl(ReservaRepository reservaRepository) {
	    this.reservaRepository = reservaRepository;
	}

	@Override
	public List<Reserva> findAll() {
		
		return reservaRepository.findAll();
	}

	
	@Override
	public Reserva save(Reserva reserva) {
		
		validarConflitoHorario(reserva);
		
		validarConflitoHorario(reserva);
		
		return reservaRepository.save(reserva);
	}
	
	private void validarConflitoHorario(Reserva novaReserva) {

	    List<Reserva> reservasExistentes =
	            reservaRepository.findBySalaAndData(
	                    novaReserva.getSala(),
	                    novaReserva.getData()
	            );

	    for (Reserva reservaExistente : reservasExistentes) {

	        boolean mesmoRegistro =
	                novaReserva.getId() != null &&
	                novaReserva.getId().equals(reservaExistente.getId());

	        if (mesmoRegistro) {
	            continue;
	        }

	        boolean existeConflito =
	                novaReserva.getHoraInicio().isBefore(reservaExistente.getHoraFim())
	                &&
	                novaReserva.getHoraFim().isAfter(reservaExistente.getHoraInicio());

	        if (existeConflito) {
	            throw new ConflitoHorarioException();
	        }
	    }
	}

	@Override
	public void deletarReserva(Reserva reserva) {
		
		reservaRepository.delete(reserva);
	}

	@Override
	public Reserva findById(Long id) {
		return reservaRepository.findById(id)
	            .orElseThrow(() -> new ReservaNaoEncontradaException(id));
	}

	private void validarHorario(Reserva reserva) {

	    if (!reserva.getHoraInicio().isBefore(reserva.getHoraFim())) {
	        throw new HorarioInvalidoException();
	    }
	}

	

}
