package muestra;


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
		Opinion o = mock(Opinion.class);
		List<Opinion> l = new ArrayList<Opinion>();
		l.add(o);
		when(Opinion.getOpiniones()).thenReturn(l);

		PostMuestra posteo = new PostMuestra(r, u, v);
				
		posteo.resultadoActual();
		

	}
}
