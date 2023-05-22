package usuario;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class EstadoBasicoTest {
	private EstadoBasico estadoB;
	private Participante participante;
	private EstadoExperto estadoE;
	
	@BeforeEach
	public void setUp() {
		estadoB = new EstadoBasico();
		participante = mock(Participante.class);
		estadoE = mock(EstadoExperto.class);
	}
	
	@Test
	public void seActualizaElEstadoDelUsuarioAExpertoTest() {
		when(participante.getCantOpiniones30Dias()).thenReturn(23);
		when(participante.getCantPosts30Dias()).thenReturn(14);
		
		estadoB.actualizarEstado(participante);
		
		verify(participante, atMost(1)).setEstado(estadoE);
	}
	
	@Test
	public void noSeActualizaElEstadoDeUsuarioTest() {
		when(participante.getCantOpiniones30Dias()).thenReturn(16);
		when(participante.getCantPosts30Dias()).thenReturn(14);
		
		estadoB.actualizarEstado(participante);
		
		verify(participante, never()).setEstado(estadoE);
		
		when(participante.getCantPosts30Dias()).thenReturn(1);

		estadoB.actualizarEstado(participante);

		verify(participante, never()).setEstado(estadoE);
	}
	
	@Test
	public void noEsExpertoTest() {
		assertFalse(estadoB.esExperto());
	}
}
