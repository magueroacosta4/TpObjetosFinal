package filtrosDeBusqueda;

import java.util.ArrayList;
import java.util.List;

import muestra.PostMuestra;

public abstract class ConectoresBusqueda extends FiltroDeBusqueda {
	private FiltroDeBusqueda primerFiltro;
	private FiltroDeBusqueda segundoFiltro;

	public final List<PostMuestra> filtrar(List<PostMuestra> postsAFiltrar) {
		List <PostMuestra> primerFiltro = new ArrayList<PostMuestra>();
		List <PostMuestra> segundoFiltro = new ArrayList<PostMuestra>();
		
		primerFiltro.addAll(this.getPrimerFiltro().filtrar(postsAFiltrar));
		segundoFiltro.addAll(this.getSegundoFiltro().filtrar(postsAFiltrar));
		
		return aplicar(primerFiltro, segundoFiltro);
	}

	protected abstract List<PostMuestra> aplicar(List<PostMuestra> primerFiltro,
			List<PostMuestra> segundoFiltro);
	
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
