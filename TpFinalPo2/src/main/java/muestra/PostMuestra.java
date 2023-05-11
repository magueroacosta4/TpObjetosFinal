package muestra;



import java.util.Date;
import java.util.HashMap;
import java.util.Set;

public class PostMuestra {
	
	private HashMap<Opinion, Set<Revision>> opiniones =
			new HashMap<Opinion, Set<Revision>>();
	private VerificadorMuestra verificador;
	private Ubicacion ubicacion;
	private Date fechaDeCreacion;
	private Opinion resultadoActual;
	private boolean esPostVerificado;
	
	public PostMuestra(Revision r, Ubicacion u) {
		this.setUbicacion(u);;
		this.setFechaDeCreacion(r.getFechaDeCreacion());;
		this.setVerificador();
		this.colocarClavesEnHashmap();
		this.opinar(r);
		this.setEsPostVerificado(false);
		this.actualizarResultadoActual();
	}

	public PostMuestra(Revision r, Ubicacion u, VerificadorMuestra v) {
		this.setUbicacion(u);;
		this.setFechaDeCreacion(r.getFechaDeCreacion());;
		this.setVerificador(v);
		this.colocarClavesEnHashmap();
		this.opinar(r);
		this.setEsPostVerificado(false);
		this.actualizarResultadoActual();
	}
	
	public void actualizarResultadoActual() {
		Opinion resultadoActual = this.verificador.getResultadoActualPost();
		this.setResultadoActual(resultadoActual);
	}
	
	
	private VerificadorMuestra getVerificador() {
		return verificador;
	}
	
	private void colocarClavesEnHashmap() {
		this.getVerificador().colocarClavesEnHashmap();
	}


	public HashMap<Opinion, Set<Revision>> getOpiniones() {
		return opiniones;
	}


	private void setVerificador() {
		this.verificador = new VerificadorMuestra(this);
	}
	
	private void setVerificador(VerificadorMuestra v) {
		this.verificador = v;
	}

	public Ubicacion getUbicacion() {
		return ubicacion;
	}

	private void setUbicacion(Ubicacion ubicacion) {
		this.ubicacion = ubicacion;
	}

	public Opinion getResultadoActual() {		
		return this.resultadoActual;
	}
	
	public void setResultadoActual(Opinion resultadoActual) {
		this.resultadoActual = resultadoActual;
	}

	public Date getFechaDeCreacion() {
		return fechaDeCreacion;
	}
	
	public void setFechaDeCreacion(Date d) {
		this.fechaDeCreacion = d;
	}



	public void opinar(Revision revision) {
		this.getVerificador().opinar(revision);
		this.getResultadoActual();
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
	
	
	
	
}
