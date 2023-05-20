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
	
	public Opinion getResultadoActualPost() {		
		Optional<Opinion> opinionConMayorVoto = Arrays.stream(Opinion.values()).max(Comparator.comparingInt(o-> this.sizeOpinion(o)));		
		return opinionConMayorVoto.get();
	}
	
	public void actualizarEstadoDePost(Revision revision) {
		Opinion op = revision.getOpinion();
		Optional<Opinion> resultadoPostActual = this.post.getResultadoActual();
		agregarRevisionAlPost(revision);
		Opinion resultadoActual = this.getResultadoActualPost();
		boolean verificacion1 = resultadoPostActual.isPresent()?
				this.sizeOpinion(resultadoPostActual.get()) == this.sizeOpinion(op):
					false;
		boolean verificacion2 = resultadoPostActual.isPresent()? resultadoPostActual.get()==resultadoActual:false;
		if (verificacion1 && !verificacion2) {
			this.cambiarResultadoActualPost(Optional.empty());
		}
		else {
		this.cambiarResultadoActualPost(Optional.of(resultadoActual));
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
		this.estadoPost = getEstadoDeUsuarioAlMomentoDeOpinar(revision).esExperto()? this.actualizarEstadoDeVerificadorPorExperto():estadoPost;
		this.actualizarEstadoDePost(revision);
		
	}

	private EstadoUsuario getEstadoDeUsuarioAlMomentoDeOpinar(Revision revision) {
		EstadoUsuario estadoUsuario = revision.getEstadoDelUsuarioActual();
		return estadoUsuario;
	}
	
	private EstadoDePost actualizarEstadoDeVerificadorPorExperto() {
		estadoPost = new EstadoPostExperto(this);
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

	public void verificarPost() {
		if(this.sizeOpinionResultadoActual() >= 2) {
			this.post.verificarPost();
		}		
	}
	
	public int sizeOpinion(Opinion opinion) {
		HashMap<Opinion, Set<Revision>> opiniones = this.post.getOpiniones();
		int sizeResultadoActual = opiniones.get(opinion).size();
		return sizeResultadoActual;
	}

	public int sizeOpinionResultadoActual() {
		Optional<Opinion> op = this.post.getResultadoActual();
		int sizeOp = op.isPresent()?sizeOpinion(op.get()):0;
		return sizeOp;		
	}
}
