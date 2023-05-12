package organizacion;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import muestra.PaginaWeb;
import muestra.Ubicacion;

public class ZonaDeCoberturaTest {
	
	ZonaDeCobertura unaZdC, otraZonaDeC, zonaA, zonaB, zonaD, zonaC;
	private Ubicacion unaUbicacion;
	private PaginaWeb unaPaginaWeb;
	
	@Before
	public void setUp() {
		unaUbicacion = mock(Ubicacion.class);
		unaPaginaWeb = mock(PaginaWeb.class);
		unaZdC = new ZonaDeCobertura("Zona 0", unaUbicacion, 10, unaPaginaWeb);
	}
	
	@Test
	public void unaZonaDeCoberturaTieneNombreEpicentroRadioEnKilometrosYConocenLaPaginaWeb() {
		assertEquals("Zona 0", unaZdC.getNombre());
		assertEquals(unaUbicacion, unaZdC.getEpicentro());
		assertEquals(10, unaZdC.getRadioEnKM());
		assertEquals(unaPaginaWeb, unaZdC.getPaginaWeb());
	}
	
	@Test
	public void unaZonaDeCoberturaSabeSiSeSolapaConOtraZona() {
		/* se solapan si las distancias entre las zonas es
		 * menor o igual a la suma de sus radios
		 * */
		
		Ubicacion otraUbicacion = mock(Ubicacion.class);
		otraZonaDeC = mock(ZonaDeCobertura.class);
		when(otraZonaDeC.getEpicentro()).thenReturn(otraUbicacion);
		when(otraZonaDeC.getRadioEnKM()).thenReturn(100);
		when(unaUbicacion.distanciaA(otraUbicacion)).thenReturn(100);
		
		assertTrue(unaZdC.seSolapaCon(otraZonaDeC));
		verify(otraZonaDeC, times(1)).getEpicentro();
		verify(otraZonaDeC, times(1)).getRadioEnKM();
		verify(unaUbicacion, times(1)).distanciaA(otraUbicacion);
		
		// si la distancia es mayor a la suma de sus radios entonces no se solapan
		
		when(unaUbicacion.distanciaA(otraUbicacion)).thenReturn(120);
		
		assertFalse(unaZdC.seSolapaCon(otraZonaDeC));
		verify(otraZonaDeC, times(2)).getEpicentro();
		verify(otraZonaDeC, times(2)).getRadioEnKM();
		verify(unaUbicacion, times(2)).distanciaA(otraUbicacion);
	}
	
	@Test
	public void unaZonaNoSeSolapaConSigoMisma() {
		assertFalse(unaZdC.seSolapaCon(unaZdC));
	}
	
	@Test
	public void unaZonaSabeConCualesSeSolapa() {
		zonaA = mock(ZonaDeCobertura.class);
		when(zonaA.seSolapaCon(unaZdC)).thenReturn(true);
		zonaB = mock(ZonaDeCobertura.class);
		when(zonaB.seSolapaCon(unaZdC)).thenReturn(false);
		zonaC = mock(ZonaDeCobertura.class);
		when(zonaC.seSolapaCon(unaZdC)).thenReturn(true);
		zonaD = mock(ZonaDeCobertura.class);
		when(zonaD.seSolapaCon(unaZdC)).thenReturn(false);
		List<ZonaDeCobertura> zonas = Arrays.asList(unaZdC, zonaA, zonaB, zonaC, zonaD);
		
		when(unaPaginaWeb.getZonasDeCobertura()).thenReturn(zonas);
		
		List <ZonaDeCobertura> zonasSolapadas = Arrays.asList(zonaA, zonaC);
		assertEquals(unaZdC.getZonasSolapadas(), zonasSolapadas);
		
		verify(zonaA).seSolapaCon(unaZdC);
		verify(zonaB).seSolapaCon(unaZdC);
		verify(zonaC).seSolapaCon(unaZdC);
		verify(zonaD).seSolapaCon(unaZdC);
		verify(unaPaginaWeb).getZonasDeCobertura();
	}
	
}
