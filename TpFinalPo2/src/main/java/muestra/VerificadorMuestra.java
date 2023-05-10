package muestra;

import java.util.Arrays;
import java.util.Comparator;

import java.util.HashSet;

public class VerificadorMuestra {
	
	private PostMuestra post;
	
	public VerificadorMuestra(PostMuestra posteoAsociado) {
		post = posteoAsociado;
	}
	
	public PostMuestra getPost() {
		return post;
	}
	
	public Opinion getResultadoActual() {		
		Opinion opinionConMayorVoto = Arrays.stream(Opinion.values()).max(Comparator.comparingInt(o-> post.getOpiniones().get(o).size())).get();		
		return opinionConMayorVoto;
	}
	

	public void colocarClavesEnHashmap() {
		Arrays.stream(Opinion.values()).forEach(o -> post.getOpiniones().put(o, new HashSet<Revision>()));
	}
	
	public void opinar(Revision revision) {
		this.post.getOpiniones().get(revision.getOpinion()).add(revision);
	}
}
