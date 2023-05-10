package muestra;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;



public class TestPostMuestra {
	
	@Test
	public void AlCrearUnPostMuestraSeColocanTodosSusColaboradoresYseAgregaLaRevision() {
		Ubicacion u = mock(Ubicacion.class);
		VerificadorMuestra v = mock(VerificadorMuestra.class);
		Revision r = mock(Revision.class);
		when(r.getOpinion()).thenReturn(Opinion.NINGUNA);
	
		PostMuestra posteo = new PostMuestra(r, u, v);
				
		assertEquals(posteo.resultadoActual(), Opinion.NINGUNA);
		
		verify(r, times(2)).getOpinion();
	}
}
