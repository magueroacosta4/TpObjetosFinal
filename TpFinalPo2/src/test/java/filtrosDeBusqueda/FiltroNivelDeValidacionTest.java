package filtrosDeBusqueda;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import muestra.PostMuestra;
import muestra.EstadoPostBasico;
import muestra.EstadoPostExperto;
import muestra.EstadoPostVerificado;

public class FiltroNivelDeValidacionTest {
	private FiltroNivelDeValidacion filtroVal;
	private EstadoPostBasico estadoPB;
	private EstadoPostExperto estadoPE;
	private EstadoPostVerificado estadoPV;
	private PostMuestra muestra1;
	private PostMuestra muestra2;
	private PostMuestra muestra3;
	private PostMuestra muestra4;
	private List<PostMuestra> listaDePosts1;
	private List<PostMuestra> listaDePosts2;
	private List<PostMuestra> listaDePosts3;
	private List<PostMuestra> listaDePosts4;
	
	@Before
	public void setUp() {
		estadoPB = mock(EstadoPostBasico.class);
		estadoPE = mock(EstadoPostExperto.class);
		estadoPV = mock(EstadoPostVerificado.class);
		filtroVal = new FiltroNivelDeValidacion(estadoPB);
		muestra1 = mock(PostMuestra.class);
		muestra2 = mock(PostMuestra.class);
		muestra3 = mock(PostMuestra.class);
		muestra4 = mock(PostMuestra.class);
		listaDePosts1 = Arrays.asList(muestra1, muestra2, muestra3, muestra4);
		listaDePosts2 = Arrays.asList(muestra2, muestra3);
		listaDePosts3 = Arrays.asList(muestra1, muestra4);
		listaDePosts4 = Arrays.asList(muestra2, muestra3, muestra4);
	}
	
	@Test
	public void filtrarPorValTest() {
		when(muestra1.getEstadoDePost()).thenReturn(estadoPB);
		when(muestra2.getEstadoDePost()).thenReturn(estadoPV);
		when(muestra3.getEstadoDePost()).thenReturn(estadoPE);
		when(muestra4.getEstadoDePost()).thenReturn(estadoPB);
		
		assertEquals(filtroVal.filtrar(listaDePosts1), listaDePosts3);
	}
	
}
