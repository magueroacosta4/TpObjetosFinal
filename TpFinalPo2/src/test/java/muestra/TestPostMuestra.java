package muestra;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Optional;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import usuario.Participante;



public class TestPostMuestra {
	
	Ubicacion ubicacionA;
	VerificadorMuestra verificadorA;
	Revision revisionA;
	PostMuestra posteoA;
	Revision revisionB;
	Revision revisionC;
	LocalDate today;
	PostMuestra posteo;
	private Participante usuarioA;
	private Participante usuarioB;
	private Participante usuarioC;
	private EstadoDePost estadoPostBasico;
	private EstadoDePost estadoPostExperto;
	
	@Before
	public void setUp() {
		ubicacionA = mock(Ubicacion.class);
		verificadorA = mock(VerificadorMuestra.class);
		revisionA = mock(Revision.class);
		revisionB = mock(Revision.class);
		revisionC = mock(Revision.class);
		usuarioA = mock(Participante.class);
		usuarioB = mock(Participante.class);
		usuarioC = mock(Participante.class);
		estadoPostBasico = mock(EstadoPostBasico.class);
		estadoPostExperto = mock(EstadoPostExperto.class);
		
		doNothing().when(estadoPostBasico).opinar(revisionA, verificadorA);		
		doNothing().when(estadoPostBasico).opinar(revisionB, verificadorA);		
		doNothing().when(estadoPostBasico).opinar(revisionC, verificadorA);		
		
		today = LocalDate.now();
		when(revisionA.getFechaDeCreacion()).thenReturn(today);	
		when(revisionA.getUser()).thenReturn(usuarioA);
		when(revisionB.getUser()).thenReturn(usuarioB);
		when(revisionC.getUser()).thenReturn(usuarioC);
		when(revisionA.getOpinion()).thenReturn(Opinion.VINCHUCA_SORDIDA);
		when(revisionB.getOpinion()).thenReturn(Opinion.VINCHUCA_INFESTANTS);
		when(revisionC.getOpinion()).thenReturn(Opinion.VINCHUCA_GUASAYANA);
		
		
		posteo = new PostMuestra(ubicacionA, verificadorA, estadoPostBasico, revisionA, "");		
	}
	
	
	@Test
	public void alCrearUnPostMuestraConElConstructorDeParametroUbicacionSeColocanTodosSusColaboradores() {
		
		posteo = new PostMuestra(ubicacionA, revisionB, "");
		
		assertEquals(posteo.getResultadoActual(), Optional.of(Opinion.VINCHUCA_INFESTANTS));
		assertEquals(posteo.getUbicacion(), ubicacionA);
		assertEquals(posteo.getFechaDeCreacion(), today);
	}
	
	
	@Test
	public void alCrearUnPostMuestraSeColocanTodosSusColaboradores() {
		
		assertEquals(posteo.getResultadoActual(), Optional.of(Opinion.VINCHUCA_SORDIDA));
		assertEquals(posteo.getUbicacion(), ubicacionA);
		assertEquals(posteo.getFechaDeCreacion(), today); //
		assertEquals(posteo.getOpiniones().size(), 8);
		assertEquals(posteo.getEstadoDePost(), estadoPostBasico);
		assertEquals(posteo.getVerificador(), verificadorA);
		assertEquals(posteo.getFechaUltimaRevision(), today);//
	}
	
	@Test
	public void sePideElCreadorDelPost() {
		assertEquals(posteo.getUsuarioCreador(), usuarioA);
	}
	
	@Test
	public void cuandoUnaMuestraSeSubeOtraPersonaLoOpinaConOpinionDistintaALaDelCreador_ElEstadoDeResultadoAcualEsElMismoQueElInicial() throws Exception {
		posteo.opinar(revisionB);
		posteo.setResultadoActual(Optional.of(Opinion.VINCHUCA_GUASAYANA));
		
		assertEquals(posteo.getResultadoActual().get(), Opinion.VINCHUCA_GUASAYANA);
		
		verify(estadoPostBasico, times(1)).opinar(revisionB, verificadorA);
	}
	
