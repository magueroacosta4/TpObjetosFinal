package usuario;

import muestra.PaginaWeb;

public class ParticipanteEspecialista extends Usuario {
	
	public ParticipanteEspecialista(PaginaWeb pag) {
		super();
		this.setearEstado(new EstadoExperto());
		this.setPagina(pag);
		this.setHistorial(new HistorialEnApp());
	}
	
	@Override
	public void actualizarEstado() {
		//No hace nada, nunca puede cambiar el estado de este usuario
	}

	public void setearEstado(EstadoUsuario estado) {
		if(estado.esExperto()) {
			this.setEstado(estado);
		}
	}
	
}
