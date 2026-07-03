package com.app.agenda_reuniao.serviceImpl;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.app.agenda_reuniao.exception.ConflitoHorarioException;
import com.app.agenda_reuniao.exception.HorarioInvalidoException;
import com.app.agenda_reuniao.models.Reserva;
import com.app.agenda_reuniao.models.Sala;
import com.app.agenda_reuniao.repository.ReservaRepository;

@ExtendWith(MockitoExtension.class)
class ReservaServiceImplTest {

    @Mock
    private ReservaRepository reservaRepository;

    @InjectMocks
    private ReservaServiceImpl reservaService;
	 
	 @Test
	    void deveLancarExcecaoQuandoExistirConflitoDeHorario() {

	        Sala sala = new Sala();
	        sala.setId(1L);

	        Reserva reservaExistente = new Reserva();
	        reservaExistente.setId(1L);
	        reservaExistente.setSala(sala);
	        reservaExistente.setData(LocalDate.of(2026, 7, 1));
	        reservaExistente.setHoraInicio(LocalTime.of(9, 0));
	        reservaExistente.setHoraFim(LocalTime.of(10, 0));

	        Reserva novaReserva = new Reserva();
	        novaReserva.setSala(sala);
	        novaReserva.setData(LocalDate.of(2026, 7, 1));
	        novaReserva.setHoraInicio(LocalTime.of(9, 30));
	        novaReserva.setHoraFim(LocalTime.of(10, 30));

	        when(
	            reservaRepository.findBySalaAndData(
	                novaReserva.getSala(),
	                novaReserva.getData()
	            )
	        ).thenReturn(List.of(reservaExistente));

	        assertThrows(
	            ConflitoHorarioException.class,
	            () -> reservaService.save(novaReserva)
	        );

	 }
	 
	 @Test
	 void deveSalvarReservaQuandoNaoExistirConflitoDeHorario() {

	     Sala sala = new Sala();
	     sala.setId(1L);

	     Reserva reservaExistente = new Reserva();
	     reservaExistente.setId(1L);
	     reservaExistente.setSala(sala);
	     reservaExistente.setData(LocalDate.of(2026, 7, 1));
	     reservaExistente.setHoraInicio(LocalTime.of(9, 0));
	     reservaExistente.setHoraFim(LocalTime.of(10, 0));

	     Reserva novaReserva = new Reserva();
	     novaReserva.setSala(sala);
	     novaReserva.setData(LocalDate.of(2026, 7, 1));
	     novaReserva.setHoraInicio(LocalTime.of(11, 0));
	     novaReserva.setHoraFim(LocalTime.of(12, 0));

	     when(
	         reservaRepository.findBySalaAndData(
	             novaReserva.getSala(),
	             novaReserva.getData()
	         )
	     ).thenReturn(List.of(reservaExistente));

	     when(reservaRepository.save(novaReserva))
	         .thenReturn(novaReserva);

	     Reserva reservaSalva = reservaService.save(novaReserva);

	     assertNotNull(reservaSalva);
	 }
	 
	 @Test
	 void deveLancarExcecaoQuandoHoraInicialForMaiorQueHoraFinal() {

	     Reserva reserva = new Reserva();

	     reserva.setHoraInicio(LocalTime.of(10, 0));
	     reserva.setHoraFim(LocalTime.of(9, 0));

	     assertThrows(
	         HorarioInvalidoException.class,
	         () -> reservaService.save(reserva)
	     );
	 }
}
