package muestra;

import usuario.EstadoUsuario;

public class EstadoPostExperto extends EstadoDePost {
	
	public EstadoPostExperto(PostMuestra post) {
		super(post);
	}

	@Override
	public void opinar(Revision revision, VerificadorMuestra verificador) {
		if(getEstadoDeUsuarioAlMomentoDeOpinar(revision).esExperto()){
		verificador.actualizarEstadoDePost(revision);}
		else {};		
	}
	
	private EstadoUsuario getEstadoDeUsuarioAlMomentoDeOpinar(Revision revision) {
		EstadoUsuario estadoUsuario = revision.getEstadoDelUsuarioActual();
		return estadoUsuario;
	}
	
	@Override
	public void verificarPost() {
		this.getPost().verificarPost();
	}

	@Override
	public boolean esVerificado() {
		return false;
	}	
	
}
