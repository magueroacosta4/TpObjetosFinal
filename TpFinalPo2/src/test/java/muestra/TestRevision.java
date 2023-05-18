package muestra;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

import java.time.LocalDate;
import org.junit.Before;
import org.junit.Test;

import usuario.EstadoBasico;
import usuario.EstadoExperto;
import usuario.EstadoUsuario;
import usuario.Participante;

public class TestRevision {
	
	EstadoUsuario experto;
	EstadoUsuario basico;
	Opinion opinion;
	Revision revision;
	Participante user;
	
	@Before
	public void setUp() {
		experto = mock(EstadoExperto.class);
		user = mock(Participante.class);
		basico  = mock(EstadoBasico.class);
		opinion = Opinion.VINCHUCA_INFESTANTS;
	}
	
	@Test
	public void cuandoSeCreaUnaRevisionEsteTieneUnaFecha_UnaOpinion() {
		LocalDate today = LocalDate.now();
		
		revision = new Revision(opinion, basico, user);
		
		assertEquals(revision.getOpinion(), opinion);
		assertEquals(revision.getEstadoDelUsuarioActual(), basico);
		assertEquals(revision.getFechaDeCreacion(), today);
	}
}
