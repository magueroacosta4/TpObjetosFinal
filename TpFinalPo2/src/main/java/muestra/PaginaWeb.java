package muestra;

import java.util.ArrayList;
import java.util.List;

import Usuario.Usuario;
import organizacion.ZonaDeCobertura;

public class PaginaWeb {
	
	private List<ZonaDeCobertura> zonasDeCovertura = new ArrayList<ZonaDeCobertura>();
	private List<PostMuestra> posteosDeMuestras = new ArrayList<PostMuestra>();
	private List<Usuario> usuarios = new ArrayList<Usuario>();
	
	public PaginaWeb() {
		setUsuarios(new ArrayList<Usuario>());
		setZonasDeCovertura(new ArrayList<ZonaDeCobertura>());
		setPosteosDeMuestras(new ArrayList<PostMuestra>());
	}
	
	private void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}
	
	private void setZonasDeCovertura(List<ZonaDeCobertura> zonasDeCovertura) {
		this.zonasDeCovertura = zonasDeCovertura;
	}
	
	private void setPosteosDeMuestras(List<PostMuestra> posteosDeMuestras) {
		this.posteosDeMuestras = posteosDeMuestras;
	}

	public List<ZonaDeCobertura> getZonasDeCobertura() {
		return zonasDeCovertura;
	}

	public List<PostMuestra> getMuestras() {
		return posteosDeMuestras;
	}
	
	public void crearPostMuestra(Revision r, Ubicacion u) {
		PostMuestra posteo = new PostMuestra(r, u);
		getMuestras().add(posteo);
	}

	public List<Usuario> getUsuarios() {
		return usuarios;
	}

	public void agregarZonaDeCobertura(ZonaDeCobertura unaZonaDeC) {
		getZonasDeCobertura().add(unaZonaDeC);
	}

	public void agregarUsuario(Usuario unUsuario) {
		getUsuarios().add(unUsuario);
	}

}
