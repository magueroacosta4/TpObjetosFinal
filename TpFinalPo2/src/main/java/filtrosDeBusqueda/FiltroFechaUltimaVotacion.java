package filtrosDeBusqueda;

import java.time.LocalDate;

import muestra.PostMuestra;

public class FiltroFechaUltimaVotacion extends FiltroDeFecha {

	public FiltroFechaUltimaVotacion(LocalDate fecha, String operador) {
		super(fecha, operador);
	}

	@Override
	protected LocalDate fechaObjetivoDePost(PostMuestra post) {
		return post.getFechaUltimaRevision();
	}

}
