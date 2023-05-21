package muestra;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import org.junit.Before;
import org.junit.Test;

import organizacion.ZonaDeCobertura;
import usuario.Usuario;

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
	
	@Test
	public void unaPaginaWebCreaUnPostMuestra() throws Exception {
		Revision unaRevision = mock(Revision.class);
		PostMuestra unPostMuestra = mock(PostMuestra.class);
		
		assertTrue(unaPagina.getMuestras().isEmpty());
		
		unaPagina.crearPostMuestra(unaRevision, unPostMuestra);
		
		assertTrue(unaPagina.getMuestras().contains(unPostMuestra));
	}
}
