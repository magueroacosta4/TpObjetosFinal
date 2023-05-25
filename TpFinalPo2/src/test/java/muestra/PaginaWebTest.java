package muestra;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;


import java.util.ArrayList;
import org.junit.Before;
import org.junit.Test;

import organizacion.ZonaDeCobertura;
import usuario.Usuario;

public class PaginaWebTest {
	private PaginaWeb unaPagina;
	private ZonaDeCobertura unaZonaDeC;
	private Revision unaRevision;
	private PostMuestra unPostMuestra;
	
	
	@Before
	public void setUp() {
		unaPagina = new PaginaWeb();
		unaZonaDeC = mock(ZonaDeCobertura.class);
		unaRevision = mock(Revision.class);
		unPostMuestra = mock(PostMuestra.class);
	}
	
	
	@Test
	public void unaPaginaNuevaNoTieneMuestrasNiZonasNiUsuarios() {
		assertEquals(unaPagina.getZonasDeCobertura(), new ArrayList<ZonaDeCobertura>());
		assertEquals(unaPagina.getMuestras(), new ArrayList<PostMuestra>());
		assertEquals(unaPagina.getUsuarios(), new ArrayList<Usuario>());
	}
	
	@Test
	public void unaPaginaWebPuedeAgregarUsuariosYZonas() {

		unaPagina.agregarZonaDeCobertura(unaZonaDeC);
		assertTrue(unaPagina.getZonasDeCobertura().contains(unaZonaDeC));
		
		Usuario unUsuario = mock(Usuario.class);
		unaPagina.agregarUsuario(unUsuario);
		assertTrue(unaPagina.getUsuarios().contains(unUsuario));
	}
	
	@Test
	public void unaPaginaWebCreaUnPostMuestra() {
		
		assertTrue(unaPagina.getMuestras().isEmpty());
		
		unaPagina.crearPostMuestra(unaRevision, unPostMuestra);
		
		assertTrue(unaPagina.getMuestras().contains(unPostMuestra));
	}
	
	@Test
	public void unaPaginaWebCreaUnPostMuestraUtilizandoElMetodoQueInstanciaUnPostMuestra() {
		
		assertTrue(unaPagina.getMuestras().isEmpty());
		
		Ubicacion ubicacion = new Ubicacion(3, 4);		
		unaPagina.crearPostMuestra(unaRevision, ubicacion);
		
		assertEquals(unaPagina.getMuestras().size(), 1);
		
	}

	@Test
	public void unaPaginaWebCreaUnPostMuestra_YAvisaATodasLasZonasQueLaContengan() throws Exception {

		unaPagina.agregarZonaDeCobertura(unaZonaDeC);
		
		doNothing().when(unaZonaDeC).notificarCargaDeMuestra(unPostMuestra);
		when(unaZonaDeC.tieneLaMuestra(unPostMuestra)).thenReturn(true);
		
		unaPagina.crearPostMuestra(unaRevision, unPostMuestra);
		
		assertTrue(!unaPagina.getMuestras().isEmpty());
		assertTrue(unaPagina.getMuestras().contains(unPostMuestra));
		verify(unaZonaDeC, times(1)).notificarCargaDeMuestra(unPostMuestra);
	}
	
	@Test
	public void unUsuarioOpinaYSeVerificaElPost_YAvisaATodasLasZonasQueLaContengan() throws Exception {

		unaPagina.agregarZonaDeCobertura(unaZonaDeC);
		
		doNothing().when(unaZonaDeC).notificarValidacionDeMuestra(unPostMuestra);
		when(unaZonaDeC.tieneLaMuestra(unPostMuestra)).thenReturn(true);
		when(unPostMuestra.getEsPostVerificado()).thenReturn(true);
		
		unaPagina.opinarPostMuestra(unaRevision, unPostMuestra);
		
		verify(unaZonaDeC, times(1)).notificarValidacionDeMuestra(unPostMuestra);
	}
	
	@Test
	public void unUsuarioOpina_AlNoEstarVerificadoElPostLaPaginaNoAvisaATodasLasZonasQueLaContengan() throws Exception {

		unaPagina.agregarZonaDeCobertura(unaZonaDeC);
		
		doNothing().when(unaZonaDeC).notificarValidacionDeMuestra(unPostMuestra);
		when(unaZonaDeC.tieneLaMuestra(unPostMuestra)).thenReturn(true);
		when(unPostMuestra.getEsPostVerificado()).thenReturn(false);
		
		unaPagina.opinarPostMuestra(unaRevision, unPostMuestra);
		
		verify(unaZonaDeC, times(0)).notificarValidacionDeMuestra(unPostMuestra);
	}
}