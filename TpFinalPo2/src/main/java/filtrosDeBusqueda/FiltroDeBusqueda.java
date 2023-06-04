package filtrosDeBusqueda;

import java.util.List;

import muestra.PostMuestra;

public abstract class FiltroDeBusqueda {
		
	public abstract List<PostMuestra> filtrar(List<PostMuestra> postsAFiltrar);
}
