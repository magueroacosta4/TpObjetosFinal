package filtrosDeBusqueda;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import muestra.PostMuestra;

public class ConectorOr extends ConectoresBusqueda {

	public ConectorOr(FiltroDeBusqueda primerFil, FiltroDeBusqueda segundoFil) {
		this.setPrimerFiltro(primerFil);
		this.setSegundoFiltro(segundoFil);
	}

	@Override
	protected List<PostMuestra> aplicar(List<PostMuestra> postsAFiltrar, List<PostMuestra> primerFiltro,
			List<PostMuestra> segundoFiltro) {
		primerFiltro.addAll(segundoFiltro);
		Set<PostMuestra> set = new HashSet<>(primerFiltro);
		primerFiltro.clear();
		primerFiltro.addAll(set);
		
		return primerFiltro;
	}

}
