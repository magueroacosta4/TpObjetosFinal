package organizacion;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;

import org.junit.Before;
import org.junit.Test;

import muestra.*;

public class OrganizacionTest {
	private TipoOrganizacion unTipoOrga;
	private Ubicacion unaUbicacion;
	privatunTipoOrga = TipoOrganizacion.SALUD;
		un
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