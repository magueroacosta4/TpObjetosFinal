package usuario;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import org.junit.*;
import org.junit.jupiter.api.BeforeEach;

import muestra.Opinion;
import muestra.PaginaWeb;
import muestra.PostMuestra;
import muestra.Revision;
import muestra.Ubicacion;

public class ParticipanteTest {
	private Participante participante;
	private EstadoBasico estadoB;
	private EstadoExperto estadoE;
	private Revision rev;
	private Opinion opinion;
	private Ubicacion ubi;
	private PaginaWeb pag;
	private HistorialEnApp historial;
	private PostMuestra post;
	
	@Before
	public void setUp() {
		participante = new Participante(pag);
		estadoB = mock(EstadoBasico.class);
		estadoE = mock(EstadoExperto.class);
		rev = mock(Revision.class);
		opinion = Opinion.CHINCHE_FOLIADA;
		ubi = mock(Ubicacion.class);
		pag = mock(PaginaWeb.class);
		historial = mock(HistorialEnApp.class);
		post = mock(PostMuestra.class);
		
		participante.setEstado(estadoB);
		participante.setHistorial(historial);
		participante.setPagina(pag);
	}
	
	@Test
	public void publicarUnPostTest() throws Exception {
		
		participante.publicar(rev, ubi);
		
		verify(pag).crearPostMuestra(rev, ubi);
		verify(historial).addPost();
		verify(estadoB).actualizarEstado(participante);
	}
	
	@Test
	public void opinarEnUnPostTest() throws Exception {
		
		participante.opinar(post, opinion);
		
		
		verify(estadoB).actualizarEstado(participante);
	}
	
	@Test
	public void esExpertoSiCumpleRequerimientosTest() {
		when(historial.getCantOpiniones30Dias()).thenReturn(20);
		when(historial.getCantPosts30Dias()).thenReturn(12);
		when(estadoE.esExperto()).thenReturn(true);
		when(estadoB.esExperto()).thenReturn(false);
		
		participante.setEstado(estadoE);
		
		assertEquals(participante.opinionesUltimos30Dias(), 20);
		assertEquals(participante.postsUltimos30Dias(), 12);
		assertTrue(participante.esExperto());
		
		participante.setEstado(estadoB);
		
		assertFalse(participante.esExperto());
		
	}
}
