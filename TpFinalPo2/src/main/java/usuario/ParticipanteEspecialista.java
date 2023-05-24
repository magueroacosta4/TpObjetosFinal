package usuario;

import muestra.PaginaWeb;

public class ParticipanteEspecialista extends Usuario {
	
	public ParticipanteEspecialista(PaginaWeb pag) {
		super();
		this.setEstado(new EstadoExperto());
		this.setPagina(pag);
		this.setHistorial(new HistorialEnApp());
	}
	
	@Override
	public void actualizarEstado() {
		//No hace nada, nunca puede cambiar el estado de este usuario
	}

	@Override
	public void setEstado(EstadoUsuario estado) {
		if(estado.esExperto()) {
			this.estado = estado;
		}
	}
	
}
