package filtrosDeBusqueda;

import java.util.List;
import java.util.ArrayList;

import muestra.PostMuestra;

public class ConectorAnd extends ConectoresBusqueda {

	public ConectorAnd(FiltroDeBusqueda primerFil, FiltroDeBusqueda segundoFil) {
		this.setPrimerFiltro(primerFil);
		this.setSegundoFiltro(segundoFil);
	}
	
	@Override
	public List<PostMuestra> filtrar(List<PostMuestra> postsAFiltrar) {
		List <PostMuestra> primerFiltro = new ArrayList<PostMuestra>();
		List <PostMuestra> segundoFiltro = new ArrayList<PostMuestra>();
		
		primerFiltro.addAll(this.getPrimerFiltro().filtrar(postsAFiltrar));
		segundoFiltro.addAll(this.getSegundoFiltro().filtrar(postsAFiltrar));
		
		primerFiltro.retainAll(segundoFiltro); //Se queda solo con los posts que se repiten en ambas colecciones
		return primerFiltro;
	}

}
