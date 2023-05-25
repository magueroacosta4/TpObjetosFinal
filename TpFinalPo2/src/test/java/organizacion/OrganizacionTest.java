package organizacion;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.Optional;

import org.junit.Before;
import org.junit.Test;

import muestra.*;

public class OrganizacionTest {
	private TipoOrganizacion unTipoOrga;
	private Ubicacion unaUbicacion;
	private Organizacion unaOrga, otraOrga, orgaEdu, orgaCult, orgaAsist;
	private PaginaWeb unaPagina;
	private FuncionalidadExterna funcionalidadCarga, funcionalidadVerificacion;
	private ZonaDeCobertura unaZona;

	@Before
	public void setUp() {
		unTipoOrga = TipoOrganizacion.SALUD;
		unaUbicacion = mock(Ubicacion.class);
		unaPagina = mock(PaginaWeb.class);
		unaOrga = new Organizacion(unaUbicacion, unTipoOrga, 10, unaPagina);
		otraOrga = new Organizacion(unaUbicacion, unTipoOrga, 25, unaPagina);
		
		orgaEdu = new Organizacion(unaUbicacion, TipoOrganizacion.EDUCATIVA, 10, unaPagina);
		orgaCult = new Organizacion(unaUbicacion, TipoOrganizacion.CULTURAL, 10, unaPagina);
		orgaAsist = new Organizacion(unaUbicacion, TipoOrganizacion.ASISTENCIA, 10, unaPagina);
		
		funcionalidadCarga = mock(FuncionalidadExterna.class);
		funcionalidadVerificacion = mock(FuncionalidadExterna.class);
		
		unaZona = mock(ZonaDeCobertura.class);
	}
	
	@Test
	public void unaOrganizacionTieneUbicacionTipoYCantidadDePersonasQueTrabajanEnEllaYUnaPagina() {
		assertEquals(unaOrga.getUbicacion(), unaUbicacion);
		assertEquals(unaOrga.getTipo(), unTipoOrga);
		assertEquals(unaOrga.getCantidadTrabajadores(), 10);
		assertEquals(unaOrga.getPaginaWeb(), unaPagina);
	}
	
	@Test
	public void otraOrganizacionTieneUnaCantidadDiferenteDeTrabajadores() {
		assertEquals(otraOrga.getCantidadTrabajadores(), 25);
	}
	
	@Test
	public void lasOrganizacionesPuedenSerDeDiferentesTipos() {
		assertEquals(unaOrga.getTipo(), unTipoOrga);
		assertEquals(orgaEdu.getTipo(), TipoOrganizacion.EDUCATIVA);
		assertEquals(orgaCult.getTipo(), TipoOrganizacion.CULTURAL);
		assertEquals(orgaAsist.getTipo(), TipoOrganizacion.ASISTENCIA);
	}
	
	@Test
	public void unaOrganizacionPuedeNoTenerFuncionalidadesParaLaCargaOValidacionDeMuestras() {
		assertEquals(unaOrga.getFuncionalidadValidacion(), Optional.empty());
		assertEquals(unaOrga.getFuncionalidadCarga(), Optional.empty());
	}
	
	@Test
	public void unaOrganizacionPuedeDefinirFuncionalidadParaLaCargaDeMuestras() {
		unaOrga.definirFuncionalidadCarga(funcionalidadCarga);
		
		assertEquals(unaOrga.getFuncionalidadCarga(), Optional.of(funcionalidadCarga));
	}
	
	@Test
	public void unaOrganizacionPuedeDefinirFuncionalidadParaLaValidacionDeMuestras() {
		unaOrga.definirFuncionalidadValidacion(funcionalidadVerificacion);

		assertEquals(unaOrga.getFuncionalidadValidacion(), Optional.of(funcionalidadVerificacion));
	}
	
