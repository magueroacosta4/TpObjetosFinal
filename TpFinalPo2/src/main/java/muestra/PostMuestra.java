package muestra;


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
		Opinion.getOpiniones().stream().forEach(o -> opiniones.put(o, new HashSet<Revision>()));
		this.opiniones.get(r.opinion()).add(r);
		this.resultadoActual = r.opinion();
	}

	public Opinion resultadoActual() {
		return resultadoActual;
	}
	
	
	
	
}
