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
		post.getResultadoActual().ifPresentOrElse(v -> { 
			if (v.equals(this.getTipoInsecto()))
				resultadoBusqueda.add(post);
		}, null);
	}

	public Opinion getTipoInsecto() {
		return tipoInsecto;
	}

	public void setTipoInsecto(Opinion tipoInsecto) {
		this.tipoInsecto = tipoInsecto;
	}

}
