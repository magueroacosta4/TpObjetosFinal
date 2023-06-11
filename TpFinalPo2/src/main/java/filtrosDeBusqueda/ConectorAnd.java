package filtrosDeBusqueda;

import java.util.List;

import muestra.PostMuestra;

public class ConectorAnd extends ConectoresBusqueda {

	public ConectorAnd(FiltroDeBusqueda primerFil, FiltroDeBusqueda segundoFil) {
		this.setPrimerFiltro(primerFil);
		this.setSegundoFiltro(segundoFil);
	}

	@Override
	protected List<PostMuestra> aplicar(List<PostMuestra> primerFiltro, List<PostMuestra> segundoFiltro) {
		primerFiltro.retainAll(segundoFiltro); //Se queda solo con los posts que se repiten en ambas colecciones
		return primerFiltro;
	}

}
