package muestra;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import java.util.HashSet;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Stream;
import java.util.HashMap;

import static org.mockito.Mockito.*;
//import org.mockito.stubbing.Answer;
//import org.mockito.invocation.InvocationOnMock;

import org.junit.Before;
import org.junit.Test;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import Usuario.*;

public class TestVerificadorMuestra {
	
	private VerificadorMuestra verificadorBTest;
	private PostMuestra posteoMock;
	private VerificadorMuestra verificadorATest;
	private HashMap<Opinion, Set<Revision>> revisiones;
	private Revision revisionA;
	private Revision revisionB;
	private Revision revisionC;
	private EstadoPostBasico estadoPostA;
	private EstadoPostExperto estadoPostB;
	private EstadoBasico estadoUsuarioBasico;
	private EstadoExperto estadoUsuarioExperto;
	
	@Before
	public void setUp() {
		posteoMock = mock(PostMuestra.class);
		estadoPostA    = mock(EstadoPostBasico.class);
		estadoPostB    = mock(EstadoPostExperto.class);
		estadoUsuarioBasico = mock(EstadoBasico.class);
		estadoUsuarioExperto = mock(EstadoExperto.class);
		revisiones = new HashMap<Opinion, Set<Revision>>();
		revisionA = mock(Revision.class);
		revisionB = mock(Revision.class);
		revisionC = mock(Revision.class);
		
		when(posteoMock.getOpiniones()).thenReturn(revisiones);
		when(revisionA.getEstadoDelUsuarioActual()).thenReturn(estadoUsuarioBasico);
		when(revisionB.getEstadoDelUsuarioActual()).thenReturn(estadoUsuarioBasico);
		when(revisionC.getEstadoDelUsuarioActual()).thenReturn(estadoUsuarioBasico);
		
		doNothing().when(estadoPostA).opinar(verificadorBTest, revisionA);
		doNothing().when(estadoPostA).opinar(verificadorBTest, revisionB);
		doNothing().when(estadoPostA).opinar(verificadorBTest, revisionC);
		
		doNothing().when(estadoPostB).opinar(verificadorBTest, revisionA);
		doNothing().when(estadoPostB).opinar(verificadorBTest, revisionB);
		doNothing().when(estadoPostB).opinar(verificadorBTest, revisionC);
		
		verificadorATest = new VerificadorMuestra(posteoMock);
		verificadorATest.colocarClavesEnHashmap();
	}

	@Test
	public void cuandoSeCreaUnVerificadorEsteTieneElPostMuestraAsociado() {
		
			assertEquals(verificadorATest.getPost(), posteoMock);
		
	}
	
	@Test
	public void cuandoSeLlamaA_colocarClavesEnHashmap_ColocaComoClavesTodasLasOpinionesYSeLesDaUnSetDeRevisionComoValor() {
		
		
		Stream<Opinion> resultadoEsperadoA = Arrays.stream(Opinion.values());
		Set<Revision> resultadoEsperadoB = new HashSet<Revision>();
		
		
		assertTrue(revisiones.keySet().containsAll(resultadoEsperadoA.toList()));
		assertTrue(revisiones.values().stream().allMatch(s->s.equals(resultadoEsperadoB)));
		
		verify(posteoMock, times(7)).getOpiniones();
		
	}
	
	@Test
	public void cuandoSeLlamaA_opinar_EsteColocaEnElHashMapRevisionesLaRevisionEnLaOpinionClave() {
		
		when(revisionA.getOpinion()).thenReturn(Opinion.VINCHUCA_GUASAYANA);
		when(revisionB.getOpinion()).thenReturn(Opinion.VINCHUCA_INFESTANTS);

		verificadorBTest = new VerificadorMuestra(posteoMock, estadoPostA);
		
		when(estadoUsuarioBasico.esExperto()).thenReturn(false);
		
		verificadorBTest.opinar(revisionA);
		verificadorBTest.opinar(revisionB);
		
		verificadorBTest.opinarEnEstadoBasico(revisionA);
		verificadorBTest.opinarEnEstadoBasico(revisionB);

		
		assertTrue(revisiones.get(Opinion.VINCHUCA_GUASAYANA).contains(revisionA));
		assertTrue(revisiones.get(Opinion.VINCHUCA_INFESTANTS).contains(revisionB));
		
		verify(revisionA, times(1)).getOpinion();
		verify(revisionB, times(1)).getOpinion();
		verify(estadoPostA, times(1)).opinar(verificadorBTest, revisionA);
		verify(estadoPostA, times(1)).opinar(verificadorBTest, revisionB);
	}
	
