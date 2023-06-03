package filtrosDeBusqueda;

import java.time.LocalDate;

import muestra.PostMuestra;

public class FiltroFechaCreacion extends FiltroDeFecha {
	
	public FiltroFechaCreacion(LocalDate fecha, String operador) {
		super(fecha, operador);
	}

	@Override
	protected LocalDate fechaObjetivoDePost(PostMuestra post) {
		return post.getFechaDeCreacion();
	}

	

}
