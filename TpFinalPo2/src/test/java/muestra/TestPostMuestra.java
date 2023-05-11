package muestra;


import static org.junit.Assert.assertEquals;

import static org.mockito.Mockito.*;
import org.mockito.stubbing.Answer;

import Usuario.EstadoBasico;

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
	public void seCreaUnPostUtilizandoElConstructorQueNoRecibeUnVerficador() {
		Date today = new Date();
		when(revisionA.getFechaDeCreacion()).thenReturn(today);
		when(revisionA.getOpinion()).thenReturn(Opinion.NINGUNA);
		when(revisionA.getEstadoDelUsuarioActual()).thenReturn(estadoUsuarioBasico);
		
		when(revisionA.getEstadoDelUsuarioActual().esExperto()).thenReturn(false);
		
		
		PostMuestra posteo = new PostMuestra(revisionA, ubicacionA);
		
		assertEquals(posteo.getResultadoActual(), Opinion.NINGUNA);
		assertEquals(posteo.getUbicacion(), ubicacionA);
		assertEquals(posteo.getFechaDeCreacion(), today);
	}
	@Test
	public void alCrearUnPostMuestraSeColocanTodosSusColaboradoresYseAgregaLaRevision() {
		Date today = new Date();
		when(revisionA.getFechaDeCreacion()).thenReturn(today);
		when(verificadorA.getResultadoActualPost()).thenReturn(Opinion.NINGUNA);		
		
		
		PostMuestra posteo = new PostMuestra(revisionA, ubicacionA, verificadorA);		

		
		assertEquals(posteo.getResultadoActual(), Opinion.NINGUNA);
		assertEquals(posteo.getUbicacion(), ubicacionA);
		assertEquals(posteo.getFechaDeCreacion(), today); //
		
		verify(revisionA, times(1)).getFechaDeCreacion();
		verify(verificadorA, times(1)).colocarClavesEnHashmap();
		verify(verificadorA, times(1)).getResultadoActualPost();
		verify(verificadorA, times(1)).opinar(revisionA);
	}
	
	@Test
	public void cuandoUnaMuestraSeSubeOtraPersonaLoOpinaConOpinionDistintaALaDelCreador_ElEstadoDeResultadoAcualEsElMismoQueElInicial() {
		

		when(verificadorA.getResultadoActualPost()).thenReturn(Opinion.VINCHUCA_GUASAYANA);
		
		PostMuestra posteo = new PostMuestra(revisionA, ubicacionA, verificadorA);		
		
		posteo.opinar(revisionB);
		
		
		assertEquals(posteo.getResultadoActual(), Opinion.VINCHUCA_GUASAYANA);
		
		verify(verificadorA, times(1)).colocarClavesEnHashmap();
		verify(verificadorA, times(1)).getResultadoActualPost();
		verify(verificadorA, times(1)).opinar(revisionA);
	}
	
	@Test 
	public void seSubeUnaMuestraConUnaOpinionPeroOtras2personasOpinanLoMismoYSeCambiaElResultadoActualPor_ImagenPocoClara() {
	
		when(verificadorA.getResultadoActualPost()).thenReturn(Opinion.IMAGEN_POCO_CLARA);
		
		PostMuestra posteo = new PostMuestra(revisionA, ubicacionA, verificadorA);
		
		posteo.opinar(revisionB);
		posteo.opinar(revisionC);
		
		assertEquals(posteo.getResultadoActual(), Opinion.IMAGEN_POCO_CLARA);
		
		verify(verificadorA, times(1)).colocarClavesEnHashmap();
		verify(verificadorA, times(1)).getResultadoActualPost();
		verify(verificadorA, times(1)).opinar(revisionA);
		
	}
	

	
	
	
	
	
	
	
	
}
