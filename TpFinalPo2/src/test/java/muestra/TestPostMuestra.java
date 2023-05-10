package muestra;


import static org.junit.Assert.assertEquals;

import static org.mockito.Mockito.*;
import org.mockito.stubbing.Answer;
import java.util.Date;

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
	@Before
	public void setUp() {
		ubicacionA = mock(Ubicacion.class);
		verificadorA = mock(VerificadorMuestra.class);
		revisionA = mock(Revision.class);
		revisionB = mock(Revision.class);
		revisionC = mock(Revision.class);
		
		
		doAnswer(new Answer<Void>() {
			public Void answer(InvocationOnMock invocation) {
				return null;
			}
		}).when(verificadorA).opinar(revisionA);
		
		doAnswer(new Answer<Void>() {
			public Void answer(InvocationOnMock invocation) {
				//Arrays.stream(Opinion.values()).forEach(o -> posteoA.getOpiniones().put(o, new HashSet<Revision>()));
				return null;
			}
		}).when(verificadorA).colocarClavesEnHashmap();
	}
	
	@Test
	public void seCreaUnPostUtilizandoElConstructorQueNoRecibeUnVerficador() {
		Date today = new Date();
		when(revisionA.getFechaDeCreacion()).thenReturn(today);
		when(revisionA.getOpinion()).thenReturn(Opinion.NINGUNA);
	
		PostMuestra posteo = new PostMuestra(revisionA, ubicacionA);
		
		assertEquals(posteo.getResultadoActual(), Opinion.NINGUNA);
		assertEquals(posteo.getUbicacion(), ubicacionA);
		assertEquals(posteo.getFechaDeCreacion(), today);
	}
	@Test
	public void alCrearUnPostMuestraSeColocanTodosSusColaboradoresYseAgregaLaRevision() {
		Date today = new Date();
		when(revisionA.getFechaDeCreacion()).thenReturn(today);
		when(verificadorA.getResultadoActual()).thenReturn(Opinion.NINGUNA);		
		
		
		PostMuestra posteo = new PostMuestra(revisionA, ubicacionA, verificadorA);		

		
		assertEquals(posteo.getResultadoActual(), Opinion.NINGUNA);
		assertEquals(posteo.getUbicacion(), ubicacionA);
		assertEquals(posteo.getFechaDeCreacion(), today); //
		
		verify(revisionA, times(1)).getFechaDeCreacion();
		verify(verificadorA, times(1)).colocarClavesEnHashmap();
		verify(verificadorA, times(2)).getResultadoActual();
		verify(verificadorA, times(1)).opinar(revisionA);
	}
	
	@Test
	public void cuandoUnaMuestraSeSubeOtraPersonaLoOpinaConOpinionDistintaALaDelCreador_ElEstadoDeResultadoAcualEsElMismoQueElInicial() {
		

		when(verificadorA.getResultadoActual()).thenReturn(Opinion.VINCHUCA_GUASAYANA);
		
		PostMuestra posteo = new PostMuestra(revisionA, ubicacionA, verificadorA);		
		
		posteo.opinar(revisionB);
		
		
		assertEquals(posteo.getResultadoActual(), Opinion.VINCHUCA_GUASAYANA);
		
		verify(verificadorA, times(1)).colocarClavesEnHashmap();
		verify(verificadorA, times(2)).getResultadoActual();
		verify(verificadorA, times(1)).opinar(revisionA);
	}
	
	@Test 
	public void seSubeUnaMuestraConUnaOpinionPeroOtras2personasOpinanLoMismoYSeCambiaElResultadoActualPor_ImagenPocoClara() {
	
		when(verificadorA.getResultadoActual()).thenReturn(Opinion.IMAGEN_POCO_CLARA);
		
		PostMuestra posteo = new PostMuestra(revisionA, ubicacionA, verificadorA);
		
		posteo.opinar(revisionB);
		posteo.opinar(revisionC);
		
		assertEquals(posteo.getResultadoActual(), Opinion.IMAGEN_POCO_CLARA);
		
		verify(verificadorA, times(1)).colocarClavesEnHashmap();
		verify(verificadorA, times(2)).getResultadoActual();
		verify(verificadorA, times(1)).opinar(revisionA);
		
	}
	
	
	
	
	
	
	
	
	
}
