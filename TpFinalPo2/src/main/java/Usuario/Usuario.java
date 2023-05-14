package Usuario;

import muestra.Opinion;
import muestra.PaginaWeb;
import muestra.PostMuestra;
import muestra.Muestra;

public abstract class Usuario {
	private EstadoUsuario estado;
	private PaginaWeb pagina;
	
	public void publicar(Muestra muestra) {
		
	}
	
	public void opinar(PostMuestra post, Opinion op) {
		
	}
	
	protected abstract void confirmarEstado();
}
