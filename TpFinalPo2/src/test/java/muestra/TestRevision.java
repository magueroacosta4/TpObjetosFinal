package muestra;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import Usuario.EstadoBasico;
import Usuario.EstadoExperto;
import Usuario.EstadoUsuario;

public class TestRevision {
	
	EstadoUsuario experto;
	EstadoUsuario basico;
	Opinion opinion;
	Revision revision;
	
	@Before
	public void setUp() {
		experto = mock(EstadoExperto.class);
		basico  = mock(EstadoBasico.class);
		opinion = Opinion.VINCHUCA_INFESTANTS;
	}
	
	@Test
	public void cuandoSeCreaUnaRevisionEsteTieneUnaFecha_UnaOpinion() {
		Date today = new Date();
		
		revision = new Revision(opinion, basico);
		
		assertEquals(revision.getOpinion(), opinion);
		assertEquals(revision.getEstadoDelUsuarioActual(), basico);
		assertEquals(revision.getFechaDeCreacion(), today);
	}
}
