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
	private Set<Usuario> usuariosQueYaOpinaron = new HashSet<Usuario>();
	private EstadoDePost estadoPost;
	private Usuario usuarioCreador;
	
	public PostMuestra(Ubicacion ubicacion, Revision revision, String foto) {
		VerificadorMuestra ver = new VerificadorMuestra(this);
		this.setVerificador(ver);
		this.setearTodo(ubicacion, revision);
		this.estadoPost = new EstadoPostBasico(this);
		this.usuarioCreador = revision.getUser();	
		this.setResultadoActual(Optional.of(revision.getOpinion()));	
	}

	public PostMuestra(Ubicacion u, VerificadorMuestra v, EstadoDePost estadoDePost, Revision revision, String foto) {
		this.setVerificador(v);		
		this.setearTodo(u, revision);
		this.estadoPost = estadoDePost;
		this.usuarioCreador = revision.getUser();	
		this.setResultadoActual(Optional.of(revision.getOpinion()));

	}
	
	public Usuario getUsuarioCreador() {
		return usuarioCreador;
	}
	
	public void setearTodo(Ubicacion u, Revision r) {
		this.setUbicacion(u);
		this.setFechaDeCreacion(LocalDate.now());;
		this.colocarClavesEnHashmap();
		this.resultadoActual = Optional.empty();
		usuariosQueYaOpinaron.add(r.getUser());
		agregarRevision(r);
	}
		
	
	public EstadoDePost getEstadoDePost() {
		return estadoPost;
	}
	
	public void setEstadoPost(EstadoDePost estadoPost) {
		this.estadoPost = estadoPost;
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
			this.getEstadoDePost().opinar(revision, verificador);
			usuariosQueYaOpinaron.add(user);
		}else {
			throw new Exception("El usuario ya opino");
		}
	}

	private boolean usuarioYaOpino(Usuario user) {
		return usuariosQueYaOpinaron.contains(user);
	}
	
	
	public void verificarPost() {
		if(this.sizeOpinionResultadoActual() >= 2)
		{EstadoPostVerificado estado = new EstadoPostVerificado(this);
		this.setEstadoPost(estado);
		}		
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



	public void agregarRevision(Revision revision) {
		opiniones.get(revision.getOpinion()).add(revision);	
	}
		

}
