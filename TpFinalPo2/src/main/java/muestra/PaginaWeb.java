package muestra;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import organizacion.ZonaDeCobertura;
import usuario.Usuario;

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
	
	public void crearPostMuestra(Revision r, Ubicacion u) throws Exception {
		PostMuestra posteo = new PostMuestra(u);
		getMuestras().add(posteo);
		opinarPostMuestra(r, posteo);
	}
	
	public void crearPostMuestra(Revision r, PostMuestra post) throws Exception {
		getMuestras().add(post);
		opinarPostMuestra(r, post);
	}
	
	public void opinarPostMuestra(Revision r, PostMuestra post) throws Exception {
		post.opinar(r);
		Stream<ZonaDeCobertura> zonasConElPost = zonasDeCovertura.stream().filter(z -> z.contieneAlPost(post));
		zonasConElPost.forEach(z->z.notifyAll());;
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
