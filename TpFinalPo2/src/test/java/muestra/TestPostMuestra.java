package muestra;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.HashSet;
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
		
		doNothing().when(verificadorA).opinar(revisionA);		
		doNothing().when(verificadorA).opinar(revisionB);		
		doNothing().when(verificadorA).colocarClavesEnHashmap();
		
		today = LocalDate.now();
		when(revisionA.getFechaDeCreacion()).thenReturn(today);
		when(verificadorA.getResultadoActualPost()).thenReturn(Opinion.NINGUNA);		
		when(revisionA.getUser()).thenReturn(usuarioA);
		when(revisionB.getUser()).thenReturn(usuarioB);
		when(revisionC.getUser()).thenReturn(usuarioC);
		
		posteo = new PostMuestra(ubicacionA, verificadorA);		
	}
	

	@Test
	public void alCrearUnPostMuestraSeColocanTodosSusColaboradores() {


		posteo.setResultadoActual(Optional.empty());
		
		assertEquals(posteo.getResultadoActual(), Optional.empty());
		assertEquals(posteo.getUbicacion(), ubicacionA);
		assertEquals(posteo.getFechaDeCreacion(), today); //
		
		verify(verificadorA, times(1)).colocarClavesEnHashmap();
	}
	
	@Test
	public void cuandoUnaMuestraSeSubeOtraPersonaLoOpinaConOpinionDistintaALaDelCreador_ElEstadoDeResultadoAcualEsElMismoQueElInicial() throws Exception {
		

		when(verificadorA.getResultadoActualPost()).thenReturn(Opinion.VINCHUCA_GUASAYANA);	
		
		posteo.opinar(revisionB);
		posteo.setResultadoActual(Optional.of(Opinion.VINCHUCA_GUASAYANA));
		
		assertEquals(posteo.getResultadoActual().get(), Opinion.VINCHUCA_GUASAYANA);
		
		verify(verificadorA, times(1)).colocarClavesEnHashmap();
		verify(verificadorA, times(1)).opinar(revisionB);
	}
	
	@Test 
	public void seSubeUnaMuestraConUnaOpinionPeroOtras2personasOpinanLoMismoYSeCambiaElResultadoActualPor_ImagenPocoClara() throws Exception {
	
		when(verificadorA.getResultadoActualPost()).thenReturn(Opinion.IMAGEN_POCO_CLARA);
		
		posteo.opinar(revisionB);
		posteo.opinar(revisionC);
		
		posteo.setResultadoActual(Optional.of(Opinion.IMAGEN_POCO_CLARA));
		
		assertEquals(posteo.getResultadoActual().get(), Opinion.IMAGEN_POCO_CLARA);
		
		verify(verificadorA, times(1)).colocarClavesEnHashmap();
		verify(verificadorA, times(1)).opinar(revisionB);
		verify(verificadorA, times(1)).opinar(revisionC);
		
	}
	
	@Test
	public void sePidenLasOpinionesActuales() {
		
		HashMap<Opinion, Set <Revision>> map = new HashMap<Opinion, Set <Revision>>();
		HashMap<Opinion, Set <Revision>> resultadoDado = posteo.getOpiniones();
		
		assertTrue(resultadoDado.equals(map));
		
	}
	
	@Test 
	public void seVerificaUnPosteo() {
		
		posteo.verificarPost();
		
		assertTrue(posteo.getEsPostVerificado());
	}
	

	@Test
	public void unUsuarioOpina_LuegoElMismoUsuarioQuiereOpinarDeNuevoPeroNoPuede() throws Exception {
		when(revisionC.getUser()).thenReturn(usuarioB);
		
		
		posteo.opinar(revisionB);
		
		String errorEsperado = "El usuario ya opino";
		Exception error = assertThrows(Exception.class, ()-> posteo.opinar(revisionC));
		
		assertEquals(error.getMessage(), errorEsperado);
	}
	
	
	
	
	
}
