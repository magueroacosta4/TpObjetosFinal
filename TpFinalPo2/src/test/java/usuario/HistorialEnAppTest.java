package usuario;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;


import muestra.Revision;


public class HistorialEnAppTest {
	private HistorialEnApp historial;
	private Revision rev1;
	private Revision rev2;
	
	@Before
	public void setUp() {
		historial = new HistorialEnApp();
		rev1 = mock(Revision.class);
		rev2 = mock(Revision.class);
	}
	
	@Test
	public void constructorVacioCumpleTest() {
		assertTrue(historial.getPosts().isEmpty());
		assertTrue(historial.getOpiniones().isEmpty());
	}
	
	@Test
	public void seAgregan2ElementosALasColeccionesYCumplenEstarDentroDe30DiasTest() {
		when(rev1.getFechaDeCreacion()).thenReturn(LocalDate.of(2023, 06, 14));
		when(rev2.getFechaDeCreacion()).thenReturn(LocalDate.of(2023, 06, 24));
		
		historial.addPost();
		historial.addPost();
		historial.addOpinion(rev1);
		historial.addOpinion(rev2);
		
		assertEquals(historial.getCantOpiniones30Dias(), 2);
		assertEquals(historial.getCantPosts30Dias(), 2);
	}
	
	@Test
	public void seAgreganElementosALasColeccionesYNoCumplenEstarDentroDe30DiasTest() {
		when(rev1.getFechaDeCreacion()).thenReturn(LocalDate.of(2023, 04, 14));
		when(rev2.getFechaDeCreacion()).thenReturn(LocalDate.of(2023, 02, 04));
		
		historial.addPost();
		historial.addPost();
		historial.addOpinion(rev1);
		historial.addOpinion(rev2);
		
		assertEquals(0, historial.getCantOpiniones30Dias());
	}
}
