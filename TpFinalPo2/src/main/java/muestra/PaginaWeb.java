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
	
	public void crearPostMuestra(Revision r, Ubicacion u, String foto){
		PostMuestra posteo = new PostMuestra(u, r, foto);
		getMuestras().add(posteo);
		zonasQueContienenElPost(posteo)
		.forEach(z -> z.notificarCargaDeMuestra(posteo));
	}

	private Stream<ZonaDeCobertura> zonasQueContienenElPost(PostMuestra posteo) {
		Stream<ZonaDeCobertura> zonas = zonasDeCovertura.stream().filter(z -> z.tieneLaMuestra(posteo));
		return zonas;
	}
	
	public void crearPostMuestra(Revision r, PostMuestra post){
		getMuestras().add(post);
		zonasQueContienenElPost(post)
		.forEach(z -> z.notificarCargaDeMuestra(post));
	}
	
	public void opinarPostMuestra(Revision r, PostMuestra post) throws Exception {
		post.opinar(r);
		if(post.getEstadoDePost().esVerificado()) {
		zonasQueContienenElPost(post)
		.forEach(z->z.notificarValidacionDeMuestra(post));
		}
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
