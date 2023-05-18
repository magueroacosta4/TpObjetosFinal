package Usuario;

import muestra.Opinion;
import muestra.PaginaWeb;
import muestra.PostMuestra;
import muestra.Revision;
import muestra.Ubicacion;

import java.util.List;



public abstract class Usuario {
	private EstadoUsuario estado;
	private PaginaWeb pagina;
	private HistorialEnApp historial;
	
	public void publicar(Revision rev, Ubicacion ubicacion) {
		this.getPagina().crearPostMuestra(rev, ubicacion);
	}

	public void opinar(PostMuestra post, Opinion op) {
		Revision rev = new Revision(op, this.getEstado());
		post.opinar(rev);
		this.getHistorial().addOpinion(op);
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
	
	public HistorialEnApp getHistorial() {
		return this.historial;
	}
	
}
