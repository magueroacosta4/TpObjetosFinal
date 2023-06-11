package filtrosDeBusqueda;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

import muestra.PostMuestra;


public class ConectorOrTest {
	private ConectorOr conectorO;
	private FiltroDeBusqueda filtroFec;
	private FiltroDeBusqueda filtroVal;
	private PostMuestra muestra1;
	private PostMuestra muestra2;
	private PostMuestra muestra3;
	private List<PostMuestra> listaDePosts1;
	private List<PostMuestra> listaDePosts2;
	private List<PostMuestra> listaDePosts3;
	
	@Before
	public void setUp() {
		filtroVal = mock(FiltroDeBusqueda.class);
		filtroFec = mock(FiltroDeBusqueda.class);
		muestra1 = mock(PostMuestra.class);
		muestra2 = mock(PostMuestra.class);
		muestra3 = mock(PostMuestra.class);
		listaDePosts1 = Arrays.asList(muestra1, muestra2, muestra3);
		listaDePosts2 = Arrays.asList(muestra2, muestra3);
		listaDePosts3 = Arrays.asList(muestra1);
		conectorO = new ConectorOr(filtroVal, filtroFec);
	}
	
	@Test
	public void filtrarConAndTest() {
		when(filtroVal.filtrar(listaDePosts1)).thenReturn(listaDePosts3);
		when(filtroFec.filtrar(listaDePosts1)).thenReturn(listaDePosts2);
	
		assertTrue(conectorO.filtrar(listaDePosts1).containsAll(listaDePosts1));
		verify(filtroVal).filtrar(listaDePosts1);
		verify(filtroFec).filtrar(listaDePosts1);
	}

}

