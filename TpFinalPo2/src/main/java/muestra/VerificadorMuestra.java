package muestra;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Optional;
import usuario.EstadoUsuario;


public class VerificadorMuestra {
	
	private PostMuestra post;
	
	
	public VerificadorMuestra(PostMuestra posteoAsociado) {
		this.post = posteoAsociado;
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
	
	
	
	public void opinarEnEstadoBasico(Revision revision) {
		if(getEstadoDeUsuarioAlMomentoDeOpinar(revision).esExperto()){
			this.actualizarEstadoDeVerificadorPorExperto();
		}
		this.actualizarEstadoDePost(revision);
		
	}

	private EstadoUsuario getEstadoDeUsuarioAlMomentoDeOpinar(Revision revision) {
		EstadoUsuario estadoUsuario = revision.getEstadoDelUsuarioActual();
		return estadoUsuario;
	}
	
	private void actualizarEstadoDeVerificadorPorExperto() {
		EstadoPostExperto estado = new EstadoPostExperto(post);
		this.post.setEstadoPost(estado);
		this.post.setResultadoActual(Optional.empty());
		this.post.colocarClavesEnHashmap();
	}

	public void opinarEnEstadoExperto(Revision revision) {
		if(getEstadoDeUsuarioAlMomentoDeOpinar(revision).esExperto()){
		this.actualizarEstadoDePost(revision);}
		else {};		
	}
	
	
	public void opinionUsuarioQuePosteo(Revision revision) {
		agregarRevisionAlPost(revision);
		cambiarResultadoActualPost(Optional.of(revision.getOpinion()));
	}

	private void cambiarResultadoActualPost(Optional<Opinion> opinion) {
		this.post.setResultadoActual(opinion);
	}
	
	public void verificarPost() {
		this.post.getEstadoDePost().verificarPost();
	}

}
