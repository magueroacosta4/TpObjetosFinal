package filtrosDeBusqueda;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import org.junit.jupiter.api.*;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import muestra.PostMuestra;

public class FiltroFechaCreacionTest {
	
	private FiltroFechaCreacion filtroFC;
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
		filtroFC = new FiltroFechaCreacion(LocalDate.of(2023, 05, 25), "=");
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
		when(muestra1.getFechaDeCreacion()).thenReturn(LocalDate.now());
		when(muestra2.getFechaDeCreacion()).thenReturn(LocalDate.of(2023, 05, 25));
		when(muestra3.getFechaDeCreacion()).thenReturn(LocalDate.of(2023, 05, 25));
		when(muestra4.getFechaDeCreacion()).thenReturn(LocalDate.of(2023, 05, 25));
		
		assertEquals(filtroFC.filtrar(listaDePosts1), listaDePosts4);
	}
	
	@Test
	public void filtrarFechaAntesDelDiaTest() {
		when(muestra1.getFechaDeCreacion()).thenReturn(LocalDate.now());
		when(muestra2.getFechaDeCreacion()).thenReturn(LocalDate.of(2022, 05, 25));
		when(muestra3.getFechaDeCreacion()).thenReturn(LocalDate.of(2023, 04, 25));
		when(muestra4.getFechaDeCreacion()).thenReturn(LocalDate.of(2023, 05, 28));
		
		filtroFC.setOperador("<");
		
		assertEquals(filtroFC.filtrar(listaDePosts1), listaDePosts2);
	}
	
	@Test
	public void filtrarFechaDespuesDelDiaTest() {
		when(muestra1.getFechaDeCreacion()).thenReturn(LocalDate.now());
		when(muestra2.getFechaDeCreacion()).thenReturn(LocalDate.of(2023, 05, 25));
		when(muestra3.getFechaDeCreacion()).thenReturn(LocalDate.of(2023, 05, 25));
		when(muestra4.getFechaDeCreacion()).thenReturn(LocalDate.of(2023, 06, 02));

		filtroFC.setOperador(">");
		
		assertEquals(filtroFC.filtrar(listaDePosts1), listaDePosts3);
	}
}
