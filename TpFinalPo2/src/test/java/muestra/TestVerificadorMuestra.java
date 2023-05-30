package muestra;

import static org.junit.Assert.assertEquals;
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
	private HashMap<Opinion, Set<Revision>> opinionesInicial;
	private Revision revisionA;
	private Revision revisionB;
	private Revision revisionC;
	private EstadoPostBasico estadoPostA;
	private EstadoPostExperto estadoPostB;
	private EstadoBasico estadoUsuarioBasico;
	private EstadoExperto estadoUsuarioExperto;
	private EstadoPostVerificado estadoPostC;
	
	@Before
	public void setUp() {
		posteoMock = mock(PostMuestra.class);
		estadoPostA    = mock(EstadoPostBasico.class);
		estadoPostB    = mock(EstadoPostExperto.class);
		estadoPostC	   = mock(EstadoPostVerificado.class);
		estadoUsuarioBasico = mock(EstadoBasico.class);
		estadoUsuarioExperto = mock(EstadoExperto.class);
		opinionesInicial = new HashMap<Opinion, Set<Revision>>();
		Arrays.stream(Opinion.values()).forEach(o -> opinionesInicial.put(o, new HashSet<Revision>()));
		revisionA = mock(Revision.class);
		revisionB = mock(Revision.class);
		revisionC = mock(Revision.class);
		
		when(posteoMock.getOpiniones()).thenReturn(opinionesInicial);
		when(revisionA.getEstadoDelUsuarioActual()).thenReturn(estadoUsuarioBasico);
		when(revisionB.getEstadoDelUsuarioActual()).thenReturn(estadoUsuarioBasico);
		when(revisionC.getEstadoDelUsuarioActual()).thenReturn(estadoUsuarioBasico);
		when(posteoMock.getResultadoActual()).thenReturn(Optional.empty());
		when(posteoMock.getOpiniones()).thenReturn(opinionesInicial);
		when(posteoMock.getEstadoDePost()).thenReturn(estadoPostA);
		when(estadoPostA.esVerificado()).thenReturn(false);
		when(estadoPostB.esVerificado()).thenReturn(false);
		when(estadoPostC.esVerificado()).thenReturn(true);
		
		doNothing().when(estadoPostA).verificarPost();

		
		doNothing().when(posteoMock).verificarPost();
		
		verificadorBTest = new VerificadorMuestra(posteoMock);
		verificadorATest = new VerificadorMuestra(posteoMock);
		
		doAnswer(invocacion -> {
			opinionesInicial.get(revisionA.getOpinion()).add(revisionA);
			return null;
		}).when(posteoMock).agregarRevision(revisionA);		
		
		doAnswer(invocacion -> {
			opinionesInicial.get(revisionB.getOpinion()).add(revisionB);
			return null;
		}).when(posteoMock).agregarRevision(revisionB);	

		doAnswer(invocacion -> {
			opinionesInicial.get(revisionC.getOpinion()).add(revisionC);
			return null;
		}).when(posteoMock).agregarRevision(revisionC);	
		
		

	}

	@Test 
	public void seInstanciaUnVerificadorConUnPostoDentro() {
		
		assertEquals(verificadorATest.getPost(), posteoMock);
		
	}
	
	@Test
	public void cuandoSeLlamaA_colocarClavesEnHashmap_ColocaComoClavesTodasLasOpinionesYSeLesDaUnSetDeRevisionComoValor() {
		
		
		Stream<Opinion> resultadoEsperadoA = Arrays.stream(Opinion.values());
		Set<Revision> resultadoEsperadoB = new HashSet<Revision>();
		
		
		assertTrue(opinionesInicial.keySet().containsAll(resultadoEsperadoA.toList()));
		assertTrue(opinionesInicial.values().stream().allMatch(s->s.equals(resultadoEsperadoB)));
		
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
		
		verificadorATest.actualizarEstadoDePost(revisionA);
		verificadorATest.actualizarEstadoDePost(revisionB);
		verificadorATest.actualizarEstadoDePost(revisionC);
		
		Opinion resultadoDado = verificadorBTest.opinionConMayorVoto();
		Opinion resultadoEsperado = Opinion.IMAGEN_POCO_CLARA;
		
		assertEquals(resultadoDado, resultadoEsperado);
		
		verify(revisionA, times(2)).getOpinion();
		verify(revisionB, times(2)).getOpinion();
		verify(revisionC, times(2)).getOpinion();
	}
	
	
	@Test
	public void seSubeUnaMuestraYLaOpinanDosPersonasConDiferentesOpiniones() {
		when(revisionA.getOpinion()).thenReturn(Opinion.PHTIA_CHINCHE);
		when(revisionB.getOpinion()).thenReturn(Opinion.IMAGEN_POCO_CLARA);
		
		when(revisionA.getEstadoDelUsuarioActual()).thenReturn(estadoUsuarioExperto);
		when(revisionB.getEstadoDelUsuarioActual()).thenReturn(estadoUsuarioExperto);
		when(posteoMock.sizeOpinionResultadoActual()).thenReturn(0).thenReturn(1);
		when(posteoMock.sizeOpinion(Opinion.PHTIA_CHINCHE)).thenReturn(1);
		when(posteoMock.sizeOpinion(Opinion.IMAGEN_POCO_CLARA)).thenReturn(1);
		
		
		verificadorATest.actualizarEstadoDePost(revisionA);
		
		when(posteoMock.getResultadoActual()).thenReturn(Optional.of(Opinion.PHTIA_CHINCHE)).thenReturn(Optional.empty());
		verificadorATest.actualizarEstadoDePost(revisionB);
		
		assertTrue(opinionesInicial.get(Opinion.PHTIA_CHINCHE).contains(revisionA));
		assertTrue(opinionesInicial.get(Opinion.IMAGEN_POCO_CLARA).contains(revisionB));
		assertEquals(posteoMock.getResultadoActual(), Optional.empty());
		
		verify(revisionA, times(2)).getOpinion();
		verify(revisionB, times(2)).getOpinion();
		
	}
	
	@Test
	public void seSubeUnaMuestraYLaOpinanDosPersonasConLaMismaOpiniones() {
		when(revisionA.getOpinion()).thenReturn(Opinion.PHTIA_CHINCHE);
		when(revisionB.getOpinion()).thenReturn(Opinion.PHTIA_CHINCHE);
		
		when(revisionA.getEstadoDelUsuarioActual()).thenReturn(estadoUsuarioExperto);
		when(revisionB.getEstadoDelUsuarioActual()).thenReturn(estadoUsuarioExperto);
		when(posteoMock.sizeOpinionResultadoActual()).thenReturn(0).thenReturn(1);
		when(posteoMock.sizeOpinion(Opinion.PHTIA_CHINCHE)).thenReturn(1);

		
		
		verificadorATest.actualizarEstadoDePost(revisionA);		
		when(posteoMock.getResultadoActual()).thenReturn(Optional.of(Opinion.PHTIA_CHINCHE));
		verificadorATest.actualizarEstadoDePost(revisionB);
		

		
		assertTrue(opinionesInicial.get(Opinion.PHTIA_CHINCHE).contains(revisionA));
		assertTrue(opinionesInicial.get(Opinion.PHTIA_CHINCHE).contains(revisionB));
		assertEquals(posteoMock.getResultadoActual().get(), Opinion.PHTIA_CHINCHE);
		
		verify(revisionA, times(2)).getOpinion();
		verify(revisionB, times(2)).getOpinion();
		
	}






	
}
