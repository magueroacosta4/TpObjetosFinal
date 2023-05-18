package muestra;



import java.time.LocalDate;
import java.util.HashMap;
import java.util.Optional;
import java.util.Set;

public class PostMuestra {
	
	private HashMap<Opinion, Set<Revision>> opiniones =
			new HashMap<Opinion, Set<Revision>>();
	private VerificadorMuestra verificador;
	private Ubicacion ubicacion;
	private LocalDate fechaDeCreacion;
	private Optional<Opinion> resultadoActual;
	private boolean esPostVerificado;
	
	public PostMuestra(Ubicacion u) {
		this.setVerificador();
		this.setearTodo(u);
	}

	public PostMuestra(Ubicacion u, VerificadorMuestra v) {
		this.setVerificador(v);		
		this.setearTodo(u);
	}
	
	public void setearTodo(Ubicacion u) {
		this.setUbicacion(u);
		this.setFechaDeCreacion(LocalDate.now());;
		this.colocarClavesEnHashmap();
		this.resultadoActual = Optional.empty();
		this.setEsPostVerificado(false);
	}
		
	
	public VerificadorMuestra getVerificador() {
		return verificador;
	}
	
	private void colocarClavesEnHashmap() {
		this.getVerificador().colocarClavesEnHashmap();
	}


	public HashMap<Opinion, Set<Revision>> getOpiniones() {
		return opiniones;
	}

	public void setOpiniones(HashMap<Opinion, Set<Revision>> opiniones) {
		this.opiniones = opiniones;
	}
	
	public void setVerificador() {
		VerificadorMuestra ver = new VerificadorMuestra(this);
		this.setVerificador(ver);
	}
	
	public void setVerificador(VerificadorMuestra v) {
		this.verificador = v;
	}
	
	

	public Ubicacion getUbicacion() {
		return ubicacion;
	}

	private void setUbicacion(Ubicacion ubicacion) {
		this.ubicacion = ubicacion;
	}

	public Optional<Opinion> getResultadoActual() {		
		return this.resultadoActual;
	}
	
	public void setResultadoActual(Optional<Opinion> resultadoActual) {
		this.resultadoActual = resultadoActual;
	}

	public LocalDate getFechaDeCreacion() {
		return fechaDeCreacion;
	}
	
	public void setFechaDeCreacion(LocalDate d) {
		this.fechaDeCreacion = d;
	}



	public void opinar(Revision revision) {
		this.getVerificador().opinar(revision);
	}

	public void setEsPostVerificado(boolean esPostVerificado) {
		this.esPostVerificado = esPostVerificado;
	}
	
	public Boolean getEsPostVerificado() {
		return this.esPostVerificado;
	}
	
	public void verificarPost() {
		this.setEsPostVerificado(true);;		
	}

	public int sizeOpinion(Opinion opinion) {
		int sizeResultadoActual = this.getOpiniones().get(opinion).size();
		return sizeResultadoActual;
	}

	public int sizeOpinionResultadoActual() {
		return sizeOpinion(resultadoActual.get());		
	}

	
	
}
