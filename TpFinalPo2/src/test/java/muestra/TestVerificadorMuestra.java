package muestra;

import static org.junit.Assert.assertEquals;
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


import static org.mockito.Mockito.mock;

public class TestVerificadorMuestra {
	
	private PostMuestra posteoMock;
	private VerificadorMuestra verificadorATest;
	private HashMap<Opinion, Set<Revision>> revisiones;
	private Revision revisionA;
	private Revision revisionB;
	private Revision revisionC;
	
	@Before
	public void setUp() {
		posteoMock = mock(PostMuestra.class);
		revisiones = new HashMap<Opinion, Set<Revision>>();
		revisionA = mock(Revision.class);
		revisionB = mock(Revision.class);
		revisionC = mock(Revision.class);
		
		when(posteoMock.getOpiniones()).thenReturn(revisiones);
		
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
		verificadorATest.opinar(revisionA);
		verificadorATest.opinar(revisionB);
		
		assertTrue(revisiones.get(Opinion.VINCHUCA_GUASAYANA).contains(revisionA));
		assertTrue(revisiones.get(Opinion.VINCHUCA_INFESTANTS).contains(revisionB));
		
		verify(revisionA, times(1)).getOpinion();
		verify(revisionB, times(1)).getOpinion();
	}
	
	@Test
	public void cuandoSeLlamaA_resultadoActual_EsteDevuelveLaOpinionConMayorVoto() {
		
		when(revisionA.getOpinion()).thenReturn(Opinion.PHTIA_CHINCHE);
		when(revisionB.getOpinion()).thenReturn(Opinion.IMAGEN_POCO_CLARA);
		when(revisionC.getOpinion()).thenReturn(Opinion.IMAGEN_POCO_CLARA);
		verificadorATest.opinar(revisionA);
		verificadorATest.opinar(revisionB);
		verificadorATest.opinar(revisionC);
		
		Opinion resultadoDado = verificadorATest.getResultadoActual();
		Opinion resultadoEsperado = Opinion.IMAGEN_POCO_CLARA;
		
		assertEquals(resultadoDado, resultadoEsperado);
		
		verify(posteoMock, times(22)).getOpiniones();
		verify(revisionA, times(1)).getOpinion();
		verify(revisionB, times(1)).getOpinion();
		verify(revisionC, times(1)).getOpinion();
	}
	
}
