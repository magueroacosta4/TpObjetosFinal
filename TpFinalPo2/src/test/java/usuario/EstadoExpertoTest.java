package usuario;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import org.junit.*;

public class EstadoExpertoTest {
	private EstadoExperto estadoE;
	private EstadoBasico estadoB;
	private Participante participante;

	@Before
	public void setUp() {
		estadoE = new EstadoExperto();
		estadoB = mock(EstadoBasico.class);
		participante = mock(Participante.class);
	}
	
	@Test
	public void seActualizaElEstadoDeUsuarioABasico() {
		when(participante.opinionesUltimos30Dias()).thenReturn(5);
		when(participante.postsUltimos30Dias()).thenReturn(4);
		
		estadoE.actualizarEstado(participante);
		
		verify(participante, atMost(1)).setEstado(estadoB);
	}
	
	@Test
	public void noSeActualizaElEstadoDeUsuarioABasico() {
		when(participante.opinionesUltimos30Dias()).thenReturn(23);
		when(participante.postsUltimos30Dias()).thenReturn(14);
		
		estadoE.actualizarEstado(participante);
		
		verify(participante, never()).setEstado(estadoB);
	}
	

	@Test
	public void noEsExpertoTest() {
		assertTrue(estadoE.esExperto());
	}
	
}
