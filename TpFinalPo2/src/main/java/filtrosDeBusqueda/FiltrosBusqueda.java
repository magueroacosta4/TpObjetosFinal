package filtrosDeBusqueda;

import java.util.List;
import java.util.ArrayList;

import muestra.PostMuestra;

public abstract class FiltrosBusqueda extends FiltroDeBusqueda {

	@Override
	public List<PostMuestra> filtrar(List<PostMuestra> postsAFiltrar){
		List<PostMuestra> resultadoBusqueda = new ArrayList<PostMuestra>();
		this.aplicarFiltroALosPosts(postsAFiltrar, resultadoBusqueda);
		return resultadoBusqueda;
	}

	public void aplicarFiltroALosPosts(List<PostMuestra> postsAFiltrar,
			List<PostMuestra> resultadoBusqueda) {
		for (PostMuestra post : postsAFiltrar) {
			this.aplicarCriterioDeFiltro(resultadoBusqueda, post);
		}
	}

	//Las subclases deben implementar este metodo (Son los casos base)
	protected abstract void aplicarCriterioDeFiltro(List<PostMuestra> resultadoBusqueda, PostMuestra post);
}
