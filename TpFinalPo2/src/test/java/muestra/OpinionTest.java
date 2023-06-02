package muestra;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class OpinionTest {
	@Test
	public void unaOpinionSabeCualesSeRefierenAUnaEspecieDeVinchuca() {
		assertTrue(Opinion.VINCHUCA_INFESTANTS.esVinchuca());
		assertTrue(Opinion.VINCHUCA_GUASAYANA.esVinchuca());
		assertTrue(Opinion.VINCHUCA_SORDIDA.esVinchuca());
		
		assertFalse(Opinion.IMAGEN_POCO_CLARA.esVinchuca());
		assertFalse(Opinion.NINGUNA.esVinchuca());
		assertFalse(Opinion.CHINCHE_FOLIADA.esVinchuca());
		assertFalse(Opinion.PHTIA_CHINCHE.esVinchuca());
	}
}
