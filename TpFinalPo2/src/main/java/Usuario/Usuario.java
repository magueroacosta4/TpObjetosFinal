package Usuario;

import muestra.Opinion;
import muestra.PaginaWeb;
import muestra.PostMuestra;

import java.util.List;

import muestra.Muestra;

public abstract class Usuario {
	private EstadoUsuario estado;
	private PaginaWeb pagina;
	
	public void publicar(Muestra muestra) {
		this.getPagina().crearPostMuestra(null, null);;
	}

	public void opinar(PostMuestra post, Opinion op) {
		
	}

	protected List<PostMuestra> getPostsEnLaApp(){
		return null;
	}

	protected List<Opinion> getOpinionesEnLaApp(){
		return null;
	}

	public EstadoUsuario getEstado() {
		return this.estado;
	}
	
	public void setEstado(EstadoUsuario estado) {
		this.estado = estado;
		
	}
	
	public PaginaWeb getPagina() {
		return this.pagina;
	}
	
}
