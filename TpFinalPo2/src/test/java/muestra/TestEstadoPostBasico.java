package muestra;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.mockito.Mockito.*;


import org.junit.Before;
import org.junit.Test;

import usuario.EstadoUsuario;

public class TestEstadoPostBasico {

	Revision revisionMock;
	VerificadorMuestra verifiMock;
	EstadoPostBasico estadoTest;
	private PostMuestra post;
	private EstadoUsuario estadoUsuario;
	
	@Before
	public void setUp() {
		revisionMock = mock(Revision.class);
		verifiMock = mock(VerificadorMuestra.class);
		post = mock(PostMuestra.class);
		estadoUsuario = mock(EstadoUsuario.class);
		
		estadoTest = new EstadoPostBasico(post);
	}
	
	@Test
	public void seInstanciaUnEstadoPostBasicoYSeRevisaSiSeColocaronTodosSusColaboradoresInternos() {
		
		PostMuestra resultadoEsperado = post;
		PostMuestra resultadoDado = estadoTest.getPost();
		
		assertEquals(resultadoDado, resultadoEsperado);
		
	}
	
	@Test
	public void seUtilizaUnEstadoPostBasicoParaOpinar_ConUnUsuarioBasico() {
		doNothing().when(verifiMock).actualizarEstadoDePost(revisionMock);
		when(revisionMock.getEstadoDelUsuarioActual()).thenReturn(estadoUsuario);
		when(estadoUsuario.esExperto()).thenReturn(false);
		estadoTest.opinar(revisionMock, verifiMock);
		
		verify(verifiMock, times(1)).actualizarEstadoDePost(revisionMock);
		verify(revisionMock, times(1)).getEstadoDelUsuarioActual();
	}
	
	@Test
	public void seUtilizaUnEstadoPostBasicoParaOpinar_ConUnUsuarioExperto() {
		doNothing().when(verifiMock).actualizarEstadoDePost(revisionMock);
		when(revisionMock.getEstadoDelUsuarioActual()).thenReturn(estadoUsuario);
		when(estadoUsuario.esExperto()).thenReturn(true);
		estadoTest.opinar(revisionMock, verifiMock);
		
		verify(verifiMock, times(1)).actualizarEstadoDePost(revisionMock);
		verify(revisionMock, times(1)).getEstadoDelUsuarioActual();
	}
	
	@Test
	public void sePreguntaAUnEstadoDePostSiEsVerificado() {
		
		assertFalse(estadoTest.esVerificado());
	}
	
	@Test
	public void unEstadoDePostBasicoVerificaElPost() {
		
		estadoTest.verificarPost();
		
		verify(post, never()).verificarPost();
		
	}
}
