package usuario;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class EstadoExpertoTest {
	private EstadoExperto estadoE;
	private EstadoBasico estadoB;
	private Participante participante;

	@BeforeEach
	public void setUp() {
		estadoE = new EstadoExperto();
		estadoB = mock(EstadoBasico.class);
		participante = mock(Participante.class);
	}
	
	@Test
	public void seActualizaElEstadoDeUsuarioABasico() {
		when(participante.getCantOpiniones30Dias()).thenReturn(5);
		when(participante.getCantPosts30Dias()).thenReturn(4);
		
		estadoE.actualizarEstado(participante);
		
		verify(participante, atMost(1)).setEstado(estadoB);
	}
	
	@Test
	public void noSeActualizaElEstadoDeUsuarioABasico() {
		when(participante.getCantOpiniones30Dias()).thenReturn(23);
		when(participante.getCantPosts30Dias()).thenReturn(14);
		
		estadoE.actualizarEstado(participante);
		
		verify(participante, never()).setEstado(estadoB);
	}
	

	@Test
	public void noEsExpertoTest() {
		assertTrue(estadoE.esExperto());
	}
	
}
