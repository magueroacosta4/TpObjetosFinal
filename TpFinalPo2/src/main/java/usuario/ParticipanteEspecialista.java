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
	}

}
