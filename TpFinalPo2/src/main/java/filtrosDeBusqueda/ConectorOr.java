package filtrosDeBusqueda;

import java.util.ArrayList;
import java.util.List;

import muestra.PostMuestra;

public class ConectorOr extends ConectoresBusqueda {

	@Override
	public List<PostMuestra> filtrar(List<PostMuestra> postsAFiltrar) {
		List <PostMuestra> primerFiltro = new ArrayList<PostMuestra>();
		List <PostMuestra> segundoFiltro = new ArrayList<PostMuestra>();
		List <PostMuestra> resultado = new ArrayList<PostMuestra>();
		
		primerFiltro.addAll(this.getPrimerFiltro().filtrar(postsAFiltrar));
		segundoFiltro.addAll(this.getSegundoFiltro().filtrar(postsAFiltrar));
		
		resultado.addAll(primerFiltro);
		resultado.addAll(segundoFiltro);
		//Se queda con los posts de ambas colecciones sin duplicados
		return this.resultadoBusquedaSinDuplicados(resultado);
	}

}
