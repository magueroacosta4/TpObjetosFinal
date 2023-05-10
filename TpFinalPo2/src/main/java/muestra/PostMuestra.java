package muestra;

import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class PostMuestra {
	
	private HashMap<Opinion, Set<Revision>> opiniones =
			new HashMap<Opinion, Set<Revision>>();
	private VerificadorMuestra verificador;
	private Ubicacion ubicacion;
	private Date fechaDeCreacion;
	
	private Opinion resultadoActual;
	
	public PostMuestra(Revision r, Ubicacion u, VerificadorMuestra v) {
		this.ubicacion = u;
		this.fechaDeCreacion = new Date();
		this.verificador = v;
		Arrays.stream(Opinion.values()).forEach(o -> opiniones.put(o, new HashSet<Revision>()));
		this.opiniones.get(r.getOpinion()).add(r);
		this.resultadoActual = r.getOpinion();
	}

	public Opinion resultadoActual() {
		return resultadoActual;
	}
	
	
	
	
}
