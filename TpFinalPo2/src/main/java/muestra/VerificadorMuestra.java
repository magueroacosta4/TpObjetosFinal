package muestra;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import usuario.EstadoUsuario;


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
	

	
	public void actualizarEstadoDePost(Revision revision) {
		Opinion op = revision.getOpinion();
		agregarRevisionAlPost(revision);
		Opinion opinionConMasVotos = this.opinionConMayorVoto();
		boolean tienenMismaCantVotos = laOpinionActualTieneLaMismaCantidadDeVotosQueLaOpinion(op);
		boolean sonLaMismaOpinion = laOpinionActualDelPostEsLaMismaQue(opinionConMasVotos);
		if (tienenMismaCantVotos && !sonLaMismaOpinion) {
			this.cambiarResultadoActualPost(Optional.empty());
		}
		else {
		this.cambiarResultadoActualPost(Optional.of(opinionConMasVotos));
		this.verificarPost();
		}
	}
	
	public Opinion opinionConMayorVoto() {		
		Optional<Opinion> opinionConMayorVoto = Arrays.stream(Opinion.values()).max(Comparator.comparingInt(o-> post.sizeOpinion(o)));		
		return opinionConMayorVoto.get();
	}

	private boolean laOpinionActualDelPostEsLaMismaQue(Opinion op) {
		Optional<Opinion> opActual = post.getResultadoActual();
		boolean resultado = false;
		if (opActual.isPresent()) {
			resultado = op.equals(opActual.get());
		}
		return resultado;
	}

	private boolean laOpinionActualTieneLaMismaCantidadDeVotosQueLaOpinion(Opinion op) {
		return 	this.post.sizeOpinionResultadoActual() == this.post.sizeOpinion(op);
	}

	private void agregarRevisionAlPost(Revision revision) {
		this.post.getOpiniones().get(revision.getOpinion()).add(revision);
	}
	
	public void colocarClavesEnHashmap() {
		Arrays.stream(Opinion.values()).forEach(o -> post.getOpiniones().put(o, new HashSet<Revision>()));
	}
	
	
	public void opinarEnEstadoBasico(Revision revision) {
		this.estadoPost = getEstadoDeUsuarioAlMomentoDeOpinar(revision).esExperto()? this.actualizarEstadoDeVerificadorPorExperto():estadoPost;
		this.actualizarEstadoDePost(revision);
		
	}

	private EstadoUsuario getEstadoDeUsuarioAlMomentoDeOpinar(Revision revision) {
		EstadoUsuario estadoUsuario = revision.getEstadoDelUsuarioActual();
		return estadoUsuario;
	}
	
	private EstadoDePost actualizarEstadoDeVerificadorPorExperto() {
		estadoPost = new EstadoPostExperto(this);
		this.post.setResultadoActual(Optional.empty());
		this.colocarClavesEnHashmap();
		return estadoPost;
	}

	public void opinarEnEstadoExperto(Revision revision) {
		if(getEstadoDeUsuarioAlMomentoDeOpinar(revision).esExperto()){
		this.actualizarEstadoDePost(revision);}
		else {};		
	}
	
	public void opinar(Revision revision) {
		if(post.getEsPostVerificado()) {}
		else {
			estadoPost.opinar(revision);
			
		}
	}
	
	public void opinionUsuarioQuePosteo(Revision revision) {
		agregarRevisionAlPost(revision);
		cambiarResultadoActualPost(Optional.of(revision.getOpinion()));
	}

	private void cambiarResultadoActualPost(Optional<Opinion> opinion) {
		this.post.setResultadoActual(opinion);
	}

	public void verificarPostSiEsPostDeExpertos() {
		if(this.post.sizeOpinionResultadoActual() >= 2) {
			this.post.verificarPost();
		}		
	}
	
	public void verificarPost() {
		estadoPost.verificarPost();
	}

}
