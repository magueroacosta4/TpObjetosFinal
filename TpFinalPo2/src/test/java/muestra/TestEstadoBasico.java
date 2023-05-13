package muestra;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;


import org.junit.Before;
import org.junit.Test;

public class TestEstadoBasico {

	Revision revisionMock;
	VerificadorMuestra verifiMock;
	EstadoPostBasico estadoTest;
	
	@Before
	public void setUp() {
		revisionMock = mock(Revision.class);
		verifiMock = mock(VerificadorMuestra.class);
		
		doNothing().when(verifiMock).opinarEnEstadoBasico(revisionMock);
		
		estadoTest = new EstadoPostBasico(verifiMock);
	}
	
	@Test
	public void seInstanciaUnEstadoPostBasicoYSeRevisaSiSeColocaronTodosSusColaboradoresInternos() {
		
		VerificadorMuestra resultadoEsperado = verifiMock;
		VerificadorMuestra resultadoDado = estadoTest.getVerificador();
		
		assertEquals(resultadoDado, resultadoEsperado);
		
	}
	
	@Test
	public void seUtilizaUnEstadoPostBasicoParaOpinar() {
		estadoTest.opinar(revisionMock);
		
		verify(verifiMock, times(1)).opinarEnEstadoBasico(revisionMock);
	}
	
	@Test
	public void unEstadoDePostBasicoVerificaElPost_NoLoHacePorqueNoPuede() {
		
		estadoTest.verificarPost();
		
		verify(verifiMock, times(0)).verificarPost();
		
	}
}
