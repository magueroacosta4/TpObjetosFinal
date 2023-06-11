package filtrosDeBusqueda;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;

import muestra.PostMuestra;
import muestra.Opinion;

public class FiltroTipoInsectoTest {
	private FiltroTipoInsecto filtroTI;
	private Optional<Opinion> cFoliada;
	private Optional<Opinion> pChinche;
	private Optional<Opinion> vGuasayana;
	private Optional<Opinion> vInfestants;
	private Optional<Opinion> vSordida;
	private Optional<Opinion> imPC;
	private Optional<Opinion> ninguna;
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
		cFoliada 	= Optional.of(Opinion.CHINCHE_FOLIADA);
		pChinche 	= Optional.of(Opinion.PHTIA_CHINCHE);
		vGuasayana 	= Optional.of(Opinion.VINCHUCA_GUASAYANA);
		vInfestants = Optional.of(Opinion.VINCHUCA_INFESTANTS);
		vSordida 	= Optional.of(Opinion.VINCHUCA_SORDIDA);
		imPC 		= Optional.of(Opinion.IMAGEN_POCO_CLARA);
		ninguna 	= Optional.of(Opinion.NINGUNA);
		filtroTI = new FiltroTipoInsecto(vInfestants.get());
		muestra1 = mock(PostMuestra.class);
		muestra2 = mock(PostMuestra.class);
		muestra3 = mock(PostMuestra.class);
		muestra4 = mock(PostMuestra.class);
		listaDePosts1 = Arrays.asList(muestra1, muestra2, muestra3, muestra4);
		listaDePosts2 = Arrays.asList(muestra2, muestra3);
		listaDePosts3 = Arrays.asList(muestra1);
		listaDePosts4 = Arrays.asList(muestra4);
	}
	
	@Test
	public void filtrarPorTipoVinchucaInfestantsTest() {
		when(muestra1.getResultadoActual()).thenReturn(vInfestants);
		when(muestra2.getResultadoActual()).thenReturn(vSordida);
		when(muestra3.getResultadoActual()).thenReturn(pChinche);
		when(muestra4.getResultadoActual()).thenReturn(ninguna);

		assertEquals(filtroTI.filtrar(listaDePosts1), listaDePosts3);
	}
}
