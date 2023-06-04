package muestra;

import java.util.Optional;

import usuario.EstadoUsuario;

public class EstadoPostBasico extends EstadoDePost {

	public EstadoPostBasico(PostMuestra post) {
		super(post);
	}

	@Override
	public void opinar(Revision revision, VerificadorMuestra verificador) {
		if(getEstadoDeUsuarioAlMomentoDeOpinar(revision).esExperto()){
			this.actualizarEstadoDeVerificadorPorExperto();
		}
		verificador.actualizarEstadoDePost(revision);
		
	}
	
	void actualizarEstadoDeVerificadorPorExperto() {
		EstadoPostExperto estado = new EstadoPostExperto(post);
		this.post.setEstadoPost(estado);
		this.post.setResultadoActual(Optional.empty());
		this.post.colocarClavesEnHashmap();
	}
	
	private EstadoUsuario getEstadoDeUsuarioAlMomentoDeOpinar(Revision revision) {
		EstadoUsuario estadoUsuario = revision.getEstadoDelUsuarioActual();
		return estadoUsuario;
	}

	@Override
	protected void verificarPost() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean esVerificado() {
		// TODO Auto-generated method stub
		return false;
	}



	
}
