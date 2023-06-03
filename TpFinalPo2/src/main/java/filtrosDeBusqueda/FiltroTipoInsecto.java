package filtrosDeBusqueda;

import java.util.List;

import muestra.Opinion;
import muestra.PostMuestra;

public class FiltroTipoInsecto extends FiltrosBusqueda {

	private Opinion tipoInsecto;
	
	public FiltroTipoInsecto(Opinion insecto) {
		this.setTipoInsecto(insecto);
	}
	
	@Override
	protected void aplicarCriterioDeFiltro(List<PostMuestra> resultadoBusqueda, PostMuestra post) {
		if(post.getResultadoActual().get().equals(this.getTipoInsecto())) {
			resultadoBusqueda.add(post);
		}
	}

	public Opinion getTipoInsecto() {
		return tipoInsecto;
	}

	public void setTipoInsecto(Opinion tipoInsecto) {
		this.tipoInsecto = tipoInsecto;
	}

}
