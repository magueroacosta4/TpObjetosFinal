package muestra;

import java.time.LocalDate;

import usuario.EstadoUsuario;

public class Revision {

	private Opinion opinion;
	private LocalDate getFechaDeCreacion;
	private EstadoUsuario estadoDelUsuarioActual;
	
	public Revision(Opinion o, EstadoUsuario e) {
		setFechaDeCreacion();
		setEstadoDelUsuarioActual(e);
		setOpinion(o);
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