	@Test
	public void cuandoSeLlamaA_resultadoActual_EsteDevuelveLaOpinionConMayorVoto() {
		
		when(revisionA.getOpinion()).thenReturn(Opinion.PHTIA_CHINCHE);
		when(revisionB.getOpinion()).thenReturn(Opinion.IMAGEN_POCO_CLARA);
		when(revisionC.getOpinion()).thenReturn(Opinion.IMAGEN_POCO_CLARA);
		
		verificadorBTest = new VerificadorMuestra(posteoMock, estadoPostA);
		
		when(estadoUsuarioBasico.esExperto()).thenReturn(false);
		
		verificadorBTest.opinar(revisionA);
		verificadorBTest.opinar(revisionB);
		verificadorBTest.opinar(revisionC);
		//al ser una delegacion, es necesario colocar el metodo esperado
		verificadorBTest.opinarEnEstadoBasico(revisionA);
		verificadorBTest.opinarEnEstadoBasico(revisionB);
		verificadorBTest.opinarEnEstadoBasico(revisionC);
		
		
		
		Opinion resultadoDado = verificadorBTest.getResultadoActual();
		Opinion resultadoEsperado = Opinion.IMAGEN_POCO_CLARA;
		
		assertEquals(resultadoDado, resultadoEsperado);
		
		verify(posteoMock, times(22)).getOpiniones();
		verify(revisionA, times(1)).getOpinion();
		verify(revisionB, times(1)).getOpinion();
		verify(revisionC, times(1)).getOpinion();
		verify(estadoPostA, times(1)).opinar(verificadorBTest, revisionA);
		verify(estadoPostA, times(1)).opinar(verificadorBTest, revisionB);
	}
	
	
	@Test
	public void seSubeUnaMuestraYlaOpinaUnExperto_LuegoUnBasicoLoQuiereOpinarPeroNoSeSubeSuOpinion_OtroExpertoOpinaYSiSeSube() {
		when(revisionA.getOpinion()).thenReturn(Opinion.PHTIA_CHINCHE);
		when(revisionB.getOpinion()).thenReturn(Opinion.IMAGEN_POCO_CLARA);
		when(revisionC.getOpinion()).thenReturn(Opinion.VINCHUCA_GUASAYANA);
		when(estadoUsuarioBasico.esExperto()).thenReturn(true);
		
		
		verificadorBTest = new VerificadorMuestra(posteoMock, estadoPostA);
	
		verificadorBTest.opinar(revisionA);
		verificadorBTest.opinar(revisionB);
		verificadorBTest.opinar(revisionC);
		verificadorBTest.opinarEnEstadoBasico(revisionA);
		when(estadoUsuarioBasico.esExperto()).thenReturn(false);
		verificadorBTest.opinarEnEstadoExperto(revisionB);
		when(estadoUsuarioBasico.esExperto()).thenReturn(true);
		verificadorBTest.opinarEnEstadoExperto(revisionC);
		
		assertTrue(revisiones.get(Opinion.PHTIA_CHINCHE).contains(revisionA));
		assertTrue(revisiones.get(Opinion.VINCHUCA_GUASAYANA).contains(revisionC));
		assertFalse(revisiones.get(Opinion.IMAGEN_POCO_CLARA).contains(revisionB));
		
		verify(estadoPostA, times(1)).opinar(verificadorBTest, revisionA);
		verify(estadoPostA, times(1)).opinar(verificadorBTest, revisionB);
		verify(estadoPostA, times(1)).opinar(verificadorBTest, revisionC);
		
	}
	
}
