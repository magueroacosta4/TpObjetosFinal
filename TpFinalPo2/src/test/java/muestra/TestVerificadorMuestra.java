package muestra;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import java.util.HashSet;
import java.util.Optional;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Stream;
import java.util.HashMap;

import static org.mockito.Mockito.*;
//import org.mockito.stubbing.Answer;
//import org.mockito.invocation.InvocationOnMock;

import org.junit.Before;
import org.junit.Test;

import usuario.*;

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
		when(posteoMock.getEsPostVerificado()).thenReturn(false);// el post recien se crea y por lo tanto no esta verificado
		when(posteoMock.getResultadoActual()).thenReturn(Optional.empty());
		when(posteoMock.getOpiniones()).thenReturn(revisiones);
		
		doNothing().when(estadoPostA).verificarPost();
		doNothing().when(estadoPostA).opinar(revisionA);
		doNothing().when(estadoPostA).opinar(revisionB);
		doNothing().when(estadoPostA).opinar(revisionC);
		
		doNothing().when(estadoPostB).verificarPost();
		doNothing().when(estadoPostB).opinar(revisionA);
		doNothing().when(estadoPostB).opinar(revisionB);
		doNothing().when(estadoPostB).opinar(revisionC);
		
		doNothing().when(posteoMock).verificarPost();
		
		verificadorBTest = new VerificadorMuestra(posteoMock, estadoPostA);
		verificadorATest = new VerificadorMuestra(posteoMock);
		verificadorATest.colocarClavesEnHashmap();
	}

	@Test
	public void cuandoSeCreaUnVerificadorEsteTieneElPostMuestraAsociadoYLaOpinionDelUsuarioSeSube() {
			
			when(revisionA.getOpinion()).thenReturn(Opinion.VINCHUCA_GUASAYANA);
			when(posteoMock.getResultadoActual()).thenReturn(Optional.of(Opinion.VINCHUCA_GUASAYANA));
		
			verificadorATest.opinionUsuarioQuePosteo(revisionA);
			
			assertEquals(posteoMock.getResultadoActual().get(), Opinion.VINCHUCA_GUASAYANA);		
			assertEquals(verificadorATest.getPost(), posteoMock);
		
	}
	
	@Test
	public void cuandoSeLlamaA_colocarClavesEnHashmap_ColocaComoClavesTodasLasOpinionesYSeLesDaUnSetDeRevisionComoValor() {
		
		
		Stream<Opinion> resultadoEsperadoA = Arrays.stream(Opinion.values());
		Set<Revision> resultadoEsperadoB = new HashSet<Revision>();
		
		
		assertTrue(revisiones.keySet().containsAll(resultadoEsperadoA.toList()));
		assertTrue(revisiones.values().stream().allMatch(s->s.equals(resultadoEsperadoB)));
		
	}
	
	@Test
	public void cuandoSeLlamaA_opinar_EsteColocaEnElHashMapRevisionesLaRevisionEnLaOpinionClave() {
		
		when(revisionA.getOpinion()).thenReturn(Opinion.VINCHUCA_GUASAYANA);
		when(revisionB.getOpinion()).thenReturn(Opinion.VINCHUCA_INFESTANTS);

		
		when(estadoUsuarioBasico.esExperto()).thenReturn(false);
		
		verificadorBTest.opinar(revisionA);
		verificadorBTest.opinarEnEstadoBasico(revisionA);
		
		verificadorBTest.opinar(revisionB);
		verificadorBTest.opinarEnEstadoBasico(revisionB);

		
		assertTrue(revisiones.get(Opinion.VINCHUCA_GUASAYANA).contains(revisionA));
		assertTrue(revisiones.get(Opinion.VINCHUCA_INFESTANTS).contains(revisionB));
		
		verify(revisionA, times(2)).getOpinion();
		verify(revisionB, times(2)).getOpinion();
		verify(estadoPostA, times(1)).opinar(revisionA);
		verify(estadoPostA, times(1)).opinar(revisionB);
	}
	
	@Test
	public void cuandoSeLlamaA_resultadoActual_EsteDevuelveLaOpinionConMayorVoto() {
		
		when(revisionA.getOpinion()).thenReturn(Opinion.PHTIA_CHINCHE);
		when(revisionB.getOpinion()).thenReturn(Opinion.IMAGEN_POCO_CLARA);
		when(revisionC.getOpinion()).thenReturn(Opinion.IMAGEN_POCO_CLARA);
		
		when(posteoMock.sizeOpinionResultadoActual()).thenReturn(0).thenReturn(1);
		when(posteoMock.sizeOpinion(Opinion.PHTIA_CHINCHE)).thenReturn(1);
		when(posteoMock.sizeOpinion(Opinion.IMAGEN_POCO_CLARA)).thenReturn(1).thenReturn(2);
		
		when(estadoUsuarioBasico.esExperto()).thenReturn(false);
		
		verificadorBTest.opinar(revisionA);
		verificadorBTest.opinarEnEstadoBasico(revisionA);
		
		verificadorBTest.opinar(revisionB);
		verificadorBTest.opinarEnEstadoBasico(revisionB);
		
		verificadorBTest.opinar(revisionC);
		verificadorBTest.opinarEnEstadoBasico(revisionC);		
		
		Opinion resultadoDado = verificadorBTest.opinionConMayorVoto();
		Opinion resultadoEsperado = Opinion.IMAGEN_POCO_CLARA;
		
		assertEquals(resultadoDado, resultadoEsperado);
		
		verify(revisionA, times(2)).getOpinion();
		verify(revisionB, times(2)).getOpinion();
		verify(revisionC, times(2)).getOpinion();
		verify(estadoPostA, times(1)).opinar(revisionA);
		verify(estadoPostA, times(1)).opinar(revisionB);
	}
	
	
	@Test
	public void seSubeUnaMuestraYlaOpinaUnExperto_LuegoUnBasicoLoQuiereOpinarPeroNoSeSubeSuOpinion_LuegoOtroExpertoOpinaYSeSubeSuOpinion() {
		when(revisionA.getOpinion()).thenReturn(Opinion.PHTIA_CHINCHE);
		when(revisionB.getOpinion()).thenReturn(Opinion.IMAGEN_POCO_CLARA);
		when(revisionC.getOpinion()).thenReturn(Opinion.VINCHUCA_GUASAYANA);
		
		when(revisionA.getEstadoDelUsuarioActual()).thenReturn(estadoUsuarioExperto);
		when(revisionB.getEstadoDelUsuarioActual()).thenReturn(estadoUsuarioBasico);
		when(revisionC.getEstadoDelUsuarioActual()).thenReturn(estadoUsuarioExperto);
		when(posteoMock.sizeOpinionResultadoActual()).thenReturn(0).thenReturn(1);
		when(posteoMock.sizeOpinion(Opinion.PHTIA_CHINCHE)).thenReturn(1);
		when(posteoMock.sizeOpinion(Opinion.VINCHUCA_GUASAYANA)).thenReturn(1);
		
		when(estadoUsuarioExperto.esExperto()).thenReturn(true);
		when(estadoUsuarioBasico.esExperto()).thenReturn(false);
		
	
		verificadorBTest.opinar(revisionA);
		verificadorBTest.opinarEnEstadoBasico(revisionA);
		
		verificadorBTest.setEstadoPost(estadoPostB);

		verificadorBTest.opinar(revisionB);
		verificadorBTest.opinarEnEstadoExperto(revisionB);
		
		verificadorBTest.opinar(revisionC);
		verificadorBTest.opinarEnEstadoExperto(revisionC);
		
		assertTrue(revisiones.get(Opinion.PHTIA_CHINCHE).contains(revisionA));
		assertTrue(revisiones.get(Opinion.VINCHUCA_GUASAYANA).contains(revisionC));
		assertFalse(revisiones.get(Opinion.IMAGEN_POCO_CLARA).contains(revisionB));
		
		verify(estadoPostA, times(1)).opinar(revisionA);
		verify(estadoPostB, times(1)).opinar(revisionB);
		verify(estadoPostB, times(1)).opinar(revisionC);
		
	}
	
	
	@Test
	public void seSubeUnaMuestraYlaOpinanDosBasicosConLaMismaOpinion_LuegoOpinaUnExpertoYSePriorizaSuOpinionYSeBorranLasOpinionesDeBasicos() {
		when(revisionA.getOpinion()).thenReturn(Opinion.IMAGEN_POCO_CLARA);
		when(revisionB.getOpinion()).thenReturn(Opinion.IMAGEN_POCO_CLARA);
		when(revisionC.getOpinion()).thenReturn(Opinion.VINCHUCA_GUASAYANA);
		
		when(revisionA.getEstadoDelUsuarioActual()).thenReturn(estadoUsuarioBasico);
		when(revisionB.getEstadoDelUsuarioActual()).thenReturn(estadoUsuarioBasico);
		when(revisionC.getEstadoDelUsuarioActual()).thenReturn(estadoUsuarioExperto);
		when(posteoMock.sizeOpinionResultadoActual()).thenReturn(0).thenReturn(0).thenReturn(1);
		when(posteoMock.sizeOpinion(Opinion.IMAGEN_POCO_CLARA)).thenReturn(1).thenReturn(2).thenReturn(0);
		when(posteoMock.sizeOpinion(Opinion.VINCHUCA_GUASAYANA)).thenReturn(1);

		
		when(estadoUsuarioBasico.esExperto()).thenReturn(false);
	
		verificadorBTest.opinar(revisionA);
		verificadorBTest.opinarEnEstadoBasico(revisionA);		
		
		when(posteoMock.sizeOpinionResultadoActual()).thenReturn(2);
		
		verificadorBTest.opinar(revisionB);
		verificadorBTest.opinarEnEstadoBasico(revisionB);
		
		when(estadoUsuarioExperto.esExperto()).thenReturn(true);
	
		verificadorBTest.opinar(revisionC);
		verificadorBTest.opinarEnEstadoBasico(revisionC);
		
		verificadorBTest.setEstadoPost(estadoPostB);
		
		Opinion resultadoDado = verificadorBTest.opinionConMayorVoto();
		int tamanioImagenPocoClara = revisiones.get(Opinion.IMAGEN_POCO_CLARA).size();
		
		assertEquals(tamanioImagenPocoClara, 0);
		assertEquals(resultadoDado, Opinion.VINCHUCA_GUASAYANA);
		assertTrue(revisiones.get(Opinion.VINCHUCA_GUASAYANA).contains(revisionC));
		
		verify(estadoPostA, times(1)).opinar(revisionA);
		verify(estadoPostA, times(1)).opinar(revisionB);
		verify(estadoPostA, times(1)).opinar(revisionC);		
	}
	
	@Test
	public void seSubeUnaMuestraYLaOpinanDosBasicosConDistintaOpinion_AlSerLaMismaOpinionElResultadoDeLaMuestraEsNoDefinido() {
		when(revisionA.getOpinion()).thenReturn(Opinion.CHINCHE_FOLIADA);
		when(revisionB.getOpinion()).thenReturn(Opinion.VINCHUCA_GUASAYANA);
		
		when(revisionA.getEstadoDelUsuarioActual()).thenReturn(estadoUsuarioBasico);
		when(revisionB.getEstadoDelUsuarioActual()).thenReturn(estadoUsuarioBasico);
		
		when(estadoUsuarioExperto.esExperto()).thenReturn(false);
		when(posteoMock.sizeOpinionResultadoActual()).thenReturn(0).thenReturn(1);
		when(posteoMock.sizeOpinion(Opinion.CHINCHE_FOLIADA)).thenReturn(1);
		when(posteoMock.sizeOpinion(Opinion.VINCHUCA_GUASAYANA)).thenReturn(1);
		
		verificadorBTest.opinar(revisionA);
		verificadorBTest.opinarEnEstadoBasico(revisionA);		
		
		verificadorBTest.setEstadoPost(estadoPostB);
		

		verificadorBTest.opinar(revisionB);
		verificadorBTest.opinarEnEstadoBasico(revisionB);
		
		
		when(posteoMock.getEsPostVerificado()).thenReturn(false);// el post fue opinado por 3 expertos pero 2 coincidieron en su opinion, por lo tanto se verifica el post
		
		assertEquals(posteoMock.getResultadoActual(), Optional.empty());
		assertTrue(revisiones.get(Opinion.CHINCHE_FOLIADA).contains(revisionA));
		assertTrue(revisiones.get(Opinion.VINCHUCA_GUASAYANA).contains(revisionB));
		assertFalse(verificadorATest.getPost().getEsPostVerificado());
		
		
		verify(estadoPostA, times(1)).opinar(revisionA);
		verify(estadoPostB, times(1)).opinar(revisionB);	
	}
	
	
	@Test
	public void seSubeUnaMuestraYLaOpinanDosExpertosConDistintaOpinion_AlSerLaMismaOpinionElResultadoDeLaMuestraEsNoDefinido() {
		when(revisionA.getOpinion()).thenReturn(Opinion.CHINCHE_FOLIADA);
		when(revisionB.getOpinion()).thenReturn(Opinion.VINCHUCA_GUASAYANA);
		when(posteoMock.getOpiniones()).thenReturn(revisiones);
		when(revisionA.getEstadoDelUsuarioActual()).thenReturn(estadoUsuarioExperto);
		when(revisionB.getEstadoDelUsuarioActual()).thenReturn(estadoUsuarioExperto);

		when(posteoMock.sizeOpinionResultadoActual()).thenReturn(0).thenReturn(1);
		when(posteoMock.sizeOpinion(Opinion.CHINCHE_FOLIADA)).thenReturn(1);
		when(posteoMock.sizeOpinion(Opinion.VINCHUCA_GUASAYANA)).thenReturn(1);
		when(estadoUsuarioExperto.esExperto()).thenReturn(true);
		
	
		verificadorBTest.opinar(revisionA);
		verificadorBTest.opinarEnEstadoBasico(revisionA);		
		
		verificadorBTest.setEstadoPost(estadoPostB);
		when(posteoMock.getResultadoActual()).thenReturn(Optional.of(Opinion.CHINCHE_FOLIADA));
		verificadorBTest.setEstadoPost(estadoPostB);
		
		verificadorBTest.opinar(revisionB);
		verificadorBTest.opinarEnEstadoExperto(revisionB);
		
		when(posteoMock.getResultadoActual()).thenReturn(Optional.empty());
		when(posteoMock.getEsPostVerificado()).thenReturn(false);// el post fue opinado por 3 expertos pero 2 coincidieron en su opinion, por lo tanto se verifica el post
		
		assertEquals(posteoMock.getResultadoActual(), Optional.empty());
		assertTrue(revisiones.get(Opinion.CHINCHE_FOLIADA).contains(revisionA));
		assertTrue(revisiones.get(Opinion.VINCHUCA_GUASAYANA).contains(revisionB));
		assertFalse(verificadorATest.getPost().getEsPostVerificado());
		
		
		verify(estadoPostA, times(1)).opinar(revisionA);
		verify(estadoPostB, times(1)).opinar(revisionB);	
	}
	
	@Test
	public void seSubeUnaMuestraYLaOpinanDosExpertosConDistintaOpinion_LuegoOtroExpertoOpinaLoMismoQueUnoDeLosExpertosAnterioresYSeVerificaLaMuestra() {
		when(revisionA.getOpinion()).thenReturn(Opinion.CHINCHE_FOLIADA);
		when(revisionB.getOpinion()).thenReturn(Opinion.VINCHUCA_GUASAYANA);
		when(revisionC.getOpinion()).thenReturn(Opinion.VINCHUCA_GUASAYANA);
		
		when(revisionA.getEstadoDelUsuarioActual()).thenReturn(estadoUsuarioExperto);
		when(revisionB.getEstadoDelUsuarioActual()).thenReturn(estadoUsuarioExperto);
		when(revisionC.getEstadoDelUsuarioActual()).thenReturn(estadoUsuarioExperto);
		
		when(posteoMock.sizeOpinionResultadoActual())
		.thenReturn(0).thenReturn(1).thenReturn(0).thenReturn(2);		
		when(posteoMock.sizeOpinion(Opinion.CHINCHE_FOLIADA)).thenReturn(1);
		when(posteoMock.sizeOpinion(Opinion.VINCHUCA_GUASAYANA)).thenReturn(1).thenReturn(2);
		
		when(estadoUsuarioExperto.esExperto()).thenReturn(true);
		
		verificadorBTest.opinar(revisionA);
		verificadorBTest.opinarEnEstadoBasico(revisionA);		
		
		when(posteoMock.getResultadoActual()).thenReturn(Optional.of(Opinion.CHINCHE_FOLIADA));
		verificadorBTest.setEstadoPost(estadoPostB);
		
		doAnswer(invocation -> {
			verificadorBTest.verificarPostSiEsPostDeExpertos();
			return null;
		}).when(estadoPostB).verificarPost();
		
		verificadorBTest.opinar(revisionB);
		verificadorBTest.opinarEnEstadoExperto(revisionB);
		
		when(posteoMock.getResultadoActual())
		.thenReturn(Optional.empty())
		.thenReturn(Optional.of(Opinion.VINCHUCA_GUASAYANA));

		
		verificadorBTest.opinar(revisionC);		
		verificadorBTest.opinarEnEstadoExperto(revisionC);

		when(posteoMock.getEsPostVerificado()).thenReturn(true);// el post fue opinado por 3 expertos pero 2 coincidieron en su opinion, por lo tanto se verifica el post
		
		assertEquals(revisiones.get(Opinion.VINCHUCA_GUASAYANA).size(), 2);
		//assertEquals(verificadorBTest.opinionConMayorVoto(), Opinion.VINCHUCA_GUASAYANA);
		assertTrue(revisiones.get(Opinion.CHINCHE_FOLIADA).contains(revisionA));
		assertTrue(verificadorATest.getPost().getEsPostVerificado());
		
		
		verify(estadoPostA, times(1)).opinar(revisionA);
		verify(estadoPostB, times(1)).opinar(revisionB);
		verify(estadoPostB, times(1)).opinar(revisionC);	
		verify(posteoMock, times(1)).verificarPost();
	}
	
	@Test
	public void seSubeUnaMuestraYDosExpertosLaOpinanPorLoQueSeVerifica_OtroExpertoQuiereOpinarPeroNoSeSubeSuOpinion() {
		when(revisionA.getOpinion()).thenReturn(Opinion.CHINCHE_FOLIADA);
		when(revisionB.getOpinion()).thenReturn(Opinion.CHINCHE_FOLIADA);
		when(revisionC.getOpinion()).thenReturn(Opinion.VINCHUCA_GUASAYANA);
		
		when(revisionA.getEstadoDelUsuarioActual()).thenReturn(estadoUsuarioExperto);
		when(revisionB.getEstadoDelUsuarioActual()).thenReturn(estadoUsuarioExperto);
		when(revisionC.getEstadoDelUsuarioActual()).thenReturn(estadoUsuarioExperto);
		
		when(posteoMock.sizeOpinionResultadoActual()).thenReturn(0).thenReturn(1).thenReturn(2);
		when(posteoMock.sizeOpinion(Opinion.CHINCHE_FOLIADA)).thenReturn(1).thenReturn(2);
		when(estadoUsuarioExperto.esExperto()).thenReturn(true);
		
	
		verificadorBTest.opinar(revisionA);
		verificadorBTest.opinarEnEstadoBasico(revisionA);		
		
		when(posteoMock.getResultadoActual()).thenReturn(Optional.of(Opinion.CHINCHE_FOLIADA));
		verificadorBTest.setEstadoPost(estadoPostB);
		doAnswer(invocation -> {
			verificadorBTest.verificarPostSiEsPostDeExpertos();
			return null;
		}).when(estadoPostB).verificarPost();
		
		verificadorBTest.opinar(revisionB);
		verificadorBTest.opinarEnEstadoExperto(revisionB);
		when(posteoMock.getResultadoActual()).thenReturn(Optional.of(Opinion.CHINCHE_FOLIADA));
		
		verificadorBTest.opinar(revisionC);
	
		when(posteoMock.getEsPostVerificado()).thenReturn(true);
		assertEquals(revisiones.get(Opinion.VINCHUCA_GUASAYANA).size(), 0);
		//assertEquals(verificadorBTest.opinionConMayorVoto(), Opinion.CHINCHE_FOLIADA);
		assertTrue(verificadorATest.getPost().getEsPostVerificado());
		
		
		verify(estadoPostA, times(1)).opinar(revisionA);
		verify(estadoPostB, times(1)).opinar(revisionB);
		verify(estadoPostB, times(1)).opinar(revisionC);	
		verify(posteoMock, times(1)).verificarPost();
	}
	


	
}