	@Test
	public void unaOrganizacionPuedeEjecutarSuFuncionalidadDeCargaSiTieneLlamaANuevoEventoSinoNoHaceNada() {
		// las ejecuciones reciben la zona de cobertura y la muestra involucados.
		ZonaDeCobertura unaZonaDeCobertura = mock(ZonaDeCobertura.class);
		PostMuestra unPostMuestra = mock(PostMuestra.class);
		unaOrga.ejecutarFuncionalidadCarga(unaZonaDeCobertura, unPostMuestra);
		
		verify(funcionalidadCarga, never()).nuevoEvento(orgaAsist, unaZonaDeCobertura, unPostMuestra);
		
		unaOrga.definirFuncionalidadCarga(funcionalidadCarga);
		
		unaOrga.ejecutarFuncionalidadCarga(unaZonaDeCobertura, unPostMuestra);
		
		verify(funcionalidadCarga).nuevoEvento(unaOrga, unaZonaDeCobertura, unPostMuestra);
	}
	
	@Test
	public void unaOrganizacionPuedeEjecutarSuFuncionalidadDeValidacionSiTieneLlamaANuevoEventoSinoNoHaceNada() {
		// las ejecuciones reciben la zona de cobertura y la muestra involucados.
		ZonaDeCobertura unaZonaDeCobertura = mock(ZonaDeCobertura.class);
		PostMuestra unPostMuestra = mock(PostMuestra.class);
		
		unaOrga.ejecutarFuncionalidadValidacion(unaZonaDeCobertura, unPostMuestra);
		verify(funcionalidadVerificacion, never()).nuevoEvento(orgaAsist, unaZonaDeCobertura, unPostMuestra);
		
		unaOrga.definirFuncionalidadValidacion(funcionalidadVerificacion);
		
		unaOrga.ejecutarFuncionalidadValidacion(unaZonaDeCobertura, unPostMuestra);
		
		verify(funcionalidadVerificacion).nuevoEvento(unaOrga, unaZonaDeCobertura, unPostMuestra);
	}
	
	@Test
	public void unaOrganizacionSePuedeSuscribirALaCargaDeMuestrasDeUnaZona() {
		unaOrga.suscribirseACargaEn(unaZona);

		verify(unaZona).suscribirACarga(unaOrga);
	}
	
	@Test
	public void unaOrganizacionSePuedeSuscribirALaValidacionDeMuestrasDeUnaZona() {
		unaOrga.suscribirseAValidacionEn(unaZona);
		
		verify(unaZona).suscribirAValidacion(unaOrga);
	}
	
	@Test
	public void unaOrganizacionSePuedeSuscribirALaCargaDeMuestrasDeUnaZonaDaIgualSiEstaSuscritoONo() {
		unaOrga.suscribirseACargaEn(unaZona);
		
		unaOrga.suscribirseACargaEn(unaZona);
		
		verify(unaZona, times(2)).suscribirACarga(unaOrga);
	}
	
	@Test
	public void unaOrganizacionSePuedeSuscribirALaValidacionDeMuestrasDeUnaZonaDaIgualSiEstaSuscritoONo() {
		unaOrga.suscribirseAValidacionEn(unaZona);
		
		unaOrga.suscribirseAValidacionEn(unaZona);
		
		verify(unaZona, times(2)).suscribirAValidacion(unaOrga);
	}
	
	@Test
	public void unaOrganizacionSePuedeDesuscribirDeLaCargaDeMuestrasDeUnaZona() {
		unaOrga.desuscribirseDeCargaEn(unaZona);
		
		verify(unaZona).desuscribirDeCarga(unaOrga);
	}
	
	@Test
	public void unaOrganizacionSePuedeDesuscribirDeLaValidacionDeMuestrasDeUnaZona() {
		unaOrga.desuscribirseDeValidacionEn(unaZona);
		
		verify(unaZona).desuscribirDeValidacion(unaOrga);
	}
	
	@Test
	public void unaOrganizacionSePuedeDesuscribirALaCargDeMuestrasDeUnaZonaDaIgualSiEstaSuscritoONo() {
		unaOrga.desuscribirseDeCargaEn(unaZona);
		
		unaOrga.desuscribirseDeCargaEn(unaZona);
		
		verify(unaZona, times(2)).desuscribirDeCarga(unaOrga);
	}
	
	@Test
	public void unaOrganizacionSePuedeDesuscribirALaValidacionDeMuestrasDeUnaZonaDaIgualSiEstaSuscritoONo() {
		unaOrga.desuscribirseDeValidacionEn(unaZona);
		
		unaOrga.desuscribirseDeValidacionEn(unaZona);
		
		verify(unaZona, times(2)).desuscribirDeValidacion(unaOrga);
	}
}
