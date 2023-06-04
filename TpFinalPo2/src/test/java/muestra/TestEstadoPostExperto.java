package muestra;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;

import usuario.EstadoUsuario;

public class TestEstadoPostExperto {
	
	Revision revisionMock;
	VerificadorMuestra verifiMock;
	EstadoPostExperto estadoTest;
	private PostMuestra post;
	private EstadoUsuario estadoUsuario;
	
	@Before
	public void setUp() {
		revisionMock = mock(Revision.class);
		verifiMock = mock(VerificadorMuestra.class);
		post = mock(PostMuestra.class);
		estadoUsuario = mock(EstadoUsuario.class);
		
		
		estadoTest = new EstadoPostExperto(post);
	}
	
	@Test
	public void seInstanciaUnEstadoPostExpertoYSeRevisaSiSeColocaronTodosSusColaboradoresInternos() {
		
		PostMuestra resultadoEsperado = post;
		PostMuestra resultadoDado = estadoTest.getPost();
		
		assertEquals(resultadoDado, resultadoEsperado);
		
	}
	
	@Test
	public void seUtilizaUnEstadoPostExpertoParaOpinar_UnExpertoLoOpina() {
		doNothing().when(verifiMock).actualizarEstadoDePost(revisionMock);
		when(revisionMock.getEstadoDelUsuarioActual()).thenReturn(estadoUsuario);
		when(estadoUsuario.esExperto()).thenReturn(true);
		estadoTest.opinar(revisionMock, verifiMock);
		
		verify(verifiMock, times(1)).actualizarEstadoDePost(revisionMock);
		verify(revisionMock, times(1)).getEstadoDelUsuarioActual();
	}
	
	@Test
	public void seUtilizaUnEstadoPostExpertoParaOpinar_UnBasicoLoOpina() {
		doNothing().when(verifiMock).actualizarEstadoDePost(revisionMock);
		when(revisionMock.getEstadoDelUsuarioActual()).thenReturn(estadoUsuario);
		when(estadoUsuario.esExperto()).thenReturn(false);
		estadoTest.opinar(revisionMock, verifiMock);
		
		verify(verifiMock, never()).actualizarEstadoDePost(revisionMock);
		verify(revisionMock, times(1)).getEstadoDelUsuarioActual();
	}
	
	@Test
	public void unEstadoDePostExpertoVerificaElPost() {
		
		estadoTest.verificarPost();
		
		verify(post, times(1)).verificarPost();
		
	}
	
	@Test
	public void sePreguntaAUnEstadoDePostSiEsVerificado() {
		
		assertFalse(estadoTest.esVerificado());
	}
	
}
