package muestra;

import java.time.LocalDate;

public class Revision {

	private Opinion opinion;
	private LocalDate getFechaDeCreacion;
	private EstadoUsuario estadoDelUsuarioActual;
	private Usuario user;
	
	public Revision(Opinion o, EstadoUsuario e, Usuario u) {
		setFechaDeCreacion();
		setUsuario(u);
		setEstadoDelUsuarioActual(e);
		setOpinion(o);
	}

	private void setUsuario(Usuario u) {
		// TODO Auto-generated method stub
		this.user = u;
	}
	
	public Usuario getUser() {
		return user;
	}

	public Opinion getOpinion() {
		return opinion;
	}
	
	public LocalDate getFechaDeCreacion() {
		return getFechaDeCreacion;
	}
	public void setFechaDeCreacion() {
		this.getFechaDeCreacion = LocalDate.now();
	}
	
	public EstadoUsuario getEstadoDelUsuarioActual() {
		return estadoDelUsuarioActual;
	}
	
	public void setEstadoDelUsuarioActual(EstadoUsuario estadoDelUsuarioActual) {
		this.estadoDelUsuarioActual = estadoDelUsuarioActual;
	}
	
	public void setOpinion(Opinion opinion) {
		this.opinion = opinion;
	}
}
