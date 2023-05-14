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
	
	@Before
	public void setUp() {
		revisionMock = mock(Revision.class);
		verifiMock = mock(VerificadorMuestra.class);
		
		doNothing().when(verifiMock).opinarEnEstadoExperto(revisionMock);
		
		estadoTest = new EstadoPostExperto(verifiMock);
	}
	
	@Test
	public void seInstanciaUnEstadoPostExpertoYSeRevisaSiSeColocaronTodosSusColaboradoresInternos() {
		
		VerificadorMuestra resultadoEsperado = verifiMock;
		VerificadorMuestra resultadoDado = estadoTest.getVerificador();
		
		assertEquals(resultadoDado, resultadoEsperado);
		
	}
	
	@Test
	public void seUtilizaUnEstadoPostBasicoParaOpinar() {
		estadoTest.opinar(revisionMock);
		
		verify(verifiMock, times(1)).opinarEnEstadoExperto(revisionMock);
	}
	
	@Test
	public void unEstadoDePostBasicoVerificaElPost() {
		
		estadoTest.verificarPost();
		
		verify(verifiMock, times(1)).verificarPost();
		
	}
	
}