	@Test 
	public void seSubeUnaMuestraConUnaOpinionPeroOtras2personasOpinanLoMismoYSeCambiaElResultadoActualPor_ImagenPocoClara() throws Exception {
		posteo.opinar(revisionB);
		posteo.opinar(revisionC);
		
		posteo.setResultadoActual(Optional.of(Opinion.IMAGEN_POCO_CLARA));
		
		assertEquals(posteo.getResultadoActual().get(), Opinion.IMAGEN_POCO_CLARA);
		
		verify(estadoPostBasico, times(1)).opinar(revisionB, verificadorA);
		verify(estadoPostBasico, times(1)).opinar(revisionC, verificadorA);
		
	}
	
	@Test
	public void sePidenLasOpinionesActuales() {
		
		HashMap<Opinion, Set <Revision>> resultadoDado = posteo.getOpiniones();
		
		assertEquals(resultadoDado.size(), 8);
		
	}
	
	@Test 
	public void seVerificaUnPosteo() throws Exception {
		
		when(revisionB.getOpinion()).thenReturn(Opinion.VINCHUCA_GUASAYANA);
		doAnswer(invocation -> {
			posteo.agregarRevision(revisionB);
			posteo.setEstadoPost(estadoPostExperto);
			return null;
		}).when(estadoPostBasico).opinar(revisionB, verificadorA);
		
		doAnswer(invocation -> {
			posteo.agregarRevision(revisionC);
			posteo.setResultadoActual(Optional.of(Opinion.VINCHUCA_GUASAYANA));
			return null;
		}).when(estadoPostExperto).opinar(revisionC, verificadorA);
		
		posteo.opinar(revisionB);
		
		posteo.opinar(revisionC);
		
		posteo.verificarPost();
		boolean resultado = posteo.getEstadoDePost().esVerificado();
		
		assertTrue(resultado);
	}
	
	@Test 
	public void seVerificaUnPosteo_EsteNoSeVerificaPorQueLasOpininionesSonDistintas() throws Exception {
		doAnswer(invocation -> {
			posteo.agregarRevision(revisionB);
			return null;
		}).when(estadoPostBasico).opinar(revisionB, verificadorA);
		
		posteo.opinar(revisionB);
		
		
		posteo.verificarPost();
		boolean resultado = posteo.getEstadoDePost().equals(estadoPostBasico);
		
		assertTrue(resultado);
	}
	
	

	@Test
	public void unUsuarioOpina_LuegoElMismoUsuarioQuiereOpinarDeNuevoPeroNoPuede() throws Exception {
		when(revisionC.getUser()).thenReturn(usuarioB);
		
		
		posteo.opinar(revisionB);
		
		String errorEsperado = "El usuario ya opino";
		Exception error = assertThrows(Exception.class, ()-> posteo.opinar(revisionC));
		
		assertEquals(error.getMessage(), errorEsperado);
	}
	
	

	@Test 
	public void sePreguntaCauntasRevisionesTieneUnaOpinion() {
		
		int resultadoDado = posteo.sizeOpinionResultadoActual();
		int resultadoEsperado = 1;
			
		assertEquals(resultadoDado, resultadoEsperado);
	}
	
	@Test
	public void sePreguntaCauntasRevisionesTieneLaOpinionActual() throws Exception {
	
		
		doAnswer(invocation -> {
			posteo.agregarRevision(revisionB);
			posteo.setResultadoActual(Optional.of(Opinion.VINCHUCA_INFESTANTS));
			return null;
		}).when(estadoPostBasico).opinar(revisionB, verificadorA);;
		
		posteo.opinar(revisionB);

		int resultadoDado = posteo.sizeOpinionResultadoActual();
		int resultadoEsperado = 1;
			
		assertEquals(resultadoDado, resultadoEsperado);
		verify(estadoPostBasico, times(1)).opinar(revisionB, verificadorA);
	}
	
	@Test
	public void sePreguntaCauntasRevisionesTieneLaOpinionActual_AlSerEmptyDaCero() throws Exception {
		
		doAnswer(invocation -> {
			posteo.agregarRevision(revisionB);
			posteo.setResultadoActual(Optional.empty());
			return null;
		}).when(estadoPostBasico).opinar(revisionB, verificadorA);
		posteo.opinar(revisionB);
		
		int resultadoDado = posteo.sizeOpinionResultadoActual();
		int resultadoEsperado = 0;
			
		assertEquals(resultadoDado, resultadoEsperado);		
	}
	
}
