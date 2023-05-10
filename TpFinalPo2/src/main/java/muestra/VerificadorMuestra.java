package muestra;

import java.util.Arrays;
import java.util.Comparator;

import java.util.HashSet;


public class VerificadorMuestra {
	
	private PostMuestra post;
	private EstadoDePost estadoPost;
	
	public VerificadorMuestra(PostMuestra posteoAsociado) {
		this.post = posteoAsociado;
		this.estadoPost = new EstadoPostBasico();
	}
	
	public VerificadorMuestra(PostMuestra posteoAsociado, EstadoDePost estadoPost) {
		this.post = posteoAsociado;
		this.estadoPost = estadoPost;
	}
	
	public PostMuestra getPost() {
		return post;
	}
	
	public Opinion getResultadoActual() {		
		Opinion opinionConMayorVoto = Arrays.stream(Opinion.values()).max(Comparator.comparingInt(o-> post.getOpiniones().get(o).size())).get();		
		return opinionConMayorVoto;
	}
	
	public void actualizarEstadoDePost() {
		this.post.getResultadoActual();
	}
	
	public void colocarClavesEnHashmap() {
		Arrays.stream(Opinion.values()).forEach(o -> post.getOpiniones().put(o, new HashSet<Revision>()));
	}
	
	
	public void opinarEnEstadoBasico(Revision revision) {
		this.estadoPost = revision.getEstadoDelUsuarioActual().esExperto()? new EstadoPostExperto():estadoPost;
		this.post.getOpiniones().get(revision.getOpinion()).add(revision);
	}
	
	public void opinarEnEstadoExperto(Revision revision) {
		if(revision.getEstadoDelUsuarioActual().esExperto()){
		this.post.getOpiniones().get(revision.getOpinion()).add(revision);}
		else {};		
	}
	
	public void opinar(Revision revision) {
		estadoPost.opinar(this, revision);
		this.actualizarEstadoDePost();
	}
}
