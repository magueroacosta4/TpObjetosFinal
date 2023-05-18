package Usuario;

import muestra.Opinion;
import muestra.PaginaWeb;
import muestra.PostMuestra;
import muestra.Revision;
import muestra.Ubicacion;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;



public abstract class Usuario {
	private EstadoUsuario estado;
	private PaginaWeb pagina;
	private HistorialEnApp historial;
	
	public void publicar(Revision rev, Ubicacion ubicacion) {
		this.getPagina().crearPostMuestra(rev, ubicacion);
		this.getHistorial().addPost(LocalDate.now());
	}

	public void opinar(PostMuestra post, Opinion op) {
		Revision rev = new Revision(op, this.getEstado());
		post.opinar(rev);
		this.getHistorial().addOpinion(rev);
	}

	protected int getCantPosts30Dias(){
		List<LocalDate> cant;
		LocalDate hoy = LocalDate.now();
		cant = this.getHistorial().getPosts().
				stream().
				filter(p -> p.isAfter(hoy.minusDays(30))).collect(Collectors.toList());
		return cant.size();
	}

	protected int getCantOpiniones30Dias(){
		List<Revision> cant;
		LocalDate hoy = LocalDate.now();
		cant = this.getHistorial().getOpiniones().
				stream().
				filter(p -> p.getFechaDeCreacion().isAfter(hoy.minusDays(30))).
				collect(Collectors.toList());
		return cant.size();
	}
	
	public boolean esExperto() {
		return this.getEstado().getClass() == EstadoExperto.class;
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
