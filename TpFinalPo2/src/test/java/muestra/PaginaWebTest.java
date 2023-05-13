package muestra;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;

import java.util.ArrayList;
import java.util.HashSet;

import org.junit.Before;
import org.junit.Test;

import Usuario.Usuario;
import organizacion.ZonaDeCobertura;

public class PaginaWebTest {
	private PaginaWeb unaPagina;
	
	@Before
	public void setUp() {
		unaPagina = new PaginaWeb();
	}

	@Test
	public void unaPaginaNuevaNoTieneMuestrasNiZonasNiUsuarios() {
		assertEquals(unaPagina.getZonasDeCobertura(), new ArrayList<ZonaDeCobertura>());
		assertEquals(unaPagina.getMuestras(), new ArrayList<PostMuestra>());
		assertEquals(unaPagina.getUsuarios(), new ArrayList<Usuario>());
	}
	
	@Test
	public void unaPaginaWebPuedeAgregarUsuariosZonas() {
		ZonaDeCobertura unaZonaDeC = mock(ZonaDeCobertura.class);
		unaPagina.agregarZonaDeCobertura(unaZonaDeC);
		assertTrue(unaPagina.getZonasDeCobertura().contains(unaZonaDeC));
		
		Usuario unUsuario = mock(Usuario.class);
		unaPagina.agregarUsuario(unUsuario);
		assertTrue(unaPagina.getUsuarios().contains(unUsuario));
	}
	
	// hay que hacer que las muestras se agregen directamente y no
			// se creen dentro de la pagina
}
