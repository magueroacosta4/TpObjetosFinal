package filtrosDeBusqueda;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import muestra.PostMuestra;

public class FiltroFechaUltimaVotacionTest {
	
	private FiltroFechaUltimaVotacion filtroFUV;
	private PostMuestra muestra1;
	private PostMuestra muestra2;
	private PostMuestra muestra3;
	private PostMuestra muestra4;
	private List<PostMuestra> listaDePosts1;
	private List<PostMuestra> listaDePosts2;
	private List<PostMuestra> listaDePosts3;
	private List<PostMuestra> listaDePosts4;
	
	@BeforeEach
	public void setUp() {
		filtroFUV = new FiltroFechaUltimaVotacion(LocalDate.of(2023, 05, 25), "=");
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
	public void filtrarFechaDelDiaTest() {
		when(muestra1.getFechaUltimaRevision()).thenReturn(LocalDate.now());
		when(muestra2.getFechaUltimaRevision()).thenReturn(LocalDate.of(2023, 05, 25));
		when(muestra3.getFechaUltimaRevision()).thenReturn(LocalDate.of(2023, 05, 25));
		when(muestra4.getFechaUltimaRevision()).thenReturn(LocalDate.of(2023, 05, 25));
		
		assertEquals(filtroFUV.filtrar(listaDePosts1), listaDePosts4);
	}
	
	@Test
	public void filtrarFechaAntesDelDiaTest() {
		when(muestra1.getFechaUltimaRevision()).thenReturn(LocalDate.now());
		when(muestra2.getFechaUltimaRevision()).thenReturn(LocalDate.of(2022, 05, 25));
		when(muestra3.getFechaUltimaRevision()).thenReturn(LocalDate.of(2023, 04, 25));
		when(muestra4.getFechaUltimaRevision()).thenReturn(LocalDate.of(2023, 05, 28));
		
		filtroFUV.setOperador("<");
		
		assertEquals(filtroFUV.filtrar(listaDePosts1), listaDePosts2);
	}
	
	@Test
	public void filtrarFechaDespuesDelDiaTest() {
		when(muestra1.getFechaUltimaRevision()).thenReturn(LocalDate.now());
		when(muestra2.getFechaUltimaRevision()).thenReturn(LocalDate.of(2023, 05, 25));
		when(muestra3.getFechaUltimaRevision()).thenReturn(LocalDate.of(2023, 05, 25));
		when(muestra4.getFechaUltimaRevision()).thenReturn(LocalDate.of(2023, 06, 02));

		filtroFUV.setOperador(">");
		
		assertEquals(filtroFUV.filtrar(listaDePosts1), listaDePosts3);
	}
}
