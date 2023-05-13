package muestra;

import java.util.ArrayList;
import java.util.List;

import organizacion.ZonaDeCobertura;

public class PaginaWeb {
	
	List<ZonaDeCobertura> zonasDeCovertura = new ArrayList<ZonaDeCobertura>();
	List<PostMuestra> posteosDeMuestras = new ArrayList<PostMuestra>();

	public List<ZonaDeCobertura> getZonasDeCobertura() {
		return zonasDeCovertura;
	}

	public List<PostMuestra> getMuestras() {
		return posteosDeMuestras;
	}
	
	public void crearPostMuestra(Revision r, Ubicacion u) {
		PostMuestra posteo = new PostMuestra(r, u);
		posteosDeMuestras.add(posteo);
	}

}
