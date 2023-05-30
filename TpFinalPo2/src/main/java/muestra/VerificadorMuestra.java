package muestra;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Optional;


public class VerificadorMuestra {
	
	private PostMuestra post;
	
	
	public VerificadorMuestra(PostMuestra posteoAsociado) {
		this.post = posteoAsociado;
	}
	
	public PostMuestra getPost() {
		return post;
	}
	

	
	void actualizarEstadoDePost(Revision revision) {
		Opinion op = revision.getOpinion();
		agregarRevisionAlPost(revision);
		Opinion opinionConMasVotos = this.opinionConMayorVoto();
		boolean tienenMismaCantVotos = laOpinionActualTieneLaMismaCantidadDeVotosQueLaOpinion(op);
		boolean sonLaMismaOpinion = laOpinionActualDelPostEsLaMismaQue(op);
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
		this.post.agregarRevision(revision);
	}
	

	private void cambiarResultadoActualPost(Optional<Opinion> opinion) {
		this.post.setResultadoActual(opinion);
	}
	
	public void verificarPost() {
		this.post.getEstadoDePost().verificarPost();
	}

}
