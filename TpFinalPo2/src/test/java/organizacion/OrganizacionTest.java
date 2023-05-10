package organizacion;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;

import org.junit.Before;
import org.junit.Test;

import muestra.*;

public class OrganizacionTest {
	private TipoOrganizacion unTipoOrga;
	private Ubicacion unaUbicacion;
	private Organizacion unaOrga, otraOrga, orgaEdu, orgaCult, orgaAsist;
	private PaginaWeb unaPagina;

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
}
