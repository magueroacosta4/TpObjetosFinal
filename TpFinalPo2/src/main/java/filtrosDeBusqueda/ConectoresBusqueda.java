package filtrosDeBusqueda;

import java.util.ArrayList;
import java.util.List;

import muestra.PostMuestra;

public abstract class ConectoresBusqueda extends FiltroDeBusqueda {
	private FiltroDeBusqueda primerFiltro;
	private FiltroDeBusqueda segundoFiltro;

	@Override
	public abstract List<PostMuestra> filtrar(List<PostMuestra> postsAFiltrar);

	protected List<PostMuestra> resultadoBusquedaSinDuplicados(List<PostMuestra> listaDePosts) {
		List<PostMuestra> listaSinDuplicados = new ArrayList<PostMuestra>();
		for (PostMuestra proyecto : listaDePosts) {
			if (!(listaSinDuplicados.contains(proyecto))) {
				listaSinDuplicados.add(proyecto);
			}
		}
		return listaSinDuplicados;
	}
	
	public FiltroDeBusqueda getPrimerFiltro() {
		return this.primerFiltro;
	}

	public void setPrimerFiltro(FiltroDeBusqueda primerFiltro) {
		this.primerFiltro = primerFiltro;
	}

	public FiltroDeBusqueda getSegundoFiltro() {
		return this.segundoFiltro;
	}

	public void setSegundoFiltro(FiltroDeBusqueda segundoFiltro) {
		this.segundoFiltro = segundoFiltro;
	}

}
