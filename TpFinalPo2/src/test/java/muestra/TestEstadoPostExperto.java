package muestra;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;

public class TestEstadoPostExperto {
	
	Revision revisionMock;
	VerificadorMuestra verifiMock;
	EstadoPostExperto estadoTest;
	private PostMuestra post;
	
	@Before
	public void setUp() {
		revisionMock = mock(Revision.class);
		verifiMock = mock(VerificadorMuestra.class);
		post = mock(PostMuestra.class);
		
		doNothing().when(verifiMock).opinarEnEstadoExperto(revisionMock);
		
		estadoTest = new EstadoPostExperto(post);
	}
	
	@Test
	public void seInstanciaUnEstadoPostExpertoYSeRevisaSiSeColocaronTodosSusColaboradoresInternos() {
		
		PostMuestra resultadoEsperado = post;
		PostMuestra resultadoDado = estadoTest.getPost();
		
		assertEquals(resultadoDado, resultadoEsperado);
		
	}
	
	@Test
	public void seUtilizaUnEstadoPostExpertoParaOpinar() {
		estadoTest.opinar(revisionMock, verifiMock);
		
		verify(verifiMock, times(1)).opinarEnEstadoExperto(revisionMock);
	}
	
	@Test
	public void unEstadoDePostExpertoVerificaElPost() {
		
		estadoTest.verificarPost();
		
		verify(post, times(1)).verificarPost();
		
	}
	
}
