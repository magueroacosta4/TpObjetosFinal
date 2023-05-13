package muestra;

import java.util.Arrays;
import java.util.Comparator;

import java.util.HashSet;


public class VerificadorMuestra {
	
	private PostMuestra post;
	private EstadoDePost estadoPost;
	
	public VerificadorMuestra(PostMuestra posteoAsociado) {
		this.post = posteoAsociado;
		this.estadoPost = new EstadoPostBasico(this);
	}
	
	public VerificadorMuestra(PostMuestra posteoAsociado, EstadoDePost estadoPost) {
		this.post = posteoAsociado;
		this.setEstadoPost(estadoPost);
	}
	
	public void setEstadoPost(EstadoDePost estadoPost) {
		this.estadoPost = estadoPost;
	}
	
	public PostMuestra getPost() {
		return post;
	}
	
	public Opinion getResultadoActualPost() {		
		Opinion opinionConMayorVoto = Arrays.stream(Opinion.values()).max(Comparator.comparingInt(o-> post.getOpiniones().get(o).size())).get();		
		return opinionConMayorVoto;
	}
	
	public void actualizarEstadoDePost(Revision revision) {
		Opinion resultadoPostActual = this.post.getResultadoActual();
		agregarRevisionAlPost(revision);
		Opinion resultadoActual = this.getResultadoActualPost();
		if (this.post.sizeOpinion(resultadoPostActual) == this.post.sizeOpinion(resultadoActual)) {
			this.cambiarResultadoActualPost(Opinion.NO_DEFINIDO);
		}
		else {
		this.post.setResultadoActual(resultadoActual);
		this.verificarPost();
		}
	}

	private void agregarRevisionAlPost(Revision revision) {
		this.post.getOpiniones().get(revision.getOpinion()).add(revision);
	}
	
	public void colocarClavesEnHashmap() {
		Arrays.stream(Opinion.values()).forEach(o -> post.getOpiniones().put(o, new HashSet<Revision>()));
	}
	
	
	public void opinarEnEstadoBasico(Revision revision) {
		this.estadoPost = revision.getEstadoDelUsuarioActual().esExperto()? this.actualizarEstadoDeVerificadorPorExperto():estadoPost;
		this.actualizarEstadoDePost(revision);
		
	}
	
	private EstadoDePost actualizarEstadoDeVerificadorPorExperto() {
		EstadoDePost estado = new EstadoPostExperto(this);
		this.colocarClavesEnHashmap();
		return estado;
	}

	public void opinarEnEstadoExperto(Revision revision) {
		if(revision.getEstadoDelUsuarioActual().esExperto()){
		this.actualizarEstadoDePost(revision);}
		else {};		
	}
	
	public void opinar(Revision revision) {
		if(post.getEsPostVerificado()) {}
		else {
			estadoPost.opinar(revision);
			
		}
	}
	
	public void opinarUsuarioQuePosteo(Revision revision) {
		agregarRevisionAlPost(revision);
		cambiarResultadoActualPost(revision.getOpinion());
	}

	private void cambiarResultadoActualPost(Opinion opinion) {
		this.post.setResultadoActual(opinion);
	}

	public void verificarPost() {
		if(post.sizeOpinion(post.getResultadoActual()) >= 2) {
			this.post.verificarPost();
		}		
	}
}
