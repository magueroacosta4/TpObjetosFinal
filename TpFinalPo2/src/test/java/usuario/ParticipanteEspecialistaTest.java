package usuario;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import org.junit.*;
import org.junit.jupiter.api.BeforeEach;

import muestra.PaginaWeb;

public class ParticipanteEspecialistaTest {
	private ParticipanteEspecialista partEsp;
	private PaginaWeb pag;
	private EstadoBasico estadoB;
	
	@Before
	public void setUp() {
		partEsp = new ParticipanteEspecialista(pag);
		pag = mock(PaginaWeb.class);
		estadoB = mock(EstadoBasico.class);
	}
	
	@Test
	public void elEstadoDeEspecialistaNoCambiaTest() {
		partEsp.setearEstado(estadoB);
		partEsp.actualizarEstado();
		
		verify(estadoB, never()).actualizarEstado(partEsp);
		assertTrue(partEsp.esExperto());
	}
}
