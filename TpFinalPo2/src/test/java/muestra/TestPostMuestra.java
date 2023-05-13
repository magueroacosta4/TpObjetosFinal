package muestra;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;
import org.mockito.stubbing.Answer;

import Usuario.EstadoBasico;

import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.mockito.invocation.InvocationOnMock;



public class TestPostMuestra {
	
	Ubicacion ubicacionA;
	VerificadorMuestra verificadorA;
	Revision revisionA;
	PostMuestra posteoA;
	Revision revisionB;
	Revision revisionC;
	private EstadoBasico estadoUsuarioBasico;
	@Before
	public void setUp() {
		ubicacionA = mock(Ubicacion.class);
		verificadorA = mock(VerificadorMuestra.class);
		revisionA = mock(Revision.class);
		revisionB = mock(Revision.class);
		revisionC = mock(Revision.class);
		estadoUsuarioBasico = mock(EstadoBasico.class);
		
		doNothing().when(verificadorA).opinar(revisionA);		
		doNothing().when(verificadorA).colocarClavesEnHashmap();
	}
	

	@Test
	public void alCrearUnPostMuestraSeColocanTodosSusColaboradoresYseAgregaLaRevision() {
		Date today = new Date();
		when(revisionA.getFechaDeCreacion()).thenReturn(today);
		when(verificadorA.getResultadoActualPost()).thenReturn(Opinion.NINGUNA);		
		
		
		PostMuestra posteo = new PostMuestra(revisionA, ubicacionA, verificadorA);		

		posteo.setResultadoActual(Opinion.NINGUNA);
		
		assertEquals(posteo.getResultadoActual(), Opinion.NINGUNA);
		assertEquals(posteo.getUbicacion(), ubicacionA);
		assertEquals(posteo.getFechaDeCreacion(), today); //
		
		verify(revisionA, times(1)).getFechaDeCreacion();
		verify(verificadorA, times(1)).colocarClavesEnHashmap();
		verify(verificadorA, times(1)).opinar(revisionA);
	}
	
	@Test
	public void cuandoUnaMuestraSeSubeOtraPersonaLoOpinaConOpinionDistintaALaDelCreador_ElEstadoDeResultadoAcualEsElMismoQueElInicial() {
		

		when(verificadorA.getResultadoActualPost()).thenReturn(Opinion.VINCHUCA_GUASAYANA);
		
		PostMuestra posteo = new PostMuestra(revisionA, ubicacionA, verificadorA);		
		
		posteo.opinar(revisionB);
		posteo.setResultadoActual(Opinion.VINCHUCA_GUASAYANA);
		
		assertEquals(posteo.getResultadoActual(), Opinion.VINCHUCA_GUASAYANA);
		
		verify(verificadorA, times(1)).colocarClavesEnHashmap();
		verify(verificadorA, times(1)).opinar(revisionA);
	}
	
	@Test 
	public void seSubeUnaMuestraConUnaOpinionPeroOtras2personasOpinanLoMismoYSeCambiaElResultadoActualPor_ImagenPocoClara() {
	
		when(verificadorA.getResultadoActualPost()).thenReturn(Opinion.IMAGEN_POCO_CLARA);
		
		PostMuestra posteo = new PostMuestra(revisionA, ubicacionA, verificadorA);
		
		posteo.opinar(revisionB);
		posteo.opinar(revisionC);
		
		posteo.setResultadoActual(Opinion.IMAGEN_POCO_CLARA);
		
		assertEquals(posteo.getResultadoActual(), Opinion.IMAGEN_POCO_CLARA);
		
		verify(verificadorA, times(1)).colocarClavesEnHashmap();
		verify(verificadorA, times(1)).opinar(revisionB);
		verify(verificadorA, times(1)).opinar(revisionC);
		
	}
	
	@Test
	public void sePidenLasOpinionesActuales() {
		PostMuestra posteo = new PostMuestra(revisionA, ubicacionA, verificadorA);
		
		HashMap<Opinion, Set <Revision>> map = new HashMap<Opinion, Set <Revision>>();
		HashMap<Opinion, Set <Revision>> resultadoDado = posteo.getOpiniones();
		
		assertTrue(resultadoDado.equals(map));
		
	}
	
	@Test 
	public void seVerificaUnPosteo() {
		PostMuestra posteo = new PostMuestra(revisionA, ubicacionA, verificadorA);
		
		posteo.verificarPost();
		
		assertTrue(posteo.getEsPostVerificado());
	}
	
	@Test 
	public void sePreguntaCauntasRevisionesTieneUnaOpinion() {
		PostMuestra posteo = new PostMuestra(revisionA, ubicacionA, verificadorA);
		HashMap<Opinion, Set <Revision>> map = new HashMap<Opinion, Set <Revision>>();
		Set<Revision> set = new HashSet<Revision>();
		set.add(revisionA);
		map.put(Opinion.CHINCHE_FOLIADA, set);
		
		posteo.setOpiniones(map);
		
		int resultadoDado = posteo.sizeOpinion(Opinion.CHINCHE_FOLIADA);
		int resultadoEsperado = 1;
			
		assertEquals(resultadoDado, resultadoEsperado);
		
	}
	
	
	
	
	
	
	
	
}
