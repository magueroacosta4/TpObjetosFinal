package organizacion;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import muestra.PaginaWeb;
import muestra.PostMuestra;
import muestra.Ubicacion;

public class ZonaDeCoberturaTest {
	
	ZonaDeCobertura unaZdC, otraZonaDeC, zonaA, zonaB, zonaD, zonaC;
	private Ubicacion unaUbicacion;
	private PaginaWeb unaPaginaWeb;
	private PostMuestra unPostM, pm1, pm2, pm3, pm4;
	private List<PostMuestra> muestrasWeb;
	private Ubicacion ubipm1, ubipm2, ubipm3, ubipm4;
	private ObserverZona unObservadorZ, unObservadorZ2, unObservadorZ3, unObservadorZ4;
	
	@Before
	public void setUp() {
		unaUbicacion = mock(Ubicacion.class);
		unaPaginaWeb = mock(PaginaWeb.class);
		
		pm1 = mock(PostMuestra.class);
		ubipm1 = mock(Ubicacion.class);
		when(pm1.getUbicacion()).thenReturn(ubipm1);
		
		pm2 = mock(PostMuestra.class);
		ubipm2 = mock(Ubicacion.class);
		when(pm2.getUbicacion()).thenReturn(ubipm2);
		
		pm3 = mock(PostMuestra.class);
		ubipm3 = mock(Ubicacion.class);
		when(pm3.getUbicacion()).thenReturn(ubipm3);
		
		pm4 = mock(PostMuestra.class);
		ubipm4 = mock(Ubicacion.class);
		when(pm4.getUbicacion()).thenReturn(ubipm4);
		
		muestrasWeb = Arrays.asList(pm1, pm2, pm3, pm4);
		unaZdC = new ZonaDeCobertura("Zona 0", unaUbicacion, 10, unaPaginaWeb);
		unObservadorZ = mock(ObserverZona.class);
		unObservadorZ2 = mock(ObserverZona.class);
		unObservadorZ3 = mock(ObserverZona.class);
		unObservadorZ4 = mock(ObserverZona.class);
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
		when(unaUbicacion.distanciaA(otraUbicacion)).thenReturn(100f);
		
		assertTrue(unaZdC.seSolapaCon(otraZonaDeC));
		verify(otraZonaDeC, times(1)).getEpicentro();
		verify(otraZonaDeC, times(1)).getRadioEnKM();
		verify(unaUbicacion, times(1)).distanciaA(otraUbicacion);
		
		// si la distancia es mayor a la suma de sus radios entonces no se solapan
		
		when(unaUbicacion.distanciaA(otraUbicacion)).thenReturn(120f);
		
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

	@Test
	public void unaZonaTieneUnaMuestraEnSuAreaSiLaDistanciaAEstaDesdeElEpicentroEsMenorOIgualAlRadio() {
		Ubicacion ubiPost = mock(Ubicacion.class);
		when(unaUbicacion.distanciaA(ubiPost)).thenReturn(10f);
		unPostM = mock(PostMuestra.class);
		when(unPostM.getUbicacion()).thenReturn(ubiPost);
		
		// en este caso el radio es igual a la distancia a la muestra
		assertTrue(unaZdC.tieneLaMuestra(unPostM));
		
		when(unaUbicacion.distanciaA(ubiPost)).thenReturn(11f);
		// en este caso la distancia a la muestra es mayor al radio
		assertFalse(unaZdC.tieneLaMuestra(unPostM));
	}
	
	@Test
	public void unaZonaSabeCualesSonLasMuestasQueEstanEnSuRadio() {
		when(unaPaginaWeb.getMuestras()).thenReturn(muestrasWeb);
		when(unaUbicacion.distanciaA(ubipm1)).thenReturn(11f);
		when(unaUbicacion.distanciaA(ubipm2)).thenReturn(10f);
		when(unaUbicacion.distanciaA(ubipm3)).thenReturn(6f);
		when(unaUbicacion.distanciaA(ubipm4)).thenReturn(99f);
		
		// en este caso la zona tiene en su rango las muestras pm2 y pm3
		List<PostMuestra> muestrasDeLaZona = Arrays.asList(pm2, pm3);
		assertEquals(unaZdC.getMuestras(), muestrasDeLaZona);
		
		verify(unaUbicacion, times(1)).distanciaA(ubipm1);
		verify(unaUbicacion, times(1)).distanciaA(ubipm2);
		verify(unaUbicacion, times(1)).distanciaA(ubipm3);
		verify(unaUbicacion, times(1)).distanciaA(ubipm4);
		
		verify(pm1, times(1)).getUbicacion();
		verify(pm2, times(1)).getUbicacion();
		verify(pm3, times(1)).getUbicacion();
		verify(pm4, times(1)).getUbicacion();
		verify(unaPaginaWeb, times(1)).getMuestras();
	}
	
	@Test
	public void unaZonaInicialmenteNoTieneNiSubscriptosAValidacionNiACargaDeMuestras() {
		assertTrue(unaZdC.getSubscritosACarga().isEmpty());
		assertTrue(unaZdC.getSubscritosAValidacion().isEmpty());
	}
	
	@Test
	public void unaZonaPuedeSuscribirAUnObservadorAlEventoDeCargaDeMuestras() {
		unaZdC.suscribirACarga(unObservadorZ);
		assertTrue(unaZdC.getSubscritosACarga().contains(unObservadorZ));
	}
	
	@Test
	public void unaZonaPuedeSuscribirAUnObservadorAlEventoDeValidacionDeMuestras() {
		unaZdC.suscribirAValidacion(unObservadorZ);
		assertTrue(unaZdC.getSubscritosAValidacion().contains(unObservadorZ));
	}
	
	@Test
	public void unaZonaPuedeDesuscribirAUnObservadorAlEventoDeCargaDeMuestras() {
		unaZdC.suscribirACarga(unObservadorZ);
		unaZdC.desuscribirDeCarga(unObservadorZ);
		assertFalse(unaZdC.getSubscritosACarga().contains(unObservadorZ));
	}
	
	@Test
	public void unaZonaPuedeDesuscribirAUnObservadorAlEventoDeValidacionDeMuestras() {
		unaZdC.suscribirAValidacion(unObservadorZ);
		unaZdC.desuscribirDeValidacion(unObservadorZ);
		assertFalse(unaZdC.getSubscritosAValidacion().contains(unObservadorZ));
	}
	
	@Test
	public void unaZonaPuedeNotificarDeLaCargaDeUnaMuestraSoloASusSubscriptores() {
		unaZdC.suscribirACarga(unObservadorZ);
		unaZdC.suscribirACarga(unObservadorZ2);
		unaZdC.suscribirACarga(unObservadorZ3);
		unaZdC.notificarCargaDeMuestra(unPostM);
		
		verify(unObservadorZ).actualizarPorCarga(unaZdC, unPostM);
		verify(unObservadorZ2).actualizarPorCarga(unaZdC, unPostM);
		verify(unObservadorZ3).actualizarPorCarga(unaZdC, unPostM);
		verify(unObservadorZ4, never()).actualizarPorCarga(otraZonaDeC, unPostM);
	}
	
	@Test
	public void unaZonaPuedeNotificarDeLaValidacionDeUnaMuestraSoloASusSubscriptores() {
		unaZdC.suscribirAValidacion(unObservadorZ);
		unaZdC.suscribirAValidacion(unObservadorZ2);
		unaZdC.suscribirAValidacion(unObservadorZ3);
		unaZdC.notificarValidacionDeMuestra(unPostM);
		
		verify(unObservadorZ).actualizarPorValidacion(unaZdC, unPostM);
		verify(unObservadorZ2).actualizarPorValidacion(unaZdC, unPostM);
		verify(unObservadorZ3).actualizarPorValidacion(unaZdC, unPostM);
		verify(unObservadorZ4, never()).actualizarPorValidacion(unaZdC, unPostM);
	}
	
	@Test
	public void unaZonaNoNotificaDeLaValidacionDeUnaMuestraASusExsubscriptores() {
		unaZdC.suscribirAValidacion(unObservadorZ);
		
		unaZdC.suscribirAValidacion(unObservadorZ2);
		unaZdC.desuscribirDeValidacion(unObservadorZ2);
		
		unaZdC.suscribirAValidacion(unObservadorZ3);
		unaZdC.desuscribirDeValidacion(unObservadorZ3);
		
		unaZdC.notificarValidacionDeMuestra(unPostM);
		
		verify(unObservadorZ).actualizarPorValidacion(unaZdC, unPostM);
		verify(unObservadorZ2, never()).actualizarPorValidacion(unaZdC, unPostM);
		verify(unObservadorZ3, never()).actualizarPorValidacion(unaZdC, unPostM);
		verify(unObservadorZ4, never()).actualizarPorValidacion(unaZdC, unPostM);
	}
	
	@Test
	public void unaZonaNoNotificaDeLaCargaDeUnaMuestraASusExsubscriptores() {
		unaZdC.suscribirACarga(unObservadorZ);
		
		unaZdC.suscribirACarga(unObservadorZ2);
		unaZdC.desuscribirDeCarga(unObservadorZ2);
		
		unaZdC.suscribirACarga(unObservadorZ3);
		unaZdC.desuscribirDeCarga(unObservadorZ3);
		
		unaZdC.notificarCargaDeMuestra(unPostM);
		
		verify(unObservadorZ).actualizarPorCarga(unaZdC, unPostM);
		verify(unObservadorZ2, never()).actualizarPorCarga(unaZdC, unPostM);
		verify(unObservadorZ3, never()).actualizarPorCarga(unaZdC, unPostM);
		verify(unObservadorZ4, never()).actualizarPorCarga(unaZdC, unPostM);
	}
	
}
