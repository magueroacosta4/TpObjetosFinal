package usuario;

import muestra.Opinion;
import muestra.PaginaWeb;
import muestra.PostMuestra;
import muestra.Revision;
import muestra.Ubicacion;


public abstract class Usuario {
	private EstadoUsuario estado;
	private PaginaWeb pagina;
	private HistorialEnApp historial;
	
	public void publicar(Revision rev, Ubicacion ubicacion) throws Exception {
		this.getPagina().crearPostMuestra(rev, ubicacion);
		this.getHistorial().addPost();
		this.actualizarEstado();
	}

	public void opinar(PostMuestra post, Opinion op) throws Exception {
		//
		//
		// HACE QUE LA OPINION LO HAGA LA PAGINA WEB ---> getPagina().opinarPostMuestra()
		// ES PARA QUE SE PUEDAN EJECUTAR TODOS LOS METODOS DEL OBSERVER
		//
		Revision rev = new Revision(op, this.getEstado(), this);
		post.opinar(rev);
		this.getHistorial().addOpinion(rev);
		this.actualizarEstado();
	}

	protected int postsUltimos30Dias(){
		return this.getHistorial().getCantPosts30Dias();
	}

	protected int opinionesUltimos30Dias(){
		return this.getHistorial().getCantOpiniones30Dias();
	}
	
	public void actualizarEstado() {
		this.getEstado().actualizarEstado(this);
	}
	
	public boolean esExperto() {
		return this.getEstado().esExperto();
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
	
	public void setPagina(PaginaWeb pagina) {
		this.pagina = pagina;
	}
	
	public HistorialEnApp getHistorial() {
		return this.historial;
	}
	
	public void setHistorial(HistorialEnApp historial) {
		this.historial = historial;
	}
	
}
