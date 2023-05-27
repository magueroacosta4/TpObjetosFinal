package filtrosDeBusqueda;

import java.util.List;

import muestra.EstadoDePost;
import muestra.PostMuestra;

public class FiltroNivelDeValidacion extends FiltrosBusqueda {
	
	private EstadoDePost validacion;
	
	public FiltroNivelDeValidacion(EstadoDePost valid) {
		this.setValidacion(valid);
	}
	
	@Override
	protected void aplicarCriterioDeFiltro(List<PostMuestra> resultadoBusqueda, PostMuestra post) {
		if(true) {
			resultadoBusqueda.add(post);
		}
	}

	public EstadoDePost getValidacion() {
		return validacion;
	}

	public void setValidacion(EstadoDePost validacion) {
		this.validacion = validacion;
	}
	
}
