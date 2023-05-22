package muestra;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class UbicacionTest {
	Ubicacion unaUbicacion;
	
	@Before
	public void setUp() {
		 unaUbicacion = new Ubicacion(41.57879F, 1.617221F);
	}
	
	@Test
	public void unaUbicacionTieneLatitudYLongitud() {
		assertEquals(unaUbicacion.getLongitud(), 1.617221F);
		assertEquals(unaUbicacion.getLatitud(), 41.57879F);
	}
	
	@Test
	public void unaUbicacionSabeLaDistanciaAOtraUbicacion() {
		Ubicacion ubicacionUnqui = new Ubicacion(-34.7065325f,-58.2874116f);

		Ubicacion otraUbicacion = mock(Ubicacion.class);
		when(otraUbicacion.getLongitud()).thenReturn(-3.597929F);
		when(otraUbicacion.getLatitud()).thenReturn(37.176487F);
		
		Ubicacion ubicacionCasaRosada = mock(Ubicacion.class);
		when(ubicacionCasaRosada.getLatitud()).thenReturn(-34.6080511F);
		when(ubicacionCasaRosada.getLongitud()).thenReturn(-58.3748912F);
		
		assertEquals(unaUbicacion.distanciaA(otraUbicacion), 664.2027587890625);
		
		assertEquals(ubicacionUnqui.distanciaA(ubicacionCasaRosada), 13.577118873596191);
	}
	
	@Test
	public void laDistanciaDeUnaUbicacionASiEllaMismaEsCero() {
		assertEquals(unaUbicacion.distanciaA(unaUbicacion), 0);
	}
	
	@Test
	public void unaUbicacionSabeDeUnaListaCualesEstanAMenosDeUnaCantidadDeKilometros() {
		Ubicacion u1 = mock(Ubicacion.class),
				u2 = mock(Ubicacion.class),
				u3 = mock(Ubicacion.class);
		
		when(u1.getLatitud()).thenReturn(10F);
		when(u1.getLongitud()).thenReturn(10F);
		
		when(u2.getLatitud()).thenReturn(41.5894172f);
		when(u2.getLongitud()).thenReturn(1.6102821f);
		
		when(u3.getLatitud()).thenReturn(-40F);
		when(u3.getLongitud()).thenReturn(40F);
		
		List<Ubicacion> ubicaciones = Arrays.asList(u1, u2, u3, unaUbicacion);
		List<Ubicacion> ubicacionesEsperadas = Arrays.asList(u2, unaUbicacion);
		assertEquals(
				unaUbicacion.lasQueEstanAMenosDeNKM(ubicaciones, 10),
				ubicacionesEsperadas);
	}

}
