package muestra;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;

public class TestEstadoPostVerificado {
	Revision revisionMock;
	VerificadorMuestra verifiMock;
	EstadoPostVerificado estadoTest;
	private PostMuestra post;
	
	@Before
	public void setUp() {
		revisionMock = mock(Revision.class);
		verifiMock = mock(VerificadorMuestra.class);
		post = mock(PostMuestra.class);
		
		
		estadoTest = new EstadoPostVerificado(post);
	}
	
	@Test
	public void seInstanciaUnEstadoPostExpertoYSeRevisaSiSeColocaronTodosSusColaboradoresInternos() {
		
		PostMuestra resultadoEsperado = post;
		PostMuestra resultadoDado = estadoTest.getPost();
		
		assertEquals(resultadoDado, resultadoEsperado);
		
	}
	
	@Test
	public void seUtilizaUnEstadoPostVerificadoParaOpinar() {
		estadoTest.opinar(revisionMock, verifiMock);
		
		verify(verifiMock, never()).actualizarEstadoDePost(revisionMock);
	}
	
	@Test
	public void sePreguntaAUnEstadoDePostSiEsVerificado() {
		
		assertTrue(estadoTest.esVerificado());
	}
	
	@Test
	public void unEstadoDePostExpertoVerificaElPost() {
		
		estadoTest.verificarPost();
		
		verify(post, never()).verificarPost();
		
	}
}
