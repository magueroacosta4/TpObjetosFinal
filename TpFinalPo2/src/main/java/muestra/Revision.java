package muestra;

import java.util.Date;

import Usuario.EstadoUsuario;

public class Revision {

	private Opinion opinion;
	private Date getFechaDeCreacion;
	private EstadoUsuario estadoDelUsuarioActual;
	
	public Revision(Opinion o, EstadoUsuario e) {
		setFechaDeCreacion();
		setEstadoDelUsuarioActual(e);
		setOpinion(o);
	}

	public Opinion getOpinion() {
		return opinion;
	}
	
	public Date getFechaDeCreacion() {
		return getFechaDeCreacion;
	}
	public void setFechaDeCreacion() {
		this.getFechaDeCreacion = new Date();
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
