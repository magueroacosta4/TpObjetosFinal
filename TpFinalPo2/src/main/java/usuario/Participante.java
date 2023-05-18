package usuario;

import muestra.PaginaWeb;

public class Participante extends Usuario {
	
	public Participante(PaginaWeb pag) {
		super();
		this.setEstado(new EstadoExperto());
		this.setPagina(pag);
		this.setHistorial(new HistorialEnApp());
	}
}
