package muestra;

import java.util.Arrays;
import java.util.Comparator;

import java.util.HashSet;
import java.util.stream.Stream;

public class VerificadorMuestra {
	
	private PostMuestra post;
	
	
	public Opinion getResultadoActual() {		
		Opinion opinionConMayorVoto = this.streamOpiniones().max(Comparator.comparingInt(o-> post.getOpiniones().get(o).size())).get();		
		return opinionConMayorVoto;
	}
	
	private Stream<Opinion> streamOpiniones() {
		return Arrays.stream(Opinion.values());
	}
	
	public void colocarClavesEnHashmap() {
		streamOpiniones().forEach(o -> post.getOpiniones().put(o, new HashSet<Revision>()));
	}
	
	public void opinar(Revision revision) {
		this.post.getOpiniones().get(revision.getOpinion()).add(revision);
		this.getResultadoActual();
	}
}
