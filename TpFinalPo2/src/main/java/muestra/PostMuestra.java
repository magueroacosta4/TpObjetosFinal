package muestra;



import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import usuario.Usuario;

public class PostMuestra {
	
	private HashMap<Opinion, Set<Revision>> opiniones =
			new HashMap<Opinion, Set<Revision>>();
	private VerificadorMuestra verificador;
	private Ubicacion ubicacion;
	private LocalDate fechaDeCreacion;
	private Optional<Opinion> resultadoActual;
	private boolean esPostVerificado;
	private Set<Usuario> usuariosOpinados = new HashSet<Usuario>();
	
	public PostMuestra(Ubicacion u) {
		VerificadorMuestra ver = new VerificadorMuestra(this);
		this.setVerificador(ver);
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
	
	public void colocarClavesEnHashmap() {
		Arrays.stream(Opinion.values()).forEach(o -> this.getOpiniones().put(o, new HashSet<Revision>()));
	}


	public HashMap<Opinion, Set<Revision>> getOpiniones() {
		return opiniones;
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



	public void opinar(Revision revision) throws Exception {
		Usuario user = revision.getUser();
		if(!usuarioYaOpino(user)) {
			this.getVerificador().opinar(revision);
			usuariosOpinados.add(user);
		}else {
			throw new Exception("El usuario ya opino");
		}
	}

	private boolean usuarioYaOpino(Usuario user) {
		return usuariosOpinados.contains(user);
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
		int sizeResultadoActual = opiniones.get(opinion).size();
		return sizeResultadoActual;
	}

	public int sizeOpinionResultadoActual() {
		Optional<Opinion> op = this.resultadoActual;
		int sizeOp = op.isPresent()?sizeOpinion(op.get()):0;
		return sizeOp;		
	}

	public void setOpiniones(HashMap<Opinion, Set<Revision>> map) {
		this.opiniones = map;		
	}
		

}
