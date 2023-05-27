package filtrosDeBusqueda;

import java.time.LocalDate;
import java.util.List;

import muestra.PostMuestra;

public class FiltroFechaCreacion extends FiltrosBusqueda {

	private LocalDate fechaReferencia;
	
	public FiltroFechaCreacion(LocalDate fecha) {
		this.setFechaReferencia(fecha);
	}
	
	@Override
	protected void aplicarCriterioDeFiltro(List<PostMuestra> resultadoBusqueda, PostMuestra post) {
		

	}

	public LocalDate getFechaReferencia() {
		return fechaReferencia;
	}

	public void setFechaReferencia(LocalDate fechaReferencia) {
		this.fechaReferencia = fechaReferencia;
	}

}
